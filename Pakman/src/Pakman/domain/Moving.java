/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman.domain;

import Pakman.Direction;

public interface Moving {
    public Direction getDirection();
    public void setDirection(Direction direction);
    public void forward();
    
}
