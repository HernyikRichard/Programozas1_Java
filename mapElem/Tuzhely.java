package mapElem;
/**
 * Tuzhely osztály egy Elem osztály leszármazotja.
 * lénygi információk az osztályban beállitásra kerülnek.
 */
public class Tuzhely extends Elem{
    private boolean vanBenne;
    private int szint;
    private boolean eheto;

    public Tuzhely() {
        super("¶", 5);
        szint = 0;
        vanBenne = false;
        eheto = false;
    }

    /**
     * vanBenne adat tagott állitja igazra.
     */
    public void beleTesz(){
        vanBenne = true;
    }

    public boolean isVanBenne() {
        return vanBenne;
    }


    public boolean isEheto() {
        return eheto;
    }

    /**
     * a szint adat tag értékés nőveli eggyel annak fügvényében amennyibe vanBenne igaz.
     * és ha a szint adattag eléri 25os szintett abban az esetbe eheto adattag igaz értékre állitja be.
     */
    public void szintNo(){
        if (vanBenne&&!eheto){
            szint+=1;
        }
        if (szint==25){
            eheto=true;
        }
    }

    /**
     * szint adat tagot 0 értére állitja be
     * eheto hamis értére állitja be
     * vanBenne hamis értére állitja be
     */
    public void urit(){
        szint=0;
        eheto=false;
        vanBenne=false;
    }
}
