/*
 * buy class:
 * date;
 * User object;
 * Goods object;
 * quantity;
 */
public class Buy {
    private String date;
    private User user;
    private Goods goods;
    private int quantity;

    public Buy(String date, User user, Goods goods, int quantity) {
        this.date = date;
        this.user = user;
        this.goods = goods;
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}