import additional.Kraft;
import entities.*;
import item.*;
import mapElem.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class Palya {
    private int lepesszam;
    private final Player jatekos;
    private final Elem[][] palyaElem;
    private final Item[][] palyaItem;
    private final Kraft[] recept;
    private final int meretX;
    private final int meretY;
    private final int nyeresErtek;

    /**
     * a konstrukorba létrehozzúk a kellő objektumokat, be állitunk megfelelő paramétereket.
     *
     * @param x           pálya magassaga
     * @param y           pálya szelessege
     * @param nyeresErtek hány lépés szükséges a nyeréshez
     */
    public Palya(int x, int y, int nyeresErtek) {
        this.meretX = x;
        this.meretY = y;

        palyaElem = new Elem[this.meretX][this.meretY];
        palyaItem = new Item[this.meretX][this.meretY];


        for (int i = 0; i < this.meretX; i++) {
            for (int j = 0; j < this.meretY; j++) {
                if (j >= 18 && j < 20 && i >= 13 && i < 15) {
                    this.palyaElem[i][j] = new Platform();
                } else {
                    this.palyaElem[i][j] = new Tenger();
                }
            }
        }


        this.jatekos = new Player(14, 19);
        this.lepesszam = 0;
        this.nyeresErtek = nyeresErtek;
        this.recept = new Kraft[5];
        this.kraftokInit();


    }

    /**
     * fel -> 8 | fel-jobra -> 9 | jobra -> 6 | jobra-le -> 3
     * le -> 2 | le-balra -> 1 | balra -> 4 | balra-fel -> 7
     * <p>
     * 7 8 9
     * 4 _ 6
     * 1 2 3
     * <p>
     * numpad billentyük íránya
     */
    public void emberMozgat(int irany) {
        switch (irany) {
// mozog fel
            case 8 -> {
                if (jatekos.mozogX(jatekos.getX() - 1, this.meretX)) {
                    this.lep();
                }
            }
// mozog jobra
            case 6 -> {
                if (jatekos.mozogY(jatekos.getY() + 1, this.meretY)) {
                    this.lep();
                }
// mozog le
            }
            case 2 -> {
                if (jatekos.mozogX(jatekos.getX() + 1, this.meretX)) {
                    this.lep();
                }
            }
// mozog balra
            case 4 -> {
                if (jatekos.mozogY(jatekos.getY() - 1, this.meretY)) {
                    this.lep();
                }
            }
// mozog balra-fel
            case 7 -> {
                jatekos.setY(jatekos.getY() - 1, this.meretY);
                jatekos.setX(jatekos.getX() - 1, this.meretX);
                this.lep();
            }
// mozog balra-le
            case 1 -> {
                jatekos.setY(jatekos.getY() - 1, this.meretY);
                jatekos.setX(jatekos.getX() + 1, this.meretX);
                this.lep();
            }
// mozog jobra-fel
            case 9 -> {
                jatekos.setY(jatekos.getY() + 1, this.meretY);
                jatekos.setX(jatekos.getX() - 1, this.meretX);
                this.lep();
            }
// mozog jobra-le
            case 3 -> {
                jatekos.setY(jatekos.getY() + 1, this.meretY);
                jatekos.setX(jatekos.getX() + 1, this.meretX);
                this.lep();
            }
            default -> System.out.println("nem megfelelőt adtál meg");
        }
    }

    /**
     * iszik methodusát hívja meg a játékosnak
     */
    public void iszik() {
        if (palyaElem[jatekos.getX()][jatekos.getY()] instanceof Viztisztito) {
            if (((Viztisztito) palyaElem[jatekos.getX()][jatekos.getY()]).ihato()) {
                lep();
                jatekos.iszik();
                ((Viztisztito) palyaElem[jatekos.getX()][jatekos.getY()]).urit();
                System.out.println("Ittal így csökkentetted a szomjasságod.");
            } else {
                System.out.println("nem tudsz inni még");
            }
        } else {
            System.out.println("nem olyan területen vagy ahol elérhető lenne ez.");
        }
    }

    /**
     * játékos eszik funkcióját hívja meg
     */
    public void eszik() {
        if (palyaElem[jatekos.getX()][jatekos.getY()] instanceof Tuzhely) {
            if (((Tuzhely) palyaElem[jatekos.getX()][jatekos.getY()]).isEheto()) {
                lep();
                jatekos.eszik();
                ((Tuzhely) palyaElem[jatekos.getX()][jatekos.getY()]).urit();
                System.out.println("Ettél, így csökkentetted a éhségedet.");
            } else {
                System.out.println("még nem ehetsz.");
            }
        } else {
            System.out.println("nem olyan területen vagy ahol elérhető lenne ez.");
        }
    }

    /**
     * ellenőri h a sütőbe van es sütésre alkalmas item ha nincs beletsz és lép egyet.
     */
    public void beteszEtel() {
        if (palyaElem[jatekos.getX()][jatekos.getY()] instanceof Tuzhely) {
            if (etelVan()) {
                ((Tuzhely) palyaElem[jatekos.getX()][jatekos.getY()]).beleTesz();
                lep();
            } else {
                System.out.println("Nincs nálad étel amit meg süthetnél.");
            }
        } else {
            System.out.println("nem sütőn állsz így nem tudsz sütni.");
        }
    }

    /**
     * eljárás megnézi hogy aktuálisan hány lépésnél vagyunk. Ha a lépés számunk eléri
     * a megfelelőt akkor a játék véget ér és kilépünk.
     */
    private void nyertes() {
        if (this.lepesszam == this.nyeresErtek) {
            System.out.println("A játéknak vége Megnyerted.");
            System.exit(1);
        }
    }

    /**
     * a lép eljárás meghívásakor a lépés számot csökentjük, ellenőrizzük a gyözemi helyzetet,
     * illetve a játékosnak meg hívjuk a statLehuzas eljárását.
     */
    private void lep() {
        this.lepesszam++;
        this.jatekos.statLehuzas();
        this.itemStep();
        this.itemGenerate();
        this.itemFelvesz();
        this.vizLep();
        this.sutoLep();
        this.nyertes();
    }

    /**
     * egy itemet generálunk több külömbözök funkció segitségéve.
     */
    private void itemGenerate() {
        Random r = new Random();
        int ertek = r.nextInt(4);
        if (ertek > 0) {
            int[] positonsY = new int[ertek];
            for (int i = 0; i < ertek; i++) {
                positonsY[i] = -1;
            }
            for (int i = 0; i < ertek; i++) {
                int y = this.genYpositonsY();
                if (positonsY[i] != y) {
                    this.palyaItem[0][y] = this.genRandomItem();
                    positonsY[i] = y;
                } else {
                    if (y + 1 > this.meretY) {
                        this.palyaItem[0][y - 1] = this.genRandomItem();
                    } else {
                        this.palyaItem[0][y + 1] = this.genRandomItem();
                    }
                }
            }
        }
    }

    /**
     * @return vissza adja a vizszintes tengejen lévő koordinátát amit generálunk
     */
    private int genYpositonsY() {
        Random r = new Random();
        return r.nextInt(this.meretY - 1);
    }

    /**
     * @return egy random generált itemet generálunk ki.
     */
    private Item genRandomItem() {
        Item item;
        Random r = new Random();
        int ertek = r.nextInt(100);
        if (ertek < 32) {
            item = new Deszka();
        } else if (ertek < 63) {
            item = new Level();
        } else if (ertek <= 95) {
            item = new Hulladek();
        } else {
            item = new Hordo();
        }
        return item;
    }

    /**
     * itemek a pályán mozognak egyet és a előző helyükön nem marad semmi..
     */
    private void itemStep() {
        for (int i = this.meretX - 1; i >= 0; i--) {
            for (int j = 0; j < this.meretY; j++) {
                if (this.palyaItem[i][j] != null) {
                    if (i == this.meretX - 1) {
                        this.palyaItem[i][j] = null;
                    } else {
                        this.palyaItem[i + 1][j] = this.palyaItem[i][j];
                        this.palyaItem[i][j] = null;
                    }
                }
            }
        }

    }

    /**
     * Eljárás meghívja a játékosnak a getInfo fügvényét és a játékos oszály ki írja
     * a megfelelő adatokat.
     */
    public void infoPlayer() {
        System.out.println(this.jatekos.getInfo() + "eddigi cselekvés szám: " + this.lepesszam + ".\n");
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("");

        out = out.append("""
                ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┐
                │ 0 │ 1 │ 2 │ 3 │ 4 │ 5 │ 6 │ 7 │ 8 │ 9 │ 10│ 11│ 12│ 13│ 14│ 15│ 16│ 17│ 18│ 19│ 20│ 21│ 22│ 23│ 24│ 25│ 26│ 27│ 28│ 28│ 30│ 31│ 32│ 33│ 34│ 35│
                ├───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┤
                """);


        for (int i = 0; i < this.meretX; i++) {
            if (i + 1 < 10) {
                out.append("│ ").append(i + 1).append(" ");
            } else {
                out.append("│ ").append(i + 12);
            }
            for (int j = 0; j < this.meretY; j++) {
                if (jatekos.getX() == i && jatekos.getY() == j) {
                    out.append("│").append(jatekos.toString());
                } else if (palyaElem[i][j] instanceof Tenger && palyaItem[i][j] != null) {
                    out.append("│").append(palyaItem[i][j].toString());
                } else {
                    out.append("│").append(palyaElem[i][j].toString());
                }
            }
            if (i + 1 == this.meretX) {
                out.append("│\n└───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┘\n");

            } else {
                out.append("│\n├───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┤\n");

            }

        }
        out.append("\n");
        return out.toString();
    }

    /**
     * Megvizsgáljuk hogy aktuálisan a játékosunk Tenger mezőn áll-e ha nem akkor hiba
     * üzenetett írunk neki, amennyiben igen akkor a játékosnak a horgászik eljárását
     * hívjuk meg, illetve nőveljük a lépés számot 1 db-al.
     */
    public void horgaszik() {
        if (palyaElem[jatekos.getX()][jatekos.getY()] instanceof Tenger) {
            jatekos.horgaszik();
            lep();
        } else {
            System.out.println("Csak a tenegreb hórgászhatsz.");
        }
    }

    /**
     * Item felvesz funkció segitségével minden lépésfolyamán körbenézünk és ha van elérhető item 1
     * távólságban azt felvesszük.
     */
    private void itemFelvesz() {
        int pintX = this.jatekos.getX();
        int pintY = this.jatekos.getY();
        for (int i = 0; i < this.meretX; i++) {
            for (int j = 0; j < this.meretY; j++) {
                if (palyaItem[i][j] != null) {
                    if (i == pintX - 1 && j == pintY - 1 && hordoBegyujt(palyaItem[i][j], i, j)) {
                        this.jatekos.invektoriAdd(palyaItem[i][j]);
                        palyaItem[i][j] = null;
                    }
                    if (i == pintX - 1 && j == pintY && hordoBegyujt(palyaItem[i][j], i, j)) {
                        this.jatekos.invektoriAdd(palyaItem[i][j]);
                        palyaItem[i][j] = null;
                    }
                    if (i == pintX - 1 && j == pintY + 1 && hordoBegyujt(palyaItem[i][j], i, j)) {
                        this.jatekos.invektoriAdd(palyaItem[i][j]);
                        palyaItem[i][j] = null;
                    }
                    if (i == pintX && j == pintY - 1 && hordoBegyujt(palyaItem[i][j], i, j)) {
                        this.jatekos.invektoriAdd(palyaItem[i][j]);
                        palyaItem[i][j] = null;
                    }
                    if (i == pintX && j == pintY && hordoBegyujt(palyaItem[i][j], i, j)) {
                        this.jatekos.invektoriAdd(palyaItem[i][j]);
                        palyaItem[i][j] = null;
                    }
                    if (i == pintX && j == pintY + 1 && hordoBegyujt(palyaItem[i][j], i, j)) {
                        this.jatekos.invektoriAdd(palyaItem[i][j]);
                        palyaItem[i][j] = null;
                    }
                    if (i == pintX + 1 && j == pintY - 1 && hordoBegyujt(palyaItem[i][j], i, j)) {
                        this.jatekos.invektoriAdd(palyaItem[i][j]);
                        palyaItem[i][j] = null;
                    }
                    if (i == pintX + 1 && j == pintY && hordoBegyujt(palyaItem[i][j], i, j)) {
                        this.jatekos.invektoriAdd(palyaItem[i][j]);
                        palyaItem[i][j] = null;
                    }
                    if (i == pintX + 1 && j == pintY + 1 && hordoBegyujt(palyaItem[i][j], i, j)) {
                        this.jatekos.invektoriAdd(palyaItem[i][j]);
                        palyaItem[i][j] = null;
                    }
                }

            }
        }
    }

    /**
     * kraftok recepjét inicializája
     */
    private void kraftokInit() {
        recept[0] = new Kraft(2, "Platfor", 2, 2);
        recept[1] = new Kraft(16, "Lánzsa", 4, 4, 4);
        recept[2] = new Kraft(5, "Tűzhely", 2, 4, 3);
        recept[3] = new Kraft(4, "Viztisztito", 2, 4);
        recept[4] = new Kraft(3, "Háló", 2, 6);
    }

    /**
     * lekraftolja azt az itemet amit vár a fügvény
     *
     * @param mit
     */
    public void kroftol(int mit) {
        if (mit == 1) {
            if (ellenoriz(mit - 1)) {
                if (elemLehelyez(mit - 1)) {
                    itemElvesz(this.recept[mit - 1].getDeszkaDb(), this.recept[mit - 1].getLevelDb(), this.recept[mit - 1].getHulladekdb());
                    lep();
                }
            }
        } else if (mit == 2) {
            if (ellenoriz(mit - 1)) {
                if (elemLehelyez(mit - 1)) {
                    itemElvesz(this.recept[mit - 1].getDeszkaDb(), this.recept[mit - 1].getLevelDb(), this.recept[mit - 1].getHulladekdb());
                    lep();
                }
            }
        } else if (mit == 3) {
            if (ellenoriz(mit - 1)) {
                System.out.print("3. ");
                if (elemLehelyez(mit - 1)) {
                    itemElvesz(this.recept[mit - 1].getDeszkaDb(), this.recept[mit - 1].getLevelDb(), this.recept[mit - 1].getHulladekdb());
                    lep();
                }
            }
        } else if (mit == 4) {
            if (ellenoriz(mit - 1)) {
                if (elemLehelyez(mit - 1)) {
                    itemElvesz(this.recept[mit - 1].getDeszkaDb(), this.recept[mit - 1].getLevelDb(), this.recept[mit - 1].getHulladekdb());
                    lep();
                }
            }

        } else if (mit == 5) {
            if (ellenoriz(mit - 1)) {
                if (elemLehelyez(mit - 1)) {
                    itemElvesz(this.recept[mit - 1].getDeszkaDb(), this.recept[mit - 1].getLevelDb(), this.recept[mit - 1].getHulladekdb());
                    lep();
                }
            }

        } else {
            System.out.println("Nem létező elemet probáltál kraftolni.");
        }
    }

    private boolean ellenoriz(int idex) {
        int deszka = 0;
        int level = 0;
        int hulladek = 0;
        for (int i = 0; i < jatekos.getInventory().size(); i++) {
            if (jatekos.getInventory().get(i) instanceof Deszka) {
                deszka++;
            }
            if (jatekos.getInventory().get(i) instanceof Level) {
                level++;
            }
            if (jatekos.getInventory().get(i) instanceof Hulladek) {
                hulladek++;
            }
        }
        if (recept[idex].getDeszkaDb() <= deszka &&
                recept[idex].getLevelDb() <= level &&
                recept[idex].getHulladekdb() <= hulladek) {
            System.out.println("Van alapanyag. ");
            return true;
        } else {
            System.out.println("Nincs alapanyag. Nálad lévő eszközök ami lényeges deszka: " + deszka + "db, levél: " + level + "db, hulladék: " + hulladek + "db. ");
            return false;
        }

    }

    /**
     * elveszi azt az itemet az invektoriból abban a mennyiségben ami kell a krafthoz.
     *
     * @param deszka   megkapjuk a deszka értékét
     * @param level    megkapjuk a level értékét
     * @param hulladek megkapjuk a hulladek értékét
     */
    private void itemElvesz(int deszka, int level, int hulladek) {
        int dbDeszka = deszka;
        int dbLevel = level;
        int dbHulladek = hulladek;
        for (int j = 0; dbDeszka > 0 || dbLevel > 0 || dbHulladek > 0; j++) {
            for (int i = 0; i < jatekos.getInventory().size(); i++) {
                if (dbDeszka > 0 && jatekos.getInventory().get(i) instanceof Deszka) {
                    //System.out.println(jatekos.getInventory().get(i));
                    jatekos.getInventory().remove(i);
                    dbDeszka--;
                }
                if (dbLevel > 0 && jatekos.getInventory().get(i) instanceof Level) {
                    //System.out.println(jatekos.getInventory().get(i));
                    jatekos.getInventory().remove(i);
                    dbLevel--;
                }
                if (dbHulladek > 0 && jatekos.getInventory().get(i) instanceof Hulladek) {
                    //System.out.println(jatekos.getInventory().get(i));
                    jatekos.getInventory().remove(i);
                    dbHulladek--;
                }
            }
        }
    }

    /**
     * @param index kraft recepteknek az idexét kapjuk meg és erre tesztel a method
     * @return visszatér igazzal ha lehelyezte a térképre az itemet
     */
    private boolean elemLehelyez(int index) {
        int receptId = recept[index].getId();
        if (palyaElem[jatekos.getX()][jatekos.getY()] instanceof Tenger) {
            if (receptId == 2) {
                System.out.println("2");
                palyaElem[jatekos.getX()][jatekos.getY()] = new Platform();
                return true;
            } else if (receptId == 3) {
                System.out.println("3");
                palyaElem[jatekos.getX()][jatekos.getY()] = new Halo();
                return true;
            }
        } else if (palyaElem[jatekos.getX()][jatekos.getY()] instanceof Platform) {
            if (receptId == 5) {
                System.out.println("5");
                palyaElem[jatekos.getX()][jatekos.getY()] = new Tuzhely();
                return true;
            } else if (receptId == 4) {
                System.out.println("4");
                palyaElem[jatekos.getX()][jatekos.getY()] = new Viztisztito();
                return true;
            }
        }
        System.out.println("erre a pontra nem kraftolhatsz ilyen elemet.");
        return false;
    }

    /**
     * vizLep belső adat tagját lépteti ha le van helyezve, a térképre.
     */
    private void vizLep() {
        for (int i = 0; i < this.meretX; i++) {
            for (int j = 0; j < this.meretY; j++) {
                if (palyaElem[i][j] instanceof Viztisztito) {
                    ((Viztisztito) palyaElem[i][j]).szintNo();
                }
            }
        }


    }

    /**
     * sütő belső adat tagját lépteti ha le van helyezve, a térképre.
     */
    private void sutoLep() {
        for (int i = 0; i < this.meretX; i++) {
            for (int j = 0; j < this.meretY; j++) {
                if (palyaElem[i][j] instanceof Tuzhely) {
                    if (((Tuzhely) palyaElem[i][j]).isVanBenne()) {
                        ((Tuzhely) palyaElem[i][j]).szintNo();
                    }
                }
            }
        }


    }

    /**
     * elveszi az invektoriból az ételt amit meg sütsz és
     *
     * @return vissza tér igaz / hamis értékkel ha elvette az embertől az ételt
     */
    private boolean etelVan() {
        for (int i = 0; i < jatekos.getInventory().size(); i++) {
            if (jatekos.getInventory().get(i) instanceof Hal || jatekos.getInventory().get(i) instanceof Krumpli) {
                jatekos.getInventory().remove(i);
                return true;
            }

        }
        return false;
    }

    /**
     * @param a item amit el fog raktározni a player invektoriába
     * @param x az item x koordinátája
     * @param y az item y koordinátája
     * @return
     */
    private boolean hordoBegyujt(Item a, int x, int y) {
        if (a instanceof Hordo) {
            jatekos.invektoriAdd(((Hordo) a).getBelso());
            System.out.println("|item felvéve.|");
            palyaItem[x][y] = null;
            return false;
        } else {
            return true;
        }

    }

    /**
     * mentés methodussegitségével el lehet menteni a játék aktuális állását.
     *
     * @throws FileNotFoundException
     */
    public void mentes() throws FileNotFoundException {
        File f = new File("mentes.txt");
        PrintWriter ki = new PrintWriter(f);
        ki.println(this.meretX + ";" + this.meretY);
        ki.println(jatekos.getX() + ";" + jatekos.getX());
        ki.println(jatekos.getEhesseg() + ";" + jatekos.getSzomjusag() + ";" + this.lepesszam);
        ki.println(jatekos.getInventory());
        for (int i = 0; i < meretX; i++) {
            for (int j = 0; j < meretY; j++) {
                if (this.palyaElem[i][j] != null) {
                    ki.print("" + this.palyaElem[i][j].getId() + ";");
                } else {
                    ki.print("nullElem;");
                }
            }
            ki.println();
        }
        ki.println();
        for (int i = 0; i < meretX; i++) {
            for (int j = 0; j < meretY; j++) {
                if (this.palyaItem[i][j] != null) {
                    ki.print(this.palyaItem[i][j].getId() + ";");
                } else {
                    ki.print("nullItem;");
                }
            }
            ki.println();
        }


        ki.close();
        System.out.println("sikeresen mentettel!");
    }

}
