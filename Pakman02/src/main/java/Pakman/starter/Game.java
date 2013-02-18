/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman.starter;

import Pakman.AbstractAndSuper.Direction;
import Pakman.AbstractAndSuper.Wall;
import Pakman.gui.QuestionsGraphicInterface;
import Pakman.gui.Updatable;
import Pakman.inTheGame.Bonuses;
import Pakman.inTheGame.ChiefEnemy;
import Pakman.inTheGame.Enemy;
import Pakman.inTheGame.Hero;
import Pakman.inTheGame.Level;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.Timer;


/**
 *
 * @author simone
 * 
 * The scenery where everything happens
 */
public class Game extends Timer implements ActionListener, KeyListener {
    
    private int height;
    private int length;
    private boolean gamesEnd;
    private Updatable updatable;
    private Hero hero;
    private ArrayList<Enemy> enemies;
    private Level level;

    /**
     * 
     * @param height The height of the board
     * @param length The length of the board
     * @param level The level of the game, 1 at the start
     */
    public Game(int height, int length, Level level) {
        super(1000, null);
        this.enemies=new ArrayList<Enemy>();
 
        this.height = height;
        this.length = length;
        this.gamesEnd = false;
        this.level=level;
        this.hero=newHero();
        enemies.add(newChiefEnemy());
        enemies.add(newEnemy());
        
        
        addActionListener(this);
        // The time before the game starts. 1000 equals to one second
        setInitialDelay(1000);
    }
    /**
     * 
     * @return ChiefEnemy The first of the enemies chasing the hero: the most intelligent
     */
    private ChiefEnemy newChiefEnemy() {
        // Default enemy's location: length-3, heigth-3
        ChiefEnemy newEnemy=new ChiefEnemy(length-3,height-3);
        newEnemy.setDirection(Direction.LEFT);
        newEnemy.set(level.getWall());
        return newEnemy;
    }
    /**
     * 
     * @return Enemy One of the enemies chasing the hero
     */
    private Enemy newEnemy() {
        // Default enemy's location: length-3, heigth-3
        Enemy newEnemy=new Enemy(length-3,height-3);
        newEnemy.setDirection(Direction.LEFT);
        newEnemy.set(level.getWall());
        return newEnemy;
    }
    /**
     * 
     * @return Hero, the hero of the game, moved by the player
     */
    private Hero newHero() {
        Hero newHero = new Hero(2,2);
        newHero.set(level.getWall());
        return newHero;
    }
    /**
     * 
     * @return Hero Returns the hero of the game
     */
    public Hero getHero(){
        return this.hero;
    }
    /**
     * 
     * @return ArrayList<Enemy> Returns an ArrayList of the enemies chasing the hero
     */
    public ArrayList<Enemy> getEnemies() {
        return this.enemies;
    }
    /**
     * 
     * @return int Returns the height of the board
     */
    public int getHeight() {
        return this.height;
    }
    /**
     * 
     * @return int Returns the length of the board
     */
    public int getLength() {
        return this.length;
    }
    /**
     * 
     * @return Bonuses Returns the bonuses in the game
     */
    public Bonuses getBonuses() {
        return level.getBonuses();
    }
    /**
     * 
     * @param updatable An Updatable is set into the world, among the object variables
     */
    public void setUpdatable(Updatable updatable) {
        this.updatable=updatable;
    }
    /**
     * 
     * @return Returns the wall of the game
     */
    public Wall getWall() {
        return level.getWall();
    }
    /**
     * 
     * @return Level Returns the level of the game
     */
    public Level getLevel() {
        return this.level;
    }
    /**
     * 
     * @return Returns true is the game must end
     */
    public boolean gamesEnd() {
        return this.gamesEnd;
    }
    /**
     * Changes to the following level
     */
    public void nextLevel() {
        int lv = this.level.getLevelNumber()+1;
        this.level=new Level(height,length,lv,level.getHSK());
//        this.level.moreDifficultCharacters();
        resetHero();
        resetEnemies();
    }
    /**
     * The hero is replaced to its original location, and is given a new wall
     */
    private void resetHero() {
        this.hero.reset(2,2);
        this.hero.set(this.getWall());
    }
    /**
     * The enemies are replaced to their original locations, and given a new wall
     */
    private void resetEnemies() {
        for (Enemy enemy : enemies) {
            enemy.reset(length-3,height-3);
            enemy.set(this.getWall());
        }
    }
    // Inherited from ActionListener
    @Override
    public void actionPerformed(ActionEvent ae) {
        heroMoves();
        enemiesMove();
        // The time between each move. 1000 equals to one second 
        setDelay(300);
    }
    /**
     * The hero moves
     */
    private void heroMoves() {
        if (this.hero.isMoving()) {
            this.hero.forward();
            heroRunsIntoBonuses();
            this.hero.stop();
            updatable.update();
            checkTheGameCanContinue();
            
        } 
    }
    /**
     * All the enemies move
     */
    private void enemiesMove() {
        chiefEnemyMoves();
        for (int i = 1;i<enemies.size();i++) {
            enemies.get(i).moves();
            updatable.update();
            checkTheGameCanContinue();
        }
    }
    /**
     * The chief enemy moves
     */
    private void chiefEnemyMoves() {
        ChiefEnemy chief = (ChiefEnemy) enemies.get(0);
        chief.moves(hero);
        updatable.update();
        checkTheGameCanContinue();
        
    }
    private void checkTheGameCanContinue() {
        for (Enemy enemy : enemies) {
            if (enemy.runsIntoBidimensional(hero)) {
                this.gamesEnd= true;
            }
        }
        if (level.getBonuses().getCoins().isEmpty()) {
            this.stop();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                System.out.println("The exception caused by Thread.sleep(3000) in checkTheGameCanContinue() was not handled properly");
            }
            this.start();
            nextLevel();
        }
    }
    /**
     * 
     * Checks whether the hero eats a bonus
     */
    private void heroRunsIntoBonuses() {
        Bonuses bonuses =level.getBonuses();
        if (this.hero.runsIntoBonus(bonuses.getFruits())) {
            askQuestion();
        }
        if (this.hero.runsIntoBonus(bonuses.getCoins())) {
            bonuses.removeBonuses(this.hero.getX(), this.hero.getY());
            bonuses.removeBonuses(this.hero.getX(), this.hero.getY1());
            bonuses.removeBonuses(this.hero.getX1(), this.hero.getY());
            bonuses.removeBonuses(this.hero.getX1(), this.hero.getY1());
        }
    }
    /*
     * 
     * The user is asked a character question. If they answer wrong, a new enemy starts cheasing them.
     * 
     */
    private void askQuestion() {
        QuestionsGraphicInterface questions = new QuestionsGraphicInterface(level.getHSK());
        boolean answersRight = questions.ask();
        if (!answersRight) {
            this.enemies.add(newEnemy());
        } 
    }
    @Override
    public void keyTyped(KeyEvent ke) {
    }

    /*
     * The right direction is set though keyboard arrows, 
       and the hero is allowed to take the next move forward
       unless he is in front of the wall
     */
    @Override
    public void keyPressed(KeyEvent ke) {
        // With this method, 
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
    public void testHeroMoves() {
        heroMoves();
    }    
    public void testEnemiesMove() {
        enemiesMove();
    }
    
    
}
