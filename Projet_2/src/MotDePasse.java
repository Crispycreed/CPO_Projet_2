
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * La classe MotDePasse gère un fichier de mots de passe et d'identifiants.
 */
public class MotDePasse {

    private static final String FICHIER_MDP = "MDP.txt";
    private static final String SEPARATEUR = "/"; // Nouveau séparateur

    /**
     * Crée le fichier MDP.txt s'il n'existe pas déjà.
     */
    void creerFichierSiAbsent() {
        try {
            if (!Files.exists(Paths.get(FICHIER_MDP))) {
                Files.createFile(Paths.get(FICHIER_MDP));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ajoute un mot de passe et son identifiant dans le fichier txt.
     *
     * @param identifiant L'identifiant à ajouter.
     * @param motDePasse Le mot de passe à ajouter.
     */
    void ajouterMotDePasse(String identifiant, String motDePasse) {
        String ligne = identifiant + SEPARATEUR + motDePasse;
        try {
            Files.write(Paths.get(FICHIER_MDP), (ligne + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Affiche tous les identifiants avec leurs mots de passe.
     */
    void afficherTousLesIdentifiants() {
        try {
            List<String> lignes = Files.readAllLines(Paths.get(FICHIER_MDP));
            for (String ligne : lignes) {
                System.out.println(ligne);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ouvre le fichier MDP.txt avec l'application par défaut.
     */
    void ouvrirFichierSurBureau() {
        Path fichierMdpPath = Paths.get(FICHIER_MDP);
        File fichierMdp = fichierMdpPath.toFile();

        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
                Desktop.getDesktop().open(fichierMdp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Récupère le mot de passe associé à un identifiant dans le fichier txt.
     *
     * @param identifiant L'identifiant dont on veut récupérer le mot de passe.
     * @return Le mot de passe associé à l'identifiant, ou null s'il n'est pas
     * trouvé.
     */
    String recupererMotDePasse(String identifiant) {
        try {
            List<String> lignes = Files.readAllLines(Paths.get(FICHIER_MDP));
            for (String ligne : lignes) {
                String[] elements = ligne.split(SEPARATEUR);
                if (elements.length == 2 && elements[0].equals(identifiant)) {
                    return elements[1];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // Retourne null si l'identifiant n'est pas trouvé.
    }

    /**
     * Vérifie si l'identifiant existe dans le fichier txt.
     *
     * @param identifiant L'identifiant à vérifier.
     * @return 1 si l'identifiant existe, 0 sinon.
     */
    int verificationIdentifiant(String identifiant) {
        try {
            List<String> lignes = Files.readAllLines(Paths.get(FICHIER_MDP));
            for (String ligne : lignes) {
                String[] elements = ligne.split(SEPARATEUR);
                if (elements.length >= 1 && elements[0].equals(identifiant)) {
                    return 1; // Identifiant trouvé, retourne 1.
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0; // Aucun identifiant correspondant trouvé, retourne 0.
    }
}
