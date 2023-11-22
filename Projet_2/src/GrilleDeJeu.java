
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

    public void additionnerCellulesAdjacentesVersLaDroite() {
        for (int ligne = nbLignes -1 ; ligne >= 0; ligne-- ) {
            for (int colonne = nbColonnes - 2; colonne >= 0; colonne--) {
                Cellule celluleCourante = matriceCellules[ligne][colonne];

                if (celluleCourante.getValeur() != 0) {
                    // Trouver la première cellule non vide à droite
                    int colonneDroite = colonne + 1;

                    if (colonneDroite < nbColonnes) {
                        Cellule celluleDroite = matriceCellules[ligne][colonneDroite];

                        // Additionner les cellules adjacentes
                        celluleDroite.modifierValeur(celluleDroite.getValeur() + celluleCourante.getValeur());
                        celluleCourante.modifierValeur(0);
                    }
                }
            }
        }
    }

}
