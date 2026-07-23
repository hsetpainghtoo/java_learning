/**
 * Represents a single course offered by the university.
 *
 * Each Course object tracks its own capacity and current enrollment,
 * while a static (class-level) variable, totalEnrolledStudents, keeps
 * a running total of how many enrollments have happened across every
 * Course object that has ever been created. That single static counter
 * is what lets the whole program answer "how many students are enrolled
 * in courses overall?" without adding up every course individually.
 */
public class Course {
 
    // ---- Instance variables (belong to ONE course) ----
    private String courseCode;
    private String name;
    private int maxCapacity;
    private int currentEnrollment;
 
    // ---- Static variable (shared by ALL Course objects) ----
    private static int totalEnrolledStudents = 0;
 
    /**
     * Creates a new course with zero students currently enrolled.
     */
    public Course(String courseCode, String name, int maxCapacity) {
        this.courseCode = courseCode;
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.currentEnrollment = 0;
    }
 
    // ---- Getters (public, read-only access to private fields) ----
    public String getCourseCode() {
        return courseCode;
    }
 
    public String getName() {
        return name;
    }
 
    public int getMaxCapacity() {
        return maxCapacity;
    }
 
    public int getCurrentEnrollment() {
        return currentEnrollment;
    }
 
    /**
     * Instance method: is THIS course full? Depends on this object's
     * own currentEnrollment/maxCapacity, so it cannot be static.
     */
    public boolean isFull() {
        return currentEnrollment >= maxCapacity;
    }
 
    /**
     * Called by CourseManagement after a student has successfully been
     * added to this course. Bumps this course's own counter AND the
     * shared static counter.
     *
     * @return true if the enrollment count was incremented, false if
     *         the course was already full.
     */
    public boolean incrementEnrollment() {
        if (isFull()) {
            return false;
        }
        currentEnrollment++;
        totalEnrolledStudents++;
        return true;
    }
 
    /**
     * Static method: returns the total number of enrolled students
     * across every Course object that exists. Static because it
     * reports on the class as a whole, not on any single course.
     */
    public static int getTotalEnrolledStudents() {
        return totalEnrolledStudents;
    }
 
    @Override
    public String toString() {
        return courseCode + " - " + name + " (" + currentEnrollment + "/" + maxCapacity + " enrolled)";
    }
}
