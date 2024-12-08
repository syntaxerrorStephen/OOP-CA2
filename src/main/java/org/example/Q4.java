import java.util.Scanner;

public class Q4 {
 
    static class Cell {
        int row, col;

        Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) {
        int[][] grid = new int[10][10]; 
        Scanner scanner = new Scanner(System.in);

        // Get the starting position for the flood fill
        System.out.print("Enter the starting row (0-9): ");
        int startRow = scanner.nextInt();
        System.out.print("Enter the starting column (0-9): ");
        int startCol = scanner.nextInt();

        if (startRow < 0 || startRow >= 10 || startCol < 0 || startCol >= 10) {
            System.out.println("Oops! Starting position must be between 0 and 9.");
            return;
        }

        scanner.close();
    }
}
