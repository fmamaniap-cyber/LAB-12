
public class Espadachin extends Soldado {

    private double longitudEspada;

    public Espadachin(String n, String r, int f, int c) {
        super(n, r, "Espadachin", 10, 8,
                8 + (int) (Math.random() * 3), f, c);

        longitudEspada = 0.8 + Math.random() * 0.6;
    }

    public String muroDeEscudos() {
        return "Muro de escudos activado.";
    }

    @Override
    public int defender() {
        return defensa + 2;
    }

    @Override
    public String habilidad() {
        return muroDeEscudos()
                + " Longitud de espada: "
                + String.format("%.2f", longitudEspada)
                + " metros.";
    }
}
