package exceptions;

/**
 * Kivétel: ha a felfedező inventoryjának slotja meghaladja a maximális mennyiséget
 */
public class NincsElegHelyKivetel extends Exception {
    @Override
    public void printStackTrace() {
        System.out.println("Nincs elég hely az inventorydban!");
    }
}
