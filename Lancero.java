
public class Lancero extends Soldado {

    private double longitudLanza;

    public Lancero(String n, String r, int f, int c) {
        super(n, r, "Lancero", 5, 10,
                5 + (int) (Math.random() * 4), f, c);

        longitudLanza = 1.5 + Math.random();
    }

    public String schiltrom() {
        defensa++;

        return "Schiltrom: defensa +1.";
    }

    @Override
    public String habilidad() {
        return schiltrom()
                + " Longitud de lanza: "
                + String.format("%.2f", longitudLanza)
                + " metros.";
    }
}
