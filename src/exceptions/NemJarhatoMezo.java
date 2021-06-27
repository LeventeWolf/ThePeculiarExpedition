package exceptions;

/**
 * Kivétel: ha a felfedező nem járható mezőre akar lépni
 */
public class NemJarhatoMezo extends Throwable {
    @Override
    public void printStackTrace() {
        System.out.println("Nem járható mezőre akarsz lépni!");
    }
}
