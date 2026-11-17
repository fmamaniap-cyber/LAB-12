import java.util.*;
public class JuegoLaboratorio12 {
    static Scanner sc=new Scanner(System.in);
    static Random rd=new Random();
    static String[] reinos= {
        "Inglaterra","Francia","Sacro Imperio Romano Germanico","Castilla-Aragon","Moros"
    }
    ,terrs= {
        "bosque","campo abierto","montaña","desierto","playa"
    }
    ;
    static Ejercito a,b;
    static Mapa mapa;
    public static void main(String[]z) {
        crear();
        int op;
        do {
            System.out.println("\nLABORATORIO 12\n1 Mapa\n2 Datos y estadisticas\n3 Ranking\n4 Batalla automatica\n5 Jugar por turnos\n6 Buscar soldado\n7 Evolucionar especial\n0 Salir");
            op=entero("Opcion: ");
            switch(op) {
                case 1:mapa.dibujar();
                break;
                case 2:a.mostrar();
                b.mostrar();
                System.out.println("Soldados creados: "+Soldado.getTotalCreados());
                break;
                case 3:ranking();
                break;
                case 4:auto();
                break;
                case 5:jugar();
                break;
                case 6:buscar();
                break;
                case 7:evolucionar();
            }
        }
        while(op!=0);
    }
    static void crear() {
        String r1=reinos[rd.nextInt(5)],r2;
        do {
            r2=reinos[rd.nextInt(5)];
        }
        while(r1.equals(r2));
        a=new Ejercito(r1);
        b=new Ejercito(r2);
        mapa=new Mapa(terrs[rd.nextInt(5)]);
        generar(a,1);
        generar(b,2);
        bonus(a);
        bonus(b);
        mapa.dibujar();
        System.out.println("Reinos: "+r1+" y "+r2+"; territorio: "+mapa.getTerritorio());
    }
    static void generar(Ejercito e,int num) {
        int n=1+rd.nextInt(10);
        for(int i=0;i<n;i++) {
            int f,c;
            do {
                f=rd.nextInt(10);
                c=rd.nextInt(10);
            }
            while(!mapa.libre(f,c));
            Soldado s;
            boolean esp=(i==n-1&&rd.nextInt(4)==0);
            if(esp)s=especial(e.getReino(),i,num,f,c);
            else switch(rd.nextInt(4)) {
                case 0:s=new Espadachin("Espadachin"+i+"X"+num,e.getReino(),f,c);
                break;
                case 1:s=new Arquero("Arquero"+i+"X"+num,e.getReino(),f,c);
                break;
                case 2:s=new Caballero("Caballero"+i+"X"+num,e.getReino(),f,c);
                break;
                default:s=new Lancero("Lancero"+i+"X"+num,e.getReino(),f,c);
            }
            e.agregar(s);
            mapa.colocar(s);
        }
    }
    static Soldado especial(String r,int i,int n,int f,int c) {
        String t,arma;
        int v;
        if(r.equals("Inglaterra")) {
            t="Espadachin Real";
            arma="cuchillos";
            v=12;
        }
        else if(r.equals("Francia")) {
            t="Caballero Franco";
            arma="lanzas";
            v=15;
        }
        else if(r.startsWith("Sacro")) {
            t="Espadachin Teutonico";
            arma="jabalina";
            v=13;
        }
        else if(r.equals("Castilla-Aragon")) {
            t="Espadachin Conquistador";
            arma="hachas";
            v=14;
        }
        else {
            t="Caballero Moro";
            arma="flechas";
            v=13;
        }
        return new UnidadEspecial(t+i+"X"+n,r,t,v,arma,f,c);
    }
    static void bonus(Ejercito e) {
        String r=e.getReino(),t=mapa.getTerritorio();
        boolean ok=(r.equals("Inglaterra")&&t.equals("bosque"))||(r.equals("Francia")&&t.equals("campo abierto"))||(r.equals("Castilla-Aragon")&&t.equals("montaña"))||(r.equals("Moros")&&t.equals("desierto"))||(r.startsWith("Sacro")&&(t.equals("bosque")||t.equals("playa")||t.equals("campo abierto")));
        if(ok)for(Soldado s:e.getSoldados())s.recuperar(1);
    }
    static void ranking() {
        for(Ejercito e:new Ejercito[] {
        a,b
        }
        ) {
            e.ordenar();
            System.out.println("\nRanking "+e.getReino());
            for(Soldado s:e.getSoldados())System.out.println(s.getNombre()+" vida "+s.getVidaActual()+" poder "+s.poder());
        }
    }
    static void buscar() {
        String n=texto("Nombre: ");
        Soldado s=a.buscar(n);
        if(s==null)s=b.buscar(n);
        System.out.println(s==null?"No encontrado":s);
    }
    static void evolucionar() {
        String n=texto("Nombre unidad especial: ");
        for(Ejercito e:new Ejercito[] {
        a,b
        }
        ) {
            Soldado s=e.buscar(n);
            if(s instanceof Evolucionable) {
                ((Evolucionable)s).evolucionar();
                System.out.println("Evolucion nivel "+((Evolucionable)s).getNivelEvolucion());
                return;
            }
        }
        System.out.println("No es unidad especial.");
    }
    static void auto() {
        int x=a.vidaTotal(),y=b.vidaTotal();
        double p=100.0*x/(x+y);
        System.out.printf("Probabilidades por vida total: %s %.1f%%, %s %.1f%%%n",a.getReino(),p,b.getReino(),100-p);
        Ejercito gana=rd.nextDouble()*100<p?a:b,pierde=gana==a?b:a;
        for(Soldado s:pierde.getSoldados()) {
            mapa.retirar(s);
            s.morir();
        }
        pierde.getSoldados().clear();
        System.out.println("Gana "+gana.getReino());
        mapa.dibujar();
    }
    static void jugar() {
        int turno=0;
        while(a.cantidad()>0&&b.cantidad()>0) {
            Ejercito e=turno%2==0?a:b;
            mapa.dibujar();
            System.out.println("Turno: "+e.getReino());
            int f=entero("Fila: "),c=entero("Columna: ");
            Soldado s=mapa.en(f,c);
            if(s==null||!s.getReino().equals(e.getReino())) {
                System.out.println("No hay soldado propio.");
                continue;
            }
            int d=entero("1 arriba 2 abajo 3 izquierda 4 derecha: "),nf=f,nc=c;
            if(d==1)nf--;
            else if(d==2)nf++;
            else if(d==3)nc--;
            else if(d==4)nc++;
            else continue;
            if(!mapa.dentro(nf,nc)) {
                System.out.println("Fuera del tablero.");
                continue;
            }
            Soldado rival=mapa.en(nf,nc);
            if(rival!=null&&rival.getReino().equals(e.getReino())) {
                System.out.println("Casilla ocupada por aliado.");
                continue;
            }
            if(rival==null)mapa.mover(s,nf,nc);
            else duelo(s,rival,e);
            a.getSoldados().removeIf(q->!q.estaVivo());
            b.getSoldados().removeIf(q->!q.estaVivo());
            turno++;
        }
        System.out.println("Ganador: "+(a.cantidad()>0?a.getReino():b.getReino()));
    }
    static void duelo(Soldado s,Soldado r,Ejercito e) {
        double p=100.0*s.getVidaActual()/(s.getVidaActual()+r.getVidaActual());
        System.out.printf("Probabilidad %s %.1f%% / rival %.1f%%%n",s.getNombre(),p,100-p);
        Soldado g=rd.nextDouble()*100<p?s:r,per=g==s?r:s;
        System.out.println("Gana "+g.getNombre()+" por sorteo proporcional a vida.");
        mapa.retirar(s);
        mapa.retirar(r);
        per.morir();
        g.recuperar(1);
        g.moverA(r.getFila(),r.getColumna());
        mapa.colocar(g);
        (e==a?b:a).getSoldados().remove(per);
    }
    static int entero(String m) {
        while(true) {
            try {
                System.out.print(m);
                return Integer.parseInt(sc.nextLine());
            }
            catch(Exception ex) {
                System.out.println("Ingrese entero.");
            }
        }
    }
    static String texto(String m) {
        System.out.print(m);
        return sc.nextLine();
    }
}
