package jatekosok;


/**
 * Játékos ősosztály, amely a Fefledező és a Riválisok tulajdonságait fogja össze
 */
public abstract class Jatekos implements Comparable<Jatekos> {
    protected int hirnev;
    protected String nev;

    /** GETTERS & SETTERS */

    public int getHirnev() {
        return hirnev;
    }

    public void setHirnev(int hirnev) {
        this.hirnev = hirnev;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    /**
     * Játékosokkal teli Lista rendezésére szolgál hírnév alapján <br>
     *     Akinek nagyobb a hírneve az kerül előrébb a ranglistában
     * @param masikJatekos
     * @return
     */
    public int compareTo(Jatekos masikJatekos) {
        return  masikJatekos.hirnev - this.hirnev;
    }
}
