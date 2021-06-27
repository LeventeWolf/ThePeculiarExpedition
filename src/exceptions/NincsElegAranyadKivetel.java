package exceptions;

/**
 * Kivétel: ha a felfedező vásárolna és nincs elég aranya
 */
public class NincsElegAranyadKivetel extends Throwable {

    @Override
    public void printStackTrace() {
        System.out.println("Nincs elég aranyad :(!");
    }
}
