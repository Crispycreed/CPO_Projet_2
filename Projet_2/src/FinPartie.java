
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

/**
 * Cette classe représente la fenêtre de fin de partie.
 */
public class FinPartie extends javax.swing.JFrame {

    GrilleDeJeu grille;
    int nbCoups;
    int i;
    private String PresetChrono;
    private String temps;

    /**
     * Constructeur de la classe FinPartie. Initialise la fenêtre de fin de
     * partie avec la grille de jeu sauvegardée.
     *
     * @param sauvegarde La matrice d'entiers représentant l'état sauvegardé de
     * la grille.
     * @param nbColonnes Le nombre de colonnes de la grille.
     * @param nbLignes Le nombre de lignes de la grille.
     */
    public FinPartie(int[][] sauvegarde, int nbColonnes, int nbLignes, String PresetChrono, String temps) {

        initComponents();
        this.PresetChrono = PresetChrono;
        this.temps = temps;
        PanneauGrille.setLayout(new GridLayout(nbLignes, nbColonnes));
        this.grille = new GrilleDeJeu(nbLignes, nbColonnes);
        
        
        CustomPopup popup = new CustomPopup("GAME OVER", "Page de fin");
        popup.setVisible(true);
        
        // Charger la grille à partir de la sauvegarde
        grille.chargerGrille(sauvegarde);

        getContentPane().add(PanneauGrille, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, nbColonnes * 40, nbLignes * 40));

        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                CelluleGraphique bouton_cellule = new CelluleGraphique(grille.matriceCellules[i][j], 36, 36);

                PanneauGrille.add(bouton_cellule); // ajout au Jpanel PanneauGrille
            }
        }

        ImageIcon icon = new ImageIcon(getClass().getResource("/logo.jpg"));
        setIconImage(icon.getImage());

        int score = grille.getScore();
        jLabelScore.setText(String.valueOf(score)); // Méthode 1
        sauvegarderScore(score);
        
        RELANCER.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code à exécuter lorsque le bouton "LancerPartie" est cliqué.
                DebutPartieClone f = new DebutPartieClone(nbLignes, nbColonnes, PresetChrono);
                f.setVisible(true);
                dispose();

            }
        });

        LabelChrono.setText(temps);

        System.out.println("woww preset : " + PresetChrono);

        setLocationRelativeTo(null);
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        RELANCER = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabelScore = new javax.swing.JLabel();
        LabelChrono = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 600));
        setPreferredSize(new java.awt.Dimension(560, 500));

        PanneauGrille.setBackground(new java.awt.Color(0, 0, 0));
        PanneauGrille.setPreferredSize(new java.awt.Dimension(310, 500));

        javax.swing.GroupLayout PanneauGrilleLayout = new javax.swing.GroupLayout(PanneauGrille);
        PanneauGrille.setLayout(PanneauGrilleLayout);
        PanneauGrilleLayout.setHorizontalGroup(
            PanneauGrilleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );
        PanneauGrilleLayout.setVerticalGroup(
            PanneauGrilleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 456, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Snap ITC", 0, 24)); // NOI18N
        jLabel1.setText(" GAME OVER");

        RELANCER.setBackground(new java.awt.Color(237, 237, 237));
        RELANCER.setFont(new java.awt.Font("Snap ITC", 0, 14)); // NOI18N
        RELANCER.setText("RELANCER");
        RELANCER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RELANCERActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Snap ITC", 0, 18)); // NOI18N
        jLabel2.setText("Ton Score :");

        jLabelScore.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelScore.setText("Score");

        LabelChrono.setFont(new java.awt.Font("Snap ITC", 0, 18)); // NOI18N
        LabelChrono.setText("00:00");

        jLabel3.setFont(new java.awt.Font("Snap ITC", 0, 18)); // NOI18N
        jLabel3.setText("Temps de jeu :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(52, 52, 52))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(LabelChrono, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabelScore, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(RELANCER)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(LabelChrono)
                .addGap(50, 50, 50)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelScore, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(RELANCER)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanneauGrille, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanneauGrille, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
            /**
     * Méthode pour sauvegarder le score dans un fichier texte.
     * @param score Le score à sauvegarder.
     */
    private void sauvegarderScore(int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("topscores.txt", true))) {
            // Ajouter le score au fichier
            writer.write(String.valueOf(score));
            writer.newLine();  // passer à la ligne suivante pour le prochain score
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    /**
     * Cette méthode est appelée lorsqu'on appuie sur le bouton RELANCER. Elle
     * gère les actions à effectuer lors du clic sur le bouton de relance.
     *
     * @param evt L'événement associé au clic sur le bouton RELANCER.
     */
    private void RELANCERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RELANCERActionPerformed

    }//GEN-LAST:event_RELANCERActionPerformed

    /**
     * Méthode principale qui lance l'application de la fenêtre de fin de
     * partie.
     *
     * @param args Les arguments de la ligne de commande.
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelChrono;
    private javax.swing.JPanel PanneauGrille;
    private javax.swing.JButton RELANCER;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelScore;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
