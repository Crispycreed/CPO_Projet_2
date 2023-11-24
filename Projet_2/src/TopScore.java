
import java.io.BufferedReader;
import java.io.BufferedWriter;
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
    public static void sauvegarderScore(int score) {
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter("topscores.txt", true))) {
            // Ajouter le score au fichier
            writer.write(String.valueOf(score));
            writer.newLine();  // passer à la ligne suivante pour le prochain score
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode pour afficher tous les scores sauvegardés.
     */
    public static void afficherScoresSauvegardes() {
        try ( BufferedReader reader = new BufferedReader(new FileReader("topscores.txt"))) {
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
    public static void supprimerTopScores() {
        String cheminFichier = "topscores.txt";

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
    public static int recupererScore(int rang) {
        Set<Integer> scores = lireScores();

        if (rang <= 0 || rang > scores.size()) {
            return -1; // Valeur par défaut ou code d'erreur selon le contexte
        }

        List<Integer> sortedScores = scores.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
        return sortedScores.get(rang - 1);
    }

    private static Set<Integer> lireScores() {
        Set<Integer> scores = new HashSet<>();
        try ( BufferedReader reader = new BufferedReader(new FileReader("topscores.txt"))) {
            reader.lines()
                    .map(Integer::parseInt)
                    .forEach(scores::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scores;
    }

}
