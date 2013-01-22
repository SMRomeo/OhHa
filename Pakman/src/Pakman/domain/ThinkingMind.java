/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman.domain;

/**
 *
 * @author simone
 */
public interface ThinkingMind {
    public void stop();
    public void prepareToMove();
    public boolean isMoving();
    public void acsAccordingToTheWall();
}
