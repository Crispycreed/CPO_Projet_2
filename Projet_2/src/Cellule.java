/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * La classe qui caractérise une cellule (peut être vide ou associée à un chiffre positif différent de 0)
 *
 * @author MAUMEJEAN DENIS
 */
public class Cellule {

    private int valeur; // La valeur de la cellule (0 si vide)
    private boolean vide; // Indique si la cellule est vide

    /**
     * Constructeur pour une cellule vide
     */
    public Cellule() {
        this.vide = true;
        this.valeur = 0;
    }

    /**
     * Méthode pour modifier la valeur de la cellule
     *
     * @param nouvelleValeur La nouvelle valeur à assigner à la cellule
     */
    public void modifierValeur(int nouvelleValeur) {
        if (nouvelleValeur < 0) {
            throw new IllegalArgumentException("La nouvelle valeur doit être un chiffre positif différent de 0.");
        }
        this.valeur = nouvelleValeur;
        this.vide = false;
    }

    /**
     * Méthode pour obtenir la valeur de la cellule
     *
     * @return La valeur de la cellule
     */
    public int getValeur() {
        return valeur;
    }

    /**
     * Méthode pour vérifier si la cellule est vide
     *
     * @return true si la cellule est vide, false sinon
     */
    public boolean estVide() {
        return vide;
    }

    // Méthode pour doubler la valeur de la cellule
    public void doubleValeur() {
        this.valeur *= 2;
    }

    // Méthode pour vider la cellule
    public void viderCellule() {
        this.valeur = 0;
    }

    /**
     * Redéfinition de la méthode toString pour afficher la cellule
     *
     * @return La représentation de la cellule sous forme de chaîne de
     * caractères
     */
    @Override
    public String toString() {
        return estVide() ? " " : String.valueOf(getValeur());
    }
}
