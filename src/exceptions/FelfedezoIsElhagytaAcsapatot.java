package exceptions;

/**
 * Kivétel: ha a felfedező elhagyta a csapatot
 */
public class FelfedezoIsElhagytaAcsapatot extends Throwable {
    @Override
    public void printStackTrace() {
        System.out.println("A felfedező is elhagyta a csapatot!");
    }
}
