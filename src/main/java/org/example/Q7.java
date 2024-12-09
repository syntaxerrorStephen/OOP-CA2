import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// Class to represent a batch of stock purchases
class StockBatch {
    // Number of shares in this batch
    int shares; 
    // Cost per share in this batch
    double cost; 

    public StockBatch(int shares, double cost) {
        this.shares = shares;
        this.cost = cost;
    }
}

// Main class for handling stock transactions
public class Q7 {
    // Queue to manage stock batches in FIFO order
    private Queue<StockBatch> purchaseQueue; 

    public Q7() {
        this.purchaseQueue = new LinkedList<>();
    }

    // Method to add a new stock purchase to the queue
    public void recordPurchase(int shares, double cost) {
        purchaseQueue.add(new StockBatch(shares, cost));
        System.out.println("Purchased " + shares + " shares at $" + cost + " each.");
    }

    // Method to sell stocks and calculate profit based on FIFO rule
    public void processSale(int sharesToSell, double sellingPrice) {
        if (sharesToSell <= 0) {
            System.out.println("Invalid quantity. Cannot process sale.");
            return;
        }

        double totalProfit = 0;

        while (sharesToSell > 0 && !purchaseQueue.isEmpty()) {
            StockBatch currentBatch = purchaseQueue.peek();

            if (currentBatch.shares <= sharesToSell) {
                // Sell all shares in the current batch
                totalProfit += currentBatch.shares * (sellingPrice - currentBatch.cost);
                sharesToSell -= currentBatch.shares;
                purchaseQueue.poll(); 
            } else {
                // Sell a portion of the shares in the current batch
                totalProfit += sharesToSell * (sellingPrice - currentBatch.cost);
                currentBatch.shares -= sharesToSell;
                sharesToSell = 0;
            }
        }

        if (sharesToSell > 0) {
            System.out.println("Not enough shares available to complete the sale.");
        } else {
            System.out.println("Sale completed. Total profit: $" + totalProfit);
        }
    }

    // Main method to provide user interaction
    public static void main(String[] args) {
        Q7 app = new Q7();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter commands (buy quantity price, sell quantity price, quit):");

        while (true) {
            String input = scanner.nextLine();
            String[] inputs = input.split(" ");

            if (inputs[0].equalsIgnoreCase("quit")) {
                System.out.println("Program terminated.");
                break;
            } else if (inputs[0].equalsIgnoreCase("buy") && inputs.length == 3) {
                int shares = Integer.parseInt(inputs[1]);
                double price = Double.parseDouble(inputs[2]);
                app.recordPurchase(shares, price);
            } else if (inputs[0].equalsIgnoreCase("sell") && inputs.length == 3) {
                int sharesToSell = Integer.parseInt(inputs[1]);
                double price = Double.parseDouble(inputs[2]);
                app.processSale(sharesToSell, price);
            } else {
                System.out.println("Invalid command. Please try again.");
            }
        }

        scanner.close();
    }
}
