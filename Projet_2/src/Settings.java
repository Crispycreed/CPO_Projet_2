
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import static java.awt.Image.SCALE_SMOOTH;
import java.awt.Taskbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 * class qui gere la page des settings
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
    private ImageIcon icon2;
    private int mute2;
    private int reset;

    /**
     * Creates new form Settings
     */
    public Settings(int nbLignes2, int nbColonnes2, String PresetChrono, ImageIcon icon, int mute, String username) {
        initComponents();

        this.nbColonnes2 = nbColonnes2;
        this.nbLignes2 = nbLignes2;
        this.PresetChrono = PresetChrono;
        mute2 = mute;
        icon2 = icon;

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
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                CelluleGraphique bouton_cellule = new CelluleGraphique(grille.matriceCellules[i][j], 36, 36);

                PanneauGrille.add(bouton_cellule); // ajout au Jpanel PanneauGrille
            }
        }

        // --------------------------------------------------- Action des boutons
        VALIDER.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lecteur.lireFichierWAV("Sclic.wav");
                int nbLignes2 = SliderLignes.getValue();
                int nbColonnes2 = SliderColonnes.getValue();
                // Code à exécuter lorsque le bouton "LancerPartie" est cliqué.
                String PresetChrono2 = (String) ComboBox_tps.getSelectedItem();
                DebutPartieClone f = new DebutPartieClone(nbLignes2, nbColonnes2, PresetChrono2, icon2, mute2, username);
                f.setVisible(true);
                dispose();

            }

        });

        ANNULER.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lecteur.lireFichierWAV("Sclic.wav");

                String messagePart1 = "Êtes-vous sûr de votre choix ?";
                String messagePart2 = "(Vos paramètres seront rétablis par défaut)";

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
                    lecteur.lireFichierWAV("Sclic.wav");
                    reset = 1;

                    DebutPartie f = new DebutPartie(username);
                    f.setVisible(true);
                    dispose();

                } else {
                    lecteur.lireFichierWAV("Sclic.wav");
                    reset = 0;
                    // L'utilisateur a cliqué sur "Retour" ou fermé la fenêtre
                }
            }

        });

        // --------------------------------------------------- initialisation des slider
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
        if (PresetChrono.equals("Infini")) {
            tp1 = "Infini";
            tp2 = "30secs";
            tp3 = "1min";
            tp4 = "2mins";
            tp5 = "3mins";
        } else if (PresetChrono.equals("30secs")) {
            tp1 = "30secs";
            tp5 = "Infini";
            tp2 = "1min";
            tp3 = "2mins";
            tp4 = "3mins";
        } else if (PresetChrono.equals("1min")) {
            tp1 = "1min";
            tp2 = "30secs";
            tp3 = "2mins";
            tp4 = "3mins";
            tp5 = "Infini";
        } else if (PresetChrono.equals("2mins")) {
            tp1 = "2mins";
            tp4 = "3mins";
            tp5 = "Infini";
            tp2 = "30secs";
            tp3 = "1min";
        } else if (PresetChrono.equals("3mins")) {
            tp1 = "3mins";
            tp5 = "Infini";
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

        // --------------------------------------------------- changer logo
        setIconImage(icon.getImage());
        if (System.getProperty("os.name").toLowerCase().contains("mac")) {

            Taskbar taskbar = Taskbar.getTaskbar();

            // Vérifier si la barre des tâches prend en charge les icônes du Dock
            if (taskbar.isSupported(Taskbar.Feature.ICON_IMAGE)) {

                taskbar.setIconImage(icon.getImage());

            }

        }

        // --------------------------------------------------- Changer ICON        
        jMenu1.add(ICON1);
        ICON1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                lecteur.lireFichierWAV("Sclic.wav");
                if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                    icon2 = new ImageIcon(getClass().getResource("/lOGO1mac.png"));
                } else {
                    icon2 = new ImageIcon(getClass().getResource("/lOGO1.png"));
                }
                setIconImage(icon2.getImage());
            }
        });

        jMenu1.add(ICON2);
        ICON2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                lecteur.lireFichierWAV("Sclic.wav");
                if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                    icon2 = new ImageIcon(getClass().getResource("/lOGO2mac.png"));
                } else {
                    icon2 = new ImageIcon(getClass().getResource("/lOGO2.png"));
                }
                setIconImage(icon2.getImage());
            }
        });

        jMenu1.add(ICON3);
        ICON3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                lecteur.lireFichierWAV("Sclic.wav");
                if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                    icon2 = new ImageIcon(getClass().getResource("/lOGO3mac.png"));
                } else {
                    icon2 = new ImageIcon(getClass().getResource("/lOGO3.png"));
                }
                setIconImage(icon2.getImage());
            }
        });

        // --------------------------------------------------- bouton son on/off
        ButtonMute.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (ButtonMute.isSelected()) {
                    ButtonMute.setText("Off");
                } else {
                    ButtonMute.setText("On");
                }
            }
        });

        ButtonMute.setSelected(mute == 0);
        ButtonMute.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // Vérifiez si le bouton est appuyé ou relâché
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // Bouton appuyé
                    mute2 = 0;
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    // Bouton relâché
                    mute2 = 1;
                }
            }
        });

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

        buttonGroup1 = new javax.swing.ButtonGroup();
        ICON1 = new javax.swing.JButton();
        ICON2 = new javax.swing.JButton();
        ICON3 = new javax.swing.JButton();
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
        ButtonMute = new javax.swing.JToggleButton();
        jSeparator4 = new javax.swing.JSeparator();
        ANNULER = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        ICON1.setBackground(new java.awt.Color(237, 237, 237));
        ICON1.setFont(new java.awt.Font("Snap ITC", 0, 14)); // NOI18N
        ICON1.setText("Icon 1");
        ICON1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ICON1ActionPerformed(evt);
            }
        });

        ICON2.setBackground(new java.awt.Color(237, 237, 237));
        ICON2.setFont(new java.awt.Font("Snap ITC", 0, 14)); // NOI18N
        ICON2.setText("Icon 2");
        ICON2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ICON2ActionPerformed(evt);
            }
        });

        ICON3.setBackground(new java.awt.Color(237, 237, 237));
        ICON3.setFont(new java.awt.Font("Snap ITC", 0, 14)); // NOI18N
        ICON3.setText("Icon 3");
        ICON3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ICON3ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanneauGrille.setBackground(new java.awt.Color(0, 0, 0));
        PanneauGrille.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 4)));
        PanneauGrille.setPreferredSize(new java.awt.Dimension(310, 500));

        javax.swing.GroupLayout PanneauGrilleLayout = new javax.swing.GroupLayout(PanneauGrille);
        PanneauGrille.setLayout(PanneauGrilleLayout);
        PanneauGrilleLayout.setHorizontalGroup(
            PanneauGrilleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 286, Short.MAX_VALUE)
        );
        PanneauGrilleLayout.setVerticalGroup(
            PanneauGrilleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 4)));

        jLabel4.setFont(new java.awt.Font("Snap ITC", 0, 24)); // NOI18N
        jLabel4.setText("   Settings   ");

        VALIDER.setBackground(new java.awt.Color(237, 237, 237));
        VALIDER.setFont(new java.awt.Font("Snap ITC", 0, 14)); // NOI18N
        VALIDER.setText("SAVE");
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
        jLabel5.setText("Music 0/F");

        ButtonMute.setText("On");
        ButtonMute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonMuteActionPerformed(evt);
            }
        });

        ANNULER.setBackground(new java.awt.Color(237, 237, 237));
        ANNULER.setFont(new java.awt.Font("Snap ITC", 0, 14)); // NOI18N
        ANNULER.setText("RESET");
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
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ButtonMute, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(VALIDER)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ANNULER)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(ButtonMute))
                .addGap(18, 18, 18)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ANNULER)
                    .addComponent(VALIDER))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jMenu1.setText("Changer Icon");
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

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
            .addComponent(PanneauGrille, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void VALIDERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VALIDERActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_VALIDERActionPerformed

    private void ButtonMuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonMuteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonMuteActionPerformed

    private void ANNULERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ANNULERActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ANNULERActionPerformed

    private void ICON1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ICON1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ICON1ActionPerformed

    private void ICON2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ICON2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ICON2ActionPerformed

    private void ICON3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ICON3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ICON3ActionPerformed

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
    private javax.swing.JToggleButton ButtonMute;
    private javax.swing.JComboBox<String> ComboBox_tps;
    private javax.swing.JButton ICON1;
    private javax.swing.JButton ICON2;
    private javax.swing.JButton ICON3;
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
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    // End of variables declaration//GEN-END:variables
}
