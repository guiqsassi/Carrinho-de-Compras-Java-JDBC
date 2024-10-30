package model.dao.impl;

import db.Db;
import model.dao.CartDao;
import model.entities.Cart;
import model.entities.CartItem;
import model.entities.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CartDaoJDBC implements CartDao {

    Connection conn;

    public CartDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Cart cart) {

    }

    @Override
    public void update(Cart cart) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public Cart getById(int id) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            ps = conn.prepareStatement("SELECT cart.id, cart.quantity, cart.total_value ,cart_item.quantity as \"item_quantity\", stock.name, stock.value, stock.quantity AS \"stQuantity\"," +
                    " stock.id AS \"stId\" FROM cart inner join cart_item \n" +
                    "ON cart.id = cart_item.cartId\n" +
                    "INNER JOIN stock\n" +
                    "ON cart_item.stockId = stock.id" +
                    " WHERE cart.id = ?");

            ps.setInt(1, id);
            rs = ps.executeQuery();

            Cart cart = new Cart();

            if(rs.next()) {
                cart = cartInstantiation(rs);
            }

            do{
                CartItem cartItem = cartItemInstantiation(rs);
                cart.addItem(cartItem);
            }
            while (rs.next());

            return cart;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Db.closeResultSet(rs);
            Db.closeStatement(ps);
        }

    }

    @Override
    public List<Cart> getAll() {
        return List.of();
    }

    public Cart cartInstantiation(ResultSet rs){
        Cart cart = new Cart();
        try {
            cart.setId(rs.getInt("id"));
            cart.setQuantity(rs.getInt("quantity"));
            cart.setTotalValue(rs.getDouble("total_value"));

            return cart;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public CartItem cartItemInstantiation(ResultSet rs){
        CartItem cartItem = new CartItem();
        Stock stock = new Stock();
        try {
            cartItem.setId(rs.getInt("id"));
            cartItem.setQuantity(rs.getInt("item_quantity"));
            stock.setId(rs.getInt("stId"));
            stock.setQuantity(rs.getInt("stQuantity"));
            stock.setPrice(rs.getDouble("value"));
            stock.setName(rs.getString("name"));
            cartItem.setStock(stock);

            return cartItem;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
