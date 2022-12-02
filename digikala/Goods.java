public class Goods {
    private int id;
    private String name;
    private String expirDate;
    private double price;
    private int quantity;

    public Goods(int id, String name, String expirDate, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.expirDate = expirDate;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpirDate() {
        return expirDate;
    }

    public void setExpirDate(String expirDate) {
        this.expirDate = expirDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Goods{" + "id=" + id + ", name=" + name + ", expirDate=" + expirDate + ", price=" + price + ", quantity=" + quantity + '}';
    }
}