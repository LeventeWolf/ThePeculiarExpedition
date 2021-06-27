package jatek;

import exceptions.*;
import figyelok.BonuszFigyelo;
import figyelok.LepesFigyelo;
import jatekosok.Felfedezo;

import java.io.FileNotFoundException;
import java.util.*;

import jatekosok.Jatekos;
import vasarlas.KuldetesKozottiVasarlas;
import palya.PalyaKezelo;

import static jatek.KonzolSzinek.*;

/**
 * A játék kezelésére szolgál
 */
public class JatekKezelo {
    private static final int KULDETESEK_SZAMA = 5;
    private final LepesFigyelo lepesFigyelo;
    private final BonuszFigyelo bonuszFigyelo;
    public CsapattarsKezelo csapattarsKezelo;
    private final Felfedezo felfedezo;
    private final PalyaKezelo palyaKezelo;
    private final RivalisKezelo rivalisKezelo;
    private final int megengedettSlotokSzama = 7;
    private int kuldetes = 1;

    /**
     * Konstrukor amely az alábbiakat incializálja: <br>
     *     Felfedező, PályaKezelő, LépésFigyelő, CsapattársKezelo, RiválisKezelo, BonuszFigyelo
     */
    public JatekKezelo() {
        felfedezo = new Felfedezo();
        palyaKezelo = new PalyaKezelo(felfedezo);
        lepesFigyelo = new LepesFigyelo(felfedezo, palyaKezelo);
        csapattarsKezelo = new CsapattarsKezelo();
        rivalisKezelo = new RivalisKezelo();
        bonuszFigyelo = new BonuszFigyelo(csapattarsKezelo, felfedezo);
    }

    /**
     * A játék leírását írja ki a standard kimenetre
     */
    private void leiras() {
        System.out.println(BLUE_BOLD + "         ====The Peciluar Expedition====" + RESET);
        System.out.println("A játékban felfedezőt kell alakítanunk, aki hajóra száll\n" +
                "és veszedelmes tájakon igyekszik összegyűjteni az ott elrejtett\n" +
                "kincseket és a legendás Arany piramist! A felfedező sajnos\n" +
                "nincs egyedül, más felfedezőkkel kell versengenie a legjobb\n" +
                "felfedező címéért, amelyet 5 küldetés után a legtöbb hírnévvel\n" +
                "rendelkező felfedező kap.\n" +
                "Minden küldetés elején a játék felajánl egy csapattársatn\n" +
                "illetve tudsz vásárolni a boltban.\n");
    }

    /**
     * A standard inputról bekér egy nevet amit beállít a Felfedezőnek
     */
    private void felfedezoNevenekBeallitasa() {
        Scanner sc = new Scanner(System.in);

        System.out.println(CYAN_BOLD + "Add meg a neved!:)" + RESET);
        System.out.print("> ");

        felfedezo.setNev(sc.nextLine());
    }

    /**
     * A játék kezdeti állapotának incializálása, ezek: <br>
     *     leírás kiíratása,
     *     felfedező nevének beállítása,
     *     riválisk számának beállítása,
     *     riválisok állásának kiíratása,
     *     sok sikert kiíratása!
     */
    private void setupJatek() {
        leiras();
        felfedezoNevenekBeallitasa();
        rivalisKezelo.rivalosokInit();
        rivalisKezelo.rivalisokAllasa();

        System.out.println(PURPLE + "Sok sikert " + felfedezo.getNev() + "!\n" + RESET);
    }

    /**
     * A játék inicializálása majd elinditása
     * @throws FileNotFoundException ha a nem található a fájl
     */
    public void jatekotElindit() throws FileNotFoundException {
        setupJatek();

        try {
            kuldetesek();
        } catch (FelfedezoIsElhagytaAcsapatot felfedezoIsElhagytaAcsapatot) {
            felfedezoIsElhagytaAcsapatot.printStackTrace();
            System.out.println("Vesztettél!");
        }

        System.out.println("Véget értek a küldetéseid!\n");
        eredmenyHirdetes();
    }

    /**
     * A játék végén a riválisok és a felfedező hírnevének megjelenítése
     * majd a győzetes kihírdetése
     */
    private void eredmenyHirdetes() {
        ArrayList<Jatekos> jatekosok = new ArrayList<>(rivalisKezelo.getRivalisok());
        jatekosok.add(felfedezo);

        Collections.sort(jatekosok);

        System.out.println(YELLOW_BOLD + "==========Eredményhírdetés==========" + RESET);
        System.out.println(YELLOW + " Helyezés |       Név       | Hírnév" + RESET);

        for (int i = 0; i < jatekosok.size(); i++) {
            Jatekos jatekos = jatekosok.get(i);
            int helyezes = i + 1;
            System.out.printf("   %s.     | %-15s | %d\n", helyezes, jatekos.getNev(), jatekos.getHirnev());
        }
    }

