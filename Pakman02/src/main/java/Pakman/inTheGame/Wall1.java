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
public class Wall1 extends Wall {
    
    public Wall1(int height, int length) {
        super(height,length);
        addLabyrinth(height, length);
    }
    private void addLabyrinth(int height, int length) {
        for (int i = 1; i < height; i++) {
            for (int j = 1; j < length; j++) {
                if (border(i,j,length,height)) {
                    super.addBricks(new Point(i, j));
                    
                } else if (everyFourthLine(i,j,length, height)) {
                    super.addBricks(new Point(i, j));
                } else if (threeColumns(i,j,length, height)) {
                    super.addBricks(new Point(i, j));
                } else if (someBricksInTheFrame(i,j,length,height)) {
                    super.addBricks(new Point(i, j));
                }
            }
        }
    }
    private boolean border(int i, int j, int length, int height) {
        return (leftAndRightRow(i, j, height) 
                || upperAndLowerRow(i, j, length) 
                && holesOnTheSides(i, j, length, height));
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
    private boolean everyFourthLine(int i, int j,int length, int height) {
        return (j%3==0 && j>4 && j<height-5)
                && i>5 && i<length-5
                && holesEverySecond(i,j,length,height)
                ;
    }

    private boolean threeColumns(int i, int j, int length, int height) {
        return (i+4)%5==0
                && j>5 && j<height-5
                && holesOnTheColumns(i,j,length,height);
    }

    private boolean holesEverySecond(int i, int j, int length, int height) {
        if (j%6==0) {
            return i%5==2 || i%5 == 3;
        } else {
            return i%5==0 || i%5 == 4;
        }
    }

    private boolean holesOnTheColumns(int i, int j, int length, int height) {
        if ( i>height/2) {
            return j!=length/3+1 && j!=length/3+2;
        } else {
            return j!=length/2+3 && j!=length/2+4;
        }
    }

    private boolean someBricksInTheFrame(int i, int j, int length, int height) {
        return i==11 && j<6
                || i==12 && j<6
                || i>=length-6&&j<10
                || j>=height-9&&i<7
                ;
//               || i==5
    }
}
