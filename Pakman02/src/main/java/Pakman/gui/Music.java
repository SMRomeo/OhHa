/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman.gui;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class Music implements Runnable {
    private AudioClip sound;
    
    public Music(String songName) {
         URL url = Music.class.getResource(songName);
         this.sound=Applet.newAudioClip(url);
    }
    public void play() {
        this.sound.play();
    }
    
    public void haeJaSoitaMusiikki() {
        URL url = Music.class.getResource("ArsenioLupin.wav");
        //URL url = new URL("src/Musiikki.wav");
        AudioClip aani = Applet.newAudioClip(url);
        aani.play(); 
    } 

    @Override
    public void run() {
        this.play();
    }

} 
