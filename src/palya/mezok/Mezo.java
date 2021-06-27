package palya.mezok;

/**
 * Egy pályán található Mező megvalósítására szolgál
 */
public abstract class Mezo {
    protected String hatterSzin;
    public char jeloles;
    protected boolean jarhato;
    protected boolean nedves;

    /**
     * @return true ha az adott mező járható, különben false
     */
    public boolean isJarhato(){
        return jarhato;
    }


    /** GETTERS & SETTERS */

    public void setNedves(boolean nedves) {
        this.nedves = nedves;
    }

    public String getHatterSzin(){
        return hatterSzin;
    }

}
