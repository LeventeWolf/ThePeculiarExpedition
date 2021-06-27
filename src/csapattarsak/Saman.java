package csapattarsak;

/**
 * Sámán csapattárs implementálása, aki egy speciális Csapattárs
 */
public class Saman extends Csapattars {
    public Saman() {
        nev = "Sámán";
    }

    @Override
    public String elony() {
        return "a Kábítószer +20% energia";
    }
}
