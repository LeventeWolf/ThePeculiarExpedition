package palya.mezok;

import static jatek.KonzolSzinek.*;

/**
 * Dzsungel mező megvalósítása <br>
 *      jeloles : '⌂';
 *      nedves : nem;
 *      jarhato : nem;
 *      hatterSzin : világos fekete;
 */
public class Dzsungel extends Mezo {

    public Dzsungel() {
        jeloles = '#';
        nedves = false;
        jarhato = true;
        hatterSzin = GREEN_BACKGROUND;
    }

    @Override
    public String toString() {
        if (nedves) hatterSzin = CYAN_BACKGROUND_BRIGHT;
        return hatterSzin + jeloles + RESET;
    }


}
