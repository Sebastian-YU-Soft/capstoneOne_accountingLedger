package com.ps;

import java.util.Scanner;

public class personalLedger {
    public static final String CSV_FILE = "personal_transaction.csv";
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        ensureCSVFileExists();
        mainMenu();
    }

    public static void mainMenu(){
        boolean running = true;
        while (running){
            System.out.println("Home Screen");
            System.out.println("1. Add Deposit");
            System.out.println("2. Make Payment (DEBIT)");
            System.out.println("3. Ledger");
            System.out.println("4. Exit");
            System.out.println("Please enter your choice:");
            String choice = scanner.nextLine().trim().toUpperCase();
        }
    }
}
