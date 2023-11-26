
import java.util.Random;

/**
 * La classe GrilleDeJeu
 */
class GrilleDeJeu {

    /**
     * La matrice de cellules représentant la grille du jeu.
     */
    Cellule[][] matriceCellules;

    /**
     * Le nombre de lignes dans la grille.
     */
    int nbLignes;

    /**
     * Le nombre de colonnes dans la grille.
     */
    int nbColonnes;

    /**
     * Constructeur de la classe GrilleDeJeu.
     *
     * @param p_nbLignes Le nombre de lignes de la grille.
     * @param p_nbColonnes Le nombre de colonnes de la grille.
     */
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

    /**
     * Initialise deux cellules aléatoires avec les valeurs 1 et 2.
     */
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

    /**
     * Initialise deux cellules aléatoires avec les valeurs 12 et 24.
     */
    public void initialiserCellulesAleatoires2() {
        Random random = new Random();
        int count = 0;

        while (count < 2) {
            int randomLigne = random.nextInt(nbLignes);
            int randomColonne = random.nextInt(nbColonnes);

            if (matriceCellules[randomLigne][randomColonne].estVide()) {
                // Si la cellule est vide, lui attribuer la valeur 1 ou 2
                int valeur = (count == 0) ? 12 : 24;
                matriceCellules[randomLigne][randomColonne].modifierValeur(valeur);
                count++;
            }
        }
    }

    /**
     * Initialise deux cellules aléatoires avec les valeurs 48 et 96.
     */
    public void initialiserCellulesAleatoires3() {
        Random random = new Random();
        int count = 0;

        while (count < 2) {
            int randomLigne = random.nextInt(nbLignes);
            int randomColonne = random.nextInt(nbColonnes);

            if (matriceCellules[randomLigne][randomColonne].estVide()) {
                // Si la cellule est vide, lui attribuer la valeur 1 ou 2
                int valeur = (count == 0) ? 96 : 48;
                matriceCellules[randomLigne][randomColonne].modifierValeur(valeur);
                count++;
            }
        }
    }

