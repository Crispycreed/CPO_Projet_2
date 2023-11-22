
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
import javax.swing.border.Border;

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
    private int nbColonnes = 4;
    private int nbLignes = 4;

    /**
     * Creates new form FenetrePrincipale
     */
    public FenetrePrincipale() {
        this.nbColonnes = nbColonnes;
        this.nbLignes = nbLignes;
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
                    FinPartie f = new FinPartie(sauvegarde, nbColonnes, nbLignes);
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
                    FinPartie f = new FinPartie(sauvegarde, nbColonnes, nbLignes);
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
                    FinPartie f = new FinPartie(sauvegarde, nbColonnes, nbLignes);
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
                    FinPartie f = new FinPartie(sauvegarde, nbColonnes, nbLignes);
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
                    FinPartie f = new FinPartie(sauvegarde, nbColonnes, nbLignes);
                    f.setVisible(true);
                }
            }
        });

        // Assurez-vous que votre fenêtre est focalisée pour recevoir des événements clavier
        setFocusable(true);

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 600));

        jPanelText1.setBackground(new java.awt.Color(237, 237, 237));
        jPanelText1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Sylfaen", 2, 10)); // NOI18N
        jLabel1.setText("Ou touches Clavier");

        javax.swing.GroupLayout jPanelText1Layout = new javax.swing.GroupLayout(jPanelText1);
        jPanelText1.setLayout(jPanelText1Layout);
        jPanelText1Layout.setHorizontalGroup(
            jPanelText1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelText1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelText1Layout.setVerticalGroup(
            jPanelText1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelText1Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        PanneauGrille.setBackground(new java.awt.Color(0, 0, 0));

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

        ControlPanel.setBackground(new java.awt.Color(255, 255, 255));
        ControlPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ControlPanel.setPreferredSize(new java.awt.Dimension(160, 120));

        javax.swing.GroupLayout ControlPanelLayout = new javax.swing.GroupLayout(ControlPanel);
        ControlPanel.setLayout(ControlPanelLayout);
        ControlPanelLayout.setHorizontalGroup(
            ControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 156, Short.MAX_VALUE)
        );
        ControlPanelLayout.setVerticalGroup(
            ControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanneauGrille, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ControlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelText1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(84, 84, 84))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ControlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(343, Short.MAX_VALUE))
            .addComponent(PanneauGrille, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FenetrePrincipale().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ControlPanel;
    private javax.swing.JPanel PanneauGrille;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanelText1;
    // End of variables declaration//GEN-END:variables
}
