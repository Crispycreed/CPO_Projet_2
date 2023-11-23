
import java.awt.Color;
import static java.awt.Color.BLACK;
import static java.awt.Color.black;
import static java.awt.Color.white;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author ordim
 */
public class FenetrePrincipale extends javax.swing.JFrame {

    GrilleDeJeu grille;
    int nbCoups;
    int i;
    int minutes;
    int secondes;

    private int nbColonnes;
    private int nbLignes;
    private Timer timer;
    private String PresetChrono;

    /**
     * Creates new form FenetrePrincipale
     */
    public FenetrePrincipale(int nbLignes2, int nbColonnes2, String PresetChrono) {
        this.nbColonnes = nbColonnes2;
        this.nbLignes = nbLignes2;
        this.PresetChrono = PresetChrono;
        initComponents();

        PanneauGrille.setLayout(new GridLayout(nbLignes, nbColonnes));
        this.grille = new GrilleDeJeu(nbLignes, nbColonnes);
        getContentPane().add(PanneauGrille, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, nbColonnes * 40, nbLignes * 40));
        grille.initialiserCellulesAleatoires();

        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                CelluleGraphique bouton_cellule = new CelluleGraphique(grille.matriceCellules[i][j], 36, 36);

                PanneauGrille.add(bouton_cellule); // ajout au Jpanel PanneauGrille
            }

        }

        ControlPanel.setLayout(null);
        getContentPane().add(ControlPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 0, 0));
        getContentPane().add(jPanelText1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 0, 0));
        getContentPane().add(jPanelText2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 0, 0));
        this.pack();
        this.revalidate();

        // création des boutons directionnels
        JButton bouton_droite = new JButton("→");
        JButton bouton_gauche = new JButton("←");
        JButton bouton_haut = new JButton("↑");
        JButton bouton_bas = new JButton("↓");

        // positionnement des boutons
        bouton_gauche.setBounds(10, 4 * 20 - 20, 40, 40);
        bouton_haut.setBounds(4 * 20 - 20, 10, 40, 40);
        bouton_bas.setBounds(4 * 20 - 20, 4 * 20 - 20, 40, 40);
        bouton_droite.setBounds(4 * 20 * 2 - 50, 4 * 20 - 20, 40, 40);

        ControlPanel.add(bouton_gauche);
        ControlPanel.add(bouton_haut);
        ControlPanel.add(bouton_bas);
        ControlPanel.add(bouton_droite);

        ActionListener ecouteurClick = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == bouton_droite) {
                    grille.additionnerCellulesAdjacentesVersLaDroite();
                }
                repaint();
                requestFocusInWindow(); // Assurez-vous que la fenêtre a le focus
                int cellulesVides = grille.nombreCellulesVides();
                if (cellulesVides == 0) {
                    int[][] sauvegarde = grille.sauvegarderGrille();
                    dispose();
                    FinPartie f = new FinPartie(sauvegarde, nbColonnes, nbLignes, PresetChrono);
                    f.setVisible(true);
                }
            }
        };
        bouton_droite.addActionListener(ecouteurClick);

        ActionListener ecouteurClick2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == bouton_gauche) {
                    grille.additionnerCellulesAdjacentesVersLaGauche();
                }
                repaint();
                requestFocusInWindow(); // Assurez-vous que la fenêtre a le focus
                int cellulesVides = grille.nombreCellulesVides();
                if (cellulesVides == 0) {
                    int[][] sauvegarde = grille.sauvegarderGrille();
                    dispose();
                    FinPartie f = new FinPartie(sauvegarde, nbColonnes, nbLignes, PresetChrono);
                    f.setVisible(true);
                }
            }
        };
        bouton_gauche.addActionListener(ecouteurClick2);

        ActionListener ecouteurClick3 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == bouton_haut) {
                    grille.additionnerCellulesAdjacentesVersLeHaut();
                }
                repaint();
                requestFocusInWindow(); // Assurez-vous que la fenêtre a le focus
                int cellulesVides = grille.nombreCellulesVides();
                if (cellulesVides == 0) {
                    int[][] sauvegarde = grille.sauvegarderGrille();
                    dispose();
                    FinPartie f = new FinPartie(sauvegarde, nbColonnes, nbLignes, PresetChrono);
                    f.setVisible(true);
                }
            }
        };
        bouton_haut.addActionListener(ecouteurClick3);

        ActionListener ecouteurClick4 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == bouton_bas) {
                    grille.additionnerCellulesAdjacentesVersLeBas();
                }
                repaint();
                requestFocusInWindow(); // Assurez-vous que la fenêtre a le focus
                int cellulesVides = grille.nombreCellulesVides();
                if (cellulesVides == 0) {
                    int[][] sauvegarde = grille.sauvegarderGrille();
                    dispose();
                    FinPartie f = new FinPartie(sauvegarde, nbColonnes, nbLignes, PresetChrono);
                    f.setVisible(true);
                }
            }
        };
        bouton_bas.addActionListener(ecouteurClick4);
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon(getClass().getResource("/logo.jpg"));
        setIconImage(icon.getImage());

        // Ajoutez un KeyListener à votre fenêtre
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Traitement des touches de direction
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        grille.additionnerCellulesAdjacentesVersLaGauche();
                        break;
                    case KeyEvent.VK_RIGHT:
                        grille.additionnerCellulesAdjacentesVersLaDroite();
                        break;
                    case KeyEvent.VK_UP:
                        grille.additionnerCellulesAdjacentesVersLeHaut();
                        break;
                    case KeyEvent.VK_DOWN:
                        grille.additionnerCellulesAdjacentesVersLeBas();
                        break;
                }

                // Redessinez la grille
                repaint();

                // Vérifiez s'il y a des cellules vides
                int cellulesVides = grille.nombreCellulesVides();
                if (cellulesVides == 0) {
                    int[][] sauvegarde = grille.sauvegarderGrille();
                    dispose();
                    FinPartie f = new FinPartie(sauvegarde, nbColonnes, nbLignes, PresetChrono);
                    f.setVisible(true);
                }
            }
        });

        // Assurez-vous que votre fenêtre est focalisée pour recevoir des événements clavier
        setFocusable(true);
        setResizable(false);

        QUITTER.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code à exécuter lorsque le bouton "LancerPartie" est cliqué.
                DebutPartieClone f = new DebutPartieClone(nbLignes2, nbColonnes2, PresetChrono);
                f.setVisible(true);
                dispose();

            }
        });

        // --------------------------------------------------------------- TIMER
        // Initialisation du Timer pour exécuter une tâche toutes les 1000 millisecondes
        minutes = 0;
        secondes = 0;

        
        System.out.println("woww preset : " + PresetChrono);
        
        
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondes++;
                if (secondes == 60) {
                    secondes = 0;
                    minutes++;
                }

                // Formatage pour afficher les minutes et les secondes sous la forme MM:SS
                String temps = String.format("%02d:%02d", minutes, secondes);
                LabelChrono.setText(temps);

                if (PresetChrono == "30secs") {
                    if (secondes == 30) {
                        int[][] sauvegarde = grille.sauvegarderGrille();
                        dispose();
                        FinPartie f = new FinPartie(sauvegarde, nbColonnes, nbLignes, PresetChrono);
                        f.setVisible(true);
                    }
                } else if (PresetChrono.equals("1min")) {
                    if (minutes == 1) {
                        int[][] sauvegarde = grille.sauvegarderGrille();
                        dispose();
                        FinPartie f = new FinPartie(sauvegarde, nbColonnes, nbLignes, PresetChrono);
                        f.setVisible(true);
                    }
                } else if (PresetChrono.equals("2mins")) {
                    if (minutes == 2) {
                        int[][] sauvegarde = grille.sauvegarderGrille();
                        dispose();
                        FinPartie f = new FinPartie(sauvegarde, nbColonnes, nbLignes, PresetChrono);
                        f.setVisible(true);
                    }
                } else if (PresetChrono.equals("3mins")) {
                    if (minutes == 3) {
                        int[][] sauvegarde = grille.sauvegarderGrille();
                        dispose();
                        FinPartie f = new FinPartie(sauvegarde, nbColonnes, nbLignes, PresetChrono);
                        f.setVisible(true);
                    }
                }

            }
        });
        timer.start(); // Démarre le Timer

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelText1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        PanneauGrille = new javax.swing.JPanel();
        ControlPanel = new javax.swing.JPanel();
        jPanelText2 = new javax.swing.JPanel();
        QUITTER = new javax.swing.JButton();
        LabelChrono = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 600));

        jPanelText1.setBackground(new java.awt.Color(237, 237, 237));
        jPanelText1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Snap ITC", 0, 10)); // NOI18N
        jLabel1.setText("Ou touches Clavier");

        javax.swing.GroupLayout jPanelText1Layout = new javax.swing.GroupLayout(jPanelText1);
        jPanelText1.setLayout(jPanelText1Layout);
        jPanelText1Layout.setHorizontalGroup(
            jPanelText1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelText1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jPanelText1Layout.setVerticalGroup(
            jPanelText1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
        );

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
            .addGap(0, 0, Short.MAX_VALUE)
        );

        ControlPanel.setBackground(new java.awt.Color(237, 237, 237));
        ControlPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ControlPanel.setPreferredSize(new java.awt.Dimension(160, 120));

        javax.swing.GroupLayout ControlPanelLayout = new javax.swing.GroupLayout(ControlPanel);
        ControlPanel.setLayout(ControlPanelLayout);
        ControlPanelLayout.setHorizontalGroup(
            ControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ControlPanelLayout.setVerticalGroup(
            ControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );

        jPanelText2.setBackground(new java.awt.Color(237, 237, 237));
        jPanelText2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        QUITTER.setBackground(new java.awt.Color(237, 237, 237));
        QUITTER.setFont(new java.awt.Font("Snap ITC", 0, 14)); // NOI18N
        QUITTER.setText("QUITTER");
        QUITTER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QUITTERActionPerformed(evt);
            }
        });

        LabelChrono.setFont(new java.awt.Font("Snap ITC", 0, 24)); // NOI18N
        LabelChrono.setText("00:00");

        javax.swing.GroupLayout jPanelText2Layout = new javax.swing.GroupLayout(jPanelText2);
        jPanelText2.setLayout(jPanelText2Layout);
        jPanelText2Layout.setHorizontalGroup(
            jPanelText2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelText2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(QUITTER)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelText2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(LabelChrono, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelText2Layout.setVerticalGroup(
            jPanelText2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelText2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(LabelChrono)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 215, Short.MAX_VALUE)
                .addComponent(QUITTER)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanneauGrille, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ControlPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelText2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelText1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ControlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelText2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(PanneauGrille, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void QUITTERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QUITTERActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_QUITTERActionPerformed

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ControlPanel;
    private javax.swing.JLabel LabelChrono;
    private javax.swing.JPanel PanneauGrille;
    private javax.swing.JButton QUITTER;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanelText1;
    private javax.swing.JPanel jPanelText2;
    // End of variables declaration//GEN-END:variables
}
