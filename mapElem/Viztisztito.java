package mapElem;

public class Viztisztito extends Elem{
    private int szint;
    private boolean ihato;

    public Viztisztito() {
        super("•", 4);
        szint=0;
        ihato=false;
    }

    /**
     * növeli a belső szint adattag értékét +1-el és a ihato értékét be állitja true-ra ha szint eléri a 25 -öt.
     */
    public void szintNo(){
        szint+=1;
        if (szint==25&&!true){
            ihato=true;
        }
    }
    /**
     * szint adat tagot 0 értére állitja be
     * eheto hamis értére állitja be
     * vanBenne hamis értére állitja be
     */
    public void urit(){
        szint=0;
        ihato=false;
    }

    /**
     * visszatérési methodus
     * @return értéke igaz lesz ha szint legalább 25 egyébként false-értéket kapunk.
     */
    public boolean ihato() {
        if (szint>=25) {
            return true;
        }else {
            return false;
        }
    }
}
