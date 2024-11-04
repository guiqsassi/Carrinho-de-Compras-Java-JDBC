package model.dao;

import model.entities.Cart;
import model.entities.CartItem;

import java.util.List;

public interface CartDao {

    void insert(Cart cart);
    void update(Cart cart);
    void deleteById(int id);
    Cart getById(int id);
    List<Cart> getAll();
    void insertItem( Cart cart,CartItem cartItem);
    void updateItem( Cart cart,CartItem cartItem);
    void removeItem( Cart cart,CartItem cartItem);
}
