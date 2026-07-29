import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * VehicleRentalApp is the main program for the car rental agency's
 * vehicle information system. It lets the user interactively create
 * Car, Motorcycle, and Truck objects, storing each one as a Vehicle,
 * and then display the details of every vehicle that has been added.
 *
 * All vehicles are stored in a single List&lt;Vehicle&gt; even though they
 * are three different concrete classes -- this is polymorphism in
 * action: the list only needs to know each element is "a Vehicle",
 * and each object's toString() prints its own subtype-specific details.
 */
public class VehicleRentalApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Vehicle> vehicles = new ArrayList<>();

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("=======================================");
        System.out.println(" Car Rental Agency - Vehicle Info System");
        System.out.println("=======================================");

        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ", 1, 5);

            switch (choice) {
                case 1:
                    addCar();
                    break;
                case 2:
                    addMotorcycle();
                    break;
                case 3:
                    addTruck();
                    break;
                case 4:
                    displayAllVehicles();
                    break;
                case 5:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    // readInt already restricts the range, so this is unreachable,
                    // but it is kept as a defensive fallback.
                    System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Add a Car");
        System.out.println("2. Add a Motorcycle");
        System.out.println("3. Add a Truck");
        System.out.println("4. Display all vehicles");
        System.out.println("5. Exit");
    }

    /**
     * Prompts for and creates a new Car, then adds it to the vehicle list.
     */
    private static void addCar() {
        System.out.println("\n-- New Car --");
        String make = readNonEmptyString("Make: ");
        String model = readNonEmptyString("Model: ");
        int year = readInt("Year of manufacture: ", 1900, 2100);

        Car car = new Car(make, model, year);
        car.setNumDoors(readInt("Number of doors: ", 1, 10));
        car.setFuelType(readChoice("Fuel type (Petrol/Diesel/Electric): ",
                new String[] { "Petrol", "Diesel", "Electric" }));

        vehicles.add(car);
        System.out.println("Car added successfully.");
    }

    /**
     * Prompts for and creates a new Motorcycle, then adds it to the vehicle list.
     */
    private static void addMotorcycle() {
        System.out.println("\n-- New Motorcycle --");
        String make = readNonEmptyString("Make: ");
        String model = readNonEmptyString("Model: ");
        int year = readInt("Year of manufacture: ", 1900, 2100);

        Motorcycle motorcycle = new Motorcycle(make, model, year);
        motorcycle.setNumWheels(readInt("Number of wheels: ", 2, 4));
        motorcycle.setMotorcycleType(readChoice("Motorcycle type (Sport/Cruiser/Off-road): ",
                new String[] { "Sport", "Cruiser", "Off-road" }));

        vehicles.add(motorcycle);
        System.out.println("Motorcycle added successfully.");
    }

    /**
     * Prompts for and creates a new Truck, then adds it to the vehicle list.
     */
    private static void addTruck() {
        System.out.println("\n-- New Truck --");
        String make = readNonEmptyString("Make: ");
        String model = readNonEmptyString("Model: ");
        int year = readInt("Year of manufacture: ", 1900, 2100);

        Truck truck = new Truck(make, model, year);
        truck.setCargoCapacity(readDouble("Cargo capacity (tons): ", 0, 200));
        truck.setTransmissionType(readChoice("Transmission type (Manual/Automatic): ",
                new String[] { "Manual", "Automatic" }));

        vehicles.add(truck);
        System.out.println("Truck added successfully.");
    }

    /**
     * Displays every vehicle added so far by relying on polymorphism:
     * each vehicle's own toString() is invoked automatically, so this
     * method does not need to know or check which concrete class each
     * vehicle actually is.
     */
    private static void displayAllVehicles() {
        System.out.println();
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles have been added yet.");
            return;
        }

        System.out.println("---- All Vehicles (" + vehicles.size() + ") ----");
        for (int i = 0; i < vehicles.size(); i++) {
            System.out.println((i + 1) + ". " + vehicles.get(i));
        }
    }

    // ---------------------------------------------------------------
    // Input helpers -- all user input funnels through these so that
    // invalid input (wrong type, out-of-range value, blank text, or a
    // fuel/motorcycle/transmission type that isn't one of the allowed
    // options) is caught and re-prompted instead of crashing the program.
    // ---------------------------------------------------------------

    /**
     * Reads an integer within [min, max] from the user, re-prompting on
     * non-numeric input or out-of-range values.
     */
    private static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value < min || value > max) {
                    System.out.printf("Please enter a number between %d and %d.%n", min, max);
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("That's not a valid whole number. Please try again.");
            }
        }
    }

    /**
     * Reads a double within [min, max] from the user, re-prompting on
     * non-numeric input or out-of-range values.
     */
    private static double readDouble(String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value < min || value > max) {
                    System.out.printf("Please enter a number between %.1f and %.1f.%n", min, max);
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("That's not a valid number. Please try again.");
            }
        }
    }

    /**
     * Reads a non-blank line of text from the user, re-prompting if the
     * input is empty.
     */
    private static String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (value.isEmpty()) {
                System.out.println("This field cannot be empty. Please try again.");
                continue;
            }
            return value;
        }
    }

    /**
     * Reads a string from the user and validates it against a fixed set
     * of allowed options (case-insensitive), re-prompting until the
     * input matches one of them.
     */
    private static String readChoice(String prompt, String[] options) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            for (String option : options) {
                if (option.equalsIgnoreCase(value)) {
                    return option;
                }
            }
            System.out.println("Invalid option. Please enter one of the listed choices.");
        }
    }
}
