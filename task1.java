import java.util.Scanner;

class ReservationSystem {
    private boolean[] seats;
    private int totalSeats;

    public ReservationSystem(int totalSeats) {
        this.totalSeats = totalSeats;
        this.seats = new boolean[totalSeats];
    }

    public boolean makeReservation(int seatNumber) {
        if (seatNumber < 1 || seatNumber > totalSeats) {
            System.out.println("Invalid seat number. Please enter a valid seat number.");
            return false;
        }

        if (seats[seatNumber - 1]) {
            System.out.println("Sorry, seat " + seatNumber + " is already reserved.");
            return false;
        }

        seats[seatNumber - 1] = true;
        System.out.println("Reservation for seat " + seatNumber + " successful.");
        return true;
    }

    public void displayAvailableSeats() {
        System.out.println("Available seats:");
        for (int i = 0; i < totalSeats; i++) {
            if (!seats[i]) {
                System.out.print((i + 1) + " ");
            }
        }
        System.out.println();
    }
}

public class task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Online Reservation System!");

        // Initialize reservation system with 10 seats
        ReservationSystem reservationSystem = new ReservationSystem(10);

        while (true) {
            System.out.println("\n1. Make a reservation");
            System.out.println("2. View available seats");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the seat number you want to reserve: ");
                    int seatNumber = scanner.nextInt();
                    reservationSystem.makeReservation(seatNumber);
                    break;
                case 2:
                    reservationSystem.displayAvailableSeats();
                    break;
                case 3:
                    System.out.println("Thank you for using the Online Reservation System!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
