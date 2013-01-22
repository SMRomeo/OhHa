/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inTheGame;

import Pakman.domain.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author simone
 */
public class Coins {
    private ArrayList<Point> coins;

    public Coins(int height, int length, Wall wall) {
        this.coins=new ArrayList<Point>();
        addCoins(height,length,wall);
    }

    private void addCoins(int height, int length, Wall wall) {
        for (int i=2;i<length-1;i++) {
            for (int j=2;j<length-1;j++) {
                Point coin = new Point(i,j);
                if (!wall.getBriks().contains(coin)) {
                    coins.add(coin);
                }
            }
        }
    }
    public List<Point> getCoins() {
        return this.coins;
    }
    public void removeCoin(int x, int y) {
        Point toBeRemoved = new Point(x,y);
        this.coins.remove(toBeRemoved);
    }
    @Override
    public String toString() {
        String coinsPositions = "";
        for (Point coin : coins) {
            coinsPositions+=coin+" - ";
        }
        return coinsPositions;
    }
}
