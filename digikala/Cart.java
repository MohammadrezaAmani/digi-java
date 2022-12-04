import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int owner_id;
    private List<Goods> goodsList = new ArrayList<>();
    private double totalPrice;
    public Cart(int owner_id, Goods goods) {
        this.owner_id = owner_id;
        this.goodsList.add(goods);
    }
    public void set_owner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public int get_owner_id() {
        return owner_id;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void add(Goods goods) {
        goodsList.add(goods);
    }

    public void remove(Goods goods) {
        goodsList.remove(goods);
    }

    public void clear() {
        goodsList.clear();
    }

    public void calculateTotalPrice() {
        double sum = 0;
        for (Goods goods : goodsList) {
            sum += goods.getPrice();
        }
        totalPrice = sum;
    }

    public void print() {
        for (Goods goods : goodsList) {
            System.out.println(goods);
        }
    }
}