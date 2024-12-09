import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

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
    // Map to manage stock batches for multiple companies
    private Map<String, Queue<StockBatch>> stockMap;

    public Q7() {
        this.stockMap = new HashMap<>();
    }

    // Method to add a new stock purchase to the queue for a specific company
    public void recordPurchase(String symbol, int shares, double cost) {
        stockMap.putIfAbsent(symbol, new LinkedList<>());
        stockMap.get(symbol).add(new StockBatch(shares, cost));
        System.out.println("Purchased " + shares + " shares of " + symbol + " at $" + cost + " each.");
    }

    // Method to sell stocks of a specific company and calculate profit based on FIFO rule
    public void processSale(String symbol, int sharesToSell, double sellingPrice) {
        if (!stockMap.containsKey(symbol) || stockMap.get(symbol).isEmpty()) {
            System.out.println("No shares available for " + symbol + " to sell.");
            return;
        }

        Queue<StockBatch> purchaseQueue = stockMap.get(symbol);
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
            System.out.println("Not enough shares of " + symbol + " available to complete the sale.");
        } else {
            System.out.println("Sale of " + symbol + " shares completed. Total profit: $" + totalProfit);
        }
    }

    // Main method to provide user interaction
    public static void main(String[] args) {
        Q7 app = new Q7();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter commands (buy symbol quantity price, sell symbol quantity price, quit):");

        while (true) {
            String input = scanner.nextLine();
            String[] inputs = input.split(" ");

            if (inputs[0].equalsIgnoreCase("quit")) {
                System.out.println("Program terminated.");
                break;
            } else if (inputs[0].equalsIgnoreCase("buy") && inputs.length == 4) {
                String symbol = inputs[1];
                int shares = Integer.parseInt(inputs[2]);
                double price = Double.parseDouble(inputs[3]);
                app.recordPurchase(symbol, shares, price);
            } else if (inputs[0].equalsIgnoreCase("sell") && inputs.length == 4) {
                String symbol = inputs[1];
                int sharesToSell = Integer.parseInt(inputs[2]);
                double price = Double.parseDouble(inputs[3]);
                app.processSale(symbol, sharesToSell, price);
            } else {
                System.out.println("Invalid command. Please try again.");
            }
        }

        scanner.close();
    }
}
