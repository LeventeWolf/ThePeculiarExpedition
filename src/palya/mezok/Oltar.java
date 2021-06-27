package palya.mezok;

import static jatek.KonzolSzinek.*;

/**
 * Oltár mező megvalósítása <br>
 *      jeloles = '∩';
 *      nedves : nem;
 *      jarhato : igen;
 *      hatterSzin = világos fekete;
 */
public class Oltar extends Mezo {

    public Oltar() {
        jeloles = '∩';
        nedves = false;
        jarhato = true;
        hatterSzin = BLACK_BACKGROUND_BRIGHT;

    }

    @Override
    public String toString() {
        if (nedves) hatterSzin = CYAN_BACKGROUND_BRIGHT;
        return hatterSzin + jeloles + RESET;
    }

}
