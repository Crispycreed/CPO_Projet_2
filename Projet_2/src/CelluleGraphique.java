
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.JButton;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ordim
 */
public class CelluleGraphique extends JButton {

    int largeur; // largeur en pixel de la cellule
    int hauteur; // hauteur en pixel de la cellule
    Cellule celluleLumineuseAssociee;

    /**
     * Représente une cellule graphique associée à une cellule lumineuse.
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

    // Methode gérant le dessin de la cellule
    /**
     * sert a mettre de la couleur dans les cases du jeu
     *
     * @param g
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
        } else if (valeur2 == 0) {
            g.setColor(Color.WHITE);
        } else if (valeur2 == 1) {
            g.setColor(new Color(173, 216, 230));  // Bleu clair
        } else if (valeur2 == 2) {
            g.setColor(new Color(128, 128, 128));  // Gris
        } else if (valeur2 == 3) {
            g.setColor(new Color(255, 0, 0));  // Rouge
        } else {
            // Ajoutez ici des couleurs spécifiques pour d'autres valeurs si nécessaire
            g.setColor(Color.RED);  // Couleur par défaut
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
        int fontSize = (int) (h / 2);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, fontSize));

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
