/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman.doman;

import Pakman.AbstractAndSuper.Direction;
import Pakman.AbstractAndSuper.Individual;
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
    private Individual individual;
    
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
        this.individual=new Individual(1,10) {};
        individual.setDirection(Direction.UP);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void getX1() {
        assertTrue(individual.getX1()==2);
    }
    @Test
    public void getY1() {
        assertTrue(individual.getY1()==11);
    }
    @Test
    public void getAndSetDirection() {
        assertEquals(individual.getDirection(),individual.getDirection());
    }
    @Test
    public void forwardXandY() {
        individual.forward();
        assertEquals(individual.toString(),"(1,9)");
    }
    @Test
    public void forwardX1() {
        individual.forward();
        assertTrue((individual.getX1()==2));
    }
    @Test
    public void forwardY1() {
        individual.forward();
        assertTrue((individual.getY1()==10));
    }
    
}
