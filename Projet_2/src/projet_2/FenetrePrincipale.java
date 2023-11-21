package projet_2;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author MAUMEJEAN DENIS
 */
public class FenetrePrincipale extends javax.swing.JFrame {

    GrilleDeJeu grille;
    int nbCoups;
    int i;
    DebutPartie1 debutPartie = new DebutPartie1();
    private int nbColonnes;
    private int nbLignes;
    private int nbMelange;

    /**
     * Fenêtre principale du jeu, affichant la grille de jeu et permettant aux
     * joueurs d'interagir en activant des lignes, colonnes ou diagonales de
     * cellules.
     *
     * @param nbColonnes Le nombre de colonnes de la grille de jeu.
     * @param nbLignes Le nombre de lignes de la grille de jeu.
     * @param nbMelange Le niveau de mélange initial de la grille.
     */
    public FenetrePrincipale(int nbColonnes, int nbLignes, int nbMelange) {
        this.nbColonnes = nbColonnes;
        this.nbLignes = nbLignes;
        this.nbMelange = nbMelange;

        initComponents();
        getContentPane().add(PanneauGrille, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, nbColonnes * 40, nbLignes * 40));
        this.pack();
        this.revalidate();
        PanneauGrille.setLayout(new GridLayout(nbLignes, nbColonnes));
        this.grille = new GrilleDeJeu(nbLignes, nbColonnes);
        this.initialiserPartie();

        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                CelluleGraphique bouton_cellule = new CelluleGraphique(grille.matriceCellules[i][j], 36, 36);

