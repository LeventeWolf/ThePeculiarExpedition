package palya.mezok;

import static jatek.KonzolSzinek.*;

/**
 * Aranypiramis mező megvalósítása <br>
 *      jeloles = 'X';
 *      nedves : nem;
 *      jarhato : igen;
 *      hatterSzin = világos sárga;
 */
public class Aranypiramis extends Mezo {
    public Aranypiramis() {
        nedves = false;
        jarhato = true;
        hatterSzin = YELLOW_BACKGROUND_BRIGHT;
        jeloles = 'X';

    }

    @Override
    public String toString() {
        return hatterSzin + RED_BOLD + String.valueOf(jeloles) + RESET;
    }



}
