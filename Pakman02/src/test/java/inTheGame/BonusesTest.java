/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inTheGame;

import Pakman.inTheGame.Bonuses;
import Pakman.AbstractAndSuper.Point;
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
public class BonusesTest {
    Wall wall;
    
    public BonusesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        wall=new Wall(1000,1000);
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void coinsAreSetRight() {
         Bonuses bonuses = new Bonuses(5,5,new Wall(5,5));
         assertTrue(bonuses.getCoins().size()==4);
     }
     @Test
     public void fruitsAreSetRight() {
         Bonuses bonuses = new Bonuses(1000,1000,new Wall(1000,1000));
         assertTrue(bonuses.getFruits().size()==11);
     }
     @Test
     public void bonusesAreRemoved() {
         Bonuses bonuses = new Bonuses(10,10,new Wall(10,10));
         Point coin = bonuses.getCoins().get(0);
         bonuses.removeBonuses(coin.getX(),coin.getY());
         assertTrue(!bonuses.getCoins().contains(coin));
         assertTrue(!bonuses.getCoins().contains(coin));
         
     }

}
