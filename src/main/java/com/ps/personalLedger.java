package com.ps;

public class personalLedger {
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
        }
    }
}
