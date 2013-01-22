/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman;

import Pakman.gui.GraphicInterface;
import Pakman.pakman.World;
import javax.swing.SwingUtilities;

/**
 *
 * @author simone
 */
public class Pakman {
    private int side;
    
    public Pakman() {
        // Side should be odd
        this.side=23;
    }
    
    public void start() {
        World scenery = new World(side,side);

        GraphicInterface gInterface = new GraphicInterface(scenery, side);
        SwingUtilities.invokeLater(gInterface);

        while (gInterface.getUpdatable() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("The graphic interface has not been created, yet.");
            }
        }

        scenery.setUpdatable(gInterface.getUpdatable());
        scenery.start();
    }
}
