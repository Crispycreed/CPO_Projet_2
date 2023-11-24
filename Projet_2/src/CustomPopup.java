
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;

public class CustomPopup extends JDialog {

    public CustomPopup(String message, String title) {
        setTitle(title);
        setModal(true);
        setLayout(new BorderLayout());

        JLabel label = new JLabel(message);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("SNAP ITC", Font.PLAIN, 24));

        add(label, BorderLayout.CENTER);

        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
