import java.util.List;
import java.util.Scanner;
 
/**
 * Administrator-facing command-line interface for the Course
 * Enrollment and Grade Management System. Reads menu choices from the
 * console, validates input, and delegates the actual work to
 * CourseManagement (which in turn delegates to Student and Course).
 */
public class Main {
 
    private static Scanner scanner = new Scanner(System.in);
 
    public static void main(String[] args) {
        boolean running = true;
 
        System.out.println("=====================================================");
        System.out.println(" Course Enrollment and Grade Management System");
        System.out.println("=====================================================");
 
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
 
            switch (choice) {
                case "1": addCourseFlow(); break;
                case "2": addStudentFlow(); break;
                case "3": enrollStudentFlow(); break;
                case "4": assignGradeFlow(); break;
                case "5": calculateOverallGradeFlow(); break;
                case "6": viewCoursesFlow(); break;
                case "7": viewStudentsFlow(); break;
                case "0":
                    running = false;
                    System.out.println("Exiting. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please enter a number from the menu.");
            }
        }
        scanner.close();
    }
 
    private static void printMenu() {
        System.out.println();
        System.out.println("---------------------- MENU ----------------------");
        System.out.println("1. Add a new course");
        System.out.println("2. Add a new student");
        System.out.println("3. Enroll a student in a course");
        System.out.println("4. Assign a grade to a student");
        System.out.println("5. Calculate overall grade for a student");
        System.out.println("6. View all courses");
        System.out.println("7. View all students");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }
 
    private static void addCourseFlow() {
        System.out.print("Enter course code (e.g., CS101): ");
        String code = scanner.nextLine().trim();
        System.out.print("Enter course name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter maximum capacity: ");
        String capacityInput = scanner.nextLine().trim();
 
        if (code.isEmpty() || name.isEmpty()) {
            System.out.println("Error: course code and name cannot be empty.");
            return;
        }
        if (findCourseSafely(code) != null) {
            System.out.println("Error: a course with code " + code + " already exists.");
            return;
        }
 
        int capacity;
        try {
            capacity = Integer.parseInt(capacityInput);
        } catch (NumberFormatException e) {
            System.out.println("Error: capacity must be a whole number.");
            return;
        }
        if (capacity <= 0) {
            System.out.println("Error: capacity must be greater than zero.");
            return;
        }
 
        Course course = CourseManagement.addCourse(code, name, capacity);
        System.out.println("Course added successfully: " + course);
    }
 
    private static void addStudentFlow() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine().trim();
 
        if (name.isEmpty() || id.isEmpty()) {
            System.out.println("Error: name and ID cannot be empty.");
            return;
        }
        if (CourseManagement.findStudentById(id) != null) {
            System.out.println("Error: a student with ID " + id + " already exists.");
            return;
        }
 
        Student student = CourseManagement.addStudent(name, id);
        System.out.println("Student added successfully: " + student);
    }
 
    private static void enrollStudentFlow() {
        Student student = promptForStudent();
        if (student == null) return;
        Course course = promptForCourse();
        if (course == null) return;
 
        if (course.isFull()) {
            System.out.println("Error: course " + course.getCourseCode()
                    + " has reached its maximum capacity (" + course.getMaxCapacity()
                    + "). Cannot enroll another student.");
            return;
        }
 
        boolean success = CourseManagement.enrollStudent(student, course);
        if (success) {
            System.out.println(student.getName() + " was enrolled in " + course.getCourseCode() + ".");
        } else {
            System.out.println("Error: " + student.getName() + " is already enrolled in "
                    + course.getCourseCode() + ".");
        }
    }
 
    private static void assignGradeFlow() {
        Student student = promptForStudent();
        if (student == null) return;
        Course course = promptForCourse();
        if (course == null) return;
 
        System.out.print("Enter grade (0-100): ");
        String gradeInput = scanner.nextLine().trim();
        double grade;
        try {
            grade = Double.parseDouble(gradeInput);
        } catch (NumberFormatException e) {
            System.out.println("Error: grade must be a number.");
            return;
        }
        if (grade < 0 || grade > 100) {
            System.out.println("Error: grade must be between 0 and 100.");
            return;
        }
 
        boolean success = CourseManagement.assignGrade(student, course, grade);
        if (success) {
            System.out.println("Grade " + grade + " assigned to " + student.getName()
                    + " for " + course.getCourseCode() + ".");
        } else {
            System.out.println("Error: " + student.getName() + " is not enrolled in "
                    + course.getCourseCode() + ", so a grade cannot be assigned.");
        }
    }
 
    private static void calculateOverallGradeFlow() {
        Student student = promptForStudent();
        if (student == null) return;
 
        double overall = CourseManagement.calculateOverallGrade(student);
        System.out.printf("Overall grade for %s: %.2f%n", student.getName(), overall);
    }
 
    private static void viewCoursesFlow() {
        List<Course> courses = CourseManagement.getCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses have been added yet.");
            return;
        }
        System.out.println("All courses:");
        for (Course c : courses) {
            System.out.println("  " + c);
        }
        System.out.println("Total enrollments across all courses (static counter): "
                + Course.getTotalEnrolledStudents());
    }
 
    private static void viewStudentsFlow() {
        List<Student> students = CourseManagement.getStudents();
        if (students.isEmpty()) {
            System.out.println("No students have been added yet.");
            return;
        }
        System.out.println("All students:");
        for (Student s : students) {
            System.out.println("  " + s + " - enrolled in " + s.getEnrolledCourses().size() + " course(s)");
        }
    }
 
    // ---- Helper prompts shared by several flows ----
 
    private static Student promptForStudent() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine().trim();
        Student student = CourseManagement.findStudentById(id);
        if (student == null) {
            System.out.println("Error: no student found with ID " + id + ".");
        }
        return student;
    }
 
    private static Course promptForCourse() {
        System.out.print("Enter course code: ");
        String code = scanner.nextLine().trim();
        Course course = CourseManagement.findCourseByCode(code);
        if (course == null) {
            System.out.println("Error: no course found with code " + code + ".");
        }
        return course;
    }
 
    private static Course findCourseSafely(String code) {
        return CourseManagement.findCourseByCode(code);
    }
}
