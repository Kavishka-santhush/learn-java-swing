import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MiniCalculator {
    public static void main(String[] args) {
        // 1. Frame එක සකස් කිරීම
        JFrame frame = new JFrame("Mini Calculator");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        // 2. පළමු අංකය සඳහා Label සහ TextField
        JLabel label1 = new JLabel("පළමු අංකය:");
        label1.setBounds(30, 30, 100, 25);
        frame.add(label1);

        JTextField text1 = new JTextField();
        text1.setBounds(150, 30, 180, 25);
        frame.add(text1);

        // 3. දෙවන අංකය සඳහා Label සහ TextField
        JLabel label2 = new JLabel("දෙවන අංකය:");
        label2.setBounds(30, 70, 100, 25);
        frame.add(label2);

        JTextField text2 = new JTextField();
        text2.setBounds(150, 70, 180, 25);
        frame.add(text2);

        // 4. එකතු කිරීමේ බටන් එක (Add Button)
        JButton addButton = new JButton("එකතු කරන්න (+)");
        addButton.setBounds(150, 110, 180, 30);
        frame.add(addButton);

        // 5. ප්‍රතිඵලය පෙන්වන Label එක
        JLabel resultLabel = new JLabel("ප්‍රතිඵලය: ");
        resultLabel.setBounds(30, 160, 300, 25);
        frame.add(resultLabel);

        // 6. බටන් එක ක්ලික් කළ විට ගණනය කිරීම සිදුකිරීම
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Text Fields වල ඇති අංක ලබාගෙන ඒවා double වලට (Numbers) හරවා ගැනීම
                    double num1 = Double.parseDouble(text1.getText());
                    double num2 = Double.parseDouble(text2.getText());
                    
                    // ගණනය කිරීම
                    double sum = num1 + num2;
                    
                    // ප්‍රතිඵලය පෙන්වීම
                    resultLabel.setText("ප්‍රතිඵලය: " + sum);
                    
                } catch (NumberFormatException ex) {
                    // අංක වෙනුවට වෙනත් අකුරක් හෝ හිස්ව තිබුණහොත් Error එකක් පෙන්වීම
                    JOptionPane.showMessageDialog(frame, "කරුණාකර වලංගු අංක දෙකක් ඇතුළත් කරන්න!", "දෝෂයක් (Error)", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 7. Window එක පෙන්වීම
        frame.setVisible(true);
    }
}