
import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ordim
 */
class GrilleDeJeu {

    Cellule[][] matriceCellules;
    int nbLignes;
    int nbColonnes;

    public GrilleDeJeu(int p_nbLignes, int p_nbColonnes) {
        nbLignes = p_nbLignes;
        nbColonnes = p_nbColonnes;
        matriceCellules = new Cellule[nbLignes][nbColonnes];
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                matriceCellules[i][j] = new Cellule();
            }
        }
    }

    // Méthode pour initialiser deux cellules aléatoires avec les valeurs 1 et 2
    public void initialiserCellulesAleatoires() {
        Random random = new Random();
        int count = 0;

        while (count < 2) {
            int randomLigne = random.nextInt(nbLignes);
            int randomColonne = random.nextInt(nbColonnes);

            if (matriceCellules[randomLigne][randomColonne].estVide()) {
                // Si la cellule est vide, lui attribuer la valeur 1 ou 2
                int valeur = (count == 0) ? 1 : 2;
                matriceCellules[randomLigne][randomColonne].modifierValeur(valeur);
                count++;
            }
        }
    }

    public void deplacerToutesCellulesDroite() {
        for (int i = 0; i < nbLignes; i++) {
            for (int j = nbColonnes - 2; j >= 0; j--) {
                deplacerCelluleDroite(i, j);
            }
        }
    }

    private void deplacerCelluleDroite(int ligne, int colonne) {
        if (!matriceCellules[ligne][colonne].estVide()) {
            int valeurCourante = matriceCellules[ligne][colonne].getValeur();

            // Vérifier si la cellule peut se déplacer vers la droite
            if (colonne + 1 < nbColonnes && matriceCellules[ligne][colonne + 1].estVide()) {
                // Déplacer la cellule vers la droite
                matriceCellules[ligne][colonne + 1].modifierValeur(valeurCourante);
                matriceCellules[ligne][colonne].modifierValeur(0);
            }
        }
    }

public void deplacerToutesCellulesHaut() {
    for (int i = 1; i < nbLignes; i++) {
        for (int j = 0; j < nbColonnes; j++) {
            deplacerCelluleHaut(i, j);
        }
    }
}

private void deplacerCelluleHaut(int ligne, int colonne) {
    if (!matriceCellules[ligne][colonne].estVide()) {
        int valeurCourante = matriceCellules[ligne][colonne].getValeur();

        // Vérifier si la cellule peut se déplacer vers le haut
        if (ligne - 1 >= 0 && matriceCellules[ligne - 1][colonne].estVide()) {
            // Déplacer la cellule vers le haut
            matriceCellules[ligne - 1][colonne].modifierValeur(valeurCourante);
            matriceCellules[ligne][colonne].modifierValeur(0);
        }
    }
}


}
