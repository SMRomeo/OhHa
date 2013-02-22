/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman.pakman;

import Pakman.starter.Game;
import Pakman.AbstractAndSuper.Direction;
import Pakman.AbstractAndSuper.Point;
import Pakman.gui.GraphicInterface;
import Pakman.inTheGame.ChiefEnemy;
import Pakman.inTheGame.Enemy;
import Pakman.inTheGame.Hero;
import Pakman.inTheGame.Level;
import java.io.FileNotFoundException;
import java.util.List;
import javax.swing.SwingUtilities;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author simone
 */
public class GameTest {
    private Level level;
    private Game world;
    
    public GameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
         this.level = new Level(27,27, 1,1);
         this.world=new Game(27,27,level);
         createTheInterface();
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void heroWasCreated() {
        assertTrue(this.world.getHero()!=null);
    }
    
    @Test
    public void heroStartsAtTheRightPlace() {
        assertTrue(this.world.getHero().getX()==2);
        assertTrue(this.world.getHero().getY()==2);
    }
    @Test
    public void chiefEnemyStartsAtTheRightPlace() {
        ChiefEnemy c = (ChiefEnemy) world.getEnemies().get(0);
        assertTrue(c.getX()==world.getLength()-3);
        assertTrue(c.getY()==world.getHeight()-3);
    }
    @Test
    public void twoEnemiesAtTheStart() {
        assertTrue(this.world.getEnemies().size()==2);
    }
    @Test
    public void firstEnemyIsAChief() {
        ChiefEnemy c = new ChiefEnemy(1,1);
        assertTrue(this.world.getEnemies().get(0).getClass().equals(c.getClass()));
    }    
    @Test
    public void heroMoves() throws FileNotFoundException, InterruptedException {
        Hero hero = world.getHero();
        makeHeroMove(hero);
        assertTrue(hero.getX()==3);
        assertTrue(hero.getY()==2);
    }  
    @Test
    public void heroGetsCoins() throws FileNotFoundException, InterruptedException {
        int size = world.getBonuses().getCoins().size();
        Hero hero = world.getHero();
        hero.setDirection(Direction.RIGHT);
        hero.prepareToMove();
        world.testHeroMoves();
        assertTrue(size>world.getBonuses().getCoins().size());
    } 
    @Test
    public void heroStopsAfterMoving() throws FileNotFoundException, InterruptedException {
        int size = world.getBonuses().getCoins().size();
        Hero hero = world.getHero();
        hero.setDirection(Direction.RIGHT);
        hero.prepareToMove();
        world.testHeroMoves();
        assertTrue(!hero.isMoving());
    }     
    @Test
    public void chiefEnemyMoves() throws InterruptedException {
        world.testEnemiesMove();
        ChiefEnemy c = (ChiefEnemy) world.getEnemies().get(0);
        assertTrue(c.getX()!=world.getLength()-3 || c.getY()!=world.getLength()-3);
    }  
    @Test
    public void enemiesMove() throws InterruptedException {
        world.testEnemiesMove();
        for (int i = 1; i < world.getEnemies().size(); i++) {
            Enemy e = world.getEnemies().get(i);
            assertTrue(e.getX()!=world.getLength()-3 || e.getY()!=world.getLength()-3);
        }
    }
    @Test
    public void chiefChangesDirectionIfCantMove() throws InterruptedException {
        ChiefEnemy c = (ChiefEnemy) world.getEnemies().get(0);
        c.setDirection(Direction.RIGHT);
        world.testEnemiesMove();
        assertTrue(c.getDirection()!=Direction.RIGHT);
    }  
    @Test
    public void enemiesChangeDirectionIfCantMove() throws InterruptedException {
        for (int i = 1; i < world.getEnemies().size(); i++) {
            Enemy e = world.getEnemies().get(i);
            e.setDirection(Direction.RIGHT);
        }
        world.testEnemiesMove();
        for (int i = 1; i < world.getEnemies().size(); i++) {
            Enemy e = world.getEnemies().get(i);
            assertTrue(e.getDirection()!=Direction.RIGHT);
        }
    }
    @Test
    public void chiefMayTurnIfPossible() throws InterruptedException {
        ChiefEnemy c = (ChiefEnemy) world.getEnemies().get(0);
        for (int i = 0; i < 100; i++) {
            if (!c.isInFrontOfWall()) {
                if (!c.wallOnTheLeft() || !c.wallOnTheRight()) {
                    world.testEnemiesMove();
                    if (c.testHasTurned()) {
                        assertTrue(true);
                        return;
                    }
                }
            }
            world.testEnemiesMove();
        }
        assertTrue(c.testHasTurned());
    }  
    @Test
    public void gameEnds() throws InterruptedException {
        Enemy enemy = this.world.getEnemies().get(0);
        this.world.getHero().reset(enemy.getX(), enemy.getY());
        world.testEnemiesMove();
        assertTrue(world.gamesEnd());
    }   
    @Test
    public void nextLevelWorks() throws FileNotFoundException, InterruptedException {
        Hero hero = world.getHero();
        makeHeroMove(hero);
        world.testEnemiesMove();
        world.nextLevel();
        
    }  
    private void createTheInterface() {
        GraphicInterface gInterface = new GraphicInterface(world, 27);
        SwingUtilities.invokeLater(gInterface);
        
        while (gInterface.getUpdatable() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("The graphic interface has not been created, yet.");
            }
        }
        world.setUpdatable(gInterface.getUpdatable());
    }  
    private void makeHeroMove(Hero hero) throws FileNotFoundException, InterruptedException {
        hero.setDirection(Direction.RIGHT);
        hero.prepareToMove();
        world.testHeroMoves();
        
    }
}
