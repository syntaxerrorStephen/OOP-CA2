import java.util.Scanner;
import java.util.Stack;

public class Q4 {
    // Defines the cell on the grid
    static class Cell {
        int row, col;

        Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) {
        // Start with all 0
        int[][] grid = new int[10][10]; 

        Stack<Cell> stack = new Stack<>(); 
        Scanner scanner = new Scanner(System.in);

        // Get start pos
        System.out.print("Enter the starting row (0-9): ");
        int startRow = scanner.nextInt();
        System.out.print("Enter the starting column (0-9): ");
        int startCol = scanner.nextInt();

        // Validate
        if (startRow < 0 || startRow >= 10 || startCol < 0 || startCol >= 10) {
            System.out.println("Incorrect input! Starting position must be between 0 and 9.");
            return;
        }

        // Put the startinf cell in the stack
        stack.push(new Cell(startRow, startCol));
        int fillNumber = 1; 

        // Keep going as long as there are cells to process
        while (!stack.isEmpty()) {
            Cell current = stack.pop();
            int row = current.row;
            int col = current.col;

            if (row >= 0 && row < 10 && col >= 0 && col < 10 && grid[row][col] == 0) {
                grid[row][col] = fillNumber++; 

                stack.push(new Cell(row - 1, col));
                 // North
                stack.push(new Cell(row + 1, col));
                 // South
                stack.push(new Cell(row, col - 1));
                 // West
                stack.push(new Cell(row, col + 1));
                 // East
            }
        }

        // Print out the grid 
        System.out.println("Here’s the filled grid:");
        for (int[] row : grid) {
            for (int cell : row) {
                // Using the %2d format specifier to make sure each number is printed with 2 spaces
                System.out.printf("%2d ", cell); 
            }
            System.out.println();
        }

        scanner.close(); 
    }
}
