package StudentManagementSystem.src;

/**
 * Represents a single student record.
 * Kept as a plain data holder (a "model") — no GUI code belongs in here.
 * This separation is worth keeping for the rest of the assignment too:
 * Course, Enrollment, and Grade should each get their own simple model class.
 */
public class Student {
    private String id;
    private String name;
    private String email;

    public Student(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "(" + id + ") " + name;
    }
}
