package StudentManagementSystem.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Holds all student data and the operations on it, completely separate
 * from any GUI code. StudentPanel calls into this class — it never
 * touches a List<Student> directly. This separation is what makes
 * "dynamic updates" straightforward: the GUI just asks this class for
 * the current data whenever it needs to redraw.
 *
 * TODO (for Enrollment/Grades tabs): build a CourseManager the same way,
 * plus a way to associate students with courses (e.g. a
 * Map<String, List<String>> of studentId -> list of courseIds for
 * enrollment, and a Map<String, Map<String,String>> of
 * studentId -> courseId -> grade for grades).
 */
public class StudentManager {
    private List<Student> students = new ArrayList<>();
    private Map<String, List<String>> enrollments = new HashMap<>(); // studentId -> courseIds
    private Map<String, Map<String, String>> grades = new HashMap<>(); // studentId -> (courseId -> grade)

    public void addStudent(Student s) {
        students.add(s);
    }

    public Student findById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id))
                return student;
        }
        return null;
    }

    public boolean updateStudent(String id, String newName, String newEmail) {
        Student student = findById(id);
        if (student == null)
            return false;
        student.setName(newName);
        student.setEmail(newEmail);
        return true;
    }

    public List<Student> getAllStudents() {
        return students;
    }

    // "Who's enrolled where?" tracker

    public void enroll(String studentId, String courseId) {
        enrollments.computeIfAbsent(studentId, k -> new ArrayList<>()).add(courseId);
    }

    public boolean isEnrolled(String studentId, String courseId) {
        return enrollments.containsKey(studentId) && enrollments.get(studentId).contains(courseId);
    }

    public List<Student> getEligibleStudents(String courseId) {
        List<Student> eligible = new ArrayList<>();
        for (Student student : students) {
            if (!isEnrolled(student.getId(), courseId)) {
                eligible.add(student);
            }
        }
        return eligible;
    }

    // Grades tracker
    public void assignGrade(String studentId, String courseId, String grade) {
        grades.computeIfAbsent(studentId, k -> new HashMap<>()).put(courseId, grade);
    }

    public String getGrade(String studentId, String courseId) {
        if (grades.containsKey(studentId) && grades.get(studentId).containsKey(courseId)) {
            return grades.get(studentId).get(courseId);
        }
        return null;
    }

    public List<String> getEnrolledCourses(String studentId) {
        return enrollments.getOrDefault(studentId, new ArrayList<>());
    }
}
