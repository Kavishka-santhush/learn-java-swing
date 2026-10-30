import javax.swing.*;
import java.awt.*;

public class LayoutDemo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Layout Managers Demo");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // 1. BorderLayout පාවිච්චි කිරීම (මෙය JFrame එකේ default layout එකම තමයි)
        frame.setLayout(new BorderLayout());

        // 2. උඩින් (North) Title එකක් දාමු
        JLabel titleLabel = new JLabel("මගේ Dashboard එක", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(titleLabel, BorderLayout.NORTH);

        // 3. යටින් (South) Status bar එකක් දාමු
        JLabel statusLabel = new JLabel("Status: Ready");
        frame.add(statusLabel, BorderLayout.SOUTH);

        // 4. මැදට (Center) බටන් කිහිපයක් දාන්න Panel එකක් පාවිච්චි කරමු
        JPanel centerPanel = new JPanel();
        // Panel එකට FlowLayout දෙනවා
        centerPanel.setLayout(new FlowLayout());

        centerPanel.add(new JButton("Button 1"));
        centerPanel.add(new JButton("Button 2"));
        centerPanel.add(new JButton("Button 3"));

        // Center Panel එක JFrame එකේ CENTER එකට එකතු කිරීම
        frame.add(centerPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}