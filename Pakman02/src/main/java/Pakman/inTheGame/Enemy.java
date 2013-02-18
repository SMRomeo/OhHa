/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman.inTheGame;

import Pakman.AbstractAndSuper.Direction;
import Pakman.AbstractAndSuper.InanimateMind;
import Pakman.AbstractAndSuper.Individual;
import java.util.Random;

public class Enemy extends Individual implements InanimateMind {
    private Random random;
//      Only useful to tests
    private boolean testHasTurned;
    
    public Enemy(int x, int y) {
        super(x,y);
        this.random = new Random();
//      Only useful to tests
        this.testHasTurned=false;
    }
    /*
     * The enemy changes direction until he is in front of a wall.
     * The new direction is random
     */
    
    // The following are InanimateMind methods:
    @Override
    public void changesDirectionIfCantMove() {
        if (super.isInFrontOfWall()) {
            newDirection();
            changesDirectionIfCantMove();
        }
    }
    /*
     * This method can change enemy's direction randomly. But it is not used at the moment.
     */
    @Override
    public void randomDirectionChange() {
        if (random.nextInt(10)==0) {
            newDirection();
        }
    }
    
    /*
     * A random new direction is set
     */
    
    public void newDirection() {
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
    /*
     * This helps enemies to move around. Without this methods the enemy would 
     * usually go to and fro, and seldom turn.
     */
    @Override
    public void mayTurnIfPossible() {
        if (random.nextBoolean()) {
            if (!super.wallOnTheRight()) {
                    super.turnRight();
                    // Only useful to tests
                    this.testHasTurned=true;
            } else if(!super.wallOnTheLeft()) {
                super.turnLeft();
                   // Only useful to tests
                this.testHasTurned=true;
            }
        }
    }
    /*
     * The Enemy moves randomly, meaning that he changes direction when he is in front of a wall
     * and turns sometimes, when it is possible
     */
    public void moves() {
        changesDirectionIfCantMove();
        forward();
        mayTurnIfPossible();
    }
    /**
     * 
     * @return True if the enemy has turned
     * Useful only for the tests
     */
    public boolean testHasTurned() {
        return this.testHasTurned;
    }
}
