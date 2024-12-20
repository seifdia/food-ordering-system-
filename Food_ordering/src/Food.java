import java.io.Serializable;

public class Food implements Serializable {
    String name;
    String type;
    double price;

    Food(String name, String type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }
    public String getName(){
        return name;
    }
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " (" + type + ") - " + price + " EGP";
    }
}
