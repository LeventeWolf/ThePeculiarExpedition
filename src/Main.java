import jatek.JatekKezelo;

import java.io.FileNotFoundException;

/**
 * Futattja a programot
 */
public class Main {
    public static void main(String[] args) {
        JatekKezelo jatekKezelo = new JatekKezelo();

        try {
            jatekKezelo.jatekotElindit();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
