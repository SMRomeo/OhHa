/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman.unused;

import Pakman.AbstractAndSuper.Direction;
import Pakman.pakman.World;
import Pakman.inTheGame.Hero;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author simone
 */
class DirectionListener implements KeyListener {
    private World scenery;

    public DirectionListener(World scenery)  {
        this.scenery=scenery;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        Hero hero = this.scenery.getHero();
        if (ke.getKeyCode()==KeyEvent.VK_UP) {
            hero.setDirection(Direction.UP);
        } else if (ke.getKeyCode()==KeyEvent.VK_DOWN) {
            hero.setDirection(Direction.DOWN);
        } else if (ke.getKeyCode()==KeyEvent.VK_RIGHT) {
            hero.setDirection(Direction.RIGHT);
        } else if (ke.getKeyCode()==KeyEvent.VK_LEFT) {
            hero.setDirection(Direction.LEFT);
        } 
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
