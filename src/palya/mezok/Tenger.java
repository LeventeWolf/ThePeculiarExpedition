package palya.mezok;

import static jatek.KonzolSzinek.*;

/**
 * Tenger mező megvalósítása <br>
 *      jeloles : '≈';
 *      nedves : nem;
 *      jarhato : nem;
 *      hatterSzin : kék;
 */
public class Tenger extends Mezo {
    public Tenger() {
        jeloles = '≈';
        nedves = false;
        jarhato = false;
        hatterSzin = BLUE_BACKGROUND;
    }

    @Override
    public String toString() {
        return hatterSzin + jeloles + RESET;
    }

}

