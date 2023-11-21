/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TEST;

/**
 * La classe qui va définir l'état de la cellule lumineuse
 *
 * @author MAUMEJEAN DENIS
 */
public class CelluleLumineuse {

    private boolean etat;

    /**
     * Constructeur par défaut, initialise l'état à éteinte (false)
     */
    public CelluleLumineuse() {
        etat = false;
    }

    /**
     * Méthode pour activer la cellule en inversant son état
     */
    public void activerCellule() {
        etat = !etat;
    }

    /**
     * Méthode pour éteindre la cellule (la met dans l'état éteinte)
     */
    public void eteindreCellule() {
        etat = false;
    }

    /**
     * Méthode pour vérifier si la cellule est éteinte ou allumée
     *
     * @return etteint : etat = true ; allumée etat = false
     */
    public boolean estEteint() {
        return !etat;
    }

    /**
     * Méthode pour obtenir l'état actuel de la cellule (true si allumée, false
     * si éteinte)
     *
     * @return etat
     */
    public boolean getEtat() {
        return etat;
    }

    /**
     * définition de la méthode toString pour afficher l'état de la cellule
     *
     * @return x si etat = false O si etat = true
     */
    @Override
    public String toString() {
        return etat ? "X" : "O";
    }
}
