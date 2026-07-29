import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
/**
 * Represents a single student. All fields are private (encapsulated);
 * outside classes may only read or change them through the public
 * getters/setters and the enrollInCourse/assignGrade behavior methods
 * defined here.
 */
public class Student {
 
    private String name;
    private String id;
    private List<Course> enrolledCourses;
    private Map<String, Double> grades; // key = course code, value = grade
 
    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.enrolledCourses = new ArrayList<>();
        this.grades = new HashMap<>(); // I use a HashMap here because I want to be able to look up a grade by course code quickly.
    }
 
    // ---- Getters ----totalEnrolledStudents
    public String getName() {
        return name;
    }
 
    public String getId() {
        return id;
    }
 
    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }
 
    public Map<String, Double> getGrades() {
        return grades;
    }
 
    // ---- Setters ----
    public void setName(String name) {
        this.name = name;
    }
 
    public void setId(String id) {
        this.id = id;
    }
 
    /**
     * Instance method: adds a course to THIS student's own list of
     * enrolled courses. Operates on this object's state, so it must
     * be an instance method, not static.
     *
     * @return true if the course was added, false if the student was
     *         already enrolled in it.
     */
    public boolean enrollInCourse(Course course) {
        if (enrolledCourses.contains(course)) {
            return false;
        }
        enrolledCourses.add(course);
        return true;
    }
 
    /**
     * Instance method: records a grade for this student in a course,
     * but only if the student is actually enrolled in that course.
     *
     * @return true if the grade was recorded, false if the student is
     *         not enrolled in the given course.
     */
    public boolean assignGrade(Course course, double grade) {
        if (!enrolledCourses.contains(course)) {
            return false;
        }
        grades.put(course.getCourseCode(), grade);
        return true;
    }
 
    public Double getGrade(Course course) {
        return grades.get(course.getCourseCode());
    }
 
    @Override
    public String toString() {
        return "[" + id + "] " + name;
    }
}
