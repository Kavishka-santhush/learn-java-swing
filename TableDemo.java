import javax.swing.*;
import java.awt.*;

public class TableDemo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Student Table Demo");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // 1. ඩේටා ටේබල් එකට අවශ්‍ය තීරු නම් (Column Names)
        String[] columnNames = {"ID", "නම (Name)", "ලකුණු (Marks)", "ශ්‍රේණිය (Grade)"};

        // 2. ටේබල් එක ඇතුළේ පිරෙන දත්ත (Data Rows)
        Object[][] data = {
            {1, "කවිෂ්ක", 85, "A"},
            {2, "නුවන්", 72, "B"},
            {3, "චමෝද්", 90, "A"},
            {4, "මධුෂාන්", 65, "C"},
              {1, "කවිෂ්ක", 85, "A"},
            {2, "නුවන්", 72, "B"},
            {3, "චමෝද්", 90, "A"},
            {4, "මධුෂාන්", 65, "C"},
              {1, "කවිෂ්ක", 85, "A"},
            {2, "නුවන්", 72, "B"},
            {3, "චමෝද්", 90, "A"},
            {4, "මධුෂාන්", 65, "C"},
              {1, "කවිෂ්ක", 85, "A"},
            {2, "නුවන්", 72, "B"},
            {3, "චමෝද්", 90, "A"},
            {4, "මධුෂාන්", 65, "C"},
        };

        // 3. JTable එක නිර්මාණය කිරීම
        JTable table = new JTable(data, columnNames);

        // 4. ටේබල් එක Scroll කරගන්න පුළුවන් වෙන්න JScrollPane එකක් ඇතුළට දාමු 
        // (ටේබල් එක දිග වැඩි වුණොත් Scroll bar එකක් එන්න මේක උදව් වෙනවා)
        JScrollPane scrollPane = new JScrollPane(table);

        // 5. Frame එකේ මැදට (CENTER) JScrollPane එක එකතු කිරීම
        frame.add(scrollPane, BorderLayout.CENTER);

        // උඩින් පුංචි මාතෘකාවක් දාමු
        JLabel titleLabel = new JLabel("සිසුන්ගේ ලකුණු ලැයිස්තුව", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        frame.add(titleLabel, BorderLayout.NORTH);

        frame.setVisible(true);
    }
}