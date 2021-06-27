package inventory;


import exceptions.NemMegfeleloTipusKivetel;
import exceptions.NincsElegHelyKivetel;
import vasarlas.targyak.Targy;

import java.util.ArrayList;

import static jatek.KonzolSzinek.*;

/**
 * A felfedező inventoryjának megvalósítása
 */
public class Inventroy {
    private final ArrayList<Slot> inventory;
    public static final int MEGENGEDETT_SLOT = 8;

    /**
     * Konstruktor
     */
    public Inventroy() {
        inventory = new ArrayList<>();
    }

    /**
     * Tárgy hozzáadása az invetoryhoz
     * @param targy amit hozzá szeretnénk adni az invetoryhoz
     * @throws NincsElegHelyKivetel ha nincs elég hely az adott slotban
     * @throws NemMegfeleloTipusKivetel ha az adott slothoz más típusú tárgy kerülne
     */
    public void addTargy(Targy targy) throws NincsElegHelyKivetel, NemMegfeleloTipusKivetel { // Targy = Hus
        int slotIndex = indexOfPossibleSlot(targy);

        if (slotIndex == -1) {
            inventory.add(new Slot(targy));
            slotIndex = inventory.size() - 1; // invenotry utolsó slotjának indexe
        }

        inventory.get(slotIndex).hozzaad(targy); //az új slotra belerakjuk az adott tárgyat

        System.out.println(PURPLE + targy.getNev() + " sikeresen hozzáadva a felszerelésedhez!" + RESET);
    }

    /**
     * Megadja hogy melyik indexre fog kerülni a hozzáadni kívánt tárgy
     * @param targy aminek a helyét keressük
     * @return index amire fog kerülni a tárgy, -1 ha új slotot kell neki létrehozni
     */
    private int indexOfPossibleSlot(Targy targy) {
        int i = getMeret() - 1;
        while (i >= 0) {
            Slot slot = inventory.get(i);
            if (slot.getTargy().getNev().equals(targy.getNev()) && slot.ferMegBele()) {
                return i;
            }
            i--;
        }

        return -1;
    }

    /**
     * @return az invetory slotjainak mérete
     */
    public int getMeret() {
        return inventory.size();
    }

    /**
     * Az inventory tartalmának kiírása
     */
    public void tartalomKiirasa() {
        System.out.println(BLUE_BOLD + "\n======A hátizsákod tartalma======" + RESET);
        if (getMeret() == 0) {
            System.out.println("A hátizsákod üres:)\n");
            return;
        }


        System.out.println(" Slot |  Típus     | Darabszám");
        for (int i = 0; i < inventory.size(); i++) {
            Slot slot = inventory.get(i);
            System.out.printf("   %-2s    %-10s     %1s\n", i + 1, slot.getTargy().getNev(), slot.getMeret());
        }
//        System.out.println("=============================");
        System.out.println();
    }

    /**
     * Megadja hogy az adott étel szerepel-e az inventoryban
     * @param etel amit keresünk hogy benne van-e az inventoryban
     * @return true ha benne van, false ha nincs benne
     */
    public boolean contains(String etel) {
        for (Slot slot : inventory) {
            if (slot.getTargy().getNev().equals(etel)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Kivesz egy tárgyat név alapján az inventoryból
     * @param targyNev a tárgy neve amit kiveszünk az inventoryból
     */
    public void kivesz(String targyNev) {
        for (int i = getMeret() - 1; i >= 0; i--) {
            Slot slot = inventory.get(i);
            if (slot.getTargy().getNev().equals(targyNev)) {
                slot.kivesz();
                if (slot.getMeret() == 0) inventory.remove(slot);
                break;
            }
        }
    }

    /**
     * Kivesz egy tárgyat az inventoryból
     * @param targy a tárgy amit kiveszünk az inventoryból
     */
    public void kivesz(Targy targy) {
        for (int i = getMeret() - 1; i >= 0; i--) {
            Slot slot = inventory.get(i);
            if (slot.getTargy().getNev().equals(targy.getNev())) {
                slot.kivesz();
                if (slot.getMeret() == 0){
                    inventory.remove(slot);
                }
                break;
            }
        }
    }
}