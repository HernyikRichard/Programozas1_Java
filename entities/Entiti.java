package entities;

/**
 * az Entiti egy ős osztály ami a Player és a Shark osztályokat késziti elő.
 * mivel mind a két leszármazott hasonló képpen müködik.
 *
 */
public class Entiti {
    private int x;
    private int y;

    public Entiti(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public void setX(int x, int mMeretX) {
        if (ellenorizX(mMeretX, x)){
            this.x = x;
        }else {
            System.out.println("nem helyezhető ide a jatekos x: "+x);
        }
    }

    /**
     *
     * @param x aktuálisan be állitandó x értéke a Entiti-nek
     * @param mMeretX a pályának a maximum mérete x koordináta szerint
     * @return igazat add vissza abban az esetbe ha sikeresen modosult a játékos x kordinátája, hamisat ha nem legetséges a be állitás.
     */
    public boolean mozogX(int x, int mMeretX) {
        if (ellenorizX(mMeretX, x)){
            this.x = x;
            return true;
        }else {
            System.out.println("Nem léphetsz ide ki mész a pályáról! x");
            return false;
        }
    }


    public int getY() {
        return y;
    }


    /**
     *
     * @param y aktuálisan be állitandó y értéke a Entiti-nek
     * @param mMeretY a pályának a maximum mérete
     */
    public void setY(int y, int mMeretY) {
        if (ellenorizY(mMeretY, y)){
            this.y = y;
        }else {
            System.out.println("nem helyezhető ide a jatekos y: "+y);
        }
    }

    /**
     *
     * @param y aktuálisan be állitandó y értéke a Entiti-nek
     * @param mMeretY a pályának a maximum mérete y koordináta szerint
     * @return igazat add vissza abban az esetbe ha sikeresen modosult a játékos x kordinátája, hamisat ha nem legetséges a be állitás.
     */
    public boolean mozogY(int y, int mMeretY) {
        if (ellenorizY(mMeretY, y)){
            this.y = y;
            return true;
        }else {
            System.out.println("Nem léphetsz ide ki mész a pályáról!: "+y);
            return false;
        }
    }

    /**
     *
     * @param mMeretX a pálya maxximális x értékét reprezentálja
     * @param xMozgatott a mozgási nagyság mérete x koordináta szerint
     * @return igaza ha még belefér a pályába a lépés, hamisat ha nem
     */
    private boolean ellenorizX(int mMeretX, int xMozgatott){
        return xMozgatott > -1 && xMozgatott < mMeretX;
    }

    /**
     *
     * @param mMeretY a pálya maxximális y értékét reprezentálja
     * @param yMozgatott a mozgási nagyság mérete y koordináta szerint
     * @return igaza ha még belefér a pályába a lépés, hamisat ha nem
     */
    private boolean ellenorizY(int mMeretY, int yMozgatott){
        return yMozgatott > -1 && yMozgatott < mMeretY ;
    }




}
