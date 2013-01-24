
package Pakman.domain;

import Pakman.Direction;

public abstract class Individual  extends Point implements Moving, Bidimensional {
    private Direction direction; 
    private Wall wall;
    private int x1;
    private int y1;
    
    public Individual(int x, int y) {
        super(x,y);
        this.x1=x+1;
        this.y1=y+1;
        // The wall is set later on by public void set(Wall wall);
    }
    @Override
    public Direction getDirection() {
        return this.direction;
    }
    @Override
    public void setDirection(Direction direction) {
        this.direction=direction;
    }
    @Override
    public void forward() {
        if (this.direction==Direction.UP) {
            up();
        } else if (this.direction==Direction.DOWN) {
            down();
        } else if (this.direction==Direction.LEFT) {
            left();
        } else if (this.direction==Direction.RIGHT) {
            right();
        }
    }
    private void up() {
        super.setY(super.getY()-1);
        this.y1--;
    }
    private void down() {
        super.setY(super.getY()+1);
        this.y1++;
    }
    private void left() {
        super.setX(super.getX()-1);
        this.x1--;
    }
    private void right() {
        super.setX(super.getX()+1);
        this.x1++;
    }
    public void set(Wall wall) {
        this.wall=wall;
    }
    public boolean isInFrontOfWall() {
        for (Point brick : wall.getBriks()) {
            
            if (direction==Direction.UP &&
                    (brick.getX()==super.getX() || brick.getX()==this.x1) &&
                    brick.getY()+1==super.getY()) {
                return true;
                
            } else if (direction==Direction.DOWN &&
                    (brick.getX()==super.getX() || brick.getX()==this.x1) &&
                    brick.getY()-1==this.y1) {
                return true;
                
            }  else if (direction==Direction.RIGHT &&
                    brick.getX()-1==this.x1 &&
                    ( brick.getY()==this.y1 || brick.getY()==super.getY() ) ){
                return true;
                
            }  else if (direction==Direction.LEFT &&
                    brick.getX()+1==super.getX() &&
                    ( brick.getY()==this.y1 || brick.getY()==super.getY() ) ) {
                return true;
            } 
        }
        return false;
    }
    @Override
    public Integer getX1() {
        return this.x1;
    }
    @Override
    public Integer getY1() {
        return this.y1;
    }
    @Override
     public boolean bidimensionalRunsInto(Point point) {
         return  (  super.getX()==point.getX()  ||  this.x1==point.getX()   ) 
                 && 
                 (  super.getY()==point.getY()  ||  this.y1==point.getY()  );
     }
    @Override
    public boolean runsIntoBidimensional(Individual otherI) {
        return (super.getX()==otherI.getX() || this.x1==otherI.getX()  || super.getX()==otherI.getX1()) &&
                (super.getY()==otherI.getY() || this.y1==otherI.getY() || super.getY()==otherI.getY1());
    }
}
