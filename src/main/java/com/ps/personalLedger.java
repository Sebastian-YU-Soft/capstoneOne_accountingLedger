package com.ps;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

    public class personalLedger {
        private static final String CSV_FILE = "personal_transactions.csv";
        private static final Scanner scanner = new Scanner(System.in);

        public static void main(String[] args) {
            ensureCSVFileExists();
            mainMenu();
        }

        private static void ensureCSVFileExists() {
            try {
                File file = new File(CSV_FILE);
                if (!file.exists()) {
                    file.createNewFile();
                }
            } catch (IOException e) {
                System.out.println("Error creating CSV file: " + e.getMessage());
            }
        }

        private static void mainMenu() {
            boolean running = true;
            while (running) {
                System.out.println(" Home Screen ");
                System.out.println("1) Add Deposit");
                System.out.println("2) Make Payment (Debit)");
                System.out.println("3) Ledger");
                System.out.println("4) Exit");
                System.out.print("Enter your choice: ");
                String choice = scanner.nextLine().trim().toUpperCase();

                switch (choice) {
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
                        System.out.println("Exiting application. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            }
        }

        private static void addTransaction(boolean isDeposit) {
            try (FileWriter fw = new FileWriter(CSV_FILE, true);
                 PrintWriter pw = new PrintWriter(fw)) {

                System.out.print("Enter vendor/description: ");
                String description = scanner.nextLine();

                System.out.print("Enter amount: ");
                double amount = Double.parseDouble(scanner.nextLine());

                if (!isDeposit) {
                    amount = -amount;
                }

                String date = LocalDate.now().toString();
                pw.println(date + "|" + description + "|" + amount);
                System.out.println("Transaction saved!");

            } catch (IOException e) {
                System.out.println("Error saving transaction: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount entered.");
            }
        }


        private static void ledgerMenu() {
            boolean inLedger = true;
            while (inLedger) {
                System.out.println(" Ledger Screen ===");
                System.out.println("1) All Entries");
                System.out.println("2) Deposits Only");
                System.out.println("3) Payments Only");
                System.out.println("4) Reports");
                System.out.println("5) Home");
                System.out.print("Enter your choice: ");
                String choice = scanner.nextLine().trim().toUpperCase();

                List<String[]> transactions = loadTransactions();
                Collections.reverse(transactions); // Newest entries first

                switch (choice) {
                    case "1":
                        displayTransactions(transactions, "ALL");
                        break;
                    case "2":
                        displayTransactions(transactions, "DEPOSITS");
                        break;
                    case "3":
                        displayTransactions(transactions, "PAYMENTS");
                        break;
                    case "4":
                        reportsMenu(transactions);
                        break;
                    case "5":
                        inLedger = false;
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            }
        }


        private static List<String[]> loadTransactions() {
            List<String[]> transactions = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    if (parts.length == 3) {
                        transactions.add(parts);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading CSV file: " + e.getMessage());
            }
            return transactions;
        }

        private static void displayTransactions(List<String[]> transactions, String filter) {
            System.out.printf("\n%-12s %-20s %10s\n", "Date", "Vendor", "Amount");
            System.out.println("-----------------------------------------------");
            for (String[] entry : transactions) {
                double amount = Double.parseDouble(entry[2]);
                boolean display = false;
                switch (filter) {
                    case "ALL":
                        display = true;
                        break;
                    case "DEPOSITS":
                        if (amount > 0) display = true;
                        break;
                    case "PAYMENTS":
                        if (amount < 0) display = true;
                        break;
                }
                if (display) {
                    System.out.printf("%-12s %-20s %10.2f\n", entry[0], entry[1], amount);
                }
            }
        }

        private static void reportsMenu(List<String[]> transactions) {
            boolean inReports = true;
            while (inReports) {
                System.out.println(" Reports Menu ");
                System.out.println("1) Month To Date");
                System.out.println("2) Previous Month");
                System.out.println("3) Year To Date");
                System.out.println("4) Previous Year");
                System.out.println("5) Search by Vendor");
                System.out.println("0) Back to Ledger");
                System.out.print("Enter your choice: ");
                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1":
                        filterByDate(transactions, LocalDate.now().withDayOfMonth(1), LocalDate.now());
                        break;
                    case "2":
                        LocalDate prevMonthStart = LocalDate.now().minusMonths(1).withDayOfMonth(1);
                        LocalDate prevMonthEnd = prevMonthStart.withDayOfMonth(prevMonthStart.lengthOfMonth());
                        filterByDate(transactions, prevMonthStart, prevMonthEnd);
                        break;
                    case "3":
                        filterByDate(transactions, LocalDate.now().withDayOfYear(1), LocalDate.now());
                        break;
                    case "4":
                        LocalDate lastYearStart = LocalDate.now().minusYears(1).withDayOfYear(1);
                        LocalDate lastYearEnd = lastYearStart.withDayOfYear(lastYearStart.lengthOfYear());
                        filterByDate(transactions, lastYearStart, lastYearEnd);
                        break;
                    case "5":
                        searchByVendor(transactions);
                        break;
                    case "0":
                        inReports = false;
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            }
        }

        private static void filterByDate(List<String[]> transactions, LocalDate start, LocalDate end) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            System.out.printf("\n%-12s %-20s %10s\n", "Date", "Vendor", "Amount");
            System.out.println("-----------------------------------------------");

            for (String[] entry : transactions) {
                LocalDate date = LocalDate.parse(entry[0], formatter);
                if ((date.isEqual(start) || date.isAfter(start)) && (date.isEqual(end) || date.isBefore(end))) {
                    System.out.printf("%-12s %-20s %10.2f\n", entry[0], entry[1], Double.parseDouble(entry[2]));
                }
            }
        }

        private static void searchByVendor(List<String[]> transactions) {
            System.out.print("Enter vendor name to search: ");
            String vendor = scanner.nextLine().toLowerCase();
            System.out.printf("\n%-12s %-20s %10s\n", "Date", "Vendor", "Amount");
            System.out.println("-----------------------------------------------");

            for (String[] entry : transactions) {
                if (entry[1].toLowerCase().contains(vendor)) {
                    System.out.printf("%-12s %-20s %10.2f\n", entry[0], entry[1], Double.parseDouble(entry[2]));
                }
            }
        }n
    }

