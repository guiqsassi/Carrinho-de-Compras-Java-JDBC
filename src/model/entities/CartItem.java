package model.entities;

import java.util.Objects;

public class CartItem {

    private Integer id;
    private Stock stock;
    private Integer quantity;

    public CartItem(Integer id, Stock stock, Integer quantity) {
        this.id = id;
        this.stock = stock;
        this.quantity = quantity;
    }

    public CartItem() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(id, cartItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public Double subTotal() {
        return quantity * getStock().getPrice();
    }

    @Override
    public String toString() {
        return
                "id: " + id +", Item:" + stock.getName() +", quantity:" + quantity + ", price: " + subTotal();
    }
}
