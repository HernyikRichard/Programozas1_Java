import additional.Szovegek;

import java.util.Scanner;

public class Main {
    public static Palya map = new Palya(25, 35, 1000);
    public static Szovegek miketLehet = new Szovegek();

    public static void main(String[] args) {
        init();
    }

    /**
     * választás aján fel a játékosnak.
     */
    public static void init() {
        System.out.println(miketLehet.getKezdoSzoveg());
        Scanner jatekBetoltVagyNem = new Scanner(System.in);
        try {
            int tipe = jatekBetoltVagyNem.nextInt();
            if (tipe == 2) {
                System.out.println("még nincs kész probálj mást.");
                init();
            }
            if (tipe == 1) {
                while (true) {
                    jatek();
                }
            }
        } catch (Exception c) {
            System.out.println("hibás bemenetet adtál meg!");
            init();
        }
    }

    /**
     * inivializája a játékot
     */
    private static void jatek() {
        Scanner be = new Scanner(System.in);
        System.out.println(miketLehet.getOptions());
        String mit = be.nextLine();
        if (mit.equals("mozog")) {
            mozog();
        }
        if (mit.equals("kilepes")) {
            System.out.println("köszi a játékot!");
            System.exit(1);
        }
        if (mit.equals("palya")) {
            System.out.println(miketLehet.getPalyaElemek());
            System.out.println(map);
        }
        if (mit.equals("info")) {
            map.infoPlayer();
        }
        if (mit.equals("horgasz")) {
            map.horgaszik();
        }
        if (mit.equals("kraft")) {
            kraft();
        }
        if (mit.equals("sut")) {
            map.beteszEtel();
        }
        if (mit.equals("eszik")) {
            map.eszik();
        }
        if (mit.equals("iszik")) {
            map.iszik();
        }
        if (mit.equals("mentes")) {
            mentes();
        }

    }

    /**
     * meghívja a kraftol methodusát a palyanak
     */
    private static void kraft() {
        System.out.println(miketLehet.getKraftok());
        try {
            Scanner be = new Scanner(System.in);
            System.out.print("add meg a mit szeretnél kraftolni(sorszámát)");
            int mit = be.nextInt();
            map.kroftol(mit);

        } catch (Exception ignored) {
            System.out.println("Hibás bemeneti értéket adtál meg.");
            kraft();
        }
    }

    /**
     * meghívja a emberMozgat methodusát a palyanak ha megfelelő irányt kap
     */
    public static void mozog() {
        System.out.println(miketLehet.getIranyitas());

        try {
            Scanner be = new Scanner(System.in);
            System.out.println("add meg a mozgas iranyt");
            int irany = be.nextInt();
            map.emberMozgat(irany);

        } catch (Exception ignored) {
            System.out.println("Hibás bemeneti értéket adtál meg. ");
            mozog();
        }
    }

    /**
     * meghívja a palya mentes methodusát.
     */
    public static void mentes() {
        try {
            map.mentes();
        } catch (Exception e) {
            System.out.println("valami hiba történt a mentéskor " + e);
        }
    }
}
