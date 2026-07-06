import java.util.ArrayList;
import java.util.List;

public class StudentManagement {
    // Shared across the whole system - not tied to one Student
    private static List<Student> students = new ArrayList<>();
    private static int totalStudents = 0;

    public static void addStudent(String name, int id, int age, String grade) {
        if (findStudentById(id) != null) {
            System.err.println("[Error] A student with ID " + id + "already exists.");
            return;
        }
        Student newStudent = new Student(name, id, age, grade);
        students.add(newStudent);
        totalStudents++;
        System.out.println("[Info] Student added successfully. Total students: " + totalStudents);
    }

    public static void updateStudent(int id, String name, int age, String grade) {
        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("[Error] Student with ID " + id + "not found.");
            return;
        }

        if (!name.isEmpty()) {
            student.setName(name);
        }

        if (age > 0) {
            student.setAge(age);
        }

        if (!grade.isEmpty()) {
            student.setGrade(grade);
        }
        System.out.println("[Info] Student with ID: " + id + " was updated successfully!");
    }

    public static void viewStudent(int id) {
        Student student = findStudentById(id);
        if (student == null) {
            System.out.println("[Error] Student with ID: " + id + "not found!");
            return;
        }
        System.out.println(student);
    }

    public static void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("[Info] No students in the system.");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }

    private static Student findStudentById(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    public static int getTotalStudents() {
        return totalStudents;
    }
}
