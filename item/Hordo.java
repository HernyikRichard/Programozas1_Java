package item;
import java.util.Random;

/**
 * Hordo osztály egy Item osztály leszármazotja.
 * lénygi információk az osztályban beállitásra kerülnek.
 * plusz adat tagót tartalmaz a Item-hez képest
 */
public class Hordo extends Item {
    private Item[] belso;

    public Hordo() {
        super("B", 15);
        belso = new Item[5];
        setBelso();
    }

    private void setBelso() {
        Random r = new Random();
        for (int i = 0; i < belso.length; i++) {
            int ertek = r.nextInt(4);
            if (ertek == 0) {
                this.belso[i] = new Krumpli();
            } else if (ertek == 1) {
                this.belso[i] = new Level();
            }else if (ertek == 2) {
                this.belso[i] = new Hulladek();
            } else{
                this.belso[i] = new Deszka();
            }
        }
    }

    public Item[] getBelso() {
        return belso;
    }
}
