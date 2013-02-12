/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman.AbstractAndSuper;

/**
 * 
 * @author simone
 * 
 * For all the objects which should move
 */
public interface Moving {
    public Direction getDirection();
    public void setDirection(Direction direction);
    public void forward();
    
}
