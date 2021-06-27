package vasarlas.targyak;

/**
 * Tárgy megvalósítása egy tárgynak lehet: <br>
 *     neve,
 *     ára (arany)
 *
 *
 */
public abstract class Targy {
    protected String nev;
    protected int ar;

    /**
     * @return az adott tárgy neve
     */
    public String getNev() {
        return nev;
    }

    /**
     * @return az adott tárgy ára
     */
    public int getAr() {
        return ar;
    }
}
