package model.entities;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer quantity;
    private Double totalValue;
    private List<CartItem> items = new ArrayList<>();

    public Cart() {}

    public Cart(int id, int quantity, double totalValue) {
        this.id = id;
        this.quantity = quantity;
        this.totalValue = totalValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public void addItem(CartItem item) {
        items.add(item);

        int quantity = 0;
        double totalValue =0;

        for (CartItem aux : items) {
            quantity += aux.getQuantity();
            totalValue += aux.subTotal();
        }

        this.quantity = quantity;
        this.totalValue = totalValue;

    }

    public void updateItem(CartItem item) {

        items.set(items.indexOf(item), item);
        int quantity = 0;
        double totalValue =0;

        for (CartItem aux : items) {
            quantity += aux.getQuantity();
            totalValue += aux.subTotal();
        }

        this.quantity = quantity;
        this.totalValue = totalValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }



}
