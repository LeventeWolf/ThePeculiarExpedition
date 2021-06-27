package palya.mezok;

import static jatek.KonzolSzinek.*;

/**
 * Tó mező megvalósítása <br>
 *      jeloles : '~';
 *      nedves : nem;
 *      jarhato : nem;
 *      hatterSzin : kék;
 */
public class To extends Mezo {
    public To() {
        jeloles = '~';
        nedves = false;
        jarhato = false;
        hatterSzin = BLUE_BACKGROUND;
    }

    @Override
    public String toString() {
        return hatterSzin + jeloles + RESET;
    }

}
