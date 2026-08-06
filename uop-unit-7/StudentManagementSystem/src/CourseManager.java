package StudentManagementSystem.src;

import java.util.ArrayList;
import java.util.List;

public class CourseManager {
    private List<Course> courses = new ArrayList<>();

    public void addCourse(Course c) {
        courses.add(c);
    }

    public Course findById(String id) {
        for (Course c : courses) {
            if (c.getId().equals(id))
                return c;
        }
        return null;
    }

    public Course findByName(String name) {
        for (Course c : courses) {
            if (c.getName().equals(name))
                return c;
        }
        return null;
    }

    public List<Course> getAllCourses() {
        return courses;
    }
}
