import java.io.*;
import java.util.Scanner;

public class airlinemanagementsystem {
    public static void main(String[] args) {
        Management mObj = new Management();
    }
}

class Management {
    public Management() {
        MainMenu.mainMenu();
    }
}

class Details {
    public static String name, gender;
    public static int cId;
    public int age;
    public String address;

    public void information() {
        Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter the Customer ID: ");
        cId = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.print("Enter the Name: ");
        name = sc.nextLine();
        System.out.print("Enter the Age: ");
        age = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.print("Enter the Address: ");
        address = sc.nextLine();
        System.out.print("Enter the Gender: ");
        gender = sc.nextLine();

        System.out.println("\nYour details have been saved with us.");
    }
}

class Registration {
    public static int choice;
    public static float charges;

    public void flight() {
        Scanner sc = new Scanner(System.in);

        String[] flightDestinations = {"Dubai", "Canada", "UK", "USA", "Australia", "Europe"};
        System.out.println("\nWelcome to the Airlines!");
        System.out.println("Select the number of the destination you want to book a flight to:");
        for (int i = 0; i < flightDestinations.length; i++) {
            System.out.println((i + 1) + ". Flight to: " + flightDestinations[i]);
        }
        choice = sc.nextInt();

        switch (choice) {
            case 1:
                bookFlight(sc, "Dubai", new String[]{"DUB-498", "DUB-658", "DUB-508"}, new int[]{14000, 10000, 9000});
                break;
            case 2:
                bookFlight(sc, "Canada", new String[]{"CA-198", "CA-158", "CA-208"}, new int[]{34000, 29000, 40000});
                break;
            case 3:
                bookFlight(sc, "UK", new String[]{"UK-798"}, new int[]{10000});
                break;
            case 4:
                bookFlight(sc, "USA", new String[]{"US-567", "US-658", "US-508"}, new int[]{42000, 40000, 39000});
                break;
            case 5:
                bookFlight(sc, "Australia", new String[]{"AS-698", "AS-158", "AS-258"}, new int[]{44000, 40000, 34000});
                break;
            case 6:
                bookFlight(sc, "Europe", new String[]{"EU-298"}, new int[]{34000});
                break;
            default:
                System.out.println("Invalid input. Returning to the main menu.");
                MainMenu.mainMenu();
        }
    }

    private void bookFlight(Scanner sc, String destination, String[] flightCodes, int[] costs) {
        System.out.println("\nWelcome to " + destination + " Airlines!");
        System.out.println("Available Flights:");

        for (int i = 0; i < flightCodes.length; i++) {
            System.out.println((i + 1) + ". " + flightCodes[i] + " - Rs." + costs[i]);
        }

        System.out.print("Select the flight you want to book: ");
        int flightChoice = sc.nextInt();

        if (flightChoice >= 1 && flightChoice <= flightCodes.length) {
            charges = costs[flightChoice - 1];
            System.out.println("\nYou have successfully booked the flight " + flightCodes[flightChoice - 1] + ".");
            System.out.println("You can go back to the main menu to take your ticket.");
        } else {
            System.out.println("Invalid input. Returning to the previous menu.");
            flight();
        }

        System.out.print("Press 1 to return to the main menu: ");
        int back = sc.nextInt();
        if (back == 1) {
            MainMenu.mainMenu();
        } else {
            MainMenu.mainMenu();
        }
    }
}

class Ticket extends Registration {
    public void generateBill() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("records.txt"))) {
            writer.println("_____________XYZ Airlines_____________");
            writer.println("______________Ticket__________________");
            writer.println("Customer ID: " + Details.cId);
            writer.println("Customer Name: " + Details.name);
            writer.println("Customer Gender: " + Details.gender);

            String destination = switch (Registration.choice) {
                case 1 -> "Dubai";
                case 2 -> "Canada";
                case 3 -> "UK";
                case 4 -> "USA";
                case 5 -> "Australia";
                case 6 -> "Europe";
                default -> "Unknown";
            };

            writer.println("Destination: " + destination);
            writer.println("Flight Cost: Rs." + charges);

            System.out.println("Your ticket has been generated and saved.");
        } catch (IOException e) {
            System.out.println("Error while generating ticket: " + e.getMessage());
        }
    }

    public void displayBill() {
        try (BufferedReader reader = new BufferedReader(new FileReader("records.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error while reading the ticket: " + e.getMessage());
        }
    }
}

class MainMenu {
    public static void mainMenu() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n___________________XYZ Airlines___________________");
        System.out.println("____________________Main Menu_____________________\n");
        System.out.println("1. Add Customer Details");
        System.out.println("2. Flight Registration");
        System.out.println("3. Ticket and Charges");
        System.out.println("4. Exit");

        System.out.print("\nEnter your choice: ");
        int choice = sc.nextInt();

        Details details = new Details();
        Registration registration = new Registration();
        Ticket ticket = new Ticket();

        switch (choice) {
            case 1:
                details.information();
                mainMenu();
                break;
            case 2:
                registration.flight();
                break;
            case 3:
                ticket.generateBill();
                System.out.print("Press 1 to display your ticket: ");
                int display = sc.nextInt();
                if (display == 1) {
                    ticket.displayBill();
                }
                mainMenu();
                break;
            case 4:
                System.out.println("\nThank you for using XYZ Airlines!");
                break;
            default:
                System.out.println("Invalid input. Please try again.");
                mainMenu();
        }
    }
}

