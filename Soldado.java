public abstract class Soldado {
    private static int totalCreados=0;
    protected String nombre,reino,tipo;
    protected int ataque,defensa,vidaMaxima,vidaActual,fila,columna;
    protected boolean vivo=true;
    public Soldado(String n,String r,String t,int a,int d,int v,int f,int c) {
        nombre=n;
        reino=r;
        tipo=t;
        ataque=a;
        defensa=d;
        vidaMaxima=v;
        vidaActual=v;
        fila=f;
        columna=c;
        totalCreados++;
    }
    public abstract String habilidad();
    public int atacar() {
        return ataque;
    }
    public int defender() {
        return defensa;
    }
    public void recibirDanio(int d) {
        vidaActual=Math.max(0,vidaActual-d);
        if(vidaActual==0)vivo=false;
    }
    public void recuperar(int p) {
        if(vivo)vidaActual=Math.min(vidaMaxima,vidaActual+p);
    }
    public void morir() {
        vivo=false;
        vidaActual=0;
    }
    public void moverA(int f,int c) {
        fila=f;
        columna=c;
    }
    public boolean estaVivo() {
        return vivo;
    }
    public String getNombre() {
        return nombre;
    }
    public String getReino() {
        return reino;
    }
    public String getTipo() {
        return tipo;
    }
    public int getVidaActual() {
        return vidaActual;
    }
    public int getFila() {
        return fila;
    }
    public int getColumna() {
        return columna;
    }
    public int poder() {
        return vidaActual*2+ataque+defensa;
    }
    public static int getTotalCreados() {
        return totalCreados;
    }
    public String toString() {
        return nombre+" ["+tipo+"] "+reino+" vida "+vidaActual+"/"+vidaMaxima+" ataque "+ataque+" defensa "+defensa+" ("+fila+","+columna+")";
    }
}
