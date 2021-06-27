package jatek;

import jatekosok.Rivalis;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static jatek.KonzolSzinek.*;

/**
 * Riválisok kezelésére szolgál
 */
public class RivalisKezelo {
    private ArrayList<Rivalis> rivalisok;

    /** GETTERS & SETTERS */
    public ArrayList<Rivalis> getRivalisok() {
        return rivalisok;
    }

    /**
     * Konstrukor <br>
     *     definiálja a riválisok Listát
     */
    public RivalisKezelo() {
        rivalisok = new ArrayList<>();
    }

    /**
     * Inicializálja a riválisokat (1-4) rivális lehet
     */
    public void rivalosokInit() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println(CYAN_BOLD + "\nAdd meg hány rivális ellen szeretnél játszani! (1-4)" + RESET);
            System.out.print("> ");
            String valasz = sc.nextLine();
            switch (valasz) {
                case "1":
                    rivalisok.add(new Rivalis("Kalóz Fred"));
                    return;
                case "2":
                    rivalisok.add(new Rivalis("Kalóz Fred"));
                    rivalisok.add(new Rivalis("Egykezű Bob"));
                    return;
                case "3":
                    rivalisok.add(new Rivalis("Kalóz Fred"));
                    rivalisok.add(new Rivalis("Egykezű Bob"));
                    rivalisok.add(new Rivalis("Makacs Morgan"));
                    return;
                case "4":
                    rivalisok.add(new Rivalis("Kalóz Fred"));
                    rivalisok.add(new Rivalis("Egykezű Bob"));
                    rivalisok.add(new Rivalis("Makacs Morgan"));
                    rivalisok.add(new Rivalis("Félszemű Robert"));
                    return;
                default:
                    System.out.println("Ilyen válaszlehetőség nincs!:)");
                    break;
            }
        }
    }

    /**
     * Minden rivális hirnevének véletlenszerű növelése [100,200]
     */
    public void rivalisokHirnevenekNovelese() {
        Random random = new Random();
        for (Rivalis rivalis : rivalisok) {
            rivalis.setHirnev(rivalis.getHirnev() + random.nextInt(101) + 100); // random [100,200]
        }
    }

    /**
     * Riválisok hírnevének kiírása a standard inputra
     */
    public void rivalisokAllasa() {
        System.out.println(RED  + "\n=======Riválisok Állása=======" + RESET);
        System.out.println(       "       Név       | Hírnév");
        for (Rivalis rivalis : rivalisok) {
            System.out.printf("%-16s | %-4d \n", rivalis.getNev(), rivalis.getHirnev());
        }
        System.out.println();
    }
}
