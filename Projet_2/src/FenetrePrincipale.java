
import java.awt.Color;
import static java.awt.Color.BLACK;
import static java.awt.Color.black;
import static java.awt.Color.white;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import static java.awt.SystemColor.desktop;
import java.awt.Taskbar;
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
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.UIManager;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 * La classe FenetrePrincipale représente la fenêtre principale du jeu. Elle
 * affiche la grille de jeu, les contrôles et gère le déroulement de la partie.
 *
 * @author ordim
 */
public class FenetrePrincipale extends javax.swing.JFrame {
    
    // ----------------------------------------------------Déclaration_Variables
    GrilleDeJeu grille;
    int nbCoups;
    int minutes = 0;
    int secondes = 0;
    private String temps;
    private int nbColonnes;
    private int nbLignes;
    private Timer timer;
    private String PresetChrono;
    private int mute;
    private int reset;
    private String username;

    /**
     * Constructeur de la classe FenetrePrincipale.
     *
     * @param nbLignes2 Le nombre de lignes de la grille.
     * @param nbColonnes2 Le nombre de colonnes de la grille.
     * @param PresetChrono La configuration du chronomètre.
     * @param icon L'icône de la fenêtre.
     * @param mute Indique si le son est activé (1) ou désactivé (0).
     * @param username Le nom d'utilisateur du joueur.
     */
    public FenetrePrincipale(int nbLignes2, int nbColonnes2, String PresetChrono, ImageIcon icon, int mute, String username) {

        // ------------------------------------------------Déclaration_Variables
        this.nbColonnes = nbColonnes2;
        this.nbLignes = nbLignes2;
        this.PresetChrono = PresetChrono;
        this.mute = mute;
        this.username = username;
        initComponents();

        // ---------------------------------------------------Panels/Emplacement
        PanneauGrille.setLayout(new GridLayout(nbLignes, nbColonnes));
        this.grille = new GrilleDeJeu(nbLignes, nbColonnes);

        getContentPane().add(PanneauGrille, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, nbColonnes * 40, nbLignes * 40));
        grille.initialiserCellulesAleatoiresEquitablement();
        LecteurWAV lecteur = new LecteurWAV();
        lecteur.arreterLecture();

