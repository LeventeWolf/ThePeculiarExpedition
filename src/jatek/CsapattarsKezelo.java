package jatek;

import csapattarsak.Csapattars;
import exceptions.FelfedezoIsElhagytaAcsapatot;
import exceptions.NemLehetTobbCsapattarsadKivetel;
import exceptions.NincsElegAranyadKivetel;
import jatekosok.Felfedezo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static jatek.KonzolSzinek.*;

/**
 * A csapattársak kezelésére szolgál
 */
public class CsapattarsKezelo {
    private final ArrayList<Csapattars> csapattarsak = new ArrayList<>();

    /**
     * @return jelenleg hány csapattársunk van
     */
    public int getMeret() {
        return csapattarsak.size();
    }

    /**
     * @return az aktuális csapattársak
     */
    public ArrayList<Csapattars> getCsapattarsak() {
        return csapattarsak;
    }

    /**
     * Megadja hogy hány ilyen csapattársunk van
     * @param csapattarsNev A csapattás neve akinek a számát keressük
     * @return hány ilyen csapattársunk van
     */
    public int getCsapattarsSzam(String csapattarsNev) {
        int db = 0;
        for (Csapattars csapattars : csapattarsak) {
            if (csapattars.getName().equals(csapattarsNev)) {
                db++;
            }
        }

        return db;
    }

    /**
     * Hozzáad egy új csapattársat a csapathoz
     * @param csapattars Csapattárs akit hozzá szeretnénk adni a csapathoz
     * @throws NemLehetTobbCsapattarsadKivetel ha túl lépné a maximális csapattársak számát
     */
    public void hozzaad(Csapattars csapattars) throws NemLehetTobbCsapattarsadKivetel {
        if (getMeret() >= 3) throw new NemLehetTobbCsapattarsadKivetel();

        csapattarsak.add(csapattars);
        System.out.println("Egy " + csapattars.getName() + " sikeresen csatlakozott a csapathodhoz!:)\n");
    }

    /**
     * Kiírja hogy a milyen csapattársaink vannak
     */
    public void csapattarsakKiirasa() {
        System.out.println(BLUE_BOLD + "======== Csapattársak =======" + RESET);
        if (getMeret() == 0) {
            System.out.println("Jelenleg egy csapattársad sincsen :(\n");
            return;
        }

        for (Csapattars csapattars : getCsapattarsak()) {
            System.out.println(csapattars.getName() + " : " + csapattars.elony());
        }

        System.out.println();
    }

    /**
     * A legkésőbb csatlakozott csapattárs elhagyja a csapatot
     */
    public void csapattarsElhagyjaAcsapatot() throws FelfedezoIsElhagytaAcsapatot {
        if (getMeret() == 0) throw new FelfedezoIsElhagytaAcsapatot();

        System.out.println(RED_BOLD + csapattarsak.get(getMeret() - 1).getName() + " elhagyta a csapatod!" + RESET);
        csapattarsak.remove(getMeret() - 1);
    }
}
