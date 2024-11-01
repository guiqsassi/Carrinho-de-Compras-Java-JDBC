package view;

import db.Db;
import model.dao.impl.CartDaoJDBC;
import model.dao.impl.StockDaoJDBC;
import model.entities.Cart;
import model.entities.CartItem;
import model.entities.Stock;

import java.util.List;
import java.util.Scanner;

public class CartManagement {

    public static void inserCartItem(int id){

        StockDaoJDBC stockDaoJDBC = new StockDaoJDBC(Db.getConnection());
        CartDaoJDBC cartDaoJDBC = new CartDaoJDBC(Db.getConnection());
        Scanner sc = new Scanner(System.in);
        List<Stock> stocks = stockDaoJDBC.findAll();


        CartItem cartItem = new CartItem();
        Cart cart = cartDaoJDBC.getById(id);

        System.out.println("Choose one item from bellow: \n");
        System.out.println(stocks);

        int stockId = sc.nextInt();

        for (Stock stock : stocks) {
            if(stock.getId() == stockId){
                cartItem.setStock(stock);
            }
        }
        System.out.println("Enter the quantity: ");
        cartItem.setQuantity(sc.nextInt());


        cartDaoJDBC.insertItem(cart, cartItem);


    }

    public static void deleteCartItem(int id){
        CartDaoJDBC cartDaoJDBC = new CartDaoJDBC(Db.getConnection());
        Scanner sc = new Scanner(System.in);
        int itemId;
        Cart cart = cartDaoJDBC.getById(id);

        System.out.println("Choose one item from bellow: \n");
        cart.getItems().forEach(System.out::println);
        itemId = sc.nextInt();
        CartItem selectedCartItem = new CartItem();

        for (CartItem cartItem : cart.getItems()) {
            if(cartItem.getId() == itemId){
                selectedCartItem = cartItem;
            }
        }

        if(selectedCartItem.getId() == null){
            System.out.println("Item not found");

            throw new RuntimeException("Item not found");
        }
        cartDaoJDBC.removeItem(cart, selectedCartItem);
        System.out.println("Item removido com sucesso");

    }

}
