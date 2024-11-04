package model.dao;

import db.Db;
import model.dao.impl.CartDaoJDBC;
import model.dao.impl.StockDaoJDBC;

public class DaoFactory {

    public CartDaoJDBC createCartDao(){
        return new CartDaoJDBC(Db.getConnection());
    }

    public StockDaoJDBC createStockDao(){
        return new StockDaoJDBC(Db.getConnection());
    }
}
