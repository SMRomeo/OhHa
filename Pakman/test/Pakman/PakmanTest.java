package Pakman;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Pakman.Direction;
import Pakman.domain.Point;
import inTheGame.Hero;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PakmanTest {
    Hero hero;
    
    public PakmanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        hero = new Hero(10,10);
    }
    
    @After
    public void tearDown() {
    }
     @Test
     public void returnsHerosX() {
         assertEquals(hero.getX(),10);
     }
     @Test
     public void returnsHerosY() {
         assertEquals(hero.getY(),10);
     }
     @Test
     public void setsHerosX() {
         hero.setX(11);
         assertEquals(hero.getX(),11);
     }
     @Test
     public void setsHerosY() {
         hero.setY(11);
         assertEquals(hero.getY(),11);
     }
     @Test
     public void heroRunsIntoAPoint() {
         Point point = new Point(10,10);
         assertTrue(hero.runsInto(point));
     }
     @Test
     public void herosToString() {
         assertEquals(hero.toString(),"(10,10)");
     }
     @Test
     public void setAndGetHerosDirection() {
         hero.setDirection(Direction.UP);
         assertEquals(hero.getDirection(),Direction.UP);
     }
     @Test
     public void heroGoesUp() {
         hero.setDirection(Direction.UP);
         hero.forward();
         assertTrue(hero.getY()==9);
     }
     @Test
     public void heroGoesDown() {
         hero.setDirection(Direction.DOWN);
         hero.forward();
         assertTrue(hero.getY()==11);
     }
     @Test
     public void heroGoesLeft() {
         hero.setDirection(Direction.LEFT);
         hero.forward();
         assertTrue(hero.getX()==9);
     }
     @Test
     public void heroGoesRight() {
         hero.setDirection(Direction.RIGHT);
         hero.forward();
         assertTrue(hero.getX()==11);
     }
     @Test
     public void heroPennilessAtTheStart() { 
         assertTrue(hero.getCoins()==0);
     }
     @Test
     public void herosCoinsIncreaseByOne() { 
         hero.earn();
         assertTrue(hero.getCoins()==1);
     }
     @Test
     public void heroCanEarn() { 
         hero.earn(5);
         assertTrue(hero.getCoins()==5);
     }
}
