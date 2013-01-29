/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inTheGame;

import Pakman.Direction;
import Pakman.domain.Individual;
import Pakman.domain.Wall;
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
public class IndividualTest {
    Individual individual;
    
    public IndividualTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        individual = new Individual(2,2) {};
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void knowsWhenInFrontOfAWall() {
        individual.setDirection(Direction.LEFT);
        individual.set(new Wall(10,10));
        assertTrue(individual.isInFrontOfWall());
    }
}
