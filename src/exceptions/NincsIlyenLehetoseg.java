package exceptions;

/**
 * Kivétel: ha a felhasználó olyan lehetőséget ad meg ami nem létezik
 */
public class NincsIlyenLehetoseg extends Throwable {
    @Override
    public void printStackTrace() {
        System.out.println("Nincs ilyen lehetőség!:)");
    }
}
