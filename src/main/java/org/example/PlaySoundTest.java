package org.example;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlaySoundTest {
    private static final int BUFFER_SIZE = 4096;

    //  Play a sound file synchronously (blocked until the playing finished
//    public static void playSound(String audioFilePath) {
//        try {
//            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(audioFilePath).getAbsoluteFile());
//            AudioFormat audioFormat = audioStream.getFormat();
//            SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getSourceDataLine(audioFormat);
//            sourceDataLine.open(audioFormat);
//            sourceDataLine.start();
//            System.out.println("Playing started.");
//            byte[] bufferBytes = new byte[BUFFER_SIZE];
//            int readBytes = -1;
//            while ((readBytes = audioStream.read(bufferBytes)) != -1) {
//                sourceDataLine.write(bufferBytes, 0, readBytes);
//                //...
//            }
//            System.out.println("Playing finished.");
//            sourceDataLine.drain();
//            sourceDataLine.close();
//            audioStream.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

    // Play a sound file asynchronously (without being blocked when the sound is played)
    public static void startPlayingSound(String audioFilePath) {
        try {
            final AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(audioFilePath).getAbsoluteFile());
            AudioFormat audioFormat = audioStream.getFormat();
            SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getSourceDataLine(audioFormat);
            sourceDataLine.open(audioFormat);
            sourceDataLine.start();
            // Detailed processes of playing are handled in another thread
            new Thread() {
                public void run() {
                    System.out.println("Playing thread starts to run");
                    byte[] bufferBytes = new byte[BUFFER_SIZE];
                    int readBytes = -1;
                    try {
                        while ((readBytes = audioStream.read(bufferBytes)) != -1) {
                            System.out.print(".");
                            sourceDataLine.write(bufferBytes, 0, readBytes);
                        }
                        System.out.println("");
                        sourceDataLine.drain();
                        sourceDataLine.close();
                        audioStream.close();
                    } catch (Exception ex) {
                        Logger.getLogger(PlaySoundTest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("Playing has been finished.");
                }
            }.start();
            System.out.println("Playing has been started.");
        } catch (Exception ex) {
            Logger.getLogger(PlaySoundTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
