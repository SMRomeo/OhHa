/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman.pakman;

import Pakman.Direction;
import Pakman.gui.Updatable;
import inTheGame.Coins;
import inTheGame.Enemy;
import inTheGame.Hero;
import inTheGame.Wall;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;


/**
 *
 * @author simone
 */
public class World extends Timer implements ActionListener, KeyListener {
    
    private int height;
    private int length;
    private boolean gamesEnd;
    private Updatable updatable;
    private Hero hero;
    private Enemy enemy;
    private Wall wall;
    private Coins coins;

    public World(int height, int length) {
        super(1000, null);
 
        this.height = height;
        this.length = length;
        this.gamesEnd = false;
        this.wall=new Wall(height, length);
        this.hero=newHero();
        this.enemy=newEnemy();
        this.coins=new Coins(height,length,this.wall);
        
        
        addActionListener(this);
        // The time before the game starts. 1000 equals to one second
        setInitialDelay(1000);
    }
    public Hero getHero(){
        return this.hero;
    }
    public Enemy getEnemy(){
        return this.enemy;
    }
    public int getHeight() {
        return this.height;
    }
    public int getLength() {
        return this.length;
    }
    public Coins getCoins() {
        return this.coins;
    }
    public void setUpdatable(Updatable updatable) {
        this.updatable=updatable;
    }
    public Wall getWall() {
        return this.wall;
    }
    // Inherited from ActionListener
    @Override
    public void actionPerformed(ActionEvent ae) {
        heroMoves();
        enemyMoves();
        // The time between each move. 1000 equals to one second 
        setDelay(400);
    }
    public boolean gamesEnd() {
        return this.gamesEnd;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        // With this method, the right direction is set though keyboard arrows, 
        // and the hero is allowed to take the next move forward
        // unless he is in front of the wall
        
        if (ke.getKeyCode()==KeyEvent.VK_UP) {
            this.hero.prepareToMove();
            hero.setDirection(Direction.UP);
        } else if (ke.getKeyCode()==KeyEvent.VK_DOWN) {
            hero.setDirection(Direction.DOWN);
            this.hero.prepareToMove();
        } else if (ke.getKeyCode()==KeyEvent.VK_RIGHT) {
            hero.setDirection(Direction.RIGHT);
            this.hero.prepareToMove();
        } else if (ke.getKeyCode()==KeyEvent.VK_LEFT) {
            hero.setDirection(Direction.LEFT);
            this.hero.prepareToMove();
        } 
        hero.acsAccordingToTheWall();
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

    private void heroMoves() {
        if (this.hero.isMoving()) {
            this.hero.forward();
            heroRunsIntoCoins();
            this.hero.stop();
            updatable.update();
            checkTheGameCanContinue();
            
        } 
    }

    private void enemyMoves() {
        for (int i = 0; i < this.hero.getCoins()/50 +1; i++) {
            enemy.randomDirectionChange();
            enemy.changesDirectionIfCantMove();
            this.enemy.forward();
            updatable.update();
            checkTheGameCanContinue();
        }
    }

    private Enemy newEnemy() {
        // Default enemy's location: length-2, heigth-4
        Enemy newEnemy=new Enemy(length-3,height-3);
        newEnemy.setDirection(Direction.LEFT);
        newEnemy.set(wall);
        return newEnemy;
    }

    private Hero newHero() {
        Hero newHero = new Hero(2,2);
        newHero.set(wall);
        return newHero;
    }

    private void checkTheGameCanContinue() {
        if (enemy.runsIntoBidimensional(hero)) {
            this.gamesEnd=true;
        }
    }

    private void heroRunsIntoCoins() {
        if (this.hero.runsIntoCoin(this.coins.getCoins())) {
            this.coins.removeCoin(this.hero.getX(), this.hero.getY());
            this.coins.removeCoin(this.hero.getX(), this.hero.getY1());
            this.coins.removeCoin(this.hero.getX1(), this.hero.getY());
            this.coins.removeCoin(this.hero.getX1(), this.hero.getY1());
        }
    }
    
}
