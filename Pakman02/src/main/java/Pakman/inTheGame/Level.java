/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman.inTheGame;

import Pakman.AbstractAndSuper.Wall;

/**
 *
 * @author simone
 */
public class Level {
    private Bonuses bonuses;
    private Wall wall;
    private int levelNumber;
    private int hsk;
    
    /*
     * The deferences between a level and the other are the kind of wall in the level
     * (affecting Pakman's possible paths) and the difficulty of the Chinese questions (hsk).
     * Every level has got bonuses (coins and fruits), which are created so that
     * they can't be where pakman would not be able to step onto.
     */
    
    public Level(int height, int length, int lv, int hsk) {
        this.levelNumber=lv;
        this.wall= newWall(height, length);
        this.bonuses=new Bonuses(height, length, wall);
        this.hsk=hsk;
    }
    public Bonuses getBonuses() {
        return this.bonuses;
    }
    public Wall getWall() {
        return this.wall;
    }
    private Wall newWall(int height, int length) {
        if (levelNumber==1) {
            return new Wall1(height,length);
        } else if (levelNumber==2) {
            return new Wall2(height,length);
        } else if (levelNumber==3) {
            return new Wall3(height,length);
        } else  {
            return new Wall1(height,length);
        }
    }
    public Integer getLevelNumber() {
        return this.levelNumber;
    }
    public void moreDifficultCharacters() {
        if (this.hsk<6) {
            this.hsk++;
        }
    }
    public int getHSK() {
        return hsk;
    }
}
