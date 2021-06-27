package figyelok;

import jatek.CsapattarsKezelo;
import exceptions.FelfedezoIsElhagytaAcsapatot;
import exceptions.MegtaltadAzAranypiramist;
import exceptions.NemMegfeleloTipusKivetel;
import exceptions.NincsElegHelyKivetel;
import jatekosok.Felfedezo;
import palya.Palya;
import palya.PalyaKezelo;
import palya.mezok.Mezo;
import vasarlas.FaluVasarlas;
import vasarlas.targyak.kincsek.Aranypiramis;

import java.util.Random;

import static jatek.KonzolSzinek.*;


/**
 * A felfedező egyes lépései utána való következményeket figyeli
 */
public class LepesFigyelo {
    private final Felfedezo felfedezo;
    private final PalyaKezelo palyaKezelo;

    /**
     * Konstruktor
     */
    public LepesFigyelo(Felfedezo felfedezo, PalyaKezelo palyaKezelo) {
        this.felfedezo = felfedezo;
        this.palyaKezelo = palyaKezelo;
    }

    /**
     * Figyeli hogy a felfedező milyen mezőre lépett és az adott Mezőn lévő interakciót végrehajtja
     */
    public void lepesekFigyelese(CsapattarsKezelo csapattarsKezelo) throws MegtaltadAzAranypiramist {
        falubaLepett(csapattarsKezelo);
        aranypiramisraLepett();
    }

    /**
     * Figyeli ha a játékos faluba lépett. Ha rálépett interakcióba lép vele
     */
    private void falubaLepett(CsapattarsKezelo csapattarsKezelo) {
        Palya palya = palyaKezelo.palya;
        Mezo mezo = palya.getPalya()[felfedezo.getY()][felfedezo.getX()];

        //Ha rálépett a falura
        if (mezo.getClass().getSimpleName().equals("Falu")) {
            System.out.println(CYAN_BOLD_BRIGHT + "=========Falu========\n" + RESET);
            FaluVasarlas faluVasarlas = new FaluVasarlas();

            //Csapattárs Vásárlás
            faluVasarlas.csapattarsVasarlasa(felfedezo, csapattarsKezelo);

            //Vásárlás
            faluVasarlas.boltiVasarlas(felfedezo);
        }
    }

    /**
     * Figyeli ha a játékos az aranypiramisra lépett. Ha rálépett interakcióba lép vele
     */
    private void aranypiramisraLepett() throws MegtaltadAzAranypiramist {
        Palya palya = palyaKezelo.palya;
        Mezo mezo = palya.getPalya()[felfedezo.getY()][felfedezo.getX()];

        //Ha rálépett az aranypiramisra
        if (mezo.getClass().getSimpleName().equals("Aranypiramis")) {
            System.out.println(GREEN_BOLD + "Gratulálok! Megtaláltad az elveszett Aranypiramist!" + RESET);
            try {
                felfedezo.inventroy.addTargy(new Aranypiramis());
            } catch (NincsElegHelyKivetel nincsElegHelyKivetel) {
                nincsElegHelyKivetel.printStackTrace();
            } catch (NemMegfeleloTipusKivetel nemMegfeleloTipusKivetel) {
                nemMegfeleloTipusKivetel.printStackTrace();
            }
            throw new MegtaltadAzAranypiramist();
        }
    }

    /**
     * Figyeli ha a játékos energiája elfogy, minden csapattag lépésenként 8% eséllyel elhagyja a csapatot.
     */
    public void energiaSzintNullaFigyeles(CsapattarsKezelo csapattarsKezelo) throws FelfedezoIsElhagytaAcsapatot {
        Random random = new Random();
        if (felfedezo.getEnergia() == 0) {
            int esely = random.nextInt(100); //0-99
            System.out.println(RED + "Vigyázat 0 az energiád! Esély a csapattag elhagyására 8% (0-7): " + esely + RESET);
            if (esely < 8){ //0-7
                csapattarsKezelo.csapattarsElhagyjaAcsapatot();
            }
        }
    }
}
