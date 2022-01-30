package entities;
import item.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Player osztály a Entiti-ból számazik, a Player objektumnak a müködése vezérelt a játékos által.
 */
public class Player extends Entiti {

    private final ArrayList<Item> inventory;
    private int id;
    private String nev;
    private int ehesseg;
    private int szomjusag;

    public Player(int x, int y) {
        super(x, y);
        this.inventory = new ArrayList<>();
        this.id = 0;
        this.nev = "J";
        this.ehesseg = 100;
        this.szomjusag = 100;
    }
    /**
     * horgaszik methodus 25% nagysággal müködik rnadom generel egy számok 0-99 közözz
     * ezt a számot megvizsgálva ha 24-nél (0->24 25 egész számjegy)kisebb akkor a táska tartalmát bővitjük 1db hallal.
     */
    public void horgaszik(){
        Random r = new Random();
        int ertek = r.nextInt(100);

        if (ertek<=24){
            this.invektoriAdd(new Hal());
            System.out.println("Yes, fogtal egy halat. ");
        }else{
            System.out.println("Sajnos most ez nem sikerült");
        }
    }

    /**
     * meghívja a inventory add methodusát ami egy beépitett methodus, kiírja hogy |item felvéve.|
     * @param item egy itemet vár amit el táról a belső ArrayList-be
     */
    public void invektoriAdd(Item item){
        System.out.println("|item felvéve.|");
        this.inventory.add(item);
    }

    /**
     * meghívja a inventory addAll methodusát ami egy beépitett methodus
     * @param item egy itemet tömbőt vár amit el táról a belső ArrayList-be
     */
    public void invektoriAdd(Item[] item){
        this.inventory.addAll(Arrays.asList(item));
    }

    /**
     * halal methodus egy ellenőrző folyamat, ellenőrzi a ehesseg és szomjusag adat tagot, ha ezek közű bármely 0 értéket érné el
     * akkor a program kilép 0 értékkel.
     */
    private void halal(){
        if (this.ehesseg ==0 || this.szomjusag==0){
            System.out.println("A jatékot elvesztetted meghaltal!");
            System.exit(0);
        }
    }

    /**
     * csokkenti a ehessegadattag értékét ami kezdetben 100-as érték
     */
    private void ehezik(){
        this.ehesseg--;
    }

    /**
     * csokkenti a szomjusag adattag értékét ami kezdetben 100-as érték
     */
    private void szomjasodik(){
        this.szomjusag--;
    }

    /**
     * statLehuzas methodus egy osszesitő methodus ami meghívja a ehezik, szomjasodik és a halal methodusokat.
     */
    public void statLehuzas(){
        this.ehezik();
        this.szomjasodik();
        this.halal();
    }

    /**
     * String értékkel vissza térő method.
     * @return szöveg értékkel tér vissza a Player adat tag információival ellátva.
     */
    public String getInfo(){
        String ki = "A karaktered jelenleg ennyire éhes: "+(100-this.ehesseg)+
                    "%, a szomlyusági szintje "+(100-this.szomjusag)+"% nálalévő itemek: "+(inventory.size()==0? "nincs nála item":inventory.toString())+"\n";
        return ki+"";
    }

    /**
     * növeli a szomjusag adat tag értékét 40-nel. szomjusag adat tag maximum értéke 100 lehet. Math.min beépittel ellenőri.
     */
    public void iszik(){
        this.szomjusag=Math.min(100,szomjusag+40);
    }

    /**
     * növeli a ehesseg adat tag értékét 60-al. ehesseg adat tag maximum értéke 100 lehet. Math.min beépittel ellenőri.
     */
    public void eszik(){
        this.ehesseg=Math.min(100,this.ehesseg+60);
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    @Override
    public String toString() {
        return " "+this.nev+" ";
    }

    public String getNev() {
        return nev;
    }

    public int getEhesseg() {
        return ehesseg;
    }

    public int getSzomjusag() {
        return szomjusag;
    }
}
