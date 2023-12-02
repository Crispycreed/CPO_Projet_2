
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Taskbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import javax.swing.ImageIcon;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author ordim
 */
public class TopScoreGraphique extends javax.swing.JFrame {

    GrilleDeJeu grille;
    int nbCoups;
    int i;
    private int nbColonnes = 4;
    private int nbLignes = 4;
    private int nbColonnes2;
    private int nbLignes2;
    private String PresetChrono;
    private ImageIcon icon;
    private int mute;
    private int reset;

    /**
     * Constructeur de la classe FinPartie. Initialise la fenêtre de fin de
     * partie avec la grille de jeu sauvegardée.
     *
     * @param sauvegarde La matrice d'entiers représentant l'état sauvegardé de
     * la grille.
     * @param nbColonnes Le nombre de colonnes de la grille.
     * @param nbLignes Le nombre de lignes de la grille.
     */
    public TopScoreGraphique(int nbLignes2, int nbColonnes2, String PresetChrono, ImageIcon icon, int mute, String username) {
        this.nbColonnes2 = nbColonnes2;
        this.nbLignes2 = nbLignes2;
        this.PresetChrono = PresetChrono;
        this.icon = icon;
        this.mute = mute;

        initComponents();
        LecteurWAV lecteur = new LecteurWAV();
        lecteur.arreterLecture();
        PanneauGrille.setLayout(new GridLayout(nbLignes, nbColonnes));
        this.grille = new GrilleDeJeu(nbLignes, nbColonnes);
        getContentPane().add(PanneauGrille, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, nbColonnes * 40, nbLignes * 40));
        grille.initialiserCellulesAleatoires();
        grille.initialiserCellulesAleatoires();
        grille.initialiserCellulesAleatoires2();
        grille.initialiserCellulesAleatoires2();
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                CelluleGraphique bouton_cellule = new CelluleGraphique(grille.matriceCellules[i][j], 36, 36);

