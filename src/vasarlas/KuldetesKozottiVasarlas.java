package vasarlas;

import csapattarsak.*;
import exceptions.NincsElegAranyadKivetel;
import jatekosok.Felfedezo;
import vasarlas.targyak.elelmiszer.Csokolade;
import vasarlas.targyak.elelmiszer.Hus;
import vasarlas.targyak.elelmiszer.Whiskey;
import vasarlas.targyak.eszkoz.Bozotvago;
import vasarlas.targyak.eszkoz.Faklya;
import vasarlas.targyak.eszkoz.Kotel;
import vasarlas.targyak.eszkoz.Uveggolyo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Küldetések közötti vásárlás megvalósítása
 */
public class KuldetesKozottiVasarlas extends Vasarlas {

    /**
     * Küldetés közötti termékek inicializálása melyek lehetnek: <br>
     *     Kötél, Bozótvágó, Fáklya, Üveggolyó, Hús, Whiskey, Csokoládé
     */
    @Override
    public void initTermekek() {
        termekek = new HashMap<>();

        termekek.put("1", new Kotel());
        termekek.put("2", new Bozotvago());
        termekek.put("3", new Faklya());
        termekek.put("4", new Uveggolyo());
        termekek.put("5", new Hus());
        termekek.put("6", new Whiskey());
        termekek.put("7", new Csokolade());
    }

    /**
     * Küldetési közötti csapattárs inicializálása melyek lehetnek: <br>
     *   Kereskedő, Katona, Szamár
     */
    @Override
    public void initCsapattarsak() {
        csapattarsak = new ArrayList<>();

//        csapattarsak.add(new Felderito());
        csapattarsak.add(new Kereskedo());
        csapattarsak.add(new Katona());
        csapattarsak.add(new Szamar());
    }
}
