
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;

/**
 * Classe représentant une boîte de dialogue personnalisée (popup) dans Swing.
 * Étend la classe JDialog de Swing.
 */
public class CustomPopup extends JDialog {
// ----------------------------------------------------Methodes  

    /**
     * Constructeur qui crée une instance de CustomPopup avec un message et un
     * titre donnés.
     *
     * @param message Le message qui sera affiché dans la boîte de dialogue.
     * @param title Le titre de la boîte de dialogue.
     */
    public CustomPopup(String message, String title) {
        // Configuration de la boîte de dialogue
        setTitle(title);
        setModal(true);
        setLayout(new BorderLayout());

        // Configuration de l'étiquette du message
        JLabel label = new JLabel(message);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("SNAP ITC", Font.PLAIN, 24));

        // Ajout de l'étiquette au centre de la boîte de dialogue
        add(label, BorderLayout.CENTER);

        // Configuration de la taille et de la position de la boîte de dialogue
        setSize(300, 150);
        setLocationRelativeTo(null);

        // Configuration de l'opération de fermeture de la boîte de dialogue
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
