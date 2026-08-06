package StudentManagementSystem.src;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class GradePanel extends JPanel {
    private StudentManager studentManager;
    private CourseManager courseManager;
    private DefaultTableModel tableModel;
    private JTable table;
    private JComboBox<Student> studentBox;

    public GradePanel(StudentManager studentManager, CourseManager courseManager) {
        this.studentManager = studentManager;
        this.courseManager = courseManager;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        formPanel.add(new JLabel("Students: "));

        // ---- Student ComboBox (top) ----
        studentBox = new JComboBox<>();
        for (Student student : studentManager.getAllStudents()) {
            studentBox.addItem(student);
        }
        formPanel.add(studentBox);
        add(formPanel, BorderLayout.NORTH);

        studentBox.addActionListener(e -> {
            Student selectedStudent = (Student) studentBox.getSelectedItem();
            refreshTable(selectedStudent);
        });

        // ---- Courses & Grades Table (center) ----
        tableModel = new DefaultTableModel(new Object[] { "Course Name", "Grade" }, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ---- Grade Assignment (bottom) ----
        JComboBox<String> gradeBox = new JComboBox<>(new String[] { "A", "B", "C", "D", "F" });
        JButton assignButton = new JButton("Assign Grade");

        JPanel gradePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        gradePanel.add(new JLabel("Grade: "));
        gradePanel.add(gradeBox);
        gradePanel.add(assignButton);

        assignButton.addActionListener(e -> {
            Student selectedStudent = (Student) studentBox.getSelectedItem();
            int selectedRow = table.getSelectedRow();
            if (selectedStudent != null && selectedRow != -1) {
                String courseName = (String) tableModel.getValueAt(selectedRow, 0);
                Course course = courseManager.findByName(courseName);
                if (course != null) {
                    String selectedGrade = (String) gradeBox.getSelectedItem();
                    studentManager.assignGrade(selectedStudent.getId(), course.getId(), selectedGrade);
                    refreshTable(selectedStudent);
                } else {
                    JOptionPane.showMessageDialog(this, "Course not found. Please select a valid course.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a student and a course.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        add(gradePanel, BorderLayout.SOUTH);

    }

    public void refreshStudentList() {
        Student selectedStudent = (Student) studentBox.getSelectedItem();
        studentBox.removeAllItems();
        for (Student student : studentManager.getAllStudents()) {
            studentBox.addItem(student);
        }
        if (selectedStudent != null) {
            studentBox.setSelectedItem(selectedStudent);
        }
    }

    private void refreshTable(Student student) {
        tableModel.setRowCount(0);
        if (student != null) {
            for (String courseId : studentManager.getEnrolledCourses(student.getId())) {
                Course course = courseManager.findById(courseId);
                String grade = studentManager.getGrade(student.getId(), courseId);
                tableModel.addRow(new Object[] { course.getName(), grade != null ? grade : "-" });
            }
        }
    }
}
