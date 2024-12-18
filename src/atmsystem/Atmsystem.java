package atmsystem;

import java.util.Scanner;

public class Atmsystem {
    static String[] List = {"Latte", "Americano", "Cappuccino", "Mocha"};
    static double[] Prices = {70, 100, 80, 60};
    static int[] Quantity = {0, 0, 0, 0};
    static boolean ordering = true;
    static double totalCost = 0; 

    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);

        Menu();
        do {
            List();
            Order();
        } while (ordering);

        
        ProcessPayment();
    }

    public static void Menu() {
        System.out.println("=====================");
        System.out.println("         MENU        ");
        System.out.println("=====================");
    }

    public static void List() {
        System.out.println("   Available Coffee    ");
        System.out.println("=====================");

        for (int i = 0; i < List.length; i++) {
            System.out.printf("%d. %s - Php %.2f\n", i + 1, List[i], Prices[i]);
        }
    }

    public static void Order() {
        Scanner inp = new Scanner(System.in);

        System.out.print("Order Item: ");
        int choices = inp.nextInt();

        // Validate the item choice
        if (choices < 1 || choices > List.length) {
            System.out.println("Invalid selection. Try again.");
            return; // Exit the method if the selection is invalid
        }

        System.out.print("Qty: ");
        int Qty = inp.nextInt();

        
        Quantity[choices - 1] += Qty;
        double totalPrice = Prices[choices - 1] * Qty;

        
        totalCost += totalPrice;

        // Display the order details for this item
        System.out.println("\n--- Order Summary ---");
        System.out.printf("Item: %s\n", List[choices - 1]);
        System.out.printf("Quantity: %d\n", Qty);
        System.out.printf("Total Price: Php %.2f\n", totalPrice);

        
        inp.nextLine();

        
        String userAnswer;
        do {
            System.out.print("Do you want to add more? (Y/N): ");
            userAnswer = inp.nextLine().toUpperCase();

            if (userAnswer.equalsIgnoreCase("N")) {
                ordering = false;
            } else if (!userAnswer.equalsIgnoreCase("Y")) {
                System.out.println("Invalid input. Please use 'Y' for Yes and 'N' for No.");
            }
        } while (!userAnswer.equalsIgnoreCase("Y") && !userAnswer.equalsIgnoreCase("N"));
    }

    public static void ProcessPayment() {
        Scanner inp = new Scanner(System.in);

        
        System.out.printf("\nYour total amount due is Php %.2f\n", totalCost);

        
        String paymentMethod;
        do {
            System.out.print("Choose Payment Method (Debit/Credit or Cash): ");
            paymentMethod = inp.nextLine().toUpperCase();

            if (!paymentMethod.equals("DEBIT/CREDIT") && !paymentMethod.equals("CASH")) {
                System.out.println("Invalid input. Please select 'Debit/Credit' or 'Cash'.");
            }
        } while (!paymentMethod.equals("DEBIT/CREDIT") && !paymentMethod.equals("CASH"));

        
        if (paymentMethod.equals("DEBIT/CREDIT")) {
            System.out.println("Processing Debit/Credit payment...");
            System.out.println("Payment successful!");
            System.out.println("Thank you for your payment!");
        } else if (paymentMethod.equals("CASH")) {
            // Handle cash payment
            System.out.print("Enter amount paid: Php ");
            double amountPaid = inp.nextDouble();

            
            if (amountPaid < totalCost) {
                System.out.println("Insufficient payment. Please pay the full amount.");
            } else {
                
                double change = amountPaid - totalCost;
                System.out.printf("Change: Php %.2f\n", change);
                System.out.println("Thank you for your payment!");
            }
        }
    }
}
