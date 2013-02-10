/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman;

import Pakman.pakman.Pakman;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


/**
 *
 * @author simone
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        Pakman game = new Pakman(1);
        game.start();
    }
}
