# capstoneOne_accountingLedger
Compile the application using:
# Personal Ledger Application

This is a simple Java command-line application for managing personal financial transactions. It allows users to record deposits and payments, view a ledger of all transactions or filtered views, and generate basic financial reports.

## Features

* **Add Deposit:** Records income with a description, category, and amount.
* **Make Payment (Debit):** Records expenses with a vendor/description, category, and amount.
* **Ledger:**
    * **All Entries:** Displays all recorded transactions, with the newest entries shown first.
    * **Deposits Only:** Shows only income transactions.
    * **Payments Only:** Shows only expense transactions.
* **Reports:**
    * **Month To Date:** Displays transactions for the current month.
    * **Previous Month:** Displays transactions from the previous calendar month.
    * **Year To Date:** Displays transactions for the current year.
    * **Previous Year:** Displays transactions from the previous calendar year.
    * **Search by Vendor:** Allows users to search transactions by vendor/description.
* **Data Storage:** Transactions are stored in a simple CSV file named `personal_transactions.csv` in the same directory as the application. Each line in the CSV file represents a transaction with the following format: `date|description|amount`.

## Getting Started

### Prerequisites

* **Java Development Kit (JDK):** Make sure you have Java installed on your system. You can download it from [Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html) or through your operating system's package manager.

### Running the Application

1.  **Save the Code:** Save the provided Java code as `personalLedger.java`.
2.  **Compile the Code:** Open a terminal or command prompt, navigate to the directory where you saved the file, and compile the code using the Java compiler:
    ```bash
    javac personalLedger.java
    ```
    This will generate a `personalLedger.class` file.
3.  **Run the Application:** Execute the compiled class file:
    ```bash
    java personalLedger
    ```
    The application will start, and you will see the main menu.

## How to Use

Once the application is running, you can interact with it by entering the number corresponding to your desired action and pressing Enter.

* **Adding Transactions (Deposit/Payment):** Follow the prompts to enter the description, category, and amount of the transaction. For payments, the amount will be automatically recorded as a negative value.
* **Viewing the Ledger:** Select the "Ledger" option to access the ledger menu, where you can choose to view all entries, only deposits, or only payments.
* **Generating Reports:** Select the "Reports" option to access the reports menu and choose the desired report. For "Search by Vendor," you will be prompted to enter the vendor name you want to search for.
* **Exiting:** Select the "Exit" option from the main menu to close the application.

## Data Storage Details

The application uses a simple CSV (Comma Separated Values) file named `personal_transactions.csv` to store your transaction data. Each transaction is stored on a new line with the following fields separated by a pipe (`|`):

* **Date:** The date of the transaction in `YYYY-MM-DD` format.
* **Description:** A brief description of the transaction or the vendor name.
* **Amount:** The transaction amount. Positive values represent deposits, and negative values represent payments.

**Important:** Do not manually edit the `personal_transactions.csv` file while the application is running, as this could lead to data corruption.

## Potential Future Enhancements

* **Category-based reporting:** Allow users to generate reports based on transaction categories.
* **Data validation:** Implement more robust input validation to prevent errors.
* **Error handling:** Improve error handling for file operations and user input.
* **User interface:** Develop a more user-friendly graphical user interface (GUI).
* **Data export:** Allow users to export their transaction data in different formats.

## Author

This simple Personal Ledger application was created as a basic example of command-line financial tracking.