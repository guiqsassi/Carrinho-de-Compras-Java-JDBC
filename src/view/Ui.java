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
                case 2: break;

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
                case 4: StockManagement.listItems(); break;

            }

        }


    }


}
