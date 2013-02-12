/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman.AbstractAndSuper;

/**
 *
 * @author simone
 * 
 * For non-human controlled objects
 */
public interface InanimateMind {
    public void changesDirectionIfCantMove();
    public void randomDirectionChange();
    public void mayTurnIfPossible();
}
