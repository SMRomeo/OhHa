/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman.gui;

import Pakman.pakman.World;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;


public class GraphicInterface implements Runnable {
 
    private JFrame frame;
    private World world;    
    private int side;
    private GraphicBoard board;
    
    public GraphicInterface(World scenery, int side) {
        this.world = scenery;
        this.side = side;
        
    }

    @Override
    public void run() {
        frame = new JFrame("The Confuncian Pakman");
        int length = (world.getLength()+1)*(this.side/3*2+3);
        int height = (world.getHeight()+2)*(this.side/3*2+3);
         
        frame.setPreferredSize(new Dimension(length, height));
 
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
 
        setUp(frame.getContentPane());
 
        frame.pack();
        frame.setVisible(true);
    }

    private void setUp(Container contentPane) {
        this.board=new GraphicBoard(this.world,this.side);
        contentPane.add(this.board);
        frame.addKeyListener(world);
    }
    public Updatable getUpdatable() {
        return this.board;
    }
 
 
    public JFrame getFrame() {
        return frame;
    }
}
