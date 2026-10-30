import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class FirstWindow {
    public static void main(String[] x) {
        // 1. JFrame එකක් හදාගැනීම (ප්‍රධාන කවුළුව)
        JFrame frame = new JFrame("මගේ පළමු Java Swing ඇප් එක");

        // 2. Window එකේ ප්‍රමාණය (Width: 400 pixels, Height: 300 pixels)
        frame.setSize(400, 300);

        // 3. Close කළාම ඇප් එක සම්පූර්ණයෙන්ම close වෙන්න දෙන විධානය
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 4. කවුළුව Screen එකේ මැදටම එන්න සකස් කිරීම
        frame.setLocationRelativeTo(null);

        // 5. පුංචි Text එකක් Window එක මැදට දාමු
        JLabel label = new JLabel("স্বাগত! Welcome to Java Swing!", SwingConstants.CENTER);
        frame.add(label);

        // 6. අවසාන වශයෙන් Window එක පෙනෙනස් සේ (Visible) කිරීම
        frame.setVisible(true);
    }
}