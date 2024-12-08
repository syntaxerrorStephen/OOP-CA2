import java.util.Scanner;
import java.util.Stack;

class ParkingManager {
    private Stack<Integer> parkingLot;
    private Stack<Integer> temporaryStorage;

    public ParkingManager() {
        this.parkingLot = new Stack<>();
        this.temporaryStorage = new Stack<>();
    }

    public void parkCar(int carNumber) {
        parkingLot.push(carNumber);
        System.out.println("Car " + carNumber + " parked in the lot.");
        displayLotState();
    }

    public void retrieveCar(int carNumber) {
        if (parkingLot.isEmpty()) {
            System.out.println("The parking lot is empty. No cars to retrieve.");
            return;
        }

        // Move cars to temporary storage until the desired car is found
        while (!parkingLot.isEmpty() && parkingLot.peek() != carNumber) {
            temporaryStorage.push(parkingLot.pop());
        }

        // Check if the car is in the lot and remove it
        if (!parkingLot.isEmpty() && parkingLot.peek() == carNumber) {
            parkingLot.pop();
            System.out.println("Car " + carNumber + " has been retrieved.");
        } else {
            System.out.println("Car " + carNumber + " is not in the parking lot.");
        }

        // Move cars back from temporary storage to the lot
        while (!temporaryStorage.isEmpty()) {
            parkingLot.push(temporaryStorage.pop());
        }

        displayLotState();
    }

    public void displayLotState() {
        System.out.println("Current state of the parking lot: " + parkingLot);
    }
}

public class Q2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ParkingManager parkingManager = new ParkingManager();

        System.out.println("Welcome to the Parking Manager App.");
        System.out.println("Enter positive numbers to park cars (e.g., 1, 2, 3).");
        System.out.println("Enter negative numbers to retrieve cars (e.g., -2).");
        System.out.println("Enter 0 to end the program.");

        while (true) {
            System.out.print("Enter your command: ");
            int command = input.nextInt();

            if (command == 0) {
                System.out.println("Exiting the program. Goodbye!");
                break;
            } else if (command > 0) {
                parkingManager.parkCar(command);
            } else {
                parkingManager.retrieveCar(-command);
            }
        }

        input.close();
    }
}
