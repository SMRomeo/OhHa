/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman.pakman;

import Pakman.gui.Music;
import Pakman.gui.GraphicInterface;
import Pakman.inTheGame.Level;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author simone
 */
public class Pakman {
    private int side;
    private JFrame frame;
    private int levelNumber;
    
    public Pakman(int levelNumber) {
        // Side should be odd
        this.side=27;
        this.levelNumber=levelNumber;
    }

    /*
     * The world is the scenery where everything happens. In this class we create a world of the 
     * of the decided dimentions (side), and which starts with a decided level.
     * The deferences between a level and the other are the kind of wall in the level
     * (affecting Pakman's possible paths) and the difficulty of the Chinese questions (hsk).
     * Another thing that the class does is playing the music.
     * 
     */

    public void start() {
        createWorld();
        startMusic();
    }
    /*
     * A world is created with a particular level, and HSK characters. The world is
     * given to the graphic interface, and then it starts running through he method world.start(),
     * inherited from timer.
     */
    private void createWorld() {
        int hsk = askLevel();
        Level level = new Level(side,side, levelNumber,hsk);
        World world = new World(side,side, level);
        createTheInterface(world);
        world.start();
    }

    private void startMusic(){
//       Thread s = new Thread(new Music("01OnceUponATimeInChina.wav"));
//       s.start();
        Music music = new Music("Zeng_xiaogang.wav");
        music.play();
    }
    private void createTheInterface(World world) {
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
    }
    
    
    private int askLevel() {
        String[] levels = {setFont("1",6),setFont("2",6),setFont("3",6),setFont("4",6),setFont("5",6),setFont("6",6)};
        int answer = JOptionPane.showOptionDialog(frame,
                setFont("What HSK level do you want to train?",6),
                "Confucious Pac-Man",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                levels,
                levels[0]);
        answer++;
        return answer;
    }
    private String setFont(String string, int size) {
        return "<html><font size='"+size+"'>"+string+"</font></html>";
    }
}
