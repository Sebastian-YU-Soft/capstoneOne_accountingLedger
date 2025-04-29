package com.ps;

import java.io.*;
import java.nio.channels.ScatteringByteChannel;
import java.time.LocalDate;
import java.util.Scanner;

public class personalLedger {
    public static final String CSV_FILE = "personal_transaction.csv";
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        ensureCSVFileExists();
        mainMenu();
    }

    private static void  ensureCSVFileExists(){
        try {
            File file = new File(CSV_FILE);
            if (!file.exists()){
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating CSV file:" + e.getMessage());
        }
    }
    private static void mainMenu(){
        boolean running = true;
        while (running){
            System.out.println("Home Screen");
            System.out.println("1. Add Deposit");
            System.out.println("2. Make Payment (Debit)");
            System.out.println("3. Ledger");
            System.out.println("4. Exit");
            System.out.println("Enter your choice:");
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice){
                case "1":
                    addTransaction(true);
                    break;
                case "2":
                    addTransaction(false);
                    break;
                case "3":
                    ledgerMenu();
                    break;
                case "4":
                    running = false;
                    System.out.println("Exiting application. Goodbye");
                    break;
                default:
                    System.out.println("invalid option. Try again.");
            }
        }
    }

    private static void addTransaction(boolean isDeposit){
        try {
            (FileWriter fw = new FileWriter(CSV_FILE, true);
            PrintWriter pw = new PrintWriter(fw)){

                System.out.print("Enter vendor/description:");
                String description = scanner.nextLine();

                System.out.print("Enter amount:");
                double amount = Double.parseDouble(scanner.nextLine());

                if (!isDeposit) {
                    amount = -amount;
                }

                String date = LocalDate.now().toString();
                pw.println(date + "|" + description + "|" + amount);
                System.out.println("Transaction saved!");

            }catch(IOException e){
                System.out.println("Error saving transaction:" + e.getMessage());
            }
        } finally {(NumberFormatException e){
            System.out.println("Invalid amount entered.");
        }

        }
    }

    private static void ledgerMenu(){
        boolean inLedger = true;
        while (inLedger){
            System.out.println("Ledger Screen");
            System.out.println("1. All Entries");
            System.out.println("2. Deposits Only");
            System.out.println("3. Payments Only");
            System.out.println("4. Reports");
            System.out.println("5. Home");
            System.out.println("Enter your choice:");
            String choice = scanner.nextLine().trim().toUpperCase();


        }
    }
}


