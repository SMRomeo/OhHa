
package Pakman.AbstractAndSuper;

import java.util.List;

public class Point {
    private int x;
    private int y;
    
     public Point(int x, int y) {
         this.x=x;
         this.y=y;
     }
     /**
      * 
      * @return int The x position 
      */
     public int getX() {
         return this.x;
     }
     /**
      * 
      * @return int The y position 
      */
     public int getY() {
         return y;
     }
     /**
      * 
      * @param x The int:x value the object will have
      */
     public void setX(int x) {
         this.x=x;
     } 
     /**
      * 
      * @param y The int:y value the object will have
      */
     public void setY(int y) {
         this.y=y;
     } 
     /**
      * 
      * @param point The point whose coordinates might equal to the object's coordinates
      * @return boolean True if the point's coordinates equal to the object's
      */
     public boolean runsInto(Point point) {
         return (x==point.getX()) && (y==point.getY());
     }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.x;
        hash = 37 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Point other = (Point) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }
    /**
     * 
     * @param points A list of point whose coordinates might equal to the object's coordinates
     * @return boolean True if the coordinates of a point of the list equal to the object's
     */
     public boolean runsInto(List<Point> points) {
         for (Point point : points) {
             if (runsInto(point)) {
                 return true;
             }
         }
         return false;
     }
    @Override
     public String toString() {
        return  "("+x+","+y+")";  
     }
    /**
     * 
     * @param x The new int:x value the object will have
     * @param y The new int:y value the object will have
     * 
     * used to reset the object's coordinates
     */
    public void reset(int x, int y) {
        this.x=x;
        this.y=y;
    }
}