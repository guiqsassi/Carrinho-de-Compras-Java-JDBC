package model.dao.impl;

import db.Db;
import exception.StockException;
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
    public void insert(Cart cart, CartItem cartItem) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        StockDaoJDBC stockDao = new StockDaoJDBC(conn);

        if(cartItem.getStock().getQuantity() < cartItem.getQuantity()) {
            throw new StockException("The requested quantity exceeds the available stock.");
        }

        try {
            conn.setAutoCommit(false);

            //Cria um carrinho e retorna o id
            ps = conn.prepareStatement("INSERT INTO cart (quantity, total_value) VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, cartItem.getQuantity());
            ps.setDouble(2, cartItem.subTotal());


            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.first();
            int id = (rs.getInt(1));

            if(id != 0) {
                id = rs.getInt(1);
                cart.setId(id);

                //Inseri os itens iniciais no carrinho
                ps = conn.prepareStatement("INSERT INTO cart_item (cartId, stockId, quantity) VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setInt(1, id);
                ps.setInt(2,cartItem.getStock().getId());
                ps.setInt(3,cartItem.getQuantity());

                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                rs.first();

                cartItem.setId(rs.getInt(1));

                Stock stock = cartItem.getStock();
                stock.setQuantity(stock.getQuantity() - cartItem.getQuantity());
                stockDao.update(stock);

            }


            conn.commit();
            conn.setAutoCommit(true);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Cart cart) {

        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement("UPDATE cart SET total_value = ?, " + "quantity = ? WHERE id = ?");
            ps.setDouble(1, cart.getTotalValue());
            ps.setInt(2, cart.getQuantity());
            ps.setInt(3, cart.getId());

            ps.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Db.closeStatement(ps);
        }

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
