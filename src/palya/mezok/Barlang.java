package palya.mezok;

import static jatek.KonzolSzinek.*;

/**
 * Barlang mező megvalósítása <br>
 *      jeloles = 'Ѧ';
 *      nedves : nem;
 *      jarhato : igen;
 *      hatterSzin = világos fekete;
 */
public class Barlang extends Mezo {

    public Barlang() {
        jeloles = 'Ѧ';
        nedves = false;
        jarhato = true;
        hatterSzin = BLACK_BACKGROUND_BRIGHT;
    }

    @Override
    public String toString() {
        return BLACK_BACKGROUND_BRIGHT + BLACK_BOLD + String.valueOf(jeloles) + RESET;
    }

}
