package item;

/**
 * Item egy ős a itemeknek, egységes adat tagok beállitásához, lekéréséhez.
 */
public class Item {
        private String name;
        private int id;

        public Item(String name, int id) {
            this.name = name;
            this.id = id;
        }

        @Override
        public String toString() {
            return " " + name + " ";
        }

    public int getId() {
        return id;
    }
}
