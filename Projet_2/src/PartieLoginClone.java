
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridLayout;
import static java.awt.SystemColor.desktop;
import java.awt.Taskbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 * classe qui gère l identification des joueurs graphiquement
 * @author ordim
 */
public class PartieLoginClone extends javax.swing.JFrame {

    // ----------------------------------------------------Déclaration_Variables
    GrilleDeJeu grille;
    int nbCoups;
    int i;
    private int nbColonnes = 4;
    private int nbLignes = 4;
    private int nbColonnes2;
    private int nbLignes2;
    private String PresetChrono = "Infinie";
    private ImageIcon icon;
    private int mute = 1;
    private String username1 = "default";
    private int idfind;
    private String mdpfind;
    private String password1 = "default";

    /**
     * Constructeur de la classe DebutPartie.
     */
    public PartieLoginClone(int nbLignes2, int nbColonnes2, String PresetChrono, ImageIcon icon, int mute, String username) {
        initComponents();
        this.nbColonnes2 = nbColonnes2;
        this.nbLignes2 = nbLignes2;
        this.PresetChrono = PresetChrono;
        this.icon = icon;
        this.mute = mute;
        this.username1 = username;

        // ---------------------------------------------------Initialisation musique
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

        MotDePasse MDP = new MotDePasse();
        MDP.creerFichierSiAbsent();

        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                CelluleGraphique bouton_cellule = new CelluleGraphique(grille.matriceCellules[i][j], 36, 36);

                PanneauGrille.add(bouton_cellule);
            }
        }

        // --------------------------------------------------- changer logo
        setLocationRelativeTo(null);

        setIconImage(icon.getImage());

        if (System.getProperty("os.name").toLowerCase().contains("mac")) {

            Taskbar taskbar = Taskbar.getTaskbar();

            // Vérifier si la barre des tâches prend en charge les icônes du Dock
            if (taskbar.isSupported(Taskbar.Feature.ICON_IMAGE)) {

                taskbar.setIconImage(icon.getImage());

            }

        }

        // --------------------------------------------------- Action des boutons
        password.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                password.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                // À faire lorsque le champ de mot de passe perd le focus
                password1 = password.getText();
            }
        });

        usernamE.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                usernamE.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                // À faire lorsque le champ de texte perd le focus
                username1 = usernamE.getText();
            }
        });

        LOGIN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                idfind = MDP.verificationIdentifiant(username1);

                if (idfind == 1) {

                    mdpfind = MDP.recupererMotDePasse(username1);

                    if (mdpfind.equals(password1)) {
                        lecteur.lireFichierWAV("Sclic.wav");
                        DebutPartieClone f = new DebutPartieClone(nbLignes2, nbColonnes2, PresetChrono, icon, mute, username1);
                        f.setVisible(true);
                        dispose();
                    } else {

                        // Message principal avec une police différente pour chaque partie
                        String messagePart1 = "Ce mot de passe est incorrect";
                        String messagePart2 = "(Essayez en un autre)";

                        Font largerFont = new Font(Font.DIALOG, Font.PLAIN, UIManager.getFont("Label.font").getSize());

                        JLabel messageLabel = new JLabel("<html><div style='text-align: center;'>"
                                + messagePart1 + "<br>"
                                + "<font size=-2 color='gray'>" + messagePart2 + "</font></div></html>");
                        messageLabel.setFont(largerFont);

                        // Créer un bouton personnalisé avec le texte "Retour" en rouge
                        JButton retourButton = new JButton("Retour");
                        retourButton.setForeground(Color.BLUE);

                        // Afficher une fenêtre de confirmation avec le texte et le bouton personnalisés
                        int choix = JOptionPane.showOptionDialog(null, messageLabel, "Erreur",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Retour"},
                                "Valider");

                    }
                } else {

                    // Message principal avec une police différente pour chaque partie
                    String messagePart1 = "Cet identifiant n'existe pas";
                    String messagePart2 = "(Essayez plutot de vous REGISTER)";

                    Font largerFont = new Font(Font.DIALOG, Font.PLAIN, UIManager.getFont("Label.font").getSize());

                    JLabel messageLabel = new JLabel("<html><div style='text-align: center;'>"
                            + messagePart1 + "<br>"
                            + "<font size=-2 color='gray'>" + messagePart2 + "</font></div></html>");
                    messageLabel.setFont(largerFont);

                    // Créer un bouton personnalisé avec le texte "Retour" en rouge
                    JButton retourButton = new JButton("Retour");
                    retourButton.setForeground(Color.BLUE);

                    // Afficher une fenêtre de confirmation avec le texte et le bouton personnalisés
                    int choix = JOptionPane.showOptionDialog(null, messageLabel, "Erreur",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Retour"},
                            "Valider");

                }

            }
        });

        REGISTER.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                idfind = MDP.verificationIdentifiant(username1);

                if (idfind == 0) {

                    MDP.ajouterMotDePasse(username1, password1);

                    String passeword2 = MDP.recupererMotDePasse(username1);

                    lecteur.lireFichierWAV("Sclic.wav");
                    DebutPartieClone f = new DebutPartieClone(nbLignes2, nbColonnes2, PresetChrono, icon, mute, username1);
                    f.setVisible(true);
                    dispose();

                } else {

                    // Message principal avec une police différente pour chaque partie
                    String messagePart1 = "Cet identifiant existe déja";
                    String messagePart2 = "(Essayez plutot de vous LOGIN)";

                    Font largerFont = new Font(Font.DIALOG, Font.PLAIN, UIManager.getFont("Label.font").getSize());

                    JLabel messageLabel = new JLabel("<html><div style='text-align: center;'>"
                            + messagePart1 + "<br>"
                            + "<font size=-2 color='gray'>" + messagePart2 + "</font></div></html>");
                    messageLabel.setFont(largerFont);

                    // Créer un bouton personnalisé avec le texte "Retour" en rouge
                    JButton retourButton = new JButton("Retour");
                    retourButton.setForeground(Color.BLUE);

                    // Afficher une fenêtre de confirmation avec le texte et le bouton personnalisés
                    int choix = JOptionPane.showOptionDialog(null, messageLabel, "Erreur",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Retour"},
                            "Valider");

                }

            }
        });

        // Ajout du KeyListener pour détecter Ctrl + F
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Ne rien faire ici
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // Détecter Ctrl + F
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_F) {
                    // Code à exécuter lorsque Ctrl + F est pressé
                    MDP.ouvrirFichierSurBureau();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Ne rien faire ici
            }
        });

        //--------------------------------------------------- Fenetre
        setFocusable(true);
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
        LOGIN = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        password = new javax.swing.JPasswordField();
        usernamE = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        REGISTER = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanneauGrille.setBackground(new java.awt.Color(255, 255, 255));
        PanneauGrille.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4)));
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
        jPanel2.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4)));

        jLabel4.setFont(new java.awt.Font("Snap ITC", 0, 24)); // NOI18N
        jLabel4.setText("   THREES!!");

        LOGIN.setBackground(new java.awt.Color(237, 237, 237));
        LOGIN.setFont(new java.awt.Font("Snap ITC", 0, 12)); // NOI18N
        LOGIN.setText("LOGIN");
        LOGIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LOGINActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Snap ITC", 0, 12)); // NOI18N
        jLabel5.setText("                  ou");

        password.setText("jPasswordField1");
        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });

        usernamE.setText("Username");

        jLabel8.setFont(new java.awt.Font("Snap ITC", 0, 12)); // NOI18N
        jLabel8.setText("  Connecte toi a ton compte  ");

        jLabel10.setFont(new java.awt.Font("Snap ITC", 0, 12)); // NOI18N
        jLabel10.setText("   Créé un nouveau compte");

        REGISTER.setBackground(new java.awt.Color(237, 237, 237));
        REGISTER.setFont(new java.awt.Font("Snap ITC", 0, 12)); // NOI18N
        REGISTER.setText("REGISTER");
        REGISTER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                REGISTERActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Ctrl+f -> open MDP files");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(usernamE, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(REGISTER)
                .addGap(50, 50, 50))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(LOGIN))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(15, 15, 15)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(usernamE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(LOGIN)
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(REGISTER)
                .addGap(38, 38, 38))
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
            .addComponent(PanneauGrille, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LOGINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LOGINActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LOGINActionPerformed

    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordActionPerformed

    private void REGISTERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REGISTERActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_REGISTERActionPerformed

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
            java.util.logging.Logger.getLogger(PartieLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PartieLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PartieLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PartieLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PartieLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LOGIN;
    private javax.swing.JPanel PanneauGrille;
    private javax.swing.JButton REGISTER;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField usernamE;
    // End of variables declaration//GEN-END:variables
}
