package exceptions;

/**
 * Kivétel: ha a hibás a bekért válasz
 */
public class HibasValaszKivetel extends Throwable {
    @Override
    public String getMessage() {
        return "Ilyen válaszlehetőség nincs:)\n";
    }

    @Override
    public void printStackTrace() {
        System.out.println(getMessage());
    }
}
