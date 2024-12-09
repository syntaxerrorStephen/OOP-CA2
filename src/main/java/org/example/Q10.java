package org.example;

// Importing libraries
import java.util.ArrayDeque;
import java.util.Deque;

public class Q10 {
    // Function to display the map
    public static void display(int[][] map) {
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                System.out.printf("%4d", map[x][y]);
            }
            System.out.println();
        }
    }

    // Function returning if current position is at the boundary of map, meaning that exit is found
    public static boolean isExit(int[][] map, int x, int y) {
        return x == 0 || y == 0 || x == map.length - 1 || y == map[0].length - 1;
    }

    // Function returning if next move is valid (within bounds and not wall)
    public static boolean isValidMove(int[][] map, int x, int y) {
        return x >= 0 && y >= 0 && x < map.length && y < map[0].length && map[x][y] == 0;
    }

    // Function solving the maze using backtracking method
    public static void solve(int[][] map, int startX, int startY) {
        // Declaring stack (deque)
        Deque<int[]> deque = new ArrayDeque<>();

        // Adding initial position to stack and marking it as visited on map
        deque.addFirst(new int[]{startX, startY});
        map[startX][startY] = 8;

        // Processing all positions in the stack until it becomes empty
        while(!deque.isEmpty()) {
            // Declaring and removing current position from stack
            int[] current = deque.removeFirst();
            int x = current[0];
            int y = current[1];

            // If it is exit - outputting the result and closing the solver
            if (isExit(map, x, y)) {
                System.out.println("Exit found at: (" + x + ", " + y + ")");
                display(map);
                return;
            }

            // If it is not - checking all possible moves in each direction
            for (DIRECTION dir : DIRECTION.values()) {
                int newX = x + dir.dx;
                int newY = y + dir.dy;

                // If next move is valid - adding it to stack and marking it as visited
                if (isValidMove(map, newX, newY)) {
                    deque.addFirst(new int[]{newX, newY});
                    map[newX][newY] = 8;
                }
            }
        }

        // If there is no any possible moves in the stack left - it means that there is no exit
        System.out.println("No exit found.");
    }

    // Main function
    public static void main(String[] args) {
        // Declaring map (0 - free space, 1 - wall, 8 - person)
        int[][] map = {
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 0, 1, 1, 1},
                {0, 0, 0, 0, 8, 0, 0, 1},
                {1, 1, 1, 1, 0, 1, 1, 1},
                {1, 0, 0, 0, 0, 1, 1, 1},
                {1, 1, 1, 1, 0, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1},
        };

        // Launching maze solver
        solve(map, 3, 4);
    }
}
