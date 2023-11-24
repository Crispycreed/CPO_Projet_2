import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TopScore {

    /**
     * Méthode pour sauvegarder le score dans un fichier texte.
     * 
     * @param score Le score à sauvegarder.
     */
    public static void sauvegarderScore(int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("topscores.txt", true))) {
            // Ajouter le score au fichier
            writer.write(String.valueOf(score));
            writer.newLine();  // passer à la ligne suivante pour le prochain score
            System.out.println("Score sauvegardé : " + score);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode pour afficher tous les scores sauvegardés.
     */
    public static void afficherScoresSauvegardes() {
        try (BufferedReader reader = new BufferedReader(new FileReader("topscores.txt"))) {
            String line;
            System.out.println("Scores sauvegardés :");

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
                System.out.println("Le fichier topscores.txt a été supprimé.");
            } else {
                System.out.println("Le fichier topscores.txt n'existe pas.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
