/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman.AbstractAndSuper;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author simone
 */
public class Wall {
    private List<Point> bricks;
    
    public Wall(int height, int length) {
        this.bricks=new ArrayList<Point>();
        addBricks(height,length);
    }
    private void addBricks(int height, int length) {
        for (int i=1;i<height;i++) {
            for (int j=1; j < length; j++) {
                if (edge(i,j, height, length) ) {
                bricks.add(new Point(i,j));
                }
            }
        }
    }
    /**
     * 
     * @param point The brick to add
     */
    public void addBricks(Point point) {
        this.bricks.add(point);
    }
    /**
     * 
     * @return List<Point> The brick which form the wall
     */
    public List<Point> getBriks() {
        return this.bricks;
    }

    private boolean edge(int i,int j,int height, int length) {
        return  i==1 || j==1|| i==height-1|| j==length-1;
    }
}
