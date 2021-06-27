package csapattarsak;

/**
 * Bölcs csapattárs implementálása, aki egy speciális Csapattárs
 */
public class Bolcs extends Csapattars {
    public Bolcs() {
        nev = "Bölcs";
    }

    @Override
    public String elony() {
        return "+3 alapviszony új térképen";
    }
}
