
package Pakman.AbstractAndSuper;

import java.util.List;

public class Point {
    private int x;
    private int y;
    
     public Point(int x, int y) {
         this.x=x;
         this.y=y;
     }
     public int getX() {
         return this.x;
     }
     public int getY() {
         return y;
     }
     public void setX(int x) {
         this.x=x;
     } 
     public void setY(int y) {
         this.y=y;
     } 
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
    public void reset(int x, int y) {
        this.x=x;
        this.y=y;
    }
}