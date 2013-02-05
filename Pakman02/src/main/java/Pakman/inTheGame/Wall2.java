/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman.inTheGame;

import Pakman.AbstractAndSuper.Point;
import Pakman.AbstractAndSuper.Wall;

/**
 *
 * @author simone
 */
public class Wall2 extends Wall {
    
    public Wall2(int height, int length) {
        super(height,length);
        addLabyrinth(height, length);
    }
    private void addLabyrinth(int height, int length) {
        for (int i = 1; i < height; i++) {
            for (int j = 1; j < length; j++) {
                
                if (border(i,j,length,height)) {
                    super.addBricks(new Point(i, j));
                    
                } else if (centralBlock(i, j, length, height) && 
                        crossWithinCentralBlock(i, j, length, height)) {  
                    super.addBricks(new Point(i, j));
                }
            }
        }
    }
    private boolean leftAndRightRow(int i, int j, int height) {
        return leftRow(i,j) || rightRow(i,j,height);
    } 
    private boolean upperAndLowerRow(int i, int j, int length) {
        return upperRow(i,j) || lowerRow(i,j,length);
    } 
    private boolean holesOnTheSides(int i, int j, int length, int height) {
        return (i!=height/2 && i!=height/2+1) && (j!=length/2 && j!=length/2+1);
    }
    private boolean leftRow(int i, int j) {
        return ((i==2 || i==3) && j>5);
    }

    private boolean rightRow(int i, int j,int height) {
        return ((i==height-2 || i== height-3) && j<height-5);
    }

    private boolean upperRow(int i, int j) {
        return ((j==2 || j==3) && i>5);
    }

    private boolean lowerRow(int i, int j, int length) {
        return ((j==length-2 || j== length-3) && i<length-3);
    }

    private boolean centralBlock(int i, int j, int length, int height) {

        return i>5 && i<height-5 && j>5 && j<length-5;
    }
    private boolean border(int i, int j, int length, int height) {
        return (leftAndRightRow(i, j, height) 
                || upperAndLowerRow(i, j, length) 
                && holesOnTheSides(i, j, length, height));
    }

    private boolean crossWithinCentralBlock(int i, int j, int length, int height) {
        return (i!=height/2 && i!=height/2+1) && (j!=length/2 && j!=length/2+1);
    }
}

