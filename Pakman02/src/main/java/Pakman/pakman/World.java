/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman.pakman;

import Pakman.AbstractAndSuper.Direction;
import Pakman.AbstractAndSuper.Wall;
import Pakman.AbstractAndSuper.Direction;
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
import java.io.FileNotFoundException;
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
    private Level level;
    private boolean nextLevel;

    public World(int height, int length, Level level) {
        super(1000, null);
        this.enemies=new ArrayList<Enemy>();
 
        this.height = height;
        this.length = length;
        this.gamesEnd = false;
        this.level=level;
        this.hero=newHero();
        enemies.add(newChiefEnemy());
        enemies.add(newEnemy());
        this.nextLevel=false;
        
        
        addActionListener(this);
        // The time before the game starts. 1000 equals to one second
        setInitialDelay(1000);
    }
    private ChiefEnemy newChiefEnemy() {
        // Default enemy's location: length-2, heigth-4
        ChiefEnemy newEnemy=new ChiefEnemy(length-3,height-3);
        newEnemy.setDirection(Direction.LEFT);
        newEnemy.set(level.getWall());
        return newEnemy;
    }
    private Enemy newEnemy() {
        // Default enemy's location: length-2, heigth-4
        Enemy newEnemy=new Enemy(length-3,height-3);
        newEnemy.setDirection(Direction.LEFT);
        newEnemy.set(level.getWall());
        return newEnemy;
    }
    private Hero newHero() {
        Hero newHero = new Hero(2,2);
        newHero.set(level.getWall());
        return newHero;
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
        return level.getBonuses();
    }
    public void setUpdatable(Updatable updatable) {
        this.updatable=updatable;
    }
    public Wall getWall() {
        return level.getWall();
    }
    public Level getLevel() {
        return this.level;
    }
    public boolean gamesEnd() {
        return this.gamesEnd;
    }
    public void nextLevel() {
        int lv = this.level.getLevelNumber()+1;
        this.level=new Level(height,length,lv,level.getHSK());
        this.level.moreDifficultCharacters();
        resetHero();
        resetEnemies();
    }
    private void resetHero() {
        this.hero.reset(2,2);
        this.hero.set(this.getWall());
    }

    private void resetEnemies() {
        for (Enemy enemy : enemies) {
            enemy.reset(length-3,height-3);
            enemy.set(this.getWall());
        }
    }
    // Inherited from ActionListener
    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            try {
                heroMoves();
            } catch (InterruptedException ex) {
            }
        } catch (FileNotFoundException ex) {
            System.out.println("The file can't be found");
        }
        try {
            enemiesMove();
        } catch (InterruptedException ex) {
        }
        // The time between each move. 1000 equals to one second 
        setDelay(300);
    }
    private void heroMoves() throws FileNotFoundException, InterruptedException {
        if (this.hero.isMoving()) {
            this.hero.forward();
            heroRunsIntoBonuses();
            this.hero.stop();
            updatable.update();
            checkTheGameCanContinue();
            
        } 
    }

    private void enemiesMove() throws InterruptedException {
        ChiefEnemy chief = (ChiefEnemy) enemies.get(0);
//        chief.randomDirectionChange();
        chief.mayTurnIfPossible();
        chief.follows(hero);
        chief.changesDirectionIfCantMove();
        chief.forward();
        updatable.update();
        checkTheGameCanContinue();
        for (int i = 1;i<enemies.size();i++) {
            Enemy enemy = enemies.get(i);
//            enemy.randomDirectionChange();
            enemy.changesDirectionIfCantMove();
            enemy.forward();
            enemy.mayTurnIfPossible();
            updatable.update();
            checkTheGameCanContinue();
        }
    }
    private void checkTheGameCanContinue() throws InterruptedException {
        for (Enemy enemy : enemies) {
            if (enemy.runsIntoBidimensional(hero)) {
                this.gamesEnd= true;
            }
        }
        if (level.getBonuses().getCoins().isEmpty()) {
            this.stop();
            Thread.sleep(3000);
            this.start();
            nextLevel();
        }
    }


    private void heroRunsIntoBonuses() throws FileNotFoundException {
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

    private void askQuestion() throws FileNotFoundException {
        QuestionsGraphicInterface questions = new QuestionsGraphicInterface(level.getHSK());
        if (!questions.ask()) {
            this.enemies.add(newEnemy());
        } 
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


    
}
