
import javax.sound.sampled.*;

/**
 * La classe LecteurWAV permet de lire des fichiers audio au format WAV.
 */
public class LecteurWAV {

    private Clip clip;

    /**
     * Lit un fichier audio WAV à partir du chemin spécifié.
     *
     * @param nomFichier Le nom du fichier WAV à lire.
     */
    public void lireFichierWAV(String nomFichier) {
        try {
            // Obtenir un flux audio à partir du fichier WAV
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(nomFichier));

            // Définir le format audio de base
            AudioFormat baseFormat = audioInputStream.getFormat();

            // Définir le format audio décodé en PCM signé
            AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false);

            // Obtenir un flux audio converti avec le format décodé
            AudioInputStream convertedInputStream = AudioSystem.getAudioInputStream(decodedFormat, audioInputStream);

            // Initialiser le lecteur audio
            clip = AudioSystem.getClip();
            clip.open(convertedInputStream);

            // Ajouter un écouteur pour gérer l'événement de fin de lecture
            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip.close();
                    }
                }
            });

            // Démarrer la lecture du fichier audio
            clip.start();
        } catch (Exception e) {
            // Gérer les exceptions en imprimant la trace de la pile
            e.printStackTrace();
        }
    }

    /**
     * Arrête la lecture du fichier audio en cours.
     */
    public void arreterLecture() {
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
            }
            // Fermer le lecteur audio
            clip.close();
        }
    }

    /**
     * Méthode principale (point d'entrée) pour tester la classe LecteurWAV.
     *
     * @param args Les arguments de la ligne de commande (non utilisés dans
     * cette application).
     */
    public static void main(String[] args) {
        // L'implémentation de cette méthode peut être ajoutée pour tester la classe.
    }
}
