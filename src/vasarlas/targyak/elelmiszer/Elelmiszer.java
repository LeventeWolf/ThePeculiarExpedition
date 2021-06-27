package vasarlas.targyak.elelmiszer;

import vasarlas.targyak.Targy;

import java.util.HashMap;
import java.util.Map;


/**
 * Elelmiszer megvalósítása amit el lehet fogyasztani (speciális tárgy)
 */
public abstract class Elelmiszer extends Targy {
    protected double energia;
    public static Map<String, Elelmiszer> elelmiszerek = setElelmiszerek();

    /**
     * Lehetséges élelmiszerek amit a felfedező elfogyaszhat
     * @return lehetséges élelmiszerek
     */
    private static HashMap<String, Elelmiszer> setElelmiszerek() {
        HashMap<String, Elelmiszer> elelmiszerek = new HashMap<>();

        elelmiszerek.put("Csokoládé", new Csokolade());
        elelmiszerek.put("Hús", new Hus());
        elelmiszerek.put("Whiskey", new Whiskey());
        elelmiszerek.put("Gyümölcs", new Gyumolcs());
        elelmiszerek.put("Kábítószer", new Kabitoszer());

        return elelmiszerek;
    }

    public double getEnergia(){
        return energia;
    }
}
