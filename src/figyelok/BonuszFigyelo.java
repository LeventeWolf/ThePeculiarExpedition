package figyelok;

import jatek.CsapattarsKezelo;
import jatekosok.Felfedezo;


/**
 * A csapattársakból adódó bónuszokat figyeli
 */
public class BonuszFigyelo {
    public static int vasarlasiBonusz = 0;
    public static int viszonyBonusz = 0;
    public static double whiskeyBonusz = 0;
    public static double kabitoszerBonusz = 0;

    private final CsapattarsKezelo csapattarsKezelo;
    private final Felfedezo felfedezo;

    /**
     * Konstruktor
     */
    public BonuszFigyelo(CsapattarsKezelo csapattarsKezelo, Felfedezo felfedezo) {
        this.csapattarsKezelo = csapattarsKezelo;
        this.felfedezo = felfedezo;
    }

    /**
     * Figyeli a csapattársak bónuszait amelyek: szamár, felderítő, kereskedő, katona, sámán, bölcs
     */
    public void bonuszokFigyelese() {
        szamarBonusz();
        felderitoBonusz();
        kereskedoBonusz();
        katonaBonusz();
        samanBonusz();
        bolcsBonusz();
    }

    /**
     * Sámán bónusza: A kábítószer +20% energia
     */
    private void samanBonusz() {
        int samanokSzama = csapattarsKezelo.getCsapattarsSzam("Sámán");

        if (samanokSzama > 0) {
            kabitoszerBonusz = 1.2 * samanokSzama;
        }
    }

    /**
     * Szamár bónusza: +2 inventory slot
     */
    private void szamarBonusz() {
        int szamarakSzama = csapattarsKezelo.getCsapattarsSzam("Szamár");

        if (szamarakSzama > 0) {
            felfedezo.setMegengedettSlotokSzama(felfedezo.getMegengedettSlotokSzama() + szamarakSzama * 2);
        } else {
            felfedezo.setMegengedettSlotokSzama(7);
        }
    }

    /**
     * Felderítő bónusza: +1 látótávolság
     */
    private void felderitoBonusz() {
        int felderitokSzama = csapattarsKezelo.getCsapattarsSzam("Felderítő");

        if (felderitokSzama > 0) {
            felfedezo.setLatotav(felderitokSzama + 1);
        }
    }

    /**
     * Kereskedő bónusza: Mindent kicsivel olcsóbban és drágábban adhatunk el
     */
    private void kereskedoBonusz() {
        int kereskedokSzama = csapattarsKezelo.getCsapattarsSzam("Kereskedő");

        if (kereskedokSzama > 0) {
            vasarlasiBonusz = 3 * kereskedokSzama;
        }
    }

    /**
     * Katona bónusza: A whiskey +20% energiát ad
     */
    private void katonaBonusz() {
        int katonakSzama = csapattarsKezelo.getCsapattarsSzam("Katona");

        if (katonakSzama > 0) {
            whiskeyBonusz = 1.2 * katonakSzama;
        }
    }

    /**
     * Bölcs bónusza: +3 viszony
     */
    private void bolcsBonusz() {
        int bolcsekSzama = csapattarsKezelo.getCsapattarsSzam("Bölcs");

        if (bolcsekSzama > 0) {
            viszonyBonusz = bolcsekSzama * 3;
        }
    }
}