    /**
     * Az összes küldetés véghezvitele
     * @throws FileNotFoundException
     * @throws FelfedezoIsElhagytaAcsapatot
     */
    private void kuldetesek() throws FileNotFoundException, FelfedezoIsElhagytaAcsapatot {
        for (int kuldetesSzam = 1; kuldetesSzam <= KULDETESEK_SZAMA; kuldetesSzam++) {
            kuldetes = kuldetesSzam;

            setupKuldetes();

            kuldetes();

            tearDownKuldetes();
        }
    }

    /**
     * Küldetése elötti tulajdonságok beállítása:
     * -arany = 250
     * -energia = 100
     */
    public void kuldetesElottiInit() {
        try {
            felfedezo.setArany(250 - BonuszFigyelo.vasarlasiBonusz);
            felfedezo.setEnergia(100);
            felfedezo.setLatotav(1);
            felfedezo.setViszony(3);
        } catch (Exception | NincsElegAranyadKivetel e) {
            e.printStackTrace();
        }
    }

    /**
     * Küldetés elötti beállítások elvégzése amelyek:
     * -pálya inicializálása
     * -felfedező inicalizálása
     * -csapattárs ajánlása
     * -boltban való vásárlás
     *
     * @throws FileNotFoundException
     */
    private void setupKuldetes() throws FileNotFoundException {
        kuldetesElottiInit();

        palyaKezelo.initPalya(kuldetes);

        KuldetesKozottiVasarlas kuldetesKozottiVasarlas = new KuldetesKozottiVasarlas();

        kuldetesKozottiVasarlas.csapattarsVasarlasa(felfedezo, csapattarsKezelo);


        bonuszFigyelo.bonuszokFigyelese();

        kuldetesKozottiVasarlas.boltiVasarlas(felfedezo);

        palyaKezelo.eddigLatottTeruletekFrissitese(felfedezo.getY(), felfedezo.getX());


    }

    /**
     * A felhasználó által végbevihető lehetőségek kiíratása
     */
    private void lehetosegek() {
        System.out.println(CYAN +
        "Mit szeretnél csinálni?: 1: mozgás" + " 2: térkép 3: inventory 4: evés 5: stats" + RESET);
    }

    /**
     * Az adott küldetés véghezvitele
     */
    private void kuldetes() throws FelfedezoIsElhagytaAcsapatot {
        System.out.printf(GREEN_BRIGHT + "\nKezdődjön az %s.-számú küldetésed!\n\n" + RESET, kuldetes);


        Scanner sc = new Scanner(System.in);

        while (true) {

            lehetosegek();
            System.out.print("> ");
            String valasz = sc.nextLine();

            switch (valasz) {
                case "1": // MOZGÁS
                    try {
                        palyaKezelo.palyatKiir();
                        mozgas();
                    } catch (MegtaltadAzAranypiramist megtaltadAzAranypiramist) {
                        return;
                    }
                    break;
                case "2": //TÉRKÉP
                    palyaKezelo.palyatKiir();
                    break;
                case "3": //HÁTIZSÁK TARTALMA
                    felfedezo.inventroy.tartalomKiirasa();
                    break;
                case "4": //EVÉS
                    felfedezo.eves();
                    break;
                case "5":
                    stats();
                    break;
                default:
                    System.out.println("Ilyen válaszlehetőség nincs:)");
                    break;
            }
        }

    }

    /**
     * A felfedező statisztikájának kiíratása
     */
    private void stats() {
        System.out.println(PURPLE_BOLD + "\n======= Felfedező =======" + RESET);
        System.out.println("Név: " + felfedezo.getNev());
        System.out.println("Hírnév: " + felfedezo.getHirnev());
        System.out.println("Koordináták:\n" +
                            " x=" + felfedezo.getX() + "\n" +
                            " y=" + felfedezo.getY());
        System.out.println("Arany: " + felfedezo.getArany());
        System.out.printf("Energia: %.2f\n", felfedezo.getEnergia());
        System.out.println("Látótávolság: " + felfedezo.getLatotav());
        System.out.println("Viszony: " + felfedezo.getViszony());
        felfedezo.inventroy.tartalomKiirasa();
        csapattarsKezelo.csapattarsakKiirasa();
    }

    /**
     * Az egyes küldetések végén lévő történések lekezelése, melyek lehetenek: <br>
     *     kincsek sorsának eldöntése, riválisok hírnevének növelése, riválisok állásának kiírása,
     *     felfedező állásának kiírása
     */
    private void tearDownKuldetes() {
        System.out.println("\n" + GREEN_BRIGHT + kuldetes + " számú küldetésed véget ért!" + RESET);

        kincsekSorsa();

        rivalisKezelo.rivalisokHirnevenekNovelese();
        rivalisKezelo.rivalisokAllasa();

        felfedezo.allasa();
    }

    /**
     * A küldetés végén a kincsek sorsának lekezelése, ami lehet: <br>
     *     Kincsek eladása vagy Kincsek eladományozása
     */
    private void kincsekSorsa() {
        System.out.println(CYAN + "Most dönthetsz arról hogy a kincseidet eladod vagy eladományozod\n" +
                "a következő küldetés megkezdése elött. (eladás/eladományozás)" + RESET);

        Scanner sc = new Scanner(System.in);

        boolean helyesValasz = false;
        while (!helyesValasz) {
            System.out.print("> ");
            String valasz = sc.nextLine();
            switch (valasz) {
                case "eladás":
                    kincsekEladasa();
                    helyesValasz = true;
                    break;
                case "eladományozás":
                    kincsekEladomanyozasa();
                    helyesValasz = true;
                    break;
                default:
                    System.out.println("Ilyen válaszlehetőség nincs!:)");
                    break;
            }
        }
    }

