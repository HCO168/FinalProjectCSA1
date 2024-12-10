public class Item extends Entity {
    public int quantity;
    public double weight;

    public Item(String name, int quantity, double weight) {
        super(name, "Item");
        this.quantity = quantity;
        this.weight = weight;
    }

    public void update() {

    }

    public double weight() {
        return weight;
    }

    public int quantity() {
        return quantity;
    }

    public void quantity(int quantity) {
        this.quantity = quantity;
    }

    public void weight(double weight) {
        this.weight = weight;
    }
}
