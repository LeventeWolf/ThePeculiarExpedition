package palya.mezok;

import static jatek.KonzolSzinek.*;

/**
 * Falu mező megvalósítása <br>
 *      jeloles : '⌂';
 *      nedves : nem;
 *      jarhato : nem;
 *      hatterSzin : világos fekete;
 */
public class Falu extends Mezo {
    public Falu() {
        jeloles = '⌂';
        nedves = false;
        jarhato = true;
        hatterSzin = BLACK_BACKGROUND_BRIGHT;
    }

    @Override
    public String toString() {
        return hatterSzin +  BLACK_BOLD + String.valueOf(jeloles) + RESET;
    }

}
