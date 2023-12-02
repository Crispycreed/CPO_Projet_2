
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TopScore {

    /**
     * Méthode pour sauvegarder le score dans un fichier texte.
     *
     * @param score Le score à sauvegarder.
     */
    public static void sauvegarderScore(int score, String username) {
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(username + ".txt", true))) {
            // Ajouter le score au fichier
            writer.write(String.valueOf(score));
            writer.newLine();  // passer à la ligne suivante pour le prochain score
        } catch (IOException e) {
            // Si le fichier n'existe pas, créer le fichier et sauvegarder le score
            if (e instanceof FileNotFoundException) {
                create_topscore(username);
                sauvegarderScore(score, username); // Appeler récursivement après la création
            } else {
                e.printStackTrace();
            }
        }
    }

    /**
     * Méthode pour afficher tous les scores sauvegardés.
     */
    public static void afficherScoresSauvegardes(String username) {
        try ( BufferedReader reader = new BufferedReader(new FileReader(username + ".txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                // Imprimer chaque score
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode pour supprimer le fichier topscores.txt.
     */
    public static void supprimerTopScores(String username) {
        String cheminFichier = username + ".txt";

        try {
            Path fichierPath = Paths.get(cheminFichier);

            if (Files.exists(fichierPath)) {
                Files.delete(fichierPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode pour récupérer le score correspondant au rang spécifié.
     *
     * @param rang Le rang du score à récupérer.
     * @return Le score correspondant au rang.
     */
public static int recupererScore(int rang, String username) {
    Set<Integer> scores = lireScores(username);

    if (rang <= 0 || rang > scores.size()) {
        return -1; // Valeur par défaut ou code d'erreur selon le contexte
    }

    List<Integer> sortedUniqueScores = scores.stream()
            .distinct()
            .sorted(Collections.reverseOrder())
            .collect(Collectors.toList());

    return sortedUniqueScores.get(rang - 1);
}

    /**
     * Méthode pour créer un fichier topscore avec un score initial.
     *
     * @param username Le nom d'utilisateur pour lequel le fichier doit être
     * créé.
     */
    public static void create_topscore(String username) {
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(username + ".txt"))) {
            // Ajouter un score initial au fichier
            int scoreInitial = 0;
            writer.write(String.valueOf(scoreInitial));
            writer.newLine();  // passer à la ligne suivante pour le prochain score

            System.out.println("Fichier topscore pour " + username + " créé avec succès.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cette méthode lit les scores à partir du fichier "topscores.txt". Chaque
     * ligne du fichier est interprétée comme un score entier, qui est ensuite
     * ajouté à un ensemble. En cas d'erreur d'entrée/sortie, une trace de la
     * pile est affichée, mais l'exécution de la méthode se poursuit avec un
     * ensemble vide.
     *
     * @return Un ensemble d'entiers représentant les scores lus à partir du
     * fichier.
     */
    private static Set<Integer> lireScores(String username) {
        Set<Integer> scores = new HashSet<>();
        try ( BufferedReader reader = new BufferedReader(new FileReader(username + ".txt"))) {
            reader.lines()
                    .map(Integer::parseInt)
                    .forEach(scores::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scores;
    }

}
