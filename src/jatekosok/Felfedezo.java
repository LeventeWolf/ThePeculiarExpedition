package jatekosok;

import exceptions.*;
import figyelok.BonuszFigyelo;
import inventory.Inventroy;
import palya.PalyaKezelo;
import vasarlas.targyak.elelmiszer.Elelmiszer;

import java.util.Scanner;

import static jatek.KonzolSzinek.*;

/**
 * Felfedező megvalósítása, viselkedései: <br>
 *
 */
public class Felfedezo extends Jatekos {
    public final char jeloles = '☺';

    private int x, y;
    private int arany;
    private double energia;
    private int latotav;
    private int megengedettSlotokSzama = 7;
    private int viszony;

    public Inventroy inventroy;

    /**
     * GETTERS & SETTERS
     */
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getArany() {
        return arany;
    }

    public void setArany(int arany) throws NincsElegAranyadKivetel {
        if (arany < 0) {
            throw new NincsElegAranyadKivetel();
        }

        this.arany = arany + BonuszFigyelo.vasarlasiBonusz;
    }

    public double getEnergia() {
        return energia;
    }

    public void setEnergia(double energia) {
        if (energia > 100) {
            this.energia = 100;

        } else if (energia < 0) {
            this.energia = 0;
        } else {
            this.energia = energia;
            System.out.printf(CYAN_BRIGHT + "Új energiaszint!: %.2f\n\n" + RESET, this.energia);
        }

    }

    public int getLatotav() {
        return latotav;
    }

    public void setLatotav(int latotav) {
        this.latotav = latotav;
    }

    public int getMegengedettSlotokSzama() {
        return megengedettSlotokSzama;
    }

    public void setMegengedettSlotokSzama(int megengedettSlotokSzama) {
        this.megengedettSlotokSzama = megengedettSlotokSzama;
    }

    public int getViszony() {
        return viszony;
    }

    public void setViszony(int viszony) {
        this.viszony = viszony;
    }

    /**
     * KONSTRUKTOR
     */
    public Felfedezo() {
        inventroy = new Inventroy();
    }

    /**
     * A felfedező lépésének megvalósítása
     * @param valasz
     * @param palyaKezelo
     * @throws Kilepes
     * @throws NemJarhatoMezo
     * @throws PalyanKivuliLepes
     * @throws NincsIlyenLehetoseg
     */
    public void lepes(String valasz, PalyaKezelo palyaKezelo) throws Kilepes, NemJarhatoMezo, PalyanKivuliLepes, NincsIlyenLehetoseg {
        switch (valasz) {
            case "0":
                throw new Kilepes();
            case "1": //FEL
                if (y - 1 < 0) throw new PalyanKivuliLepes();
                if (!palyaKezelo.jarhato(y-1, x)) throw new NemJarhatoMezo();

                y--;
                break;
            case "2": //LE
                if (y + 1 >= palyaKezelo.palya.getMagassag() - 1) throw new PalyanKivuliLepes();
                if(!palyaKezelo.jarhato(y+1,x)) throw new NemJarhatoMezo();


                y++;
                break;
            case "3": //JOBBRA
                if (x + 1 >= palyaKezelo.palya.getSzelesseg() - 1) throw new PalyanKivuliLepes();
                if (!palyaKezelo.jarhato(y, x + 1)) throw new NemJarhatoMezo();

                x++;
                break;
            case "4": //BALRA
                if (x - 1 < 0) throw new PalyanKivuliLepes();
                if (!palyaKezelo.jarhato(y, x-1)) throw new NemJarhatoMezo();

                x--;
                break;
            default:
                throw new NincsIlyenLehetoseg();
        }
    }

    /**
     * A felfedező evésének megvalósítása <br> az élelmiszereket elfogyaszthatja amelyek energiát töltenek fel
     */
    public void eves() {
        inventroy.tartalomKiirasa();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println(CYAN + "Add meg melyik ételt szeretnéd elfogyaszatni! (pl:Hús | 0:megszakít)" + RESET);
            System.out.print(">");
            String valasz = sc.nextLine();
            if (valasz.equals("0"))
                return;

            try {
                elfogyaszt(valasz);
                inventroy.tartalomKiirasa();
            } catch (NemLetezikIlyenEtelAHatizsakban nemLetezikIlyenEtel) {
                nemLetezikIlyenEtel.printStackTrace();
            }

        }
    }

    /**
     * Az evéshez szükséges efogyasztás megvalósítása
     * @param valasz
     * @throws NemLetezikIlyenEtelAHatizsakban
     */
    private void elfogyaszt(String valasz) throws NemLetezikIlyenEtelAHatizsakban {
        if (!inventroy.contains(valasz)) throw new NemLetezikIlyenEtelAHatizsakban();

        Elelmiszer elelmiszer;

        try {
            elelmiszer = Elelmiszer.elelmiszerek.get(valasz);
            inventroy.kivesz(elelmiszer);
        } catch (NullPointerException e) {
            throw new NemLetezikIlyenEtelAHatizsakban();
        }

        setEnergia(energia + elelmiszer.getEnergia());

        System.out.println(PURPLE + elelmiszer.getNev() + " sikeresen elfogyasztva! +" + elelmiszer.getEnergia() + " energia" + RESET);

        System.out.printf(CYAN_BRIGHT + "Új energiaszint!: %.2f" + RESET, getEnergia());

    }

    /**
     * A felfedező hírnevének kiíratása
     *
     */
    public void allasa() {
        System.out.println(PURPLE_BOLD + "======Saját Állás=======" + RESET);
        System.out.println( nev + " hírneve: " + hirnev + "\n" );
    }
}
