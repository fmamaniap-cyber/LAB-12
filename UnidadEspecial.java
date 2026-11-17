public class UnidadEspecial extends Soldado implements Lanzable,Evolucionable {
    private String arma;
    private int lanzamientos=1,nivel=1;
    private boolean tortuga=false;
    public UnidadEspecial(String n,String r,String t,int v,String a,int f,int c) {
        super(n,r,t,12,9,v,f,c);
        arma=a;
    }
    public String lanzarArma() {
        if(lanzamientos<=0)return "No quedan "+arma+".";
        lanzamientos--;
        return "Lanza "+arma+". Restan "+lanzamientos;
    }
    public int getCantidadLanzamientos() {
        return lanzamientos;
    }
    public void evolucionar() {
        if(nivel<4) {
            nivel++;
            lanzamientos+=nivel;
            vidaMaxima+=2;
            vidaActual+=2;
            ataque++;
        }
    }
    public int getNivelEvolucion() {
        return nivel;
    }
    public void activarModoTortuga() {
        if(tipo.equals("Espadachin Teutonico"))tortuga=true;
    }
    public int defender() {
        return defensa+(tortuga?5:0);
    }
    public String habilidad() {
        return tipo+" nivel "+nivel+"; arma: "+arma;
    }
}
