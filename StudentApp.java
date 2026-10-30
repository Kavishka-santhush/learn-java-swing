import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentApp {
    public static void main(String[] args) {
        // 1. ප්‍රධාන Window එක හැදීම
        JFrame frame = new JFrame("Student Management System");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10)); // හොද පරතරයක් (Gap) ලබාදීම

        // 2. උඩින් (North) Title එක දැමීම
        JLabel titleLabel = new JLabel("ශිෂ්‍ය ලියාපදිංචි කිරීමේ පද්ධතිය", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(titleLabel, BorderLayout.NORTH);

        // 3. Table එක සහ එහි Model එක සකස් කිරීම 
        // (DefaultTableModel පාවිච්චි කරන්නේ පස්සේ ඩේටා අලුතින් එකතු කරන්න පහසු වෙන්නයි)
        String[] columnNames = {"නම (Name)", "වයස (Age)", "පාඨමාලාව (Course)"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Table එක මැදට දමමු
        frame.add(scrollPane, BorderLayout.CENTER);

        // 4. යටින් (South) ඩේටා ඇතුළත් කරන Form එක සහ Button එක සකස් කිරීම
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JTextField nameField = new JTextField(10);
        JTextField ageField = new JTextField(5);
        JTextField courseField = new JTextField(10);
        JButton addButton = new JButton("Add Student");

        // Form එකට අදාළ Labels සහ Fields එකතු කිරීම
        inputPanel.add(new JLabel("නම:"));
        inputPanel.add(nameField);
        
        inputPanel.add(new JLabel("වයස:"));
        inputPanel.add(ageField);
        
        inputPanel.add(new JLabel("පාඨමාලාව:"));
        inputPanel.add(courseField);
        
        inputPanel.add(addButton);

        // Input Panel එක Frame එකේ යටට දමමු
        frame.add(inputPanel, BorderLayout.SOUTH);

        // 5. 'Add Student' බටන් එක ක්ලික් කළ විට සිදුවන දෙය (Event Handling)
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String age = ageField.getText();
                String course = courseField.getText();

                // හිස්ව තිබේදැයි පරීක්ෂා කිරීම
                if(name.isEmpty() || age.isEmpty() || course.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "කරුණාකර සියලුම විස්තර පුරවන්න!","දෝෂයක්", JOptionPane.WARNING_MESSAGE);
                } else {
                    // ඩේටා ටේබල් එකට අලුත් පේළියක් ලෙස එකතු කිරීම
                    tableModel.addRow(new Object[]{name, age, course});

                    // TextField පිරිසිදු කර දැමීම (Clear fields)
                    nameField.setText("");
                    ageField.setText("");
                    courseField.setText("");
                }
            }
        });

        // 6. Window එක පෙන්වීම
        frame.setVisible(true);
    }
}