        ControlPanel.setLayout(null);
        getContentPane().add(ControlPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 0, 0));
        getContentPane().add(jPanelText1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 0, 0));
        getContentPane().add(jPanelText2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 0, 0));
        this.pack();
        this.revalidate();

        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                CelluleGraphique bouton_cellule = new CelluleGraphique(grille.matriceCellules[i][j], 36, 36);

                PanneauGrille.add(bouton_cellule);
            }

        }

        // ---------------------------------------------------- Création_Boutons
        JButton bouton_droite = new JButton("→");
        JButton bouton_gauche = new JButton("←");
        JButton bouton_haut = new JButton("↑");
        JButton bouton_bas = new JButton("↓");

        bouton_gauche.setBounds(10, 4 * 20 - 20, 40, 40);
        bouton_haut.setBounds(4 * 20 - 20, 10, 40, 40);
        bouton_bas.setBounds(4 * 20 - 20, 4 * 20 - 20, 40, 40);
        bouton_droite.setBounds(4 * 20 * 2 - 50, 4 * 20 - 20, 40, 40);

        ControlPanel.add(bouton_gauche);
        ControlPanel.add(bouton_haut);
        ControlPanel.add(bouton_bas);
        ControlPanel.add(bouton_droite);

        // --------------------------------------------------------------Boutons
        QUITTER.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String messagePart1 = "Êtes-vous sûr de votre choix ?";
                String messagePart2 = "(Vous allez quitter la partie sans sauvegarder votre score)";

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
                    lecteur.arreterLecture();
                    lecteur.lireFichierWAV("Sclic.wav");
                    lecteur.arreterLecture();

                    DebutPartieClone f = new DebutPartieClone(nbLignes2, nbColonnes2, PresetChrono, icon, mute, username);
                    f.setVisible(true);

                    dispose();

                } else {
                    reset = 0;
                    lecteur.lireFichierWAV("Sclic.wav");
                    // L'utilisateur a cliqué sur "Retour" ou fermé la fenêtre
                }
            }
        });

        // --------------------------------------------------------EcouteurClick
        ActionListener ecouteurClick = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == bouton_droite) {
                    grille.additionnerCellulesAdjacentesVersLaDroite();
                }
                repaint();
                requestFocusInWindow();
                int cellulesVides = grille.nombreCellulesVides();
                int score = grille.getScore();
                jLabelScore.setText(String.valueOf(score));
                if (cellulesVides == 0) {
                    int[][] sauvegarde = grille.sauvegarderGrille();
                    dispose();
                    timer.stop();
                    lecteur.arreterLecture();
                    FinPartie f = new FinPartie(sauvegarde, nbColonnes, nbLignes, PresetChrono, temps, icon, mute, username);
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
                requestFocusInWindow();
                int cellulesVides = grille.nombreCellulesVides();
                int score = grille.getScore();
                jLabelScore.setText(String.valueOf(score));
                if (cellulesVides == 0) {
                    int[][] sauvegarde = grille.sauvegarderGrille();
                    dispose();
                    timer.stop();
                    lecteur.arreterLecture();
                    FinPartie f = new FinPartie(sauvegarde, nbColonnes, nbLignes, PresetChrono, temps, icon, mute, username);
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
                requestFocusInWindow();
                int cellulesVides = grille.nombreCellulesVides();
                int score = grille.getScore();
                jLabelScore.setText(String.valueOf(score));
                if (cellulesVides == 0) {
                    int[][] sauvegarde = grille.sauvegarderGrille();
                    dispose();
                    timer.stop();
                    lecteur.arreterLecture();
                    FinPartie f = new FinPartie(sauvegarde, nbColonnes, nbLignes, PresetChrono, temps, icon, mute, username);
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
                    int score = grille.getScore();
                    jLabelScore.setText(String.valueOf(score));
                }
                repaint();
                requestFocusInWindow();
                int cellulesVides = grille.nombreCellulesVides();
                if (cellulesVides == 0) {
                    int[][] sauvegarde = grille.sauvegarderGrille();
                    dispose();
                    timer.stop();
                    lecteur.arreterLecture();
                    FinPartie f = new FinPartie(sauvegarde, nbColonnes, nbLignes, PresetChrono, temps, icon, mute, username);
                    f.setVisible(true);
                }
            }
        };
        bouton_bas.addActionListener(ecouteurClick4);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        int score = grille.getScore();
                        jLabelScore.setText(String.valueOf(score));
                        grille.additionnerCellulesAdjacentesVersLaGauche();
                        break;
                    case KeyEvent.VK_RIGHT:
                        score = grille.getScore();
                        jLabelScore.setText(String.valueOf(score));
                        grille.additionnerCellulesAdjacentesVersLaDroite();
                        break;
                    case KeyEvent.VK_UP:
                        score = grille.getScore();
                        jLabelScore.setText(String.valueOf(score));
                        grille.additionnerCellulesAdjacentesVersLeHaut();
                        break;
                    case KeyEvent.VK_DOWN:
                        score = grille.getScore();
                        jLabelScore.setText(String.valueOf(score));
                        grille.additionnerCellulesAdjacentesVersLeBas();
                        break;
                }
                repaint();

                int cellulesVides = grille.nombreCellulesVides();
                if (cellulesVides == 0) {
                    int[][] sauvegarde = grille.sauvegarderGrille();
                    dispose();
                    timer.stop();
                    lecteur.arreterLecture();
                    FinPartie f = new FinPartie(sauvegarde, nbColonnes, nbLignes, PresetChrono, temps, icon, mute, username);
                    f.setVisible(true);
                }
            }
        });

        Desktop desktop = Desktop.getDesktop();
        tips.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    URL url = getClass().getResource("/tips.pdf");
                    if (url != null) {
                        desktop.browse(url.toURI());
                    }
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // --------------------------------------------------------------- TIMER
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondes++;
                if (secondes == 60) {
                    secondes = 0;
                    minutes++;
                }

                // Formatage pour afficher les minutes et les secondes sous la forme MM:SS
                temps = String.format("%02d:%02d", minutes, secondes);
                LabelChrono.setText(temps);

                if (PresetChrono == "30secs") {
                    if (secondes == 30) {
                        int[][] sauvegarde = grille.sauvegarderGrille();
                        timer.stop();
                        dispose();
                        lecteur.arreterLecture();
                        FinPartie f = new FinPartie(sauvegarde, nbColonnes, nbLignes, PresetChrono, temps, icon, mute, username);
                        f.setVisible(true);
                    }
                } else if (PresetChrono.equals("1min")) {
                    if (minutes == 1) {
                        int[][] sauvegarde = grille.sauvegarderGrille();
                        timer.stop();
                        dispose();
                        lecteur.arreterLecture();
                        FinPartie f = new FinPartie(sauvegarde, nbColonnes, nbLignes, PresetChrono, temps, icon, mute, username);
                        f.setVisible(true);
                    }
                } else if (PresetChrono.equals("2mins")) {
                    if (minutes == 2) {
                        int[][] sauvegarde = grille.sauvegarderGrille();
                        timer.stop();
                        dispose();
                        lecteur.arreterLecture();
                        FinPartie f = new FinPartie(sauvegarde, nbColonnes, nbLignes, PresetChrono, temps, icon, mute, username);
                        f.setVisible(true);
                    }
                } else if (PresetChrono.equals("3mins")) {
                    if (minutes == 3) {
                        int[][] sauvegarde = grille.sauvegarderGrille();
                        timer.stop();
                        dispose();
                        lecteur.arreterLecture();
                        FinPartie f = new FinPartie(sauvegarde, nbColonnes, nbLignes, PresetChrono, temps, icon, mute, username);
                        f.setVisible(true);
                    }
                }

            }
        });
        timer.start();

        // ---------------------------------------------------------Icon_Fenetre 
        setIconImage(icon.getImage()); 
        
        if (System.getProperty("os.name").toLowerCase().contains("mac")) {

            Taskbar taskbar = Taskbar.getTaskbar();

            // Vérifier si la barre des tâches prend en charge les icônes du Dock
            if (taskbar.isSupported(Taskbar.Feature.ICON_IMAGE)) {

                taskbar.setIconImage(icon.getImage());

            }

        }

        // ---------------------------------------------------------Musique
        if (mute == 1) {
            lecteur.lireFichierWAV("Scalme.wav");
        }

        // ------------------------------------------------------------- Fenetre
        setFocusable(true);
        setResizable(false);
        setLocationRelativeTo(null);
        jMenu1.setText(username);

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
        tips = new javax.swing.JButton();
        jLabelScore = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 600));

        jPanelText1.setBackground(new java.awt.Color(237, 237, 237));
        jPanelText1.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), new javax.swing.border.LineBorder(new java.awt.Color(229, 229, 48), 2, true)));

        jLabel1.setFont(new java.awt.Font("Snap ITC", 0, 10)); // NOI18N
        jLabel1.setText("Ou touches Clavier");

        javax.swing.GroupLayout jPanelText1Layout = new javax.swing.GroupLayout(jPanelText1);
        jPanelText1.setLayout(jPanelText1Layout);
        jPanelText1Layout.setHorizontalGroup(
            jPanelText1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelText1Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jPanelText1Layout.setVerticalGroup(
            jPanelText1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
        );

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

        ControlPanel.setBackground(new java.awt.Color(237, 237, 237));
        ControlPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), new javax.swing.border.LineBorder(new java.awt.Color(128, 128, 128), 3, true)));
        ControlPanel.setPreferredSize(new java.awt.Dimension(160, 120));

        javax.swing.GroupLayout ControlPanelLayout = new javax.swing.GroupLayout(ControlPanel);
        ControlPanel.setLayout(ControlPanelLayout);
        ControlPanelLayout.setHorizontalGroup(
            ControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ControlPanelLayout.setVerticalGroup(
            ControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );

        jPanelText2.setBackground(new java.awt.Color(237, 237, 237));
        jPanelText2.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), new javax.swing.border.LineBorder(new java.awt.Color(173, 216, 230), 4, true)));

        QUITTER.setBackground(new java.awt.Color(237, 237, 237));
        QUITTER.setFont(new java.awt.Font("Snap ITC", 0, 14)); // NOI18N
        QUITTER.setText("LEAVE");
        QUITTER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QUITTERActionPerformed(evt);
            }
        });

        LabelChrono.setFont(new java.awt.Font("Snap ITC", 0, 24)); // NOI18N
        LabelChrono.setText("00:00");

        tips.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tips.png"))); // NOI18N
        tips.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tips.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tips.setFocusPainted(false);
        tips.setFocusable(false);

        jLabelScore.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelScore.setText("Score");
        jLabelScore.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(173, 216, 230), 3, true));
        jLabelScore.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanelText2Layout = new javax.swing.GroupLayout(jPanelText2);
        jPanelText2.setLayout(jPanelText2Layout);
        jPanelText2Layout.setHorizontalGroup(
            jPanelText2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelText2Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addComponent(tips, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelText2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelText2Layout.createSequentialGroup()
                .addGap(0, 25, Short.MAX_VALUE)
                .addGroup(jPanelText2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(QUITTER)
                    .addComponent(LabelChrono, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanelText2Layout.setVerticalGroup(
            jPanelText2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelText2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(LabelChrono)
                .addGap(18, 18, 18)
                .addComponent(jLabelScore, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(tips, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(QUITTER)
                .addGap(21, 21, 21))
        );

        jMenu1.setText(username);
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

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
            .addComponent(PanneauGrille, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
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
    private javax.swing.JLabel jLabelScore;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanelText1;
    private javax.swing.JPanel jPanelText2;
    private javax.swing.JButton tips;
    // End of variables declaration//GEN-END:variables
}
