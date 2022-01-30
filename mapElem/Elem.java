package mapElem;

/**
 * Elem egy ős a elemek, egységes adat tagok beállitásához, lekéréséhez.
 */
public class Elem {
private String name;
private int id;

    public Elem(String name,int id) {
        this.name=name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return " "+name+" ";
    }
}
