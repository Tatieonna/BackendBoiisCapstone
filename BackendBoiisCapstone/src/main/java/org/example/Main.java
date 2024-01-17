package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    //I made this public static outside of main because I'm using it in all methods and not just in main.
    public static Ledger ledger= new Ledger();
    public static void main(String[] args) {
        ledger.readFromCSV(); //the single piece of code I was missing
        mainMenu();
        // turned all my menus into helper methods to keep main clean and readable
    }
        public static void mainMenu(){
            while(true) {

                Scanner scanner = new Scanner(System.in);

                System.out.println("Home screen: ");
                System.out.println("D): Make a deposit");
                System.out.println("P): Make a payment");
                System.out.println("L): See ledger");
                System.out.println("X): Exit application");


                String userInput = scanner.nextLine();
                switch (userInput.toUpperCase()) {
                    case "D":
                        depositMenu();
                        break;
                    case "P":
                        paymentMenu();
                        break;
                    case "L":
                        ledgerMenu();
                        break;
                    case "X":
                        System.out.println("Thank you!");
                        break;

                    default:
                        System.out.println("Please pick from our menu items! ");
                }
            }
        }
       public static void depositMenu(){
        try{
           Scanner scanner = new Scanner(System.in);
           System.out.println("Enter description of deposit: ");
           String description = scanner.nextLine();
           System.out.println("Enter vendor name: ");
           String vendor = scanner.nextLine();
           System.out.println("Enter amount: ");
           double amount = scanner.nextDouble();
           LocalDate today = LocalDate.now();  //makes it so the user does not have to manually enter their date and time
           LocalTime nowTime = LocalTime.now();

           Transaction deposit = new Transaction(today,nowTime,description,vendor,amount);
           ledger.addTransaction(deposit);


           System.out.println("deposit information: "+ today +"|" + nowTime + "|" + description + "|" + vendor + "|" + amount);

               FileWriter fileWriter = new FileWriter("src/main/resources/transactions.csv", true);
               fileWriter.write(today + "|" + nowTime +"|" + description + "|" + vendor + "|" + amount + "\n");
               fileWriter.close();
           } catch(IOException exception){


           }

       }
       public static void paymentMenu(){
           try{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter description of payment: ");
        String description = scanner.nextLine();
        System.out.println("Enter vendor name: ");
        String vendor = scanner.nextLine();
        System.out.println("Enter amount: ");
        double amount = scanner.nextDouble();
        amount= -amount;

           LocalDate today = LocalDate.now();
           LocalTime nowTime = LocalTime.now();

               Transaction payment = new Transaction(today,nowTime,description,vendor,amount);
               ledger.addTransaction(payment);

           System.out.println("deposit information: "+ today +"|" + nowTime + "|" + description + "|" + vendor + "|" + amount);
           FileWriter fileWriter = new FileWriter("src/main/resources/transactions.csv", true);
           fileWriter.write(today + "|" + nowTime +"|" + description + "|" + vendor + "|" + amount + "\n");
           fileWriter.close();
       } catch(IOException exception){


    }
    }
        public static void ledgerMenu(){
        while(true) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Your Ledger ");
            System.out.println("A): Display all entries ");
            System.out.println("D): Display deposits");
            System.out.println("P): Display payments");
            System.out.println("R): Reports");

            String userInput = scanner.nextLine();
            switch (userInput.toUpperCase()) {
                case "A":
                    ledger.displayAllTransactions();
                    break;
                case "D":
                    ledger.displayAllDeposits();
                    break;
                case "P":
                    ledger.displayAllPayments();
                    break;
                case "R":
                    reportsMenu();
                    break;

            }
        }
    }
        public static void reportsMenu(){
        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Reports: ");
            System.out.println("1) Month to date");
            System.out.println("2) Previous month ");
            System.out.println("3) Year to date ");
            System.out.println("4) Previous year ");
            System.out.println("5) Search by vendor ");
            System.out.println("0) Back ");
            System.out.println("9) Home ");

            int userInput= scanner.nextInt();
            switch (userInput) {
                case 1:
                    ledger.displayMonthToDate();
                    break;
                case 2:
                    ledger.displayLastMonth();
                    break;
                case 3:
                    ledger.displayYearToDate();
                    break;
                case 4:
                    ledger.displayLastYear();
                    break;
                case 5:
                    scanner.nextLine();

                    System.out.println("Enter name of vendor: ");
                    String vendor = scanner.nextLine();

                    ledger.searchByVendor(vendor);
                    break;
                case 0:
                    ledgerMenu();
                    break;
                case 9:
                    mainMenu();
                    break;

            }
        }
        }
}


