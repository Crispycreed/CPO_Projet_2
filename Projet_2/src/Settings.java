
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author ordim
 */
public class Settings extends javax.swing.JFrame {

    GrilleDeJeu grille;
    int nbCoups;
    int i;
    String tp1;
    String tp2;
    String tp3;
    String tp4;
    String tp5;
    private int nbColonnes = 4;
    private int nbLignes = 4;
    private int nbColonnes2;
    private int nbLignes2;
    private String PresetChrono;

    /**
     * Creates new form Settings
     */
    public Settings(int nbLignes2, int nbColonnes2, String PresetChrono) {
        initComponents();

        this.nbColonnes2 = nbColonnes2;
        this.nbLignes2 = nbLignes2;
        this.PresetChrono = PresetChrono;

        PanneauGrille.setLayout(new GridLayout(nbLignes, nbColonnes));
        this.grille = new GrilleDeJeu(nbLignes, nbColonnes);
        getContentPane().add(PanneauGrille, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, nbColonnes * 40, nbLignes * 40));
        grille.initialiserCellulesAleatoires();
        grille.initialiserCellulesAleatoires();
        grille.initialiserCellulesAleatoires2();
        grille.initialiserCellulesAleatoires3();
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                CelluleGraphique bouton_cellule = new CelluleGraphique(grille.matriceCellules[i][j], 36, 36);

