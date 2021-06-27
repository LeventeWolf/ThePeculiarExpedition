package exceptions;

/**
 * Kivétel: ha a felfedező pályán kívüli területre akar lépni
 */
public class PalyanKivuliLepes extends Throwable {
    @Override
    public void printStackTrace() {
        System.out.println("Pályán kívűlre akarsz lépni");
    }
}
