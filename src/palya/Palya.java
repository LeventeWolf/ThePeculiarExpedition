package palya;

import palya.mezok.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Pálya megvalósítása, a pályán mezők találhatóak
 */
public class Palya {
    private Mezo[][] palya;
    private int szelesseg;
    private int magassag;

    /** GETTERS & SETTERS */

    public int getSzelesseg() {
        return szelesseg;
    }

    public int getMagassag() {
        return magassag;
    }

    public Mezo[][] getPalya() {
        return palya;
    }

    /**
     * Az adott küldetéshez a pálya betöltése fájlból
     * @param kuldetesSzam
     * @throws FileNotFoundException
     */
    private void setPalya(int kuldetesSzam) throws FileNotFoundException {
        File file = new File("src/palya/palyak/palya" + kuldetesSzam + ".txt");
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String[] sor = sc.nextLine().split(" ");
            szelesseg = sor.length;
            magassag++;
        }


        sc = new Scanner(file);

        palya = new Mezo[magassag][szelesseg];

        for (int y = 0; y < magassag; y++) {
            String[] sor = sc.nextLine().split(" ");
            for (int x = 0; x < sor.length; x++) {
                palya[y][x] = jelolesek().get(sor[x].charAt(0));
            }
        }
    }

    /**
     * Konstruktor amely a küldetés szám alapján inicializálja a pályát
     * @param kuldetesSzam
     * @throws FileNotFoundException
     */
    public Palya(int kuldetesSzam) throws FileNotFoundException {
        setPalya(kuldetesSzam);
        setNedvesTeruletek();
    }

    /**
     * A tavak körüli nedves területek beállítása <br>
     *     tó körüli nedves terület kék háttérel van megvalósítva
     */
    private void setNedvesTeruletek() {
        for (int y = 0; y < magassag; y++) {
            for (int x = 0; x < szelesseg; x++) {
                Mezo mezo = palya[y][x];
                if (mezo.jeloles != '~' && vanKorulotteTo(y, x)) {
                    mezo.setNedves(true);
                }
            }
        }
    }

    /**
     * @param y az adott y koordináta
     * @param x az adott x koordináta
     * @return true ha az adott koordináta köröl van tó, false ha nincs
     */
    private boolean vanKorulotteTo(int y, int x) {
        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - 1; j <= x + 1; j++) {
                if (i >= 0 && i < magassag && j >= 0 && j < szelesseg) {
                    if (palya[i][j].jeloles == '~') {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * A pályán található jelölések definiálása
     * @return
     */
    public static HashMap<Character, Mezo> jelolesek() {
        HashMap<Character, Mezo> jelolesek = new HashMap<>();

        jelolesek.put('≈', new Tenger());
        jelolesek.put('~', new To());
        jelolesek.put('O', new Szarazfold());
        jelolesek.put('∩', new Oltar());
        jelolesek.put('⌂', new Falu());
        jelolesek.put('^', new Hegy());
        jelolesek.put('Ѧ', new Barlang());
        jelolesek.put('H', new Hajo());
        jelolesek.put('#', new Dzsungel());
        jelolesek.put('X', new Aranypiramis());

        return jelolesek;
    }

}
