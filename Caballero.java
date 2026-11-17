
public class Caballero extends Soldado {

    private boolean montando = false;
    private String arma = "espada";

    public Caballero(String n, String r, int f, int c) {
        super(n, r, "Caballero", 13, 7,
                10 + (int) (Math.random() * 3), f, c);
    }

    public String montar() {
        if (montando) {
            return "Ya está montando.";
        }

        montando = true;
        arma = "lanza";

        return "Monta y prepara la lanza.";
    }

    public String desmontar() {
        if (!montando) {
            return "Ya está desmontado.";
        }

        montando = false;
        arma = "espada";
        defensa++;

        return "Desmonta y se defiende.";
    }

    public int embestir() {
        return ataque * (montando ? 3 : 2);
    }

    @Override
    public String habilidad() {
        return "Embestida con " + arma + ": " + embestir();
    }
}
