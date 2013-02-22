/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman.inTheGame;

import Pakman.AbstractAndSuper.Enrichable;
import Pakman.AbstractAndSuper.Individual;
import Pakman.AbstractAndSuper.Point;
import Pakman.AbstractAndSuper.ThinkingMind;
import java.util.List;

public class Hero extends Individual implements Enrichable, ThinkingMind {
    private boolean canMove;
    private int coins;
    
    public Hero(int x, int y) {
        super(x,y);
        this.coins=1;
        this.canMove=false;
    }
    // The following are Enrichable methods:
    
    /**
     * The Hero earns coins
     */
    @Override
    public void earn() {
        this.coins++;
    }
    /**
     * 
     * @param coins The coins earnt
     */
    @Override
    public void earn(int coins) {
        this.coins+=coins;
    }
    /**
     * 
     * @return int Coins 
     */
    @Override
    public int getCoins() {
        return this.coins;
    }
    // The following are ThinkingMind methods:
    
    /**
     * The hero stops moving
     */
    @Override
    public void stop(){
        canMove=false;
    }
    /*
     * The hero perpares to move soon
     */
    @Override
    public void prepareToMove(){
        this.canMove=true;
    }
    /**
     * 
     * @return boolean True if the hero can move (ie he is not standing in front of a wall)
     */
    @Override
    public boolean isMoving() {
        return this.canMove;
    }
    /*
     * The hero acs according to the wall of the level. In other words, he cannot
     * step through walls.
     */
    @Override
    public void acsAccordingToTheWall() {
        if (super.isInFrontOfWall()) {
            this.canMove=false;
        } else {
            this.canMove=true;
        }
    }
    
    /**
     *  @return boolean True if the hero has run into a bonus 
     *  A hero may step unto a bonus (a coin or a fruit). For each bonus that
     *  the hero runs into, he earns money.
     */
    
    @Override
    public boolean runsIntoBonus(List<Point> bonuses) {
        for (Point bonus : bonuses) {
            if (super.bidimensionalRunsInto(bonus)) {
                this.earn();
                return true;
            }
        }
        return false;
    }
}
