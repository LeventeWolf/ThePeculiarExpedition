package vasarlas;

import csapattarsak.*;
import exceptions.NemLehetTobbCsapattarsadKivetel;
import exceptions.NemMegfeleloTipusKivetel;
import exceptions.NincsElegAranyadKivetel;
import exceptions.NincsElegHelyKivetel;
import figyelok.BonuszFigyelo;
import jatek.CsapattarsKezelo;
import jatekosok.Felfedezo;
import vasarlas.targyak.Targy;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import static jatek.KonzolSzinek.*;

/**
 * Vásárlás ősosztály amely a FaluVásárlást és a KüldetésekKözöttiVásárlást fogja össze
 */
public abstract class Vasarlas {
    protected Map<String, Targy> termekek;
    protected ArrayList<Csapattars> csapattarsak;

    /**
     * Konstrukor <br>
     * Termékek incializálása
     * Csapattársak incializálása
     */
    public Vasarlas() {
        initTermekek();
        initCsapattarsak();
    }

    /**
     * Absztrakt metódus amit az egyes gyerekosztályok valósítanak meg az általuk vásárolható termékeik alapján
     */
    public abstract void initTermekek();

    /**
     * Absztrakt metódus amit az egyes gyerekosztályok valósítanak meg az általuk vásárolható csapattársaik alapján
     */
    public abstract void initCsapattarsak();

    /**
     * Bolti vásárlás mentét valósítja meg a termékei alapján amit megadunk neki <br>
     * Addig kér be terméket ameddig a játékos ki nem lép
     *
     * @param felfedezo aki vásárolja a termékeket
     */
    public void boltiVasarlas(Felfedezo felfedezo) {
        initTermekek();


        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println(YELLOW_BOLD + "======== Bolt ========" + RESET);
            termekekListazasa(termekek);
            System.out.println(CYAN_BRIGHT + "\nAdd meg mit szeretnél vásárolni! ID" + RESET);
            System.out.print("> ");
            String valasz = sc.nextLine();
            if (valasz.equals("0")) return;
            try {
                Targy megvasaroltTargy = termekek.get(valasz);
                felfedezo.setArany(felfedezo.getArany() - megvasaroltTargy.getAr());
                System.out.printf(CYAN_BRIGHT + "Új egyenleged: %s arany\n" + RESET, felfedezo.getArany());
                felfedezo.inventroy.addTargy(megvasaroltTargy);
            } catch (NullPointerException e) {
                System.out.println("Ilyen válaszelhetőség nincs:)");
            } catch (NincsElegAranyadKivetel | NincsElegHelyKivetel | NemMegfeleloTipusKivetel kivetel) {
                kivetel.printStackTrace();
            }
        }
    }

    /**
     * A paraméterben megkapott termékek kiírása a standart kimenetre az alábbi formátumban <br>
     * ID | Terméknév | ár
     *
     * @param termekek amiket kiír a stdinputra
     */
    private void termekekListazasa(Map<String, Targy> termekek) {
        System.out.println("ID | Terméknév | ár");

        for (Map.Entry<String, Targy> entry : termekek.entrySet()) {
            System.out.format(" %1s | %-10s | %-3d arany\n",
                    entry.getKey(),
                    entry.getValue().getNev(),
                    entry.getValue().getAr() - BonuszFigyelo.vasarlasiBonusz);
        }

        System.out.println(" 0 | Bolt elhagyása");
    }

    /**
     * Csapattárs vásárlás menetét valósítja meg a csapattársai alapján amit megadunk neki <br>
     * A felhasználó eldöntheti hogy akar e venni vagy nem (igen/nem)
     *
     * @param felfedezo        aki a csapattársakat vásárolja
     * @param csapattarsKezelo a felfedező csapattársai Listának a kezelője
     */
    public void csapattarsVasarlasa(Felfedezo felfedezo, CsapattarsKezelo csapattarsKezelo) {
        System.out.println(BLUE_BOLD + "====Csapattárs Vásárlása====" + RESET);

        Csapattars randomCsapattars = csapattarsak.get((int) (Math.random() * csapattarsak.size())); //random num 0-2

        System.out.println("A csapattárs akit felvehetsz: " + GREEN_BRIGHT + randomCsapattars.getName() + RESET);
        System.out.println("Előny: " + randomCsapattars.elony());

        Scanner sc = new Scanner(System.in);
        System.out.println(CYAN + "Felveszed? (igen/nem)" + RESET);

        while (true) {
            System.out.print("> ");
            String valasz = sc.nextLine();
            switch (valasz) {
                case "igen":
                    try {
                        felfedezo.setArany(felfedezo.getArany() - 150);

                        csapattarsKezelo.hozzaad(randomCsapattars);
                        csapattarsKezelo.csapattarsakKiirasa();

                        System.out.printf(CYAN_BRIGHT + "Új egyenleged: %s arany\n" + RESET, felfedezo.getArany());

                        return;
                    } catch (NincsElegAranyadKivetel nincsElegAranyadKivetel) {
                        nincsElegAranyadKivetel.printStackTrace();
                    } catch (NemLehetTobbCsapattarsadKivetel nemLehetTobbCsapattarsadKivetel) {
                        nemLehetTobbCsapattarsadKivetel.printStackTrace();
                    }
                case "nem":
                    return;
                default:
                    System.out.println("Ilyen válaszlehetőség nincs:)");
                    break;
            }
        }
    }
}
