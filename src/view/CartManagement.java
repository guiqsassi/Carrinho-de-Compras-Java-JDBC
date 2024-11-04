package view;

import db.Db;
import model.dao.CartDao;
import model.dao.DaoFactory;
import model.dao.StockDao;
import model.dao.impl.CartDaoJDBC;
import model.dao.impl.StockDaoJDBC;
import model.entities.Cart;
import model.entities.CartItem;
import model.entities.Stock;

import java.util.List;
import java.util.Scanner;

public class CartManagement {

    public static void insertCartItem(int id){

        StockDao stockDaoJDBC = new DaoFactory().createStockDao();
        CartDao cartDaoJDBC = new DaoFactory().createCartDao();
        Scanner sc = new Scanner(System.in);
        List<Stock> stocks = stockDaoJDBC.findAll();

        if(stocks.size()== 0){
            System.out.println("Unfortunally we don't have any items in stock");

        }else{



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

    }

    public static void deleteCartItem(int id){
        CartDao cartDaoJDBC = new DaoFactory().createCartDao();
        Scanner sc = new Scanner(System.in);
        int itemId;
        Cart cart = cartDaoJDBC.getById(id);

        if(cart.getItems().size() == 0){
            System.out.println("This cart is empty");
        }else{

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
        }else {
            cartDaoJDBC.removeItem(cart, selectedCartItem);
            System.out.println("Item successfully removed");
        }
        }
    }
    public static void showItems(int id){
        CartDao cartDaoJDBC = new DaoFactory().createCartDao();

        Cart cart = cartDaoJDBC.getById(id);
        if(cart.getItems().size() == 0){
            System.out.println("This cart is empty");
        }else {

            System.out.println("Total quantity of items: " + cart.getQuantity() + " Cart total price:: " + cart.getTotalValue() + "\n");

            System.out.println("Itens: ");
            cart.getItems().forEach(System.out::println);
        }
    }
    public static void updateCartItem(int id){
        CartDao cartDaoJDBC = new DaoFactory().createCartDao();
        Scanner sc = new Scanner(System.in);
        int itemId;
        Cart cart = cartDaoJDBC.getById(id);
        if(cart.getItems().size() == 0){
            System.out.println("This cart is empty");
        }else {

            System.out.println("Choose one item from bellow: \n");
            cart.getItems().forEach(System.out::println);
            itemId = sc.nextInt();
            CartItem selectedCartItem = new CartItem();

            for (CartItem cartItem : cart.getItems()) {
                if (cartItem.getId() == itemId) {
                    selectedCartItem = cartItem;
                }
            }

            cart.setQuantity(cart.getQuantity() - selectedCartItem.getQuantity());
            cart.setTotalValue(cart.getTotalValue() - selectedCartItem.subTotal());

            System.out.println("Choose a new quantity for this item:");
            selectedCartItem.setQuantity(sc.nextInt());

            cart.setQuantity(cart.getQuantity() + selectedCartItem.getQuantity());
            cart.setTotalValue(cart.getTotalValue() + selectedCartItem.subTotal());

            cartDaoJDBC.updateItem(cart, selectedCartItem);

            System.out.println("Item sucessfully updated");
        }

    }

    public static void newClient(){
        CartDao cartDaoJDBC = new DaoFactory().createCartDao();
        Scanner sc = new Scanner(System.in);


        CartItem cartItem = new CartItem();
        Cart cart = new Cart();


        cartDaoJDBC.insert(cart);

        System.out.println("Your new Cart id is: " + cart.getId());

    }

}
