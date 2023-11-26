import javax.sound.sampled.*;

public class LecteurWAV {

    private Clip clip;

    public void lireFichierWAV(String nomFichier) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(nomFichier));

            AudioFormat baseFormat = audioInputStream.getFormat();
            AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false);

            AudioInputStream convertedInputStream = AudioSystem.getAudioInputStream(decodedFormat, audioInputStream);

            clip = AudioSystem.getClip();
            clip.open(convertedInputStream);

            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip.close();
                    }
                }
            });

            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

public void arreterLecture() {
    if (clip != null) {
        if (clip.isRunning()) {
            clip.stop();
        }
        clip.close();
    }
}




    public static void main(String[] args) {
    }
}
