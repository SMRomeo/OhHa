/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman;

import Pakman.domain.Wall;
import java.awt.Component;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;


/**
 *
 * @author simone
 */
public class Main {
    private static Component frame;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        Pakman game = new Pakman();
        game.start();
//        window();
    }

    private static void window() {
        //Custom button text
        Object[] options = {"Yes, please",
                    "No, thanks",
                    "No eggs, no ham!","Maybe"};
                int n = JOptionPane.showOptionDialog(frame,
                        "Would you like some green eggs to go "
                        + "with that ham?",
                        "A Silly Question",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[2]);
    }
}
