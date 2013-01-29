/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman.pakman;

import Pakman.Direction;
import Pakman.domain.Wall;
import Pakman.gui.QuestionsGraphicInterface;
import Pakman.gui.Updatable;
import inTheGame.Bonuses;
import inTheGame.Enemy;
import inTheGame.Hero;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
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
    private ArrayList<Enemy> enemies;
    private char hsk;
    private Wall wall;
    private Bonuses bonuses;

    public World(int height, int length, Wall wall, char hsk) {
        super(1000, null);
        this.enemies=new ArrayList<Enemy>();
 
        this.height = height;
        this.length = length;
        this.gamesEnd = false;
        this.hsk=hsk;
        this.wall=wall;
        this.hero=newHero();
        enemies.add(newEnemy());
        enemies.add(newEnemy());
        this.bonuses=new Bonuses(height,length,this.wall);
        
        
        addActionListener(this);
        // The time before the game starts. 1000 equals to one second
        setInitialDelay(1000);
    }
    public Hero getHero(){
        return this.hero;
    }
    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }
    public int getHeight() {
        return this.height;
    }
    public int getLength() {
        return this.length;
    }
    public Bonuses getBonuses() {
        return this.bonuses;
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
        enemiesMove();
        // The time between each move. 1000 equals to one second 
        setDelay(300);
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
            heroRunsIntoBonuses();
            this.hero.stop();
            updatable.update();
            checkTheGameCanContinue();
            
        } 
    }

    private void enemiesMove() {
        for (Enemy enemy : enemies) {
            for (int i = 0; i < 1; i++) {
// This loop is useless now, but it can be easily implemented if we want to modify enemies' velocity
                enemy.randomDirectionChange();
                enemy.changesDirectionIfCantMove();
                enemy.forward();
                updatable.update();
                checkTheGameCanContinue();
            }
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
        for (Enemy enemy : enemies) {
            if (enemy.runsIntoBidimensional(hero)) {
                this.gamesEnd= true;
            }
        }
    }

    private void heroRunsIntoBonuses() {
        if (this.hero.runsIntoBonus(this.bonuses.getFruits())) {
            askQuestion();
        }
        if (this.hero.runsIntoBonus(this.bonuses.getCoins())) {
            this.bonuses.removeBonuses(this.hero.getX(), this.hero.getY());
            this.bonuses.removeBonuses(this.hero.getX(), this.hero.getY1());
            this.bonuses.removeBonuses(this.hero.getX1(), this.hero.getY());
            this.bonuses.removeBonuses(this.hero.getX1(), this.hero.getY1());
        }
    }

    private void askQuestion() {
        QuestionsGraphicInterface questions = new QuestionsGraphicInterface(hsk);
        if (!questions.ask()) {
            this.enemies.add(newEnemy());
        }
    }
    
}
