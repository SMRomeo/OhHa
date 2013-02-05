/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman.doman;

import Pakman.AbstractAndSuper.Point;
import java.util.ArrayList;
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
public class PointTest {

    private Point point;

    public PointTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.point = new Point(1, 10);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void getX() {
        assertEquals(1, point.getX());
    }
    @Test
    public void getY() {
        assertEquals(10, point.getY());
    }
    @Test
    public void setX() {
        point.setX(5);
        assertEquals(5, point.getX());
    }
    @Test
    public void setY() {
        point.setY(5);
        assertEquals(5, point.getY());
    }
    @Test
    public void runsIntoPoint() {
        assertTrue(this.point.runsInto(new Point(1,10)));
    }
    @Test
    public void doesntRunIntoPoint() {
        assertTrue(!this.point.runsInto(new Point(1,9)));
    }
    @Test
    public void runsIntoPoints() {
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0,0));
        points.add(new Point(1,10));
        assertTrue(this.point.runsInto(points));
    }
    @Test
    public void doesntRunIntoPoints() {
        ArrayList<Point> points = new ArrayList<Point>();
        points.add(new Point(0,0));
        points.add(new Point(1,9));
        assertTrue(!this.point.runsInto(points));
    }
    @Test
    public void toStringWorks() {
        assertEquals(this.point.toString(), "(1,10)");
    }
    @Test
    public void equalsWorks1() {
        assertEquals(this.point, new Point(1,10));
    }
    @Test
    public void equalsWorks2() {
        assertTrue(!this.point.equals(new Point(1,11)));
    }
    @Test
    public void equalsWorks3() {
        assertTrue(!this.point.equals(new Point(0,10)));
    }
    @Test
    public void equalsWorks4() {
        assertTrue(!this.point.equals(null));
    }
    @Test
    public void equalsWorks5() {
        assertTrue(!this.point.equals(10));
    }
    
    
}
