import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SecondApp {
    public static void main(String[] args) {
        // 1. Frame එක සකස් කිරීම
        JFrame frame = new JFrame("User Input Demo");
        frame.setSize(450, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
        // අපි Layout එක null කරනවා (සම්පූර්ණයෙන්ම අපිට අවශ්‍ය තැන්වලට 
        // Components පාලනය කරගන්න (Absolute Positioning) පහසු වෙන්න)
        frame.setLayout(null);

        // 2. Name එක ඉල්ලන Label එකක්
        JLabel nameLabel = new JLabel("ඔයාගේ නම ලියන්න:");
        nameLabel.setBounds(30, 30, 150, 25); // (x, y, width, height)
        frame.add(nameLabel);

        // 3. නම ටයිප් කරන Text Field එකක්
        JTextField nameTextField = new JTextField();
        nameTextField.setBounds(180, 30, 200, 25);
        frame.add(nameTextField);

        // 4. Click කරන්න බටන් එකක්
        JButton submitButton = new JButton("Click Me");
        submitButton.setBounds(180, 70, 100, 30);
        frame.add(submitButton);

        // 5. ප්‍රතිඵලය පෙන්වන Output Label එකක්
        JLabel resultLabel = new JLabel("");
        resultLabel.setBounds(30, 130, 350, 25);
        frame.add(resultLabel);

        // 6. බටන් එක ක්ලික් කළාම සිදුවන දෙය (Event Handling)
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Text Field එකේ තියෙන නම ලබා ගැනීම
                String name = nameTextField.getText();
                
                if(name.isEmpty()) {
                    resultLabel.setText("කරුණාකර නමක් ඇතුළත් කරන්න!");
                } else {
                    resultLabel.setText("ආයුබෝවන්, " + name + "! Java Swing වලට සාදරයෙන් පිළිගනිමු.");
                }
            }
        });

        // 7. Window එක පෙන්වීම
        frame.setVisible(true);
    }
}