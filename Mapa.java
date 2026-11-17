public class Mapa {
    public static final int TAM=10;
    private String territorio;
    private Soldado[][] tablero=new Soldado[TAM][TAM];
    public Mapa(String t) {
        territorio=t;
    }
    public String getTerritorio() {
        return territorio;
    }
    public boolean dentro(int f,int c) {
        return f>=0&&f<TAM&&c>=0&&c<TAM;
    }
    public Soldado en(int f,int c) {
        return dentro(f,c)?tablero[f][c]:null;
    }
    public boolean libre(int f,int c) {
        return dentro(f,c)&&tablero[f][c]==null;
    }
    public void colocar(Soldado s) {
        tablero[s.getFila()][s.getColumna()]=s;
    }
    public void retirar(Soldado s) {
        if(dentro(s.getFila(),s.getColumna())&&tablero[s.getFila()][s.getColumna()]==s)tablero[s.getFila()][s.getColumna()]=null;
    }
    public void mover(Soldado s,int f,int c) {
        retirar(s);
        s.moverA(f,c);
        colocar(s);
    }
    public void dibujar() {
        System.out.println("\nMAPA - "+territorio);
        for(int f=0;f<TAM;f++) {
            for(int c=0;c<TAM;c++) {
                Soldado s=tablero[f][c];
                String x=".";
                if(s!=null) {
                    String t=s.getTipo();
                    x=(t.startsWith("Espadachin")?"E":t.startsWith("Arquero")?"A":t.startsWith("Caballero")?"C":t.startsWith("Lancero")?"L":"*")+(s.getReino().equals("Inglaterra")?"1":"2");
                }
                System.out.printf("|%3s",x);
            }
            System.out.println("|");
        }
    }
}
