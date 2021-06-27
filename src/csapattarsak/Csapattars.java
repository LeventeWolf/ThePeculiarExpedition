package csapattarsak;

/**
 * Ősosztály amely a speciális csapattársak megvalósításra szolgál
 */
public abstract class Csapattars {
    protected String nev;

    /**
     * @return csapattárs előnye szövegként
     */
    public abstract String elony();

    /**
     * @return csapattárs neve szövegként
     */
    public String getName() {
        return nev;
    }
}
