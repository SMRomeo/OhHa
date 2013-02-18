/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman.starter;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class Music implements Runnable {
    private AudioClip sound;
    
    public Music() {
         URL url = Music.class.getResource("Zeng_xiaogang.wav");
         this.sound=Applet.newAudioClip(url);
    }
    public void play() {
        this.sound.loop();
    }
    @Override
    public void run() {
        this.play();
    }

} 
