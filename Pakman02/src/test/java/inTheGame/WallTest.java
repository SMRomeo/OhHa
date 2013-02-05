/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inTheGame;

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
public class WallTest {
    Wall wall;
    
    public WallTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        wall= new Wall(5,5);
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void wallIsTheRightSize() {
         assertTrue(wall.getBriks().size()==12);
     }
}
