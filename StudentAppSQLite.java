import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class StudentAppSQLite {
    private static Connection connection;
    private static DefaultTableModel tableModel;
    private static JTable table;
    private static JTextField nameField, ageField, courseField;
    private static int selectedStudentId = -1; // ඩේටාබේස් එකේ ID එක හඳුනා ගැනීමට

    public static void main(String[] args) {
        // 1. ඩේටාබේස් සම්බන්ධතාවය සහ Table එක සකස් කිරීම
        connectDatabase();
        createTable();

        // 2. ප්‍රධාන Window එක හැදීම
        JFrame frame = new JFrame("Student Management - SQLite Database");
        frame.setSize(700, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));

        // Title එක
        JLabel titleLabel = new JLabel("ශිෂ්‍ය කළමනාකරණ පද්ධතිය (SQLite සමඟ)", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(titleLabel, BorderLayout.NORTH);

        // 3. Table එක සකස් කිරීම
        String[] columnNames = {"ID", "නම (Name)", "වයස (Age)", "පාඨමාලාව (Course)"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // ටේබල් එකෙන් ನೇರವಾಗಿ එඩිට් කිරීම වැළැක්වීමට
            }
        };
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // 4. යටින් Inputs සහ Buttons සකස් කිරීම
        JPanel inputPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        
        JPanel fieldsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        nameField = new JTextField(10);
        ageField = new JTextField(5);
        courseField = new JTextField(10);

        fieldsPanel.add(new JLabel("නම:"));
        fieldsPanel.add(nameField);
        fieldsPanel.add(new JLabel("වයස:"));
        fieldsPanel.add(ageField);
        fieldsPanel.add(new JLabel("පාඨමාලාව:"));
        fieldsPanel.add(courseField);

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

        // 5. ඇප් එක ඕපන් වන විට ඩේටාබේස් එකේ ඇති ඩේටා ටේබල් එකට ਲੋඩ් කිරීම
        loadDataToTable();

        // 6. ටේබල් එකේ පේළියක් ක්ලික් කළ විට
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    selectedStudentId = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
                    nameField.setText(tableModel.getValueAt(selectedRow, 1).toString());
                    ageField.setText(tableModel.getValueAt(selectedRow, 2).toString());
                    courseField.setText(tableModel.getValueAt(selectedRow, 3).toString());
                }
            }
        });

        // 7. 'Add Student' බටන් එකේ ක්‍රියාව (Database එකට Insert කිරීම)
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            String age = ageField.getText();
            String course = courseField.getText();

            if (name.isEmpty() || age.isEmpty() || course.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "කරුණාකර සියලුම විස්තර පුරවන්න!", "දෝෂයක්", JOptionPane.WARNING_MESSAGE);
            } else {
                try {
                    String query = "INSERT INTO students (name, age, course) VALUES (?, ?, ?)";
                    PreparedStatement pstmt = connection.prepareStatement(query);
                    pstmt.setString(1, name);
                    pstmt.setString(2, age);
                    pstmt.setString(3, course);
                    pstmt.executeUpdate();
                    pstmt.close();

                    loadDataToTable(); // ටේබල් එක අලුත් කිරීම
                    clearFields();
                    JOptionPane.showMessageDialog(frame, "සාර්ථකව දත්ත ඇතුළත් කරන ලදී!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // 8. 'Delete' බටන් එකේ ක්‍රියාව (Database එකෙන් Delete කිරීම)
        deleteButton.addActionListener(e -> {
            if (selectedStudentId != -1) {
                int confirm = JOptionPane.showConfirmDialog(frame, "ඔබට මෙම වාර්තාව මකා දැමීමට අවශ්‍යද?", "තහවුරු කිරීම", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        String query = "DELETE FROM students WHERE id = ?";
                        PreparedStatement pstmt = connection.prepareStatement(query);
                        pstmt.setInt(1, selectedStudentId);
                        pstmt.executeUpdate();
                        pstmt.close();

                        loadDataToTable();
                        clearFields();
                        JOptionPane.showMessageDialog(frame, "සාර්ථකව මකා දමන ලදී!");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(frame, "කරුණාකර මකා දැමිය යුතු පේළියක් තෝරන්න!", "අවවාදයක්", JOptionPane.WARNING_MESSAGE);
            }
        });

        // 9. 'Update' බටන් එකේ ක්‍රියාව (Database එකේ Update කිරීම)
        updateButton.addActionListener(e -> {
            if (selectedStudentId != -1) {
                String name = nameField.getText();
                String age = ageField.getText();
                String course = courseField.getText();

                if (name.isEmpty() || age.isEmpty() || course.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "කරුණාකර සියලුම විස්තර පුරවන්න!", "දෝෂයක්", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        String query = "UPDATE students SET name = ?, age = ?, course = ? WHERE id = ?";
                        PreparedStatement pstmt = connection.prepareStatement(query);
                        pstmt.setString(1, name);
                        pstmt.setString(2, age);
                        pstmt.setString(3, course);
                        pstmt.setInt(4, selectedStudentId);
                        pstmt.executeUpdate();
                        pstmt.close();

                        loadDataToTable();
                        clearFields();
                        JOptionPane.showMessageDialog(frame, "සාර්ථකව යාවත්කාලීන කරන ලදී!");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(frame, "කරුණාකර යාවත්කාලීන කළ යුතු පේළියක් තෝරන්න!", "අවවාදයක්", JOptionPane.WARNING_MESSAGE);
            }
        });

        frame.setVisible(true);
    }

    // SQLite Database එක සමඟ සම්බන්ධ වීමේ Method එක
    private static void connectDatabase() {
        try {
            // මෙයින් project ෆෝල්ඩර් එක ඇතුළේ 'school.db' නමින් ෆයිල් එකක් හැදේ
            connection = DriverManager.getConnection("jdbc:sqlite:school.db");
            System.out.println("Database Connected Successfully!");
        } catch (SQLException e) {
            System.out.println("Database Connection Failed: " + e.getMessage());
        }
    }

    // ඩේටාබේස් එකේ 'students' table එක සකස් කිරීම
    private static void createTable() {
        try {
            Statement stmt = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS students (" +
                         "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                         "name TEXT NOT NULL, " +
                         "age TEXT NOT NULL, " +
                         "course TEXT NOT NULL)";
            stmt.execute(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ඩේටාබේස් එකෙන් ඩේටා ලබාගෙන JTable එකට පිරවීම
    private static void loadDataToTable() {
        try {
            tableModel.setRowCount(0); // පරණ rows ඉවත් කර නැවත පැටවීම
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String age = rs.getString("age");
                String course = rs.getString("course");
                tableModel.addRow(new Object[]{id, name, age, course});
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Text Fields පිරිසිදු කිරීම සහ Selection එක Reset කිරීම
    private static void clearFields() {
        nameField.setText("");
        ageField.setText("");
        courseField.setText("");
        selectedStudentId = -1;
    }
}