    /**
     * Calcule le nombre de cellules vides dans la grille.
     *
     * @return Le nombre de cellules vides.
     */
    public int nombreCellulesVides() {
        int count = 0;

        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                if (matriceCellules[i][j].getValeur() == 0) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * Ajoute aléatoirement une cellule à gauche de la grille.
     */
    public void ajouterAleatoirementAGauche() {
        Random random = new Random();
        int stop = 2;

        while (stop == 2) {

            // Choisir aléatoirement une valeur parmi 1, 2 ou 3
            int[] valeursPossibles = {1, 2, 3, 6};
            int valeur = valeursPossibles[random.nextInt(valeursPossibles.length)];
            int randomLigne = random.nextInt(nbLignes);
            int valeurExistante = matriceCellules[randomLigne][0].getValeur();
            int cellulesVides = nombreCellulesVides();
            if (valeurExistante == 0) {
                matriceCellules[randomLigne][0].modifierValeur(valeur);
                stop = 1;
            } else if (cellulesVides == 0) {
                stop = 1;
            }
        }
    }

    /**
     * Ajoute aléatoirement une cellule à droite de la grille.
     */
    public void ajouterAleatoirementADroite() {
        Random random = new Random();

        int stop = 2;

        while (stop == 2) {
            // Choisir aléatoirement une valeur parmi 1, 2 ou 3
            int[] valeursPossibles = {1, 2, 3, 6};
            int valeur = valeursPossibles[random.nextInt(valeursPossibles.length)];
            int randomLigne = random.nextInt(nbLignes);
            int valeurExistante = matriceCellules[randomLigne][nbColonnes - 1].getValeur();
            int cellulesVides = nombreCellulesVides();
            if (valeurExistante == 0) {
                matriceCellules[randomLigne][nbColonnes - 1].modifierValeur(valeur);
                stop = 1;
            } else if (cellulesVides == 0) {
                stop = 1;
            }
        }
    }

    /**
     * Ajoute aléatoirement une cellule en haut de la grille.
     */
    public void ajouterAleatoirementEnHaut() {
        Random random = new Random();
        int stop = 2;

        while (stop == 2) {
            // Choisir aléatoirement une valeur parmi 1, 2 ou 3
            int randomColonne = random.nextInt(nbColonnes);
            int[] valeursPossibles = {1, 2, 3, 6};
            int valeur = valeursPossibles[random.nextInt(valeursPossibles.length)];
            int valeurExistante = matriceCellules[0][randomColonne].getValeur();
            int cellulesVides = nombreCellulesVides();
            if (valeurExistante == 0) {
                matriceCellules[0][randomColonne].modifierValeur(valeur);
                stop = 1;
            } else if (cellulesVides == 0) {
                stop = 1;

            }
        }
    }

    /**
     * Ajoute aléatoirement une cellule en bas de la grille.
     */
    public void ajouterAleatoirementEnBas() {
        Random random = new Random();

        int stop = 2;

        while (stop == 2) {
            // Choisir aléatoirement une valeur parmi 1, 2 ou 3
            int[] valeursPossibles = {1, 2, 3, 6};
            int valeur = valeursPossibles[random.nextInt(valeursPossibles.length)];
            int randomColonne = random.nextInt(nbColonnes);
            int valeurExistante = matriceCellules[nbLignes - 1][randomColonne].getValeur();
            int cellulesVides = nombreCellulesVides();
            if (valeurExistante == 0) {
                matriceCellules[nbLignes - 1][randomColonne].modifierValeur(valeur);
                stop = 1;

            } else if (cellulesVides == 0) {
                stop = 1;
            }
        }
    }

    /**
     * Additionne les cellules adjacentes vers la droite.
     */
    public void additionnerCellulesAdjacentesVersLaDroite() {
        for (int ligne = nbLignes - 1; ligne >= 0; ligne--) {
            for (int colonne = nbColonnes - 2; colonne >= 0; colonne--) {
                Cellule celluleCourante = matriceCellules[ligne][colonne];

                if (celluleCourante.getValeur() != 0) {
                    // Trouver la première cellule non vide à droite
                    int colonneDroite = colonne + 1;

                    if (colonneDroite < nbColonnes) {
                        Cellule celluleDroite = matriceCellules[ligne][colonneDroite];
                        int valeurDroite = celluleDroite.getValeur();
                        int valeurCourante = celluleCourante.getValeur();

                        // Additionner les cellules adjacentes
                        if (valeurDroite == valeurCourante) {
                            if (valeurDroite == 2 && valeurCourante == 2) {
                                celluleDroite.modifierValeur(valeurDroite);
                            } else if (valeurDroite == 1 && valeurCourante == 1) {
                                celluleDroite.modifierValeur(valeurDroite);
                            } else {
                                celluleDroite.modifierValeur(valeurDroite + valeurCourante);
                                celluleCourante.modifierValeur(0);
                            }
                        } else if (valeurDroite == 0) {
                            celluleDroite.modifierValeur(valeurDroite + valeurCourante);
                            celluleCourante.modifierValeur(0);
                        } else if (valeurDroite == 1 && valeurCourante == 2) {
                            celluleDroite.modifierValeur(valeurDroite + valeurCourante);
                            celluleCourante.modifierValeur(0);
                        } else if (valeurDroite == 2 && valeurCourante == 1) {
                            celluleDroite.modifierValeur(valeurDroite + valeurCourante);
                            celluleCourante.modifierValeur(0);
                        } else if (valeurDroite == 1 && valeurCourante == 1) {
                            celluleDroite.modifierValeur(valeurDroite + valeurCourante);
                            celluleCourante.modifierValeur(0);
                        }

                    }
                }
            }
        }
        ajouterAleatoirementAGauche();
        LecteurWAV lecteur = new LecteurWAV();
        lecteur.lireFichierWAV("Sfusion.wav");
    }

    /**
     * Additionne les cellules adjacentes vers la gauche.
     */
    public void additionnerCellulesAdjacentesVersLaGauche() {
        for (int ligne = 0; ligne < nbLignes; ligne++) {
            for (int colonne = 1; colonne < nbColonnes; colonne++) {
                Cellule celluleCourante = matriceCellules[ligne][colonne];

                if (celluleCourante.getValeur() != 0) {
                    // Trouver la première cellule non vide à gauche
                    int colonneGauche = colonne - 1;

                    if (colonneGauche >= 0) {
                        Cellule celluleGauche = matriceCellules[ligne][colonneGauche];
                        int valeurGauche = celluleGauche.getValeur();
                        int valeurCourante = celluleCourante.getValeur();

                        // Additionner les cellules adjacentes
                        if (valeurGauche == valeurCourante) {
                            if (valeurGauche == 2 && valeurCourante == 2) {
                                celluleGauche.modifierValeur(valeurGauche);
                            } else if (valeurGauche == 1 && valeurCourante == 1) {
                                celluleGauche.modifierValeur(valeurGauche);
                            } else {
                                celluleGauche.modifierValeur(valeurGauche + valeurCourante);
                                celluleCourante.modifierValeur(0);
                            }
                        } else if (valeurGauche == 0) {
                            celluleGauche.modifierValeur(valeurGauche + valeurCourante);
                            celluleCourante.modifierValeur(0);
                        } else if (valeurGauche == 1 && valeurCourante == 2) {
                            celluleGauche.modifierValeur(valeurGauche + valeurCourante);
                            celluleCourante.modifierValeur(0);
                        } else if (valeurGauche == 2 && valeurCourante == 1) {
                            celluleGauche.modifierValeur(valeurGauche + valeurCourante);
                            celluleCourante.modifierValeur(0);
                        } else if (valeurGauche == 1 && valeurCourante == 1) {
                            celluleGauche.modifierValeur(valeurGauche + valeurCourante);
                            celluleCourante.modifierValeur(0);
                        }
                    }
                }
            }
        }
        ajouterAleatoirementADroite();
        LecteurWAV lecteur = new LecteurWAV();
        lecteur.lireFichierWAV("Sfusion.wav");
    }

    /**
     * Additionne les cellules adjacentes vers le haut dans la grille. Si deux
     * cellules adjacentes ont la même valeur, elles sont fusionnées. Les
     * cellules vides résultantes sont déplacées vers le haut. Enfin, une
     * nouvelle cellule est ajoutée aléatoirement en bas de la grille.
     */
    public void additionnerCellulesAdjacentesVersLeHaut() {
        // Parcours de chaque colonne
        for (int colonne = 0; colonne < nbColonnes; colonne++) {
            // Parcours de chaque ligne à partir de la deuxième
            for (int ligne = 1; ligne < nbLignes; ligne++) {
                Cellule celluleCourante = matriceCellules[ligne][colonne];

                // Vérifie si la cellule courante n'est pas vide
                if (celluleCourante.getValeur() != 0) {
                    // Trouver la première cellule non vide au-dessus
                    int ligneHaut = ligne - 1;

                    if (ligneHaut >= 0) {
                        Cellule celluleHaut = matriceCellules[ligneHaut][colonne];
                        int valeurHaut = celluleHaut.getValeur();
                        int valeurCourante = celluleCourante.getValeur();

                        // Additionner les cellules adjacentes
                        if (valeurHaut == valeurCourante) {
                            if (valeurHaut == 2 && valeurCourante == 2) {
                                celluleHaut.modifierValeur(valeurHaut);
                            } else if (valeurHaut == 1 && valeurCourante == 1) {
                                celluleHaut.modifierValeur(valeurHaut);
                            } else {
                                celluleHaut.modifierValeur(valeurHaut + valeurCourante);
                                celluleCourante.modifierValeur(0);
                            }
                        } else if (valeurHaut == 0) {
                            celluleHaut.modifierValeur(valeurHaut + valeurCourante);
                            celluleCourante.modifierValeur(0);
                        } else if (valeurHaut == 1 && valeurCourante == 2) {
                            celluleHaut.modifierValeur(valeurHaut + valeurCourante);
                            celluleCourante.modifierValeur(0);
                        } else if (valeurHaut == 2 && valeurCourante == 1) {
                            celluleHaut.modifierValeur(valeurHaut + valeurCourante);
                            celluleCourante.modifierValeur(0);
                        } else if (valeurHaut == 1 && valeurCourante == 1) {
                            celluleHaut.modifierValeur(valeurHaut + valeurCourante);
                            celluleCourante.modifierValeur(0);
                        }
                    }
                }
            }
        }
        // Ajoute une nouvelle cellule aléatoirement en bas de la grille
        ajouterAleatoirementEnBas();
        LecteurWAV lecteur = new LecteurWAV();
        lecteur.lireFichierWAV("Sfusion.wav");
    }

    /**
     * Additionne les cellules adjacentes vers le bas dans la grille. Si deux
     * cellules adjacentes ont la même valeur, elles sont fusionnées. Les
     * cellules vides résultantes sont déplacées vers le bas. Enfin, une
     * nouvelle cellule est ajoutée aléatoirement en haut de la grille.
     */
    public void additionnerCellulesAdjacentesVersLeBas() {
        // Parcours de chaque colonne
        for (int colonne = 0; colonne < nbColonnes; colonne++) {
            // Parcours de chaque ligne en partant de l'avant-dernière
            for (int ligne = nbLignes - 2; ligne >= 0; ligne--) {
                Cellule celluleCourante = matriceCellules[ligne][colonne];
                // Vérifie si la cellule courante n'est pas vide
                if (celluleCourante.getValeur() != 0) {
                    // Trouver la première cellule non vide en dessous
                    int ligneBas = ligne + 1;
                    if (ligneBas < nbLignes) {
                        Cellule celluleBas = matriceCellules[ligneBas][colonne];
                        int valeurBas = celluleBas.getValeur();
                        int valeurCourante = celluleCourante.getValeur();
                        // Additionner les cellules adjacentes
                        if (valeurBas == valeurCourante) {
                            if (valeurBas == 2 && valeurCourante == 2) {
                                celluleBas.modifierValeur(valeurBas);
                            } else if (valeurBas == 1 && valeurCourante == 1) {
                                celluleBas.modifierValeur(valeurBas);
                            } else {
                                celluleBas.modifierValeur(valeurBas + valeurCourante);
                                celluleCourante.modifierValeur(0);
                            }
                        } else if (valeurBas == 0) {
                            celluleBas.modifierValeur(valeurBas + valeurCourante);
                            celluleCourante.modifierValeur(0);
                        } else if (valeurBas == 1 && valeurCourante == 2) {
                            celluleBas.modifierValeur(valeurBas + valeurCourante);
                            celluleCourante.modifierValeur(0);
                        } else if (valeurBas == 2 && valeurCourante == 1) {
                            celluleBas.modifierValeur(valeurBas + valeurCourante);
                            celluleCourante.modifierValeur(0);
                        } else if (valeurBas == 1 && valeurCourante == 1) {
                            celluleBas.modifierValeur(valeurBas + valeurCourante);
                            celluleCourante.modifierValeur(0);
                        }
                    }
                }
            }
        }
        // Ajoute une nouvelle cellule aléatoirement en haut de la grille
        ajouterAleatoirementEnHaut();
        LecteurWAV lecteur = new LecteurWAV();
        lecteur.lireFichierWAV("Sfusion.wav");
    }

    /**
     * Sauvegarde l'état actuel de la grille dans une matrice d'entiers.
     *
     * @return Une matrice d'entiers représentant l'état actuel de la grille.
     */
    public int[][] sauvegarderGrille() {
        int[][] copieGrille = new int[nbLignes][nbColonnes];

        // Copie la valeur de chaque cellule dans la nouvelle matrice
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                copieGrille[i][j] = matriceCellules[i][j].getValeur();
            }
        }

        return copieGrille;
    }

    /**
     * Charge l'état de la grille à partir d'une matrice d'entiers sauvegardée.
     *
     * @param sauvegarde La matrice d'entiers représentant l'état sauvegardé de
     * la grille.
     */
    public void chargerGrille(int[][] sauvegarde) {
        // Modifie la valeur de chaque cellule avec les valeurs de la matrice sauvegardée
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                matriceCellules[i][j].modifierValeur(sauvegarde[i][j]);
            }
        }
    }

    /**
     * Calcule et retourne le score actuel de la grille.
     *
     * @return Le score actuel de la grille.
     */
    public int getScore() {
        int score = 0;
        // Calcule la somme des valeurs de toutes les cellules
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                score += matriceCellules[i][j].getValeur();
            }
        }
        return score;
    }

    /**
     * Vide la grille en réinitialisant les valeurs de toutes les cellules à
     * zéro. Cette méthode parcourt toutes les lignes et colonnes de la grille,
     * modifiant la valeur de chaque cellule à zéro.
     */
    public void viderGrille() {
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                matriceCellules[i][j].modifierValeur(0);
            }
        }
    }
}
