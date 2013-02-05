/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inTheGame;

import Pakman.inTheGame.Enemy;
import Pakman.AbstractAndSuper.Direction;
import Pakman.AbstractAndSuper.Wall;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author simone
 */
public class EnemyTest {
    Enemy enemy;
    
    public EnemyTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        enemy = new Enemy(2,2);
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void randomDirectionIsSet() {
        enemy.newDirection();
        assertTrue(enemy.getDirection()!=null);
    }
    @Test
    public void directionIsSet() {
        enemy.setDirection(Direction.LEFT);
        assertTrue(enemy.getDirection()==Direction.LEFT);
    }
    @Test
    public void changesDirectionIfCantMove() {
        enemy.setDirection(Direction.LEFT);
        enemy.set(new Wall(10,10));
        enemy.changesDirectionIfCantMove();
        assertTrue(enemy.getDirection()!=Direction.LEFT);
    }
}
