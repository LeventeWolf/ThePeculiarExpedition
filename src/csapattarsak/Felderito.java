package csapattarsak;

/**
 * Felderítő csapattárs implementálása, aki egy speciális Csapattárs
 */
public class Felderito extends Csapattars {
    public Felderito() {
        nev = "Felderítő";
    }

    @Override
    public String elony() {
        return ("Látótávolság +1");
    }
}