    /**
     * Az összes mozgási lehetőség kiírása, melyek lehetnek: <br>
     *     1: Fel | 2: Le | 3: Jobbra | 4: Balra | 0: Vége
     */
    private void mozgasLehetosegek() {
        System.out.println(CYAN + "1: Fel | 2: Le | 3: Jobbra | 4: Balra | 0: Vége" + RESET);
    }

    /**
     * A felfedező mozgatása, bekér egy irányt amerre a felfedező menni szeretne, ez lehet: <br>
     *     Fel, Le, Jobbra, Balra
     * @throws FelfedezoIsElhagytaAcsapatot Ha elfogy az energiája
     * @throws MegtaltadAzAranypiramist Ha megtalálta az aranypiramist
     */
    public void mozgas() throws FelfedezoIsElhagytaAcsapatot, MegtaltadAzAranypiramist {
        System.out.println("    ==== Mozgás ====");
        Scanner sc = new Scanner(System.in);
        while (true) {
            mozgasLehetosegek();
            System.out.print("> ");
            String valasz = sc.nextLine();

            try {
                felfedezo.lepes(valasz, palyaKezelo);
                mozgasKovetkezmenyei();
            } catch (Kilepes kilepes) {
                return;
            } catch (NemJarhatoMezo nemJarhatoMezo) {
                nemJarhatoMezo.printStackTrace();
            } catch (PalyanKivuliLepes palyanKivuliLepes) {
                palyanKivuliLepes.printStackTrace();
            } catch (NincsIlyenLehetoseg nincsIlyenLehetoseg) {
                nincsIlyenLehetoseg.printStackTrace();
            }
        }
    }

    /**
     * Minden egyes lépés következtében fellépő dologok lekezelése amelyek: <br>
     *     Az eddig látott területek frissítése
     *     A pálya kiíratása
     *     Lépések lekezelése
     *     Csapattársakból adódó bónuszok lekezelése
     *     Megfelelő mennyiségű energia levonása
     *     Nulla energia figyelése
     * @throws FelfedezoIsElhagytaAcsapatot
     * @throws MegtaltadAzAranypiramist
     */
    private void mozgasKovetkezmenyei() throws FelfedezoIsElhagytaAcsapatot, MegtaltadAzAranypiramist {
        palyaKezelo.eddigLatottTeruletekFrissitese(felfedezo.getY(), felfedezo.getX());
        palyaKezelo.palyatKiir();

        lepesFigyelo.lepesekFigyelese(csapattarsKezelo);
        bonuszFigyelo.bonuszokFigyelese();

        energiaLevonas();
        lepesFigyelo.energiaSzintNullaFigyeles(csapattarsKezelo);
    }

    /**
     * A felfedező mozgásonkénti költésgének levonása büntetéssel együtt
     */
    private void energiaLevonas() {
        double buntetes = 1;

        //Inventory büntetés 7 nél több Slotnál, Slotonként + 20% (*=1.2)
        if (felfedezo.inventroy.getMeret() > megengedettSlotokSzama) {
            int slotBuntetes = felfedezo.inventroy.getMeret() - 7;
            for (int i = 0; i < slotBuntetes; i++) buntetes *= 1.2;
        }

        //Csapattárs büntetés minden csapattársnál +15% (=1*15)
        for (int i = 0; i < csapattarsKezelo.getMeret(); i++) buntetes *= 1.15;

        felfedezo.setEnergia(felfedezo.getEnergia() - buntetes);
    }

    /**
     * Felfedező kincsének eladása amely: <br>
     *     növeli a hírnevét 200-al
     *     arany nem jár érte
     */
    private void kincsekEladomanyozasa() {
        felfedezo.setHirnev(felfedezo.getHirnev() + 200);
        felfedezo.inventroy.kivesz("Aranypiramis");
        System.out.println("Aranypiramis sikeresen eladományozva!");
    }

    /**
     * Felfedező kincsének eladása amely: <br>
     *     növeli a hírnevét 200-al
     *     növeli az aranyát 300-al
     */
    private void kincsekEladasa() {
        felfedezo.setHirnev(felfedezo.getHirnev() + 200);

        try {
            felfedezo.setArany(felfedezo.getArany() + 300);
        } catch (NincsElegAranyadKivetel nincsElegAranyadKivetel) {
            nincsElegAranyadKivetel.printStackTrace();
        }

        System.out.println("\nAranypiramis sikeresen eladva!\n");
        System.out.println(CYAN_BRIGHT + "Új egyenleg: " + felfedezo.getArany() + RESET + "\n");

        felfedezo.inventroy.kivesz("Aranypiramis");
    }
}
