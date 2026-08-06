package StudentManagementSystem.src;

import javax.swing.*;
import java.awt.*;

public class EnrollmentPanel extends JPanel {
    private StudentManager studentManager;
    private CourseManager courseManager;
    private JComboBox<Course> courseBox;
    private DefaultListModel<Student> listModel;

    public EnrollmentPanel(StudentManager studentManager, CourseManager courseManager) {
        this.studentManager = studentManager;
        this.courseManager = courseManager;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        formPanel.add(new JLabel("Courses: "));

        // ---- Course ComboBox (top) ----
        courseBox = new JComboBox<>();
        for (Course course : courseManager.getAllCourses()) {
            courseBox.addItem(course);
        }
        formPanel.add(courseBox);

        // ---- Eligible Students List (center) ----
        listModel = new DefaultListModel<>();
        JList<Student> eligibleList = new JList<>(listModel);

        courseBox.addActionListener(e -> {
            Course selected = (Course) courseBox.getSelectedItem();
            listModel.clear();
            if (selected != null) {
                for (Student student : studentManager.getEligibleStudents(selected.getId())) {
                    listModel.addElement(student);
                }
            }
        });

        add(formPanel, BorderLayout.NORTH);
        add(new JScrollPane(eligibleList), BorderLayout.CENTER);

        // ---- Enroll Button (bottom) ----
        JButton enrollButton = new JButton("Enroll");
        enrollButton.addActionListener(e -> {
            Course selectedCourse = (Course) courseBox.getSelectedItem();
            Student selectedStudent = eligibleList.getSelectedValue();
            if (selectedCourse != null && selectedStudent != null) {
                boolean isEnrolled = studentManager.isEnrolled(selectedStudent.getId(), selectedCourse.getId());
                if (!isEnrolled) {
                    studentManager.enroll(selectedStudent.getId(), selectedCourse.getId());
                    JOptionPane.showMessageDialog(this,
                            "Enrolled " + selectedStudent.getName() + " in " + selectedCourse.getName());
                    listModel.removeElement(selectedStudent);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to enroll student.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(enrollButton, BorderLayout.SOUTH);
    }

    public void refreshEligibleList() {
        Course selectedCourse = (Course) courseBox.getSelectedItem();
        listModel.clear();
        if (selectedCourse != null) {
            for (Student student : studentManager.getEligibleStudents(selectedCourse.getId())) {
                listModel.addElement(student);
            }
        }
    }

}
