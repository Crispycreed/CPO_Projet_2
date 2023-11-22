
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

    public void ajouterAleatoirementAGauche() {
        Random random = new Random();
        int randomLigne = random.nextInt(nbLignes);
        int stop = 2;
        while (stop == 2) {

            // Choisir aléatoirement une valeur parmi 1, 2 ou 3
            int valeur = random.nextInt(3) + 1;
            int valeurExistante = matriceCellules[randomLigne][0].getValeur();

            if (valeurExistante == 0) {
                matriceCellules[randomLigne][0].modifierValeur(valeur);
                stop = 1;
            }
        }
    }

    public void ajouterAleatoirementADroite() {
        Random random = new Random();
        int randomLigne = random.nextInt(nbLignes);
        int stop = 2;

        while (stop == 2) {
            // Choisir aléatoirement une valeur parmi 1, 2 ou 3
            int valeur = random.nextInt(3) + 1;
            int valeurExistante = matriceCellules[randomLigne][nbColonnes - 1].getValeur();

            if (valeurExistante == 0) {
                matriceCellules[randomLigne][nbColonnes - 1].modifierValeur(valeur);
                stop = 1;
            }
        }
    }

    public void ajouterAleatoirementEnHaut() {
        Random random = new Random();
        int randomColonne = random.nextInt(nbColonnes);
        int stop = 2;

        while (stop == 2) {
            // Choisir aléatoirement une valeur parmi 1, 2 ou 3
            int valeur = random.nextInt(3) + 1;
            int valeurExistante = matriceCellules[0][randomColonne].getValeur();

            if (valeurExistante == 0) {
                matriceCellules[0][randomColonne].modifierValeur(valeur);
                stop = 1;
            }
        }
    }

    public void ajouterAleatoirementEnBas() {
        Random random = new Random();
        int randomColonne = random.nextInt(nbColonnes);
        int stop = 2;

        while (stop == 2) {
            // Choisir aléatoirement une valeur parmi 1, 2 ou 3
            int valeur = random.nextInt(3) + 1;
            int valeurExistante = matriceCellules[nbLignes - 1][randomColonne].getValeur();

            if (valeurExistante == 0) {
                matriceCellules[nbLignes - 1][randomColonne].modifierValeur(valeur);
                stop = 1;
            }
        }
    }

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
                            }
                            else {
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
    }

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
                            }
                            else {
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
    }

    public void additionnerCellulesAdjacentesVersLeHaut() {
        for (int colonne = 0; colonne < nbColonnes; colonne++) {
            for (int ligne = 1; ligne < nbLignes; ligne++) {
                Cellule celluleCourante = matriceCellules[ligne][colonne];

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
                            }
                            else {
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
        ajouterAleatoirementEnBas();
    }

    public void additionnerCellulesAdjacentesVersLeBas() {
        for (int colonne = 0; colonne < nbColonnes; colonne++) {
            for (int ligne = nbLignes - 2; ligne >= 0; ligne--) {
                Cellule celluleCourante = matriceCellules[ligne][colonne];

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
                            }
                            else {
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
        ajouterAleatoirementEnHaut();
    }

}
