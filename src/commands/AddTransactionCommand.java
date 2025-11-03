package commands;

import models.Transaction;
import services.ITransactionService;

import java.time.LocalDate;
import java.util.Scanner;

public class AddTransactionCommand extends Command {

    public AddTransactionCommand(ITransactionService transactionService, Scanner scanner) {
        super("Add Transaction", transactionService, scanner);
    }

    @Override
    public void execute() {
        System.out.println("\n=== Add Transaction ===");
        System.out.println("----------------------------");
        Boolean type = null;
        while (type == null) {
            System.out.println("Type of Transaction: (Income/Expense)");
            System.out.print("Type: ");
            String typeString = scanner.nextLine();


            if (typeString.equalsIgnoreCase("Income")) {
                type = true;
            } else if (typeString.equalsIgnoreCase("Expense")) {
                type = false;
            } else {
                System.out.println("Invalid type! Income/Expense!");
            }
        }


        String category = "";
        while (true) {
            System.out.println("Enter Transaction Category: ");
            System.out.print("Category: ");
            category = scanner.nextLine();
            if (category.isEmpty() || category.isBlank()) {
                System.out.println("Category can't be empty or blank!");
            } else {
                break;
            }
        }

        double amount = 0.0;
        while (true) {
            System.out.println("Transaction amount: " + amount);
            System.out.print("Amount: ");
            amount = scanner.nextDouble();
            scanner.nextLine();

            if (amount <= 0) {
                System.out.println("Amount must be greater than zero!");
            } else {
                break;
            }
        }

        LocalDate date = LocalDate.now();
        Transaction transaction = new Transaction(category, amount, date, type);

        try {
            transactionService.addTransaction(transaction);
            System.out.println("\nTransaction added: \n| Category: " + transaction.getCategory() + " | Amount: " + transaction.getAmount() + " | Date: " + transaction.getDate() + " |");
            System.out.println("----------------------------\n");
        } catch (Exception exception) {
            System.out.println("Transaction error: " + exception.getMessage());
        }
    }
}
