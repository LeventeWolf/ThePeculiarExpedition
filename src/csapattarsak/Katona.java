package csapattarsak;

/**
 * Katona csapattárs implementálása, aki egy speciális Csapattárs
 */
public class Katona extends Csapattars {

    public Katona() {
        nev = "Katona";
    }

    @Override
    public String elony() {
        return ("Whiskey +20% energia");
    }
}
