/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inTheGame;

import Pakman.domain.Enrichable;
import Pakman.domain.Individual;
import Pakman.domain.Point;
import Pakman.domain.ThinkingMind;
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
    @Override
    public void earn() {
        this.coins++;
    }
    @Override
    public void earn(int coins) {
        this.coins+=coins;
    }
    @Override
    public int getCoins() {
        return this.coins;
    }
    // The following are ThinkingMind methods:
    @Override
    public void stop(){
        canMove=false;
    }
    @Override
    public void prepareToMove(){
        this.canMove=true;
    }
    @Override
    public boolean isMoving() {
        return this.canMove;
    }

    @Override
    public void acsAccordingToTheWall() {
        if (super.isInFrontOfWall()) {
            this.canMove=false;
        } else {
            this.canMove=true;
        }
    }

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
