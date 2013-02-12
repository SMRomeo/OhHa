/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman.AbstractAndSuper;

/**
 *
 * @author simone
 * 
 * Makes the objects move in an intelligent way
 */
public interface ThinkingMind {
    public void stop();
    public void prepareToMove();
    public boolean isMoving();
    public void acsAccordingToTheWall();
}
