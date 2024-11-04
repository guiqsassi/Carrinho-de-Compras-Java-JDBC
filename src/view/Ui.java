package view;

import db.Db;
import model.dao.StockDao;
import model.dao.impl.StockDaoJDBC;
import model.entities.Stock;

import java.util.List;
import java.util.Scanner;

public class Ui {

    public static void main(){
        Scanner sc = new Scanner(System.in);
        int userOptions = 5;

        while(userOptions != 0){

            System.out.println("Welcome to our store, select your option by writing a number: ");

            System.out.println("\n1) Product Management");
            System.out.println("\n2) Shopping");

            System.out.println("\n0) Exit");

            userOptions = sc.nextInt();

            switch(userOptions){
                case 1: stockManagement(userOptions, sc); break;
                case 2: shopping(userOptions, sc); break;

            }

        }

    }

    private static void stockManagement(int userOption, Scanner sc){

        while (userOption != 0){

            System.out.println("\n1) Insert a new item");
            System.out.println("2) Remove a item");
            System.out.println("3) Update a item information");
            System.out.println("4) Display all items");
            System.out.println("0) exit");


            userOption = sc.nextInt();


            switch(userOption){
                case 1: StockManagement.addStock(); break;
                case 2: StockManagement.deleteItem(); break;
                case 3: StockManagement.updateItem(); break;
                case 4: StockManagement.listItems(); break;

            }

        }


    }
    private static void shopping(int userOption, Scanner sc){
        while (userOption != 0){
            System.out.println("Welcome to our store, select your option by writing a number");
            System.out.println("\n1) Manage my cart");
            System.out.println("2) New client");
            userOption = sc.nextInt();
            switch (userOption){
                case 1: CartManagement(userOption, sc); break;
                case 2: CartManagement.newClient(); break;
            }
        }
    }

    private static void CartManagement(int userOption, Scanner sc){
        int cartId;

        System.out.println("Write your cart id: ");
        cartId = sc.nextInt();

        while (userOption != 0){



            System.out.println("\n1) Add item to my cart");
            System.out.println("2) Display my cart information");
            System.out.println("3) Remove an item from my cart");
            System.out.println("4) Update an item in my cart");
            System.out.println("0) exit");

            userOption = sc.nextInt();
            switch (userOption){
                case 1: CartManagement.inserCartItem(cartId); break;
                case 2: CartManagement.showItems(cartId); break;
                case 3: CartManagement.deleteCartItem(cartId); break;
                case 4: CartManagement.updateCartItem(cartId); break;
             }

        }
    }

}
