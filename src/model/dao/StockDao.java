package model.dao;

import model.entities.Stock;

import java.util.List;

public interface StockDao {

    void insert(Stock stock);
    void update(Stock stock);
    void deleteById(Integer id);
    Stock findById(Integer id);
    List<Stock> findAll();


}
