package org.example;

public enum DIRECTION {
    // Directions
    NORTH(-1, 0), // Move up
    SOUTH(1, 0),  // Move down
    WEST(0, -1),  // Move left
    EAST(0, 1);   // Move right

    // Declaring variables representing changes in position for each direction
    public final int dx;
    public final int dy;

    /*
    Constructor to initialize movement offsets
    dx - change in the row index (vertical movement)
    dy - change in the column index (horizontal movement)
    */
    DIRECTION(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
}
