package StudentManagementSystem.src;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * The Students tab: a form to add students, and a JTable that displays
 * all of them. This class demonstrates every technique you'll need to
 * reuse for the Enrollment and Grades tabs:
 *
 * 1. JTable + DefaultTableModel -> displaying a live list of records
 * 2. refreshTable() -> the "dynamic update" pattern
 * 3. addStudent() -> validating input + JOptionPane errors
 *
 * Copy this file's structure for EnrollmentPanel and GradePanel. The
 * only real differences will be which fields are in the form and which
 * StudentManager/CourseManager methods you call.
 */
public class StudentPanel extends JPanel {
    private StudentManager manager;
    private DefaultTableModel tableModel;
    private JTable table;

    private JTextField idField = new JTextField(8);
    private JTextField nameField = new JTextField(12);
    private JTextField emailField = new JTextField(14);

    public StudentPanel(StudentManager manager) {
        this.manager = manager;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ---- Form (top) ----
        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        formPanel.add(new JLabel("ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(e -> addStudent());
        formPanel.add(addButton);

        // TODO (practice): add an "Update Student" button here.
        // It should call manager.updateStudent(id, name, email) instead
        // of manager.addStudent(...), then call refreshTable() the same
        // way. Show a JOptionPane error if findById(id) returns null
        // (i.e. no such student exists to update).

        JButton updateButton = new JButton("Update Student");
        updateButton.addActionListener(e -> {
            String id = idField.getText().trim();
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();

            // ---- Error handling ----
            if (id.isEmpty() || name.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "All fields are required.", "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (manager.findById(id) == null) {
                JOptionPane.showMessageDialog(this,
                        "No student with that ID exists.", "Student Not Found",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            manager.updateStudent(id, name, email);
            refreshTable();
            idField.setText("");
            nameField.setText("");
            emailField.setText("");
        });
        formPanel.add(updateButton);

        add(formPanel, BorderLayout.NORTH);

        // ---- Table (center) ----
        tableModel = new DefaultTableModel(new Object[] { "ID", "Name", "Email" }, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false; // view-only; editing happens through the form
            }
        };
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void addStudent() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();

        // ---- Error handling ----
        if (id.isEmpty() || name.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "All fields are required.", "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (manager.findById(id) != null) {
            JOptionPane.showMessageDialog(this,
                    "A student with that ID already exists.", "Duplicate ID",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        manager.addStudent(new Student(id, name, email));
        refreshTable(); // <-- this is the "dynamic update" the rubric wants
        idField.setText("");
        nameField.setText("");
        emailField.setText("");
    }

    /**
     * Clears and rebuilds the table from whatever StudentManager currently
     * holds. Call this after ANY change (add, update, delete) so the GUI
     * never shows stale data. This is the core idea behind every "dynamic
     * interface update" requirement in the rubric.
     */
    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Student s : manager.getAllStudents()) {
            tableModel.addRow(new Object[] { s.getId(), s.getName(), s.getEmail() });
        }
    }
}
