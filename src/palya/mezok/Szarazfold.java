package palya.mezok;

import static jatek.KonzolSzinek.*;

/**
 * Szárazföld mező megvalósítása <br>
 *      jeloles = 'O';
 *      nedves = nem;
 *      jarhato = igen;
 *      hatterSzin = világos zöld;
 */
public class Szarazfold extends Mezo {

    public Szarazfold() {
        jeloles = 'O';
        nedves = false;
        jarhato = true;
        hatterSzin = GREEN_BACKGROUND_BRIGHT;
    }

    @Override
    public String toString() {
        if (nedves) hatterSzin = CYAN_BACKGROUND_BRIGHT;
        return hatterSzin + " " + RESET;
    }


}
