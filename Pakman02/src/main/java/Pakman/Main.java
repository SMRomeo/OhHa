/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman;

import Pakman.pakman.Pakman;
import Pakman.AbstractAndSuper.Wall;
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
        Pakman game = new Pakman(1);
        game.start();
    }
}