                PanneauGrille.add(bouton_cellule); // ajout au Jpanel PanneauGrille
            }
        }
        setLocationRelativeTo(null);
        setIconImage(icon.getImage());

        if (System.getProperty("os.name").toLowerCase().contains("mac")) {

            Taskbar taskbar = Taskbar.getTaskbar();

            // Vérifier si la barre des tâches prend en charge les icônes du Dock
            if (taskbar.isSupported(Taskbar.Feature.ICON_IMAGE)) {

                taskbar.setIconImage(icon.getImage());

            }

        }

        RETOUR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lecteur.lireFichierWAV("Sclic.wav");
                DebutPartieClone f = new DebutPartieClone(nbLignes2, nbColonnes2, PresetChrono, icon, mute, username);
                f.setVisible(true);
                dispose();

            }

        });

        RESET.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lecteur.lireFichierWAV("Sclic.wav");

                // Message principal avec une police différente pour chaque partie
                String messagePart1 = "Êtes-vous sûr de votre choix ?";
                String messagePart2 = "(cette action va supprimer tous vos anciens scores)";

                Font largerFont = new Font(Font.DIALOG, Font.PLAIN, UIManager.getFont("Label.font").getSize());

                JLabel messageLabel = new JLabel("<html><div style='text-align: center;'>"
                        + messagePart1 + "<br>"
                        + "<font size=-2 color='gray'>" + messagePart2 + "</font></div></html>");
                messageLabel.setFont(largerFont);

                // Créer un bouton personnalisé avec le texte "Retour" en rouge
                JButton retourButton = new JButton("Retour");
                retourButton.setForeground(Color.BLUE);

                // Afficher une fenêtre de confirmation avec le texte et le bouton personnalisés
                int choix = JOptionPane.showOptionDialog(null, messageLabel, "Confirmation",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Valider", "Retour"},
                        "Valider");

                // Si l'utilisateur clique sur "Valider"
                if (choix == JOptionPane.YES_OPTION) {
                    reset = 1;
                    lecteur.lireFichierWAV("Sclic.wav");

                    // Réinitialiser le code
                    TopScore.supprimerTopScores(username);

                    int scoreRang1 = TopScore.recupererScore(1, username);
                    jLabel1.setText("TOP 1 : " + Math.max(scoreRang1, 0));

                    int scoreRang2 = TopScore.recupererScore(2, username);
                    jLabel2.setText("TOP 2 : " + Math.max(scoreRang2, 0));

                    int scoreRang3 = TopScore.recupererScore(3, username);
                    jLabel3.setText("TOP 3 : " + Math.max(scoreRang3, 0));

                    int scoreRang4 = TopScore.recupererScore(4, username);
                    jLabel4.setText("TOP 4 : " + Math.max(scoreRang4, 0));

                    int scoreRang5 = TopScore.recupererScore(5, username);
                    jLabel5.setText("TOP 5 : " + Math.max(scoreRang5, 0));

                    int scoreRang6 = TopScore.recupererScore(6, username);
                    jLabel6.setText("TOP 6 : " + Math.max(scoreRang6, 0));

                    repaint();
                } else {
                    reset = 0;
                    lecteur.lireFichierWAV("Sclic.wav");
                    // L'utilisateur a cliqué sur "Retour" ou fermé la fenêtre
                }
            }
        });

        int scoreRang1 = TopScore.recupererScore(1, username);
        jLabel1.setText("TOP 1 : " + String.valueOf(Math.max(scoreRang1, 0)));

        int scoreRang2 = TopScore.recupererScore(2, username);
        jLabel2.setText("TOP 2 : " + String.valueOf(Math.max(scoreRang2, 0)));

        int scoreRang3 = TopScore.recupererScore(3, username);
        jLabel3.setText("TOP 3 : " + String.valueOf(Math.max(scoreRang3, 0)));

        int scoreRang4 = TopScore.recupererScore(4, username);
        jLabel4.setText("TOP 4 : " + String.valueOf(Math.max(scoreRang4, 0)));

        int scoreRang5 = TopScore.recupererScore(5, username);
        jLabel5.setText("TOP 5 : " + String.valueOf(Math.max(scoreRang5, 0)));

        int scoreRang6 = TopScore.recupererScore(6, username);
        jLabel6.setText("TOP 6 : " + String.valueOf(Math.max(scoreRang6, 0)));

        setResizable(false);

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
        jPanel2 = new javax.swing.JPanel();
        titre = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        RETOUR = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        RESET = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanneauGrille.setBackground(new java.awt.Color(0, 0, 0));
        PanneauGrille.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 4)));
        PanneauGrille.setPreferredSize(new java.awt.Dimension(310, 500));

        javax.swing.GroupLayout PanneauGrilleLayout = new javax.swing.GroupLayout(PanneauGrille);
        PanneauGrille.setLayout(PanneauGrilleLayout);
        PanneauGrilleLayout.setHorizontalGroup(
            PanneauGrilleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );
        PanneauGrilleLayout.setVerticalGroup(
            PanneauGrilleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0), 4)));

        titre.setFont(new java.awt.Font("Snap ITC", 0, 24)); // NOI18N
        titre.setText("  TOP SCORE");

        jLabel1.setFont(new java.awt.Font("Snap ITC", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(225, 11, 61));
        jLabel1.setText("jLabel1");

        jLabel2.setFont(new java.awt.Font("Snap ITC", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(246, 180, 10));
        jLabel2.setText("jLabel2");

        jLabel3.setFont(new java.awt.Font("Snap ITC", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(233, 233, 101));
        jLabel3.setText("jLabel3");

        jLabel4.setFont(new java.awt.Font("Snap ITC", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(30, 232, 209));
        jLabel4.setText("jLabel4");

        jLabel5.setFont(new java.awt.Font("Snap ITC", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(32, 151, 167));
        jLabel5.setText("jLabel5");

        RETOUR.setBackground(new java.awt.Color(237, 237, 237));
        RETOUR.setFont(new java.awt.Font("Snap ITC", 0, 14)); // NOI18N
        RETOUR.setText("BACK");
        RETOUR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RETOURActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Snap ITC", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(25, 94, 175));
        jLabel6.setText("jLabel6");

        RESET.setBackground(new java.awt.Color(237, 237, 237));
        RESET.setFont(new java.awt.Font("Snap ITC", 0, 14)); // NOI18N
        RESET.setText("RESET");
        RESET.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RESETActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                            .addComponent(titre, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RETOUR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RESET)
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(titre, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RETOUR)
                    .addComponent(RESET))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanneauGrille, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanneauGrille, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void RETOURActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RETOURActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RETOURActionPerformed

    private void RESETActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RESETActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RESETActionPerformed

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
            java.util.logging.Logger.getLogger(DebutPartie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DebutPartie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DebutPartie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DebutPartie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanneauGrille;
    private javax.swing.JButton RESET;
    private javax.swing.JButton RETOUR;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel titre;
    // End of variables declaration//GEN-END:variables
}
