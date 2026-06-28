import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * LibrarySystem.java
 * A basic library management system.
 */
public class LibrarySystem {

    // Inner class to hold book data
    static class Book {
        String author;
        int quantity;

        Book(String author, int quantity) {
            this.author = author;
            this.quantity = quantity;
        }
    }

    // Library catalog: title (lowercase) -> Book
    static Map<String, Book> catalog = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Welcome to the Library System ===");

        while (running) {
            printMenu();

            int choice = -1;
            try {
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[Error] Invalid input. Enter a number 1-4.");
                continue;
            }

            switch (choice) {
                case 1:
                    addBooks(scanner);
                    break;
                case 2:
                    borrowBooks(scanner);
                    break;
                case 3:
                    returnBooks(scanner);
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("[Error] Choose 1, 2, 3, or 4.");
            }
        }
        scanner.close();
    }

    static void printMenu() {
        System.out.println("\n--- Library Menu ---");
        System.out.println("1. Add Books");
        System.out.println("2. Borrow Books");
        System.out.println("3. Return Books");
        System.out.println("4. Exit");
    }

    static void addBooks(Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine().trim();
        if (title.isEmpty()) {
            System.out.println("[Error] Title cannot be empty.");
            return;
        }

        System.out.print("Enter author name: ");
        String author = scanner.nextLine().trim();
        if (author.isEmpty()) {
            System.out.println("[Error] Author cannot be empty.");
            return;
        }       

        int quantity = 0;
        try {
            System.out.print("Enter quantity to add: ");
            quantity = Integer.parseInt(scanner.nextLine().trim());
            if (quantity <= 0) {
                System.out.println("[Error] Quantity must be positive.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("[Error] Invalid quantity.");
            return;
        }

        String key = title.toLowerCase();
        if (catalog.containsKey(key)) {
            catalog.get(key).quantity += quantity;
            System.out.println("[Success] Updated '" + title + "'. New qty: "
                    + catalog.get(key).quantity);
        } else {
            catalog.put(key, new Book(author, quantity));
            System.out.println("[Success] Added '" + title + "' by " + author
                    + ". Qty: " + quantity);
        }
    }

    static void borrowBooks(Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine().trim();
        String key = title.toLowerCase();

        if (!catalog.containsKey(key)) {
            System.out.println("[Error] '" + title + "' not found.");
            return;
        }

        int requested = 0;
        try {
            System.out.print("Copies to borrow: ");
            requested = Integer.parseInt(scanner.nextLine().trim());
            if (requested <= 0) {
                System.out.println("[Error] Must be positive.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("[Error] Invalid number.");
            return;
        }

        Book book = catalog.get(key);
        if (requested > book.quantity) {
            System.out.println("[Error] Not enough copies. Available: " + book.quantity);
        } else {
            book.quantity -= requested;
            System.out.println("[Success] Borrowed " + requested + " copy/copies of '"
                    + title + "'. Remaining: " + book.quantity);
        }
    }

    static void returnBooks(Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine().trim();
        String key = title.toLowerCase();

        if (!catalog.containsKey(key)) {
            System.out.println("[Error] '" + title + "' is not in this library.");
            return;
        }

        int returning = 0;
        try {
            System.out.print("Copies to return: ");
            returning = Integer.parseInt(scanner.nextLine().trim());
            if (returning <= 0) {
                System.out.println("[Error] Must be positive.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("[Error] Invalid number.");
            return;
        }

        Book book = catalog.get(key);
        book.quantity += returning;
        System.out.println("[Success] Returned " + returning + " copy/copies of '"
                + title + "'. Updated qty: " + book.quantity);
    }
}