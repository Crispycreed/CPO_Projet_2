/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TEST;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;

/**
 *
 * @author MAUMEJEAN DENIS
 */
public class CelluleGraphique extends JButton {

    int largeur; // largeur en pixel de la cellule
    int hauteur; // hauteur en pixel de la cellule
    CelluleLumineuse celluleLumineuseAssociee;

    /**
     * Représente une cellule graphique associée à une cellule lumineuse.
     *
     * @param celluleLumineuseAssociee La cellule lumineuse associée à cette
     * cellule graphique.
     * @param l La largeur de la cellule graphique.
     * @param h La hauteur de la cellule graphique.
     */
    public CelluleGraphique(CelluleLumineuse celluleLumineuseAssociee, int l, int h) {
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
        int w = this.getWidth();
        int h = this.getHeight();
        if (celluleLumineuseAssociee.estEteint() == true) {
            g.setColor(Color.black);
        } else {
            g.setColor(Color.orange);
        }
        g.fillOval(2, 2, w - 4, h - 4);
    }
}
