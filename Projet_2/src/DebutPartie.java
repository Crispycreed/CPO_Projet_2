
import java.awt.Desktop;
import java.awt.GridLayout;
import static java.awt.SystemColor.desktop;
import java.awt.Taskbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author ordim
 */
/**
 *
 * Cette classe représente la fenêtre de début de partie. Elle permet de lancer
 * une nouvelle partie, accéder aux paramètres, aux règles, et aux meilleurs
 * scores. La classe utilise la grille de jeu pour initialiser la fenêtre.
 *
 * @author ordim
 */
public class DebutPartie extends javax.swing.JFrame {

    // ----------------------------------------------------Déclaration_Variables
    GrilleDeJeu grille;
    int nbCoups;
    int i;
    private int nbColonnes = 4;
    private int nbLignes = 4;
    private String PresetChrono = "Infinie";
    private ImageIcon icon = new ImageIcon(getClass().getResource("/lOGO1.png"));

    /**
     * Constructeur de la classe DebutPartie.
     */
    public DebutPartie() {
        initComponents();
        LecteurWAV lecteur = new LecteurWAV();
        lecteur.arreterLecture();

        // ---------------------------------------------------Panneau_Grille
        PanneauGrille.setLayout(new GridLayout(nbLignes, nbColonnes));
        this.grille = new GrilleDeJeu(nbLignes, nbColonnes);
        getContentPane().add(PanneauGrille, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, nbColonnes * 40, nbLignes * 40));
        grille.initialiserCellulesAleatoires();
        grille.initialiserCellulesAleatoires();
        grille.initialiserCellulesAleatoires();
        grille.initialiserCellulesAleatoires();
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                CelluleGraphique bouton_cellule = new CelluleGraphique(grille.matriceCellules[i][j], 36, 36);

                PanneauGrille.add(bouton_cellule);
            }
        }

        // --------------------------------------------------- changer logo
        setLocationRelativeTo(null);
        setIconImage(icon.getImage());
        
        // --------------------------------------------------- changer logo mac

        setLocationRelativeTo(null);

        setIconImage(icon.getImage());

        if (System.getProperty("os.name").toLowerCase().contains("mac")) {

            Taskbar taskbar = Taskbar.getTaskbar();

            // Vérifier si la barre des tâches prend en charge les icônes du Dock
            if (taskbar.isSupported(Taskbar.Feature.ICON_IMAGE)) {

                taskbar.setIconImage(icon.getImage());

            }

        }
        

        // --------------------------------------------------- Action du bouton "PLAY"
        LANCER.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lecteur.lireFichierWAV("Slancer.wav");
                FenetrePrincipale f = new FenetrePrincipale(nbLignes, nbColonnes, PresetChrono, icon);
                f.setVisible(true);
                dispose();
            }
        });

        // --------------------------------------------------- Action du bouton "Settings"
        Settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lecteur.lireFichierWAV("Sclic.wav");
                Settings f = new Settings(nbLignes, nbColonnes, PresetChrono, icon);
                f.setVisible(true);
                dispose();
            }
        });

        // --------------------------------------------------- Action du bouton "Rules"
        Desktop desktop = Desktop.getDesktop();
        livre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lecteur.lireFichierWAV("Sclic.wav");
                try {
                    URL url = getClass().getResource("/regles.pdf");
                    if (url != null) {
                        desktop.browse(url.toURI());
                    } else {
                        System.out.println("Fichier PDF introuvable.");
                    }
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // --------------------------------------------------- Action du bouton "Top"
        top.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lecteur.lireFichierWAV("Sclic.wav");
                TopScoreGraphique f = new TopScoreGraphique(nbLignes, nbColonnes, PresetChrono, icon);
                f.setVisible(true);
                dispose();
            }
        });

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
        jLabel4 = new javax.swing.JLabel();
        LANCER = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        Settings = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        livre = new javax.swing.JButton();
        top = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(560, 560));

        PanneauGrille.setBackground(new java.awt.Color(0, 0, 0));
        PanneauGrille.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(173, 216, 230), 4)));
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
        jPanel2.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(173, 216, 230), 4)));

        jLabel4.setFont(new java.awt.Font("Snap ITC", 0, 24)); // NOI18N
        jLabel4.setText("   THREES!!");

        LANCER.setBackground(new java.awt.Color(237, 237, 237));
        LANCER.setFont(new java.awt.Font("Snap ITC", 0, 14)); // NOI18N
        LANCER.setText("PLAY");
        LANCER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LANCERActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Snap ITC", 0, 12)); // NOI18N
        jLabel5.setText("     essaye de faire le");

        jLabel6.setFont(new java.awt.Font("Snap ITC", 0, 12)); // NOI18N
        jLabel6.setText("  plus grand SCORE");

        jLabel7.setFont(new java.awt.Font("Snap ITC", 0, 12)); // NOI18N
        jLabel7.setText("    Combine les Cellules et");

        jLabel8.setFont(new java.awt.Font("Snap ITC", 0, 12)); // NOI18N
        jLabel8.setText("  Settings ");

        Settings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/settings.png"))); // NOI18N
        Settings.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Settings.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Settings.setFocusPainted(false);
        Settings.setFocusable(false);

        jLabel9.setFont(new java.awt.Font("Snap ITC", 0, 12)); // NOI18N
        jLabel9.setText("    Rules");

        jLabel10.setFont(new java.awt.Font("Snap ITC", 0, 12)); // NOI18N
        jLabel10.setText("     Top");

        livre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/livre.png"))); // NOI18N
        livre.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        livre.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        livre.setFocusPainted(false);
        livre.setFocusable(false);

        top.setIcon(new javax.swing.ImageIcon(getClass().getResource("/score2.png"))); // NOI18N
        top.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        top.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        top.setFocusPainted(false);
        top.setFocusable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(LANCER)
                .addGap(66, 66, 66))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(top, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(livre, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Settings, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addGap(39, 39, 39)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Settings, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(livre, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(top, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LANCER)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanneauGrille, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanneauGrille, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LANCERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LANCERActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LANCERActionPerformed

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
                new DebutPartie().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LANCER;
    private javax.swing.JPanel PanneauGrille;
    private javax.swing.JButton Settings;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton livre;
    private javax.swing.JButton top;
    // End of variables declaration//GEN-END:variables
}
