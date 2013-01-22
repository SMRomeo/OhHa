/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman.domain;

/**
 *
 * @author simone
 */
public interface Bidimensional {
    
    public Integer getX1();
    public Integer getY1();
    public boolean runsIntoBidimensional(Individual otherI);
    public boolean bidimensionalRunsInto(Point point);
    
}
