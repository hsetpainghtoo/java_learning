import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Acts as the central coordinator for the whole system. It does not
 * represent any single real-world entity the way Student or Course
 * does — instead it holds the master lists of courses and students
 * and the overall grade for each student, all as private static
 * variables, and exposes static methods for the administrator
 * interface (Main) to call. Because there is only ever one "course
 * catalog" for the whole university, static state is the right choice
 * here (compare: Student/Course use INSTANCE variables because each
 * student and each course needs its own separate copy of its data).
 */
public class CourseManagement {

    private static List<Course> courses = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();
    private static Map<String, Double> overallGrades = new HashMap<>(); // key = student ID

    /**
     * Creates a new Course object from the given details and adds it
     * to the master list of courses.
     */
    public static Course addCourse(String courseCode, String name, int maxCapacity) {
        Course course = new Course(courseCode, name, maxCapacity);
        courses.add(course);
        return course;
    }

    /**
     * Creates a new Student object and adds it to the master list of
     * students.
     */
    public static Student addStudent(String name, String id) {
        Student student = new Student(name, id);
        students.add(student);
        return student;
    }

    /**
     * Enrolls a student in a course by delegating to Student's own
     * instance method, then updates the course's enrollment counters.
     * Checks the course's capacity first so a full course cannot be
     * over-enrolled.
     */
    public static boolean enrollStudent(Student student, Course course) {
        if (course.isFull()) {
            return false;
        }
        boolean enrolled = student.enrollInCourse(course);
        if (enrolled) {
            course.incrementEnrollment();
        }
        return enrolled;
    }

    /**
     * Assigns a grade to a student for a course by delegating to
     * Student's own instance method, then refreshes that student's
     * overall grade.
     */
    public static boolean assignGrade(Student student, Course course, double grade) {
        boolean assigned = student.assignGrade(course, grade);
        if (assigned) {
            calculateOverallGrade(student);
        }
        return assigned;
    }

    /**
     * Calculates a student's overall grade as the average of every
     * grade they have received so far, and stores it in the static
     * overallGrades map for quick lookup later.
     */
    public static double calculateOverallGrade(Student student) {
        Map<String, Double> grades = student.getGrades();
        if (grades.isEmpty()) {
            overallGrades.put(student.getId(), 0.0);
            return 0.0;
        }
        double sum = 0;
        for (double g : grades.values()) {
            sum += g;
        }
        double average = sum / grades.size();
        overallGrades.put(student.getId(), average);
        return average;
    }

    public static Student getTopStudent() {
        Student topStudent = null;
        double highestGrade = -1; // Start with -1 so that any real grade (0-100) will be higher
        if (students.isEmpty()) {
            return null; // No students to evaluate
        }
        for (Student s : students) {
            double overallGrade = overallGrades.getOrDefault(s.getId(), 0.0);
            if (overallGrade > highestGrade) {
                highestGrade = overallGrade;
                topStudent = s;
            }
        }
        return topStudent;
    }

    public static List<Course> getCourses() {
        return courses;
    }

    public static List<Student> getStudents() {
        return students;
    }

    public static Course findCourseByCode(String code) {
        for (Course c : courses) {
            if (c.getCourseCode().equalsIgnoreCase(code)) {
                return c;
            }
        }
        return null;
    }

    public static Student findStudentById(String id) {
        for (Student s : students) {
            if (s.getId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null;
    }
}
