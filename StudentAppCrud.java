import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentAppCrud {
    public static void main(String[] args) {
        // 1. ප්‍රධාන Window එක හැදීම
        JFrame frame = new JFrame("Student Management - CRUD Operations");
        frame.setSize(650, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));

        // 2. Title එක දැමීම
        JLabel titleLabel = new JLabel("ශිෂ්‍ය කළමනාකරණ පද්ධතිය (CRUD)", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(titleLabel, BorderLayout.NORTH);

        // 3. Table එක සකස් කිරීම
        String[] columnNames = {"නම (Name)", "වයස (Age)", "පාඨමාලාව (Course)"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // 4. යටින් Inputs සහ Buttons සකස් කිරීම සඳහා Panel එකක්
        JPanel inputPanel = new JPanel(new GridLayout(2, 1, 5, 5));

        // Text Fields පාලනය කරන Panel එක
        JPanel fieldsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JTextField nameField = new JTextField(10);
        JTextField ageField = new JTextField(5);
        JTextField courseField = new JTextField(10);

        fieldsPanel.add(new JLabel("නම:"));
        fieldsPanel.add(nameField);
        fieldsPanel.add(new JLabel("වයස:"));
        fieldsPanel.add(ageField);
        fieldsPanel.add(new JLabel("පාඨමාලාව:"));
        fieldsPanel.add(courseField);

        // බටන් (Buttons) පාලනය කරන Panel එක
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        JButton addButton = new JButton("Add Student");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        inputPanel.add(fieldsPanel);
        inputPanel.add(buttonPanel);
        frame.add(inputPanel, BorderLayout.SOUTH);

        // 5. ටේබල් එකේ පේළියක් (Row) ක්ලික් කළ විට Text Fields වලට ඩේටා මාරු වීම
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    nameField.setText(tableModel.getValueAt(selectedRow, 0).toString());
                    ageField.setText(tableModel.getValueAt(selectedRow, 1).toString());
                    courseField.setText(tableModel.getValueAt(selectedRow, 2).toString());
                }
            }
        });

        // 6. 'Add Student' බටන් එකේ ක්‍රියාව
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            String age = ageField.getText();
            String course = courseField.getText();

            if (name.isEmpty() || age.isEmpty() || course.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "කරුණාකර සියලුම විස්තර පුරවන්න!", "දෝෂයක්", JOptionPane.WARNING_MESSAGE);
            } else {
                tableModel.addRow(new Object[]{name, age, course});
                clearFields(nameField, ageField, courseField);
            }
        });

        // 7. 'Delete' බටන් එකේ ක්‍රියාව
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                tableModel.removeRow(selectedRow);
                clearFields(nameField, ageField, courseField);
            } else {
                JOptionPane.showMessageDialog(frame, "කරුණාකර මකා දැමිය යුතු පේළියක් (Row) තෝරන්න!", "අවවාදයක්", JOptionPane.WARNING_MESSAGE);
            }
        });

        // 8. 'Update' බටන් එකේ ක්‍රියාව
        updateButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String name = nameField.getText();
                String age = ageField.getText();
                String course = courseField.getText();

                if (name.isEmpty() || age.isEmpty() || course.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "කරුණාකර සියලුම විස්තර පුරවන්න!", "දෝෂයක්", JOptionPane.WARNING_MESSAGE);
                } else {
                    // ටේබල් එකේ අදාළ අගයන් අලුත් අගයන් මඟින් වෙනස් කිරීම (Update)
                    tableModel.setValueAt(name, selectedRow, 0);
                    tableModel.setValueAt(age, selectedRow, 1);
                    tableModel.setValueAt(course, selectedRow, 2);
                    
                    clearFields(nameField, ageField, courseField);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "කරුණාකර යාවත්කාලීන කළ යුතු පේළියක් (Row) තෝරන්න!", "අවවාදයක්", JOptionPane.WARNING_MESSAGE);
            }
        });

        frame.setVisible(true);
    }

    // Text Fields පිරිසිදු කිරීමට උදව් වන පොඩි method එකක්
    private static void clearFields(JTextField name, JTextField age, JTextField course) {
        name.setText("");
        age.setText("");
        course.setText("");
    }
}