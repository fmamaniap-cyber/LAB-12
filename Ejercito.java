import java.util.*;
public class Ejercito {
    private String reino;
    private ArrayList<Soldado> soldados=new ArrayList<>();
    public Ejercito(String r) {
        reino=r;
    }
    public String getReino() {
        return reino;
    }
    public ArrayList<Soldado> getSoldados() {
        return soldados;
    }
    public void agregar(Soldado s) {
        soldados.add(s);
    }
    public int cantidad() {
        return soldados.size();
    }
    public int vidaTotal() {
        int x=0;
        for(Soldado s:soldados)x+=s.getVidaActual();
        return x;
    }
    public double promedio() {
        return soldados.isEmpty()?0:(double)vidaTotal()/soldados.size();
    }
    public Soldado mayorVida() {
        return soldados.stream().max(Comparator.comparingInt(Soldado::getVidaActual)).orElse(null);
    }
    public Soldado buscar(String n) {
        for(Soldado s:soldados)if(s.getNombre().equalsIgnoreCase(n))return s;
        return null;
    }
    public void ordenar() {
        for(int i=0;i<soldados.size()-1;i++)for(int j=0;j<soldados.size()-1-i;j++)if(soldados.get(j).getVidaActual()<soldados.get(j+1).getVidaActual())Collections.swap(soldados,j,j+1);
    }
    public void mostrar() {
        System.out.println("\n"+reino+" total="+cantidad()+" vida="+vidaTotal()+" promedio="+String.format("%.2f",promedio()));
        for(Soldado s:soldados)System.out.println(s);
    }
}
