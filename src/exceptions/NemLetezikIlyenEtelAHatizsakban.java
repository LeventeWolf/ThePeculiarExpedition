package exceptions;

/**
 * Kivétel: ha a felfedező olyan ételt akar elfogyasztani ami nincs nála
 */
public class NemLetezikIlyenEtelAHatizsakban extends Throwable {
    @Override
    public void printStackTrace() {
        System.out.println("Ilyen étel nincs a hátizsákodban!:)\n");
    }
}
