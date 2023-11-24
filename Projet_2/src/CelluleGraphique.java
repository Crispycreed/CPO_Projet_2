
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.JButton;

/**
 * Représente une cellule graphique associée à une cellule lumineuse. Cette
 * classe étend JButton pour fournir une représentation visuelle d'une cellule
 * lumineuse dans le jeu. La couleur de la cellule est déterminée par la valeur
 * de la cellule lumineuse associée. Les cellules vides sont affichées en blanc,
 * tandis que les cellules avec des valeurs ont une couleur spécifique. La
 * valeur est affichée au centre de la cellule.
 *
 * @author ordim
 */
public class CelluleGraphique extends JButton {

    int largeur; // largeur en pixel de la cellule
    int hauteur; // hauteur en pixel de la cellule
    Cellule celluleLumineuseAssociee;

    /**
     * Constructeur de la classe CelluleGraphique.
     *
     * @param celluleLumineuseAssociee La cellule lumineuse associée à cette
     * cellule graphique.
     * @param l La largeur de la cellule graphique.
     * @param h La hauteur de la cellule graphique.
     */
    public CelluleGraphique(Cellule celluleLumineuseAssociee, int l, int h) {
        this.largeur = l;
        this.hauteur = h;
        this.celluleLumineuseAssociee = celluleLumineuseAssociee;
    }

    /**
     * Obtient la couleur associée à une valeur donnée. Les cellules vides sont
     * blanches. Les valeurs de 1 à 3 ont des couleurs spécifiques. À partir de
     * 3, chaque multiple de 3 réduit le composant vert de 10 unités.
     *
     * @param valeur La valeur de la cellule lumineuse.
     * @return La couleur associée à la valeur.
     */
    private Color obtenirCouleur(int valeur) {
        if (valeur == 0) {
            return Color.WHITE;
        } else if (valeur == 1) {
            return new Color(173, 216, 230);  // Bleu clair
        } else if (valeur == 2) {
            return new Color(128, 128, 128);  // Gris
        } else if (valeur == 3) {
            return new Color(255, 255, 0);  // Jaune
        } else {
            int maxRed = 255;
            int maxGreen = 255;

            // Calculer les composantes RGB en fonction de la valeur
            int red = maxRed;
            int green = maxGreen - ((valeur - 3) / 3) * 15;
            int blue = 0;

            // Assurer que green reste dans les limites de 0 à 255
            green = Math.max(0, Math.min(maxGreen, green));

            return new Color(red, green, blue);
        }
    }

    /**
     * Méthode gérant le dessin de la cellule. Cette méthode est appelée
     * automatiquement lorsque la cellule doit être dessinée. Elle remplit le
     * rectangle avec la couleur de fond, ajoute une bordure noire, définit la
     * couleur du texte en fonction de la valeur de la cellule, puis affiche la
     * valeur au centre de la cellule.
     *
     * @param g L'objet Graphics utilisé pour dessiner la cellule.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int valeur2 = celluleLumineuseAssociee.getValeur();
        int w = this.getWidth();
        int h = this.getHeight();

        // Définir la couleur de fond
        if (celluleLumineuseAssociee.estVide()) {
            g.setColor(Color.white);
        } else {
            g.setColor(obtenirCouleur(valeur2));
        }

        // Remplir le rectangle avec la couleur de fond
        g.fillRect(0, 0, w, h);

        // Ajouter une bordure noire
        g.setColor(Color.black);
        g.drawRect(0, 0, w - 1, h - 1);

        // Définir la couleur du texte
        if (celluleLumineuseAssociee.getValeur() == 0) {
            g.setColor(Color.white);
        } else {
            g.setColor(Color.black);
        }

        // Définir la taille de la police (1.5x en hauteur)
        int fontSize = (int) (h / 5);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, fontSize));

        // Afficher la valeur au centre de la cellule
        String valeur = String.valueOf(celluleLumineuseAssociee.getValeur());
        FontMetrics fontMetrics = g.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(valeur);
        int textHeight = fontMetrics.getHeight();
        int x = (w - textWidth) / 2;
        int y = (h - textHeight) / 2 + fontMetrics.getAscent();
        g.drawString(valeur, x, y);
    }
}
