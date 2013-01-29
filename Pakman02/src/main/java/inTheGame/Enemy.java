/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inTheGame;

import Pakman.Direction;
import Pakman.domain.InanimateMind;
import Pakman.domain.Individual;
import java.util.Random;

public class Enemy extends Individual implements InanimateMind {
    private Random random;
    
    public Enemy(int x, int y) {
        super(x,y);
        this.random = new Random();
    }
    // The following are InanimateMind methods:
    @Override
    public void changesDirectionIfCantMove() {
        if (super.isInFrontOfWall()) {
            newDirection();
            changesDirectionIfCantMove();
        }
    }
    @Override
    public void randomDirectionChange() {
        // The default possibility of a direction change is 5 --> 3/20
        if (random.nextInt(5)==0) {
            newDirection();
        }
    }
    void newDirection() {
        int i = random.nextInt(4);
        if (i==0) {
            super.setDirection(Direction.UP);
        } else if (i==1) {
            super.setDirection(Direction.DOWN);
        } else if (i==2) {
            super.setDirection(Direction.LEFT);
        } else if (i==3) {
            super.setDirection(Direction.RIGHT);
        } 
    }
}
