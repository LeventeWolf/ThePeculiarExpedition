package vasarlas.targyak.elelmiszer;

import figyelok.BonuszFigyelo;

/**
 * Whiskey élémeszier <br>
 *     eneriga : 20 + a csapattársak bónuszából adódó értékek
 *     ár : 10 arany
 */
public class Whiskey extends Elelmiszer {
    public Whiskey() {
        nev = "Whiskey";
        ar = 10;
        energia = 20 + BonuszFigyelo.whiskeyBonusz;
    }
}
