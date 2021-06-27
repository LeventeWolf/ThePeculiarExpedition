package exceptions;

/**
 * Kivétel: tárgy hozzáadásánál ha más slothoz akarnánk hozzáadni az adott tárgyat
 */
public class NemMegfeleloTipusKivetel extends Exception {
    @Override
    public void printStackTrace() {
        System.out.println("Más fajta típusú tárgyat akarsz hozzáadni!");
    }
}
