package palya.mezok;

import static jatek.KonzolSzinek.*;

/**
 * Hajó mező megvalósítása, a felfedező innen indul <br>
 *      jeloles : 'H';
 *      nedves : nem;
 *      jarhato : nem;
 *      hatterSzin : sárga;
 */
public class Hajo extends Mezo {
    public Hajo() {
        jeloles = 'H';
        nedves = false;
        jarhato = true;
        hatterSzin = YELLOW_BACKGROUND;
    }

    @Override
    public String toString() {
        return hatterSzin + String.valueOf(jeloles) + RESET;
    }

}
