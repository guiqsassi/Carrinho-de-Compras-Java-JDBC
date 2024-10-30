package model.dao.impl;

import exception.DbException;
import model.dao.StockDao;
import model.entities.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDaoJDBC implements StockDao {

    private Connection con;

    public StockDaoJDBC(Connection con) {
        this.con = con;
    }

    @Override
    public void insert(Stock stock) {
        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO stock " +
                    "(name, category, value, quantity)" +
                    " VALUES (?, ?, ?, ?)" , PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, stock.getName());
            ps.setString(2, stock.getCategory().toUpperCase());
            ps.setDouble(3, stock.getPrice());
            ps.setInt(4, stock.getQuantity());

            ps.execute();
            ResultSet id = ps.getGeneratedKeys();
            if(id.next()){
                stock.setId(id.getInt(1));
            }

            ps.close();
            id.close();
        } catch (SQLException e) {
            throw new DbException("Insertion failed: " +e.getMessage());
        }
    }

    @Override
    public void update(Stock stock) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Stock findById(Integer id) {
        try{
            PreparedStatement ps = con.prepareStatement(" SELECT id, name, value, quantity, category" + " FROM stock " + " WHERE id = ?" );

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            Stock stock = new Stock();
            if(rs.next()){
                stock = stockInstantialize(rs);
            }
            return stock;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Stock> findAll() {
        try{
            PreparedStatement ps = con.prepareStatement("SELECT id, name, category, value, quantity FROM stock");
            ResultSet rs = ps.executeQuery();
            List<Stock> stocks = new ArrayList<>();

            while (rs.next()) {
                stocks.add(stockInstantialize(rs));
            }

            return stocks;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Stock stockInstantialize(ResultSet rs) {
        Stock stock = new Stock();
        try {
            stock.setId(rs.getInt("id"));
            stock.setName(rs.getString("name"));
            stock.setCategory(rs.getString("category"));
            stock.setPrice(rs.getDouble("value"));
            stock.setQuantity(rs.getInt("quantity"));
            return stock;
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
