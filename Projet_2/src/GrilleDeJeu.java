
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

    // Méthode pour déplacer toutes les cellules existantes vers la droite
    public void deplacerToutesCellulesDroite() {
        for (int i = 0; i < nbLignes; i++) {
            for (int j = nbColonnes - 1; j > 0; j--) {
                if (!matriceCellules[i][j].estVide()) {
                    // Si la cellule n'est pas vide, la déplacer vers la droite
                    int k = j;
                    while (k < nbColonnes - 1 && matriceCellules[i][k + 1].estVide()) {
                        matriceCellules[i][k + 1].modifierValeur(matriceCellules[i][k].getValeur());
                        matriceCellules[i][k].modifierValeur(0);
                        k++;
                    }
                }
            }
        }
    }

}
