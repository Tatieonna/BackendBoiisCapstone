package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;

public class Ledger {  // My wrapper around an ArrayList of Transaction objects

    private ArrayList<Transaction> transactions;

    public Ledger() {
        this.transactions = new ArrayList<Transaction>();

    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void readFromCSV() {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/transactions.csv");
            Scanner scanner = new Scanner(fis);
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String[] line = input.split("\\|");
                LocalDate date = LocalDate.parse(line[0]);
                LocalTime time = LocalTime.parse(line[1]);
                String description = line[2];
                String vendor = line[3];
                Double amount = Double.parseDouble(line[4]);
                Transaction transaction = new Transaction(date, time, description, vendor, amount);
                transactions.add(transaction);
                System.out.println(transaction);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Uh oh - something went wrong! :( ");

        }
    }

    public void displayAllTransactions() { //my methods that I used to help me look for specific transactions
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public void displayAllDeposits() {
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > 0) {
                System.out.println(transaction);

            }
        }

    }

    public void displayAllPayments() {
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                System.out.println(transaction);
            }
        }
    }

    public void displayMonthToDate() {
        Month thisMonth = LocalDate.now().getMonth();
        for (Transaction transaction : transactions) {
            Month transactionMonth = transaction.getDate().getMonth();
            if (transactionMonth == thisMonth) {
                System.out.println(transaction);
            }
        }
    }

    public void displayLastMonth() {
        Month lastMonth = LocalDate.now().getMonth().minus(1);
        for (Transaction transaction : transactions) {
            Month transactionMonth = transaction.getDate().getMonth();
            if (transactionMonth == lastMonth) {
                System.out.println(transaction);
            }
        }
    }

    public void displayYearToDate() {
        int thisYear = LocalDate.now().getYear();
        for (Transaction transaction : transactions) {
            int transactionYear = transaction.getDate().getYear();
            if (transactionYear == thisYear) {
                System.out.println(transaction);
            }
        }
    }

    public void displayLastYear() {
        int lastYear = LocalDate.now().getYear() - 1;
        for (Transaction transaction : transactions) {
            int transactionYear = transaction.getDate().getYear();
            if (transactionYear == lastYear) {
                System.out.println(transaction);
            }
        }
    }

    public void searchByVendor(String vendor){
        for (Transaction transaction : transactions) {
            if (transaction.getVendor().equalsIgnoreCase(vendor)) {
                System.out.println(transaction);
            }
        }
    }
}