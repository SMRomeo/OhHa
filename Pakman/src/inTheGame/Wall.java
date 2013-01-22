/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inTheGame;

import Pakman.domain.Point;
import java.util.ArrayList;
import java.util.List;


public class Wall {
    private List<Point> bricks;
    
    public Wall(int height, int length) {
        this.bricks=new ArrayList<Point>();
        addBricks(height,length);
    }
    private void addBricks(int height, int length) {
        for (int i=1;i<height;i++) {
            for (int j=1; j < length; j++) {
                if (edge(i,j, height, length) || labyrinth(i,j, height, length)) {
                bricks.add(new Point(i,j));
                }
            }
        }
    }
    public List<Point> getBriks() {
        return this.bricks;
    }

    private boolean edge(int i,int j,int height, int length) {
        return  i==1 || j==1|| i==height-1|| j==length-1;
    }

    private boolean labyrinth(int i, int j, int height, int length) {
        ArrayList<Integer> possibile = new ArrayList<Integer>();
        for (int s=4;s<length-3;s=s+3) {
            possibile.add(s);
        }
        return j>3 && j<height-3 && (possibile.contains(i)) && j!=height/2 && j!=height/2-1;
    }
}
