package model.dao;

import model.entities.CartItem;

import java.util.List;

public interface CartItemDao {

    void insert(CartItem cartItem);
    void update(CartItem cartItem);
    void deleteById(int id);
    CartItem findById(int id);
    List<CartItem> findByCartId();
}
