/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman.inTheGame;

import Pakman.AbstractAndSuper.Point;
import Pakman.AbstractAndSuper.Wall;
import java.util.ArrayList;


public class Wall3 extends Wall {
    
    public Wall3(int height, int length) {
        super(height,length);
        addLabyrinth(height, length);
    }
    private void addLabyrinth(int height, int length) {
        for (int i=1;i<height;i++) {
            for (int j=1; j < length; j++) {
                if (labyrinth(i,j, height, length)) {
                super.addBricks(new Point(i,j));
                }
            }
        }
    } 
    private boolean labyrinth(int i, int j, int height, int length) {
        ArrayList<Integer> possibile = new ArrayList<Integer>();
        for (int s=4;s<length-3;s=s+3) {
            possibile.add(s);
        }
        return j>3 && j<height-3 && (possibile.contains(i)) && j!=height/2 && j!=height/2-1;
    }
}
