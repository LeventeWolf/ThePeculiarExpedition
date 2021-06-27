package vasarlas.targyak.elelmiszer;

import figyelok.BonuszFigyelo;

/**
 * Kábítószer élémeszier <br>
 *     eneriga : 20 + a csapattársak bónuszából adódó értékek
 *     ár : 15 arany
 */
public class Kabitoszer extends Elelmiszer {
    public Kabitoszer() {
        nev = "Kábítószer";
        energia = 20 + BonuszFigyelo.kabitoszerBonusz;
        ar = 15;
    }
}
