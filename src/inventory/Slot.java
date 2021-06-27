package inventory;

import exceptions.NemMegfeleloTipusKivetel;
import exceptions.NincsElegHelyKivetel;
import vasarlas.targyak.Targy;

import java.util.ArrayList;

/**
 * Az inventoryban található egyes Slotok megvalósítása
 */
public class Slot {
    private Targy targy;
    private int meret;

    /**
     * Konstruktor
     */
    public Slot(Targy targy) {
        this.targy = targy;
        meret = 0;
    }

    /**
     * @return tárgy ami a sloton található
     */
    public Targy getTargy() {
        return targy;
    }

    /**
     * @return a sloton hány tárgy van
     */
    public int getMeret() {
        return meret;
    }

    /**
     * Hozzáad egy új tárgyat a slothoz (növeli a méretét)
     * @param targy amit hozzá szeretnénk adni a slothoz
     * @throws NincsElegHelyKivetel ha már nincs elég hely a sloton
     * @throws NemMegfeleloTipusKivetel ha nem egyezik a slot típúsa a hozzáadni kívánt tárgy típusával
     */
    public void hozzaad(Targy targy) throws NincsElegHelyKivetel, NemMegfeleloTipusKivetel {
        if (!this.targy.getNev().equals(targy.getNev())) {
            throw new NemMegfeleloTipusKivetel();
        } else if (getMeret() == 7) {
            throw new NincsElegHelyKivetel();
        } else {
            meret++;
        }
    }

    /**
     * Kiveszi a tárgyat a slotból (csökkenti a slot méretét)
     */
    public void kivesz() {
        meret--;
    }

    /**
     * @return true ha a slot mérete nem haladja meg a maximális méretet, különben false
     */
    public boolean ferMegBele() {
        return meret < 7;
    }
}
