package view;

import db.Db;
import model.dao.impl.StockDaoJDBC;
import model.entities.Stock;

import java.util.List;
import java.util.Scanner;

public class StockManagement {

    public static void listItems(){
        StockDaoJDBC stockDaoJDBC = new StockDaoJDBC(Db.getConnection());

        List<Stock> stocks = stockDaoJDBC.findAll();

        stocks.forEach(stock -> System.out.println( stock.toString()));
    }

    public static void addStock(){
        Scanner sc = new Scanner(System.in);
        StockDaoJDBC stockDaoJDBC = new StockDaoJDBC(Db.getConnection());

        Stock stock = new Stock();
        System.out.println("Write the product´s name: ");
        stock.setName(sc.nextLine());
        System.out.println("Write the product's stock quantity.");
        stock.setQuantity(sc.nextInt());
        System.out.println(" Write the product's price.");
        stock.setPrice(sc.nextDouble());
        System.out.println(" Write the product's category.");
        sc.nextLine();
        stock.setCategory(sc.nextLine());

        stockDaoJDBC.insert(stock);


    }

    public static void deleteItem(){
        Scanner sc = new Scanner(System.in);
        StockDaoJDBC stockDaoJDBC = new StockDaoJDBC(Db.getConnection());

        int id ;
        List<Stock> stocks = stockDaoJDBC.findAll();
        stocks.forEach(stock -> System.out.println( stock.toString()));
        System.out.println("Choose the product´s id to be deleted: ");
        id = sc.nextInt();

        stockDaoJDBC.deleteById(id);
        System.out.println("Item deleted");



    }



}
