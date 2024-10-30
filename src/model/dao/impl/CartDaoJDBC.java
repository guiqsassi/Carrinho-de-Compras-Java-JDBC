package model.dao.impl;

import db.Db;
import model.dao.CartDao;
import model.entities.Cart;

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
            ps = conn.prepareStatement("SELECT * FROM cart WHERE id = ?");
            ps.setInt(1, id);

            rs = ps.executeQuery();

            Cart cart = new Cart();
            if(rs.next()) {
                cart = cartInstantiation(rs);
            }
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

}
