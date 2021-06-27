package vasarlas;

import csapattarsak.*;
import exceptions.NincsElegAranyadKivetel;
import jatekosok.Felfedezo;
import vasarlas.targyak.elelmiszer.*;
import vasarlas.targyak.eszkoz.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Faluban történő vásárlás megvalósítása
 */
public class FaluVasarlas extends Vasarlas {
    /**
     * Faluban történő termékek inicializálása melyek lehetnek: <br>
     *     Kötél, Fáklya, Gyümölcs, Kábitószer
     */
    @Override
    public void initTermekek() {
        termekek = new HashMap<>();

        termekek.put("1", new Kotel());
        termekek.put("2", new Faklya());
        termekek.put("3", new Gyumolcs());
        termekek.put("4", new Kabitoszer());
    }

    /**
     * Faluban történő csapattársak inicializálása melyek lehetnek: <br>
     *   Felderítő, Sámán, Bölcs
     */
    @Override
    public void initCsapattarsak() {
        csapattarsak = new ArrayList<>();

        csapattarsak.add(new Felderito());
        csapattarsak.add(new Saman());
        csapattarsak.add(new Bolcs());
    }
}
