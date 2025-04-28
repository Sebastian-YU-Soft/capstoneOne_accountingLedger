package com.ps;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class personalLedger {
    public static final String CSV_FILE = "personal_transaction.csv";
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        ensureCSVFileExists();
        mainMenu();
    }

    private static void ensureCSVFileExists(){
        try {
            File file = new File(CSV_FILE);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Creating A CSV file:"+ e.getMessage());
        }
    }

    private static void mainMenu(){
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
