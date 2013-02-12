/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman.AbstractAndSuper;

/**
 *
 * @author simone
 * 
 * The interface for bidimentional objects
 */
public interface Bidimensional {
    
    public Integer getX1();
    public Integer getY1();
    public boolean runsIntoBidimensional(Individual otherI);
    public boolean bidimensionalRunsInto(Point point);
    
}