                PanneauGrille.add(bouton_cellule); // ajout au Jpanel PanneauGrille
            }

        }

        PanneauBoutonsVerticaux.setLayout(new GridLayout(nbLignes, 1));
        getContentPane().add(PanneauBoutonsVerticaux, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1 * 40, nbLignes * 40));
        this.pack();
        this.revalidate();

        PanneauBoutonsHorizontaux.setLayout(new GridLayout(1, nbColonnes)); // Inverser les arguments pour avoir 1 ligne et plusieurs colonnes
        int PositionEnBas = (nbLignes * 40) + 40;
        getContentPane().add(PanneauBoutonsHorizontaux, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, PositionEnBas, nbColonnes * 40, 1 * 40)); // Ajuster les dimensions pour la grille horizontale
        this.pack();
        this.revalidate();

        PanneauBoutonsEnBasGauche.setLayout(new GridLayout(1, 1));
        getContentPane().add(PanneauBoutonsEnBasGauche, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, PositionEnBas - 5, 40, 40));
        this.pack();
        this.revalidate();

        int PositionEnBas2 = 90 + (nbColonnes * 40);

        PanneauBoutonsEnBasDroite2.setLayout(new GridLayout(1, 1));
        getContentPane().add(PanneauBoutonsEnBasDroite2, new org.netbeans.lib.awtextra.AbsoluteConstraints(PositionEnBas2, PositionEnBas - 5, 40, 40));
        this.pack();
        this.revalidate();

        PanneauAvancement.setLayout(new GridLayout(nbLignes, 1));
        int Position3 = 110 + (nbLignes * 40);
        getContentPane().add(PanneauAvancement, new org.netbeans.lib.awtextra.AbsoluteConstraints(PositionEnBas2 + 10, 20, 20, nbLignes * (nbLignes * 40)));
        this.pack();
        this.revalidate();

        jProgressBar1.setMaximum(nbLignes * nbColonnes);
        jProgressBar1.setMinimum(0);
        int nbrCellulesetteinte = grille.combiendecellulesencoreallumés();
        jProgressBar1.setValue(nbrCellulesetteinte);
        repaint();

        // création du panneau de boutons verticaux (pour les lignes)
        for (i = 0; i < nbLignes; i++) {
            JButton bouton_ligne = new JButton();
            ActionListener ecouteurClick = new ActionListener() {
                final int j = i;

                @Override
                public void actionPerformed(ActionEvent e) {
                    grille.activerLigneDeCellules(j);
                    jProgressBar1.setMaximum(nbLignes * nbColonnes);
                    jProgressBar1.setMinimum(0);
                    int nbrCelluesetteintes = grille.combiendecellulesencoreallumés();
                    jProgressBar1.setValue(nbrCelluesetteintes);
                    nbCoups = nbCoups + 1;

                    if ((nbrCelluesetteintes - nbLignes * nbColonnes) == 0) {
                        dispose();
                        FenetreVictoire f = new FenetreVictoire(nbCoups);
                        f.setVisible(true);

                    }
                    repaint();
                }
            };
            bouton_ligne.addActionListener(ecouteurClick);
            PanneauBoutonsVerticaux.add(bouton_ligne);
        }

        // création du panneau de boutons vhorizontaux
        for (i = 0; i < nbColonnes; i++) {
            JButton bouton_Colonnes = new JButton();
            ActionListener ecouteurClick = new ActionListener() {
                final int j = i;

                @Override
                public void actionPerformed(ActionEvent e) {
                    grille.activerColonneDeCellules(j);
                    jProgressBar1.setMaximum(nbLignes * nbColonnes);
                    jProgressBar1.setMinimum(0);
                    int nbrCelluesetteintes = grille.combiendecellulesencoreallumés();
                    jProgressBar1.setValue(nbrCelluesetteintes);
                    nbCoups = nbCoups + 1;

                    if ((nbrCelluesetteintes - nbLignes * nbColonnes) == 0) {
                        dispose();
                        FenetreVictoire f = new FenetreVictoire(nbCoups);
                        f.setVisible(true);
                    }
                    repaint();
                }
            };
            bouton_Colonnes.addActionListener(ecouteurClick);
            PanneauBoutonsHorizontaux.add(bouton_Colonnes);
        }

        // création du panneau de boutons diag montante
        JButton bouton_diag1 = new JButton();
        ActionListener ecouteurClick = new ActionListener() {
            final int j = i;

            @Override
            public void actionPerformed(ActionEvent e) {
                grille.activerDiagonaleMontante();
                jProgressBar1.setMaximum(nbLignes * nbColonnes);
                jProgressBar1.setMinimum(0);
                int nbrCelluesetteintes = grille.combiendecellulesencoreallumés();
                jProgressBar1.setValue(nbrCelluesetteintes);
                nbCoups = nbCoups + 1;

                System.out.print("nbcoups : " + nbCoups);

                if ((nbrCelluesetteintes - nbLignes * nbColonnes) == 0) {
                    dispose();
                    FenetreVictoire f = new FenetreVictoire(nbCoups);
                    f.setVisible(true);
                }
                repaint();
            }
        };
        bouton_diag1.addActionListener(ecouteurClick);
        PanneauBoutonsEnBasGauche.add(bouton_diag1);

        // création du panneau de boutons diag DESCENDANTE
        JButton bouton_diag2 = new JButton();
        ActionListener ecouteurClick2 = new ActionListener() {
            final int j = i;

            @Override
            public void actionPerformed(ActionEvent e) {
                grille.activerDiagonaleDescendante();
                jProgressBar1.setMaximum(nbLignes * nbColonnes);
                jProgressBar1.setMinimum(0);
                int nbrCelluesetteintes = grille.combiendecellulesencoreallumés();
                jProgressBar1.setValue(nbrCelluesetteintes);
                nbCoups = nbCoups + 1;

                if ((nbrCelluesetteintes - nbLignes * nbColonnes) == 0) {
                    dispose();
                    FenetreVictoire f = new FenetreVictoire(nbCoups);
                    f.setVisible(true);
                }
                repaint();
            }
        };
        bouton_diag2.addActionListener(ecouteurClick2);
        PanneauBoutonsEnBasDroite2.add(bouton_diag2);

    }

    public void initialiserPartie() {
        grille.eteindreToutesLesCellules();
        grille.melangerMatriceAleatoirement(nbMelange * 5);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanneauGrille = new javax.swing.JPanel();
        PanneauBoutonsVerticaux = new javax.swing.JPanel();
        PanneauBoutonsHorizontaux = new javax.swing.JPanel();
        PanneauBoutonsEnBasGauche = new javax.swing.JPanel();
        PanneauAvancement = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();
        PanneauBoutonsEnBasDroite2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanneauGrille.setBackground(new java.awt.Color(51, 153, 0));
        PanneauGrille.setPreferredSize(new java.awt.Dimension(360, 360));

        javax.swing.GroupLayout PanneauGrilleLayout = new javax.swing.GroupLayout(PanneauGrille);
        PanneauGrille.setLayout(PanneauGrilleLayout);
        PanneauGrilleLayout.setHorizontalGroup(
            PanneauGrilleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        PanneauGrilleLayout.setVerticalGroup(
            PanneauGrilleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        getContentPane().add(PanneauGrille, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 400, 400));

        PanneauBoutonsVerticaux.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout PanneauBoutonsVerticauxLayout = new javax.swing.GroupLayout(PanneauBoutonsVerticaux);
        PanneauBoutonsVerticaux.setLayout(PanneauBoutonsVerticauxLayout);
        PanneauBoutonsVerticauxLayout.setHorizontalGroup(
            PanneauBoutonsVerticauxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        PanneauBoutonsVerticauxLayout.setVerticalGroup(
            PanneauBoutonsVerticauxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        getContentPane().add(PanneauBoutonsVerticaux, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 40, 400));

        PanneauBoutonsHorizontaux.setBackground(new java.awt.Color(0, 204, 0));

        javax.swing.GroupLayout PanneauBoutonsHorizontauxLayout = new javax.swing.GroupLayout(PanneauBoutonsHorizontaux);
        PanneauBoutonsHorizontaux.setLayout(PanneauBoutonsHorizontauxLayout);
        PanneauBoutonsHorizontauxLayout.setHorizontalGroup(
            PanneauBoutonsHorizontauxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        PanneauBoutonsHorizontauxLayout.setVerticalGroup(
            PanneauBoutonsHorizontauxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        getContentPane().add(PanneauBoutonsHorizontaux, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 440, 400, 40));

        PanneauBoutonsEnBasGauche.setBackground(new java.awt.Color(0, 255, 0));
        PanneauBoutonsEnBasGauche.setPreferredSize(new java.awt.Dimension(40, 40));

        javax.swing.GroupLayout PanneauBoutonsEnBasGaucheLayout = new javax.swing.GroupLayout(PanneauBoutonsEnBasGauche);
        PanneauBoutonsEnBasGauche.setLayout(PanneauBoutonsEnBasGaucheLayout);
        PanneauBoutonsEnBasGaucheLayout.setHorizontalGroup(
            PanneauBoutonsEnBasGaucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        PanneauBoutonsEnBasGaucheLayout.setVerticalGroup(
            PanneauBoutonsEnBasGaucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        getContentPane().add(PanneauBoutonsEnBasGauche, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 430, -1, -1));

        jProgressBar1.setForeground(new java.awt.Color(0, 153, 255));
        jProgressBar1.setOrientation(1);
        jProgressBar1.setOpaque(true);
        jProgressBar1.setPreferredSize(new java.awt.Dimension(40, 200));

        javax.swing.GroupLayout PanneauAvancementLayout = new javax.swing.GroupLayout(PanneauAvancement);
        PanneauAvancement.setLayout(PanneauAvancementLayout);
        PanneauAvancementLayout.setHorizontalGroup(
            PanneauAvancementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(PanneauAvancementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanneauAvancementLayout.createSequentialGroup()
                    .addGap(0, 15, Short.MAX_VALUE)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 15, Short.MAX_VALUE)))
        );
        PanneauAvancementLayout.setVerticalGroup(
            PanneauAvancementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(PanneauAvancementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanneauAvancementLayout.createSequentialGroup()
                    .addGap(29, 29, 29)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                    .addGap(30, 30, 30)))
        );

        getContentPane().add(PanneauAvancement, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, 50, 400));

        PanneauBoutonsEnBasDroite2.setBackground(new java.awt.Color(0, 255, 0));
        PanneauBoutonsEnBasDroite2.setPreferredSize(new java.awt.Dimension(40, 40));

        javax.swing.GroupLayout PanneauBoutonsEnBasDroite2Layout = new javax.swing.GroupLayout(PanneauBoutonsEnBasDroite2);
        PanneauBoutonsEnBasDroite2.setLayout(PanneauBoutonsEnBasDroite2Layout);
        PanneauBoutonsEnBasDroite2Layout.setHorizontalGroup(
            PanneauBoutonsEnBasDroite2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        PanneauBoutonsEnBasDroite2Layout.setVerticalGroup(
            PanneauBoutonsEnBasDroite2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        getContentPane().add(PanneauBoutonsEnBasDroite2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, -1, -1));

        setBounds(0, 0, 602, 518);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FenetrePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FenetrePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FenetrePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenetrePrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanneauAvancement;
    private javax.swing.JPanel PanneauBoutonsEnBasDroite2;
    private javax.swing.JPanel PanneauBoutonsEnBasGauche;
    private javax.swing.JPanel PanneauBoutonsHorizontaux;
    private javax.swing.JPanel PanneauBoutonsVerticaux;
    private javax.swing.JPanel PanneauGrille;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}
