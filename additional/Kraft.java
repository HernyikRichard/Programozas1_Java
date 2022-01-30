package additional;

/**
 * kraft osztály ami a kraftok tárolására jött létre 2 féle konstruktorral
 *
 *
 */
public class Kraft {
    private final int id;
    private final String nev;
    private final int levelDb;
    private final int deszkaDb;
    private final int hulladekdb;

    public Kraft(int id, String nev, int deszkaDb, int levelDb) {
        this.id = id;
        this.nev= nev;
        this.levelDb = levelDb;
        this.deszkaDb = deszkaDb;
        this.hulladekdb = 0;
    }

    public Kraft(int id,String nev, int deszkaDb, int levelDb, int hulladekdb) {
        this.id = id;
        this.nev = nev;
        this.levelDb = levelDb;
        this.deszkaDb = deszkaDb;
        this.hulladekdb = hulladekdb;
    }

    public int getId() {
        return id;
    }


    public int getLevelDb() {
        return levelDb;
    }

    public int getDeszkaDb() {
        return deszkaDb;
    }

    public int getHulladekdb() {
        return hulladekdb;
    }
}
