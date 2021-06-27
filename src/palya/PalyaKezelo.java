package palya;

import jatekosok.Felfedezo;
import palya.mezok.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static jatek.KonzolSzinek.*;

/**
 * A pályán történő interakciókat kezeli le
 */
public class PalyaKezelo {
    public Palya palya;
    private final Felfedezo felfedezo;
    private ArrayList<int[]> eddigLatottTeruletek;

    /** GETTER & SETTERS */

    public Palya getPalya() {
        return palya;
    }

    /**
     * Konstrukor
     * @param felfedezo
     */
    public PalyaKezelo(Felfedezo felfedezo) {
        this.felfedezo = felfedezo;
    }

    /**
     * Az adott pálya frissítése a küldetésszám alapján
     * @param kuldetesSzam
     * @throws FileNotFoundException
     */
    public void palyatBetolt(int kuldetesSzam) throws FileNotFoundException {
        this.palya = new Palya(kuldetesSzam);
        eddigLatottTeruletek = new ArrayList<>();
    }

    /**
     * Pálya betöltése
     *
     * @param kuldetesSzam az adott küldetés amely alapján a megfelelő pálya betöltődik
     * @throws FileNotFoundException
     */
    public void initPalya(int kuldetesSzam) throws FileNotFoundException {
        palyatBetolt(kuldetesSzam);
        felfedezotLehelyez(felfedezo);
    }

    /**
     * A pálya kiírása a standand inputra színekkel megvalósítva <br>
     * A mezők felfedező a látótávolságának nagyságától vannak megjelenítve <br>
     *     Ha nem látja az adott mezőt Fekete terület kerül kiíratásra
     */
    public void palyatKiir() {
        System.out.println(GREEN_BOLD + "========= Térkép ==========" + RESET);
        for (int y = 0; y < palya.getMagassag() - 1; y++) {
            for (int x = 0; x < palya.getSzelesseg() - 1; x++) {
                String hatterszin = palya.getPalya()[y][x].getHatterSzin();

                if (jatekosE(y, x)) {
                    System.out.print(hatterszin + BLACK_BOLD + felfedezo.jeloles + RESET);
                } else {
                    if (marMeglatogatott(y, x)) {
                        System.out.print(palya.getPalya()[y][x]);
                    } else {
                        System.out.print(BLACK_BACKGROUND + " " + RESET);
//                        System.out.print(palya.getPalya()[y][x]);
                    }
                }
            }
            System.out.println();
        }
        System.out.println(GREEN_BOLD + "======================\n" + RESET);
    }

    /**
     * A felfedező y és x koordinátájának definiálása <br>
     *     Oda kerül a felfedező ahol a Hajó is van ('H')
     * @param felfedezo
     */
    public void felfedezotLehelyez(Felfedezo felfedezo) {
        for (int i = 0; i < palya.getSzelesseg(); i++) {
            for (int j = 0; j < palya.getMagassag(); j++) {
                if (palya.getPalya()[j][i].jeloles == new Hajo().jeloles) {
                    felfedezo.setX(i);
                    felfedezo.setY(j);
                    eddigLatottTeruletekFrissitese(j, i);
                    break;
                }
            }
        }


    }

    /**
     * @param y
     * @param x
     * @return true ha a felfedező áll az adott koordinátn, különben false
     */
    private boolean jatekosE(int y, int x) {
        return y == felfedezo.getY() && x == felfedezo.getX();
    }

    /**
     * @param y az adott y koordináta
     * @param x az adott y koordináta
     * @return true ha az adott koordinátán található mező járható, különben false
     */
    public boolean jarhato(int y, int x) {
        for (int i = 0; i < palya.getMagassag(); i++) {
            for (int j = 0; j < palya.getSzelesseg(); j++) {
                if (y == i && x == j) {
                    return palya.getPalya()[y][x].isJarhato();
                }
            }
        }

        return false;
    }

    /**
     * @param y az adott y koordináta
     * @param x az adott y koordináta
     * @return true ha már a felfedező látta az adott területet, különben false
     */
    private boolean marMeglatogatott(int y, int x) {
        for (int[] yx : eddigLatottTeruletek)
            if (yx[0] == y && yx[1] == x)
                return true;

        return false;
    }

    /**
     * A koordináta pár hozzáadas az eddig látott területeket táróló Listához
     * @param y az adott y koordináta
     * @param x az adott x koordináta
     */
    public void eddigLatottTeruletekFrissitese(int y, int x) {
        ArrayList<int[]> kornyezet = new ArrayList<>();

        int latotav = felfedezo.getLatotav();

        for (int i = y - latotav; i <= y + latotav; i++) {     //i = y
            for (int j = x - latotav; j <= x + latotav; j++) { //j = x
                kornyezet.add(new int[]{i, j});
            }
        }

        for (int[] yx : kornyezet) {
            if (!marMeglatogatott(yx[0], yx[1])) {
                eddigLatottTeruletek.add(yx);
            }
        }
    }
}
