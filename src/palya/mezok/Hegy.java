package palya.mezok;

import static jatek.KonzolSzinek.*;

/**
 * Tenger mező megvalósítása <br>
 *      jeloles : '^';
 *      nedves : nem;
 *      jarhato : nem;
 *      hatterSzin : világos fekete;
 */
public class Hegy extends Mezo{
    public Hegy() {
        jeloles = '^';
        nedves = false;
        jarhato = false;
        hatterSzin = BLACK_BACKGROUND_BRIGHT;

    }

    @Override
    public String toString() {
        return hatterSzin + String.valueOf(jeloles) + RESET;
    }

}