                PanneauGrille.add(bouton_cellule); // ajout au Jpanel PanneauGrille
            }
        }

        VALIDER.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nbLignes2 = SliderLignes.getValue();
                int nbColonnes2 = SliderColonnes.getValue();
                // Code à exécuter lorsque le bouton "LancerPartie" est cliqué.
                String PresetChrono2 = (String) ComboBox_tps.getSelectedItem();
                DebutPartieClone f = new DebutPartieClone(nbLignes2, nbColonnes2, PresetChrono2);
                f.setVisible(true);
                dispose();

            }

        });
        
        ANNULER.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DebutPartie f = new DebutPartie();
                f.setVisible(true);
                dispose();

            }

        });

        SliderLignes.setMinimum(2); // Valeur minimale
        SliderLignes.setMaximum(8); // Valeur maximale
        SliderLignes.setValue(nbLignes2);
        // Définition des valeurs minimales et maximales pour SliderColonnes
        SliderColonnes.setMinimum(2); // Valeur minimale
        SliderColonnes.setMaximum(8); // Valeur maximale
        SliderColonnes.setValue(nbColonnes2);

        int nouvelleValeur = SliderLignes.getValue();
        LabelLignes.setText(String.valueOf("Ligne : " + nouvelleValeur));

        SliderLignes.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // Récupérer la nouvelle valeur de SliderLignes

                int nouvelleValeur = SliderLignes.getValue();
                int valeurMax = nouvelleValeur + 3;
                int valeurMin = nouvelleValeur - 4;

                if (valeurMax > 8) {
                    SliderColonnes.setMaximum(8); // Valeur maximale
                } else {
                    SliderColonnes.setMaximum(valeurMax); // Valeur maximale
                }

                if (valeurMin < 2) {
                    SliderColonnes.setMinimum(2); // Valeur maximale
                } else {
                    SliderColonnes.setMinimum(valeurMin); // Valeur maximale
                }

                LabelLignes.setText(String.valueOf("Ligne : " + nouvelleValeur));
            }
        });

        int nouvelleValeur2 = SliderColonnes.getValue();
        LabelColonnes.setText(String.valueOf("Colonnes : " + nouvelleValeur2));

        SliderColonnes.addChangeListener(new ChangeListener() {

            @Override

            public void stateChanged(ChangeEvent e) {

                int nouvelleValeur2 = SliderColonnes.getValue();
                int valeurMax = nouvelleValeur2 + 3;
                int valeurMin = nouvelleValeur2 - 4;

                if (valeurMax > 8) {
                    SliderLignes.setMaximum(8); // Valeur maximale
                } else {
                    SliderLignes.setMaximum(valeurMax); // Valeur maximale
                }

                if (valeurMin < 2) {
                    SliderLignes.setMinimum(2); // Valeur maximale
                } else {
                    SliderLignes.setMinimum(valeurMin); // Valeur maximale
                }

                LabelColonnes.setText(String.valueOf("Colonnes : " + nouvelleValeur2));
            }
        });
        if (PresetChrono.equals("Infinie")) {
            tp1 = "Infinie";
            tp2 = "30secs";
            tp3 = "1min";
            tp4 = "2mins";
            tp5 = "3mins";
        } else if (PresetChrono.equals("30secs")) {
            tp1 = "30secs";
            tp5 = "Infinie";
            tp2 = "1min";
            tp3 = "2mins";
            tp4 = "3mins";
        } else if (PresetChrono.equals("1min")) {
            tp1 = "1min";
            tp2 = "30secs";
            tp3 = "2mins";
            tp4 = "3mins";
            tp5 = "Infinie";
        } else if (PresetChrono.equals("2mins")) {
            tp1 = "2mins";
            tp4 = "3mins";
            tp5 = "Infinie";
            tp2 = "30secs";
            tp3 = "1min";
        } else if (PresetChrono.equals("3mins")) {
            tp1 = "3mins";
            tp5 = "Infinie";
            tp2 = "30secs";
            tp3 = "1min";
            tp4 = "2mins";
        }

        ComboBox_tps.removeAllItems();
        ComboBox_tps.addItem(tp1);
        ComboBox_tps.addItem(tp2);
        ComboBox_tps.addItem(tp3);
        ComboBox_tps.addItem(tp4);
        ComboBox_tps.addItem(tp5);

        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon(getClass().getResource("/logo.jpg"));
        setIconImage(icon.getImage());
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        PanneauGrille = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        VALIDER = new javax.swing.JButton();
        SliderLignes = new javax.swing.JSlider();
        SliderColonnes = new javax.swing.JSlider();
        jSeparator1 = new javax.swing.JSeparator();
        LabelLignes = new javax.swing.JLabel();
        LabelColonnes = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        ComboBox_tps = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jSeparator4 = new javax.swing.JSeparator();
        ANNULER = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanneauGrille.setBackground(new java.awt.Color(0, 0, 0));
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
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Snap ITC", 0, 24)); // NOI18N
        jLabel4.setText("   Settings   ");

        VALIDER.setBackground(new java.awt.Color(237, 237, 237));
        VALIDER.setFont(new java.awt.Font("Snap ITC", 0, 14)); // NOI18N
        VALIDER.setText("VALIDER");
        VALIDER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VALIDERActionPerformed(evt);
            }
        });

        SliderLignes.setSnapToTicks(true);
        SliderLignes.setToolTipText("");
        SliderLignes.setCursor(new java.awt.Cursor(java.awt.Cursor.E_RESIZE_CURSOR));

        SliderColonnes.setSnapToTicks(true);
        SliderColonnes.setCursor(new java.awt.Cursor(java.awt.Cursor.E_RESIZE_CURSOR));

        LabelLignes.setFont(new java.awt.Font("Snap ITC", 0, 10)); // NOI18N
        LabelLignes.setText("Lignes : ");

        LabelColonnes.setFont(new java.awt.Font("Snap ITC", 0, 10)); // NOI18N
        LabelColonnes.setText("Colonnes :");

        ComboBox_tps.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboBox_tps.setDoubleBuffered(true);

        jLabel3.setFont(new java.awt.Font("Snap ITC", 0, 12)); // NOI18N
        jLabel3.setText("Chrono max :");

        jLabel5.setFont(new java.awt.Font("Snap ITC", 0, 12)); // NOI18N
        jLabel5.setText("Dark Mode :");

        jToggleButton1.setText("DARK");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        ANNULER.setBackground(new java.awt.Color(237, 237, 237));
        ANNULER.setFont(new java.awt.Font("Snap ITC", 0, 14)); // NOI18N
        ANNULER.setText("RENITIALISER");
        ANNULER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ANNULERActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator2)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ComboBox_tps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator3)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator4)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(LabelColonnes, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                    .addComponent(LabelLignes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(SliderLignes, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                    .addComponent(SliderColonnes, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(VALIDER))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(ANNULER)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SliderLignes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelLignes))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SliderColonnes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelColonnes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBox_tps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jToggleButton1))
                .addGap(18, 18, 18)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(VALIDER)
                .addGap(18, 18, 18)
                .addComponent(ANNULER)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanneauGrille, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanneauGrille, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void VALIDERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VALIDERActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_VALIDERActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void ANNULERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ANNULERActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ANNULERActionPerformed

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
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ANNULER;
    private javax.swing.JComboBox<String> ComboBox_tps;
    private javax.swing.JLabel LabelColonnes;
    private javax.swing.JLabel LabelLignes;
    private javax.swing.JPanel PanneauGrille;
    private javax.swing.JSlider SliderColonnes;
    private javax.swing.JSlider SliderLignes;
    private javax.swing.JButton VALIDER;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
