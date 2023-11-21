/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TEST;

import java.util.Scanner;

/**
 * La classe Partie représente une partie du jeu LightOff en utilisant une
 * grille de cellules lumineuses. Cette classe facilite l'interaction entre le
 * joueur et la grille, permettant de jouer au jeu LightOff.
 */
public class Partie {

    private GrilleDeJeu grille; // Référence à la grille de jeu
    private int nbCoups; // Nombre de coups joués par le joueur

    /**
     * Constructeur de la classe Partie.
     *
     * @param nbLignes Le nombre de lignes de la grille.
     * @param nbColonnes Le nombre de colonnes de la grille.
     */
    public Partie() {
        Scanner scanner = new Scanner(System.in);

        int nbLignes;
        int nbColonnes;
        System.out.print("Entrez le nombre de Lignes et de Colonnes (separes par une virgule) : ");

        String input = scanner.nextLine();
        String[] parts = input.split(",");

        if (parts.length == 2) {
            nbLignes = Integer.parseInt(parts[0].trim());
            nbColonnes = Integer.parseInt(parts[1].trim());

            grille = new GrilleDeJeu(nbLignes, nbColonnes);
            nbCoups = 0;
        } else {
            System.out.println("Veuillez entrer les valeurs au format 'lignes,colonnes'.");
        }
    }

    /**
     * Initialise la partie en mélangeant la grille de jeu de manière aléatoire.
     *
     * @param nbTours Le nombre de tours de mélange.
     */
    public void initialiserPartie() {

        int nbTours = 5;
        grille.melangerMatriceAleatoirement(nbTours);
    }

    /**
     * Lance une partie du jeu LightOff en permettant au joueur d'interagir avec
     * la grille. Le jeu continue tant que toutes les cellules ne sont pas
     * éteintes.
     */
    public void lancerPartie() {
        Scanner scanner = new Scanner(System.in);

        while (!grille.cellulesToutesEteintes()) {
            System.out.println("Etat actuel de la grille :");
            System.out.println(grille.toString());

            int nbrCellulesEncoreAllumés = grille.combiendecellulesencoreallumés();

            System.out.println("Vous etes au coup : " + (nbCoups + 1) + ", Il reste : " + nbrCellulesEncoreAllumés + " cellules Allume");

            System.out.println("Entrez un coup (ligne, colonne ou diagonale) : ");
            String coup = scanner.nextLine();

            if (coup.length() < 2) {
                System.out.println("Entree invalide. Utilisez un format tel que L2 (Ligne), C3 (Colonne) ou D (Diagonale).");
                return;
            }

            char action = coup.charAt(0);
            int index = Character.getNumericValue(coup.charAt(1));

            switch (action) {
                case 'L':
                    grille.activerLigneDeCellules(index);
                    break;
                case 'C':
                    grille.activerColonneDeCellules(index);
                    break;
                case 'D':
                    if (index == 0) {
                        grille.activerDiagonaleDescendante();
                    } else if (index == 1) {
                        grille.activerDiagonaleMontante();
                    } else {
                        System.out.println("\u001B[31m" + "Index de diagonale invalide. Utilisez 0 pour descendante ou 1 pour montante." + "\u001B[0m");

                    }
                    break;
                default:
                    System.out.println("\u001B[31m" + "Action invalide. Utilisez L pour ligne, C pour colonne ou D pour diagonale." + "\u001B[0m");

            }
            nbCoups++;
        }

        System.out.println("\u001B[32m" + "Toutes les cellules sont eteintes ! Nombre de coups : " + nbCoups + "\u001B[0m");

    }
}
