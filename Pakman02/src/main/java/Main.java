/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import Pakman.starter.Pakman;
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
     * Starts the game
     */
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        Pakman pGame = new Pakman(1);
        pGame.start();
    }
}
