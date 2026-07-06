import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Student Record Management System ===");

        while (running) {
            printMenu();
            int choice = -1;

            try {
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[Error] Invalid input. Enter a number 1-5.");
                continue;
            }

            switch (choice) {
                case 1:
                    addStudentFlow(scanner);
                    break;
                case 2:
                    updateStudentFlow(scanner);
                    break;
                case 3:
                    viewStudentFlow(scanner);
                    break;
                case 4:
                    StudentManagement.viewAllStudents();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("[Error] Choose a number between 1 and 5.");
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Administrator Menu ---");
        System.out.println("1. Add New Student");
        System.out.println("2. Update Student Information");
        System.out.println("3. View Student Details");
        System.out.println("4. View All Students");
        System.out.println("5. Exit");
    }

    private static void addStudentFlow(Scanner scanner) {
        try {
            System.out.print("Enter student name: ");
            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("[Error] Name cannot be empty.");
                return;
            }

            System.out.print("Enter student ID: ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter student age: ");
            int age = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter student grade: ");
            String grade = scanner.nextLine().trim();

            StudentManagement.addStudent(name, id, age, grade);

        } catch (NumberFormatException e) {
            System.out.println("[Error] ID and age must be numbers.");
        }
    }

    private static void updateStudentFlow(Scanner scanner) {
        try {
            System.out.print("Enter student ID to update: ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter new name: ");
            String name = scanner.nextLine().trim();

            System.out.print("Enter new age: ");
            int age = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter new grade: ");
            String grade = scanner.nextLine().trim();

            StudentManagement.updateStudent(id, name, age, grade);
        } catch (NumberFormatException e) {
            System.out.println("[Error] ID and age must be numbers.");
        }
    }

    private static void viewStudentFlow(Scanner scanner) {
        try {
            System.out.print("Enter student ID to view: ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            StudentManagement.viewStudent(id);
        } catch (NumberFormatException e) {
            System.out.println("[Error] ID must be a number.");
        }
    }
}
