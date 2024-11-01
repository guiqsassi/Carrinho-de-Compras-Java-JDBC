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

    public static void updateItem(){
        Scanner sc = new Scanner(System.in);
        StockDaoJDBC stockDaoJDBC = new StockDaoJDBC(Db.getConnection());
        List<Stock> stocks = stockDaoJDBC.findAll();
        stocks.forEach(stock -> System.out.println( stock.toString()));

        Stock aux = new Stock();
        int id;

        System.out.println("Choose the product´s id to be updated: ");
        id = sc.nextInt();

        for (Stock stock : stocks) {
            if (stock.getId() == id) {
                aux = stock;
            }
        }


        System.out.println("Write the product´s name (if you dont want to update, just leave it blank ): ");
        sc.nextLine();
        String name = sc.nextLine();
        if (!name.trim().isBlank()){
        aux.setName(name);
        }
        System.out.println("Write the product's stock quantity. (if you dont want to update, just leave it blank): ");
        String quantity = sc.nextLine();
        if(!quantity.trim().isBlank()){
            aux.setQuantity(Integer.parseInt(quantity));
        }
        System.out.println(" Write the product's price. (if you dont want to update, just leave it blank): ");
        String price = sc.nextLine();
        if(!price.trim().isBlank()){
            aux.setPrice(Double.parseDouble(price));
        }
        System.out.println(" Write the product's category. (if you dont want to update, just leave it blank): ");
        String category = sc.nextLine();
        if(!category.trim().isBlank()){
            aux.setCategory(category);

        }

        stockDaoJDBC.update(aux);


    }

}
