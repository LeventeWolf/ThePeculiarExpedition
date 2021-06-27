package exceptions;

/**
 * Kivétel: ha a felfedező több csapattárssal rendelkezne mint a megengedett
 */
public class NemLehetTobbCsapattarsadKivetel extends Throwable {
    @Override
    public String getMessage() {
        return "Nem lehet 3 nál több csapattársad";
    }

    @Override
    public void printStackTrace() {
        System.out.println(getMessage());
    }
}
