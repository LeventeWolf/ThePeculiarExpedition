package csapattarsak;

/**
 * Szamár csapattárs implementálása, aki egy speciális Csapattárs
 */
public class Szamar extends Csapattars {
    public Szamar() {
        nev = "Szamár";
    }

    @Override
    public String elony() {
        return "+2 Inventory slot";
    }
}
