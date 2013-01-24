/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman;

import Pakman.domain.Wall;
import Pakman.gui.GraphicInterface;
import Pakman.gui.SoundClipTest;
import Pakman.pakman.World;
import inTheGame.Wall2;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.SwingUtilities;

/**
 *
 * @author simone
 */
public class Pakman {
    private int side;
    
    public Pakman() {
        // Side should be odd
        this.side=27;
    }
    
    public void start() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
//        createWorld(new Wall1(side,side));
        createWorld(new Wall2(side,side));
    }

    private void createWorld(Wall wall) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        char hsk = askHowDifficult();
        World world = new World(side,side, wall, hsk);

        GraphicInterface gInterface = new GraphicInterface(world, side);
        SwingUtilities.invokeLater(gInterface);

        while (gInterface.getUpdatable() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("The graphic interface has not been created, yet.");
            }
        }

        world.setUpdatable(gInterface.getUpdatable());
//        startMusic();
        new SoundClipTest();
        world.start();
    }

    private Character askHowDifficult() {
        System.out.println("What characters do you want to train?");
        System.out.println("Possible choices:");
        System.out.println("HSK1 --> press 1");
        System.out.println("HSK2 --> press 2");
        System.out.println("HSK3 --> press 3");
        System.out.println("HSK4 --> press 4");
        System.out.println("HSK5 --> press 5");
        System.out.println("HSK6 --> press 6");
        
        Scanner scanner = new Scanner(System.in);
        int hsk = scanner.nextInt();
        if (hsk==1) {
            return '1';
        } else if (hsk == 2) {
            return '2';
        } else if (hsk == 3) {
            return '3';
        } else if (hsk == 4) {
            return '4';
        } else if (hsk == 5) {
            return '5';
        } else {
            return '6';
        }
    }

    private void startMusic() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        // from a wave File
        File soundFile = new File("/home/simone/OhHa/Pakman02/src/main/java/Pakman/01OnceUponATimeInChina.mp3");
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
// For small-size file only. Do not use this to open a large file over slow network, as it blocks.
        // start()
        clip.start();  // play once
// Loop()
//        clip.loop(0);  // repeat none (play once), can be used in place of start().
//        clip.loop(5);  // repeat 5 times (play 6 times)
        clip.loop(Clip.LOOP_CONTINUOUSLY);  // repeat forever
    }
}
