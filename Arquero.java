public class Arquero extends Soldado {
    private int flechas=5;
    public Arquero(String n,String r,int f,int c) {
        super(n,r,"Arquero",7,3,3+(int)(Math.random()*3),f,c);
    }
    public boolean disparar() {
        if(flechas==0)return false;
        flechas--;
        return true;
    }
    public String habilidad() {
        return disparar()?"Disparo realizado. Flechas restantes: "+flechas:"Sin flechas.";
    }
}
