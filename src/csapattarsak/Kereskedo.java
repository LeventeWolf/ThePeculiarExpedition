package csapattarsak;

/**
 * Kereskedő csapattárs implementálása, aki egy speciális Csapattárs
 */
public class Kereskedo extends Csapattars {
    public Kereskedo() {
        nev = "Kereskedő";
    }

    @Override
    public String elony() {
        return ("mindent kicsivel olcsóbban vehetünk és drágábban adhatunk el");
    }
}
