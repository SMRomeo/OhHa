/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inTheGame;

import Pakman.domain.Point;
import Pakman.domain.Wall;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author simone
 */
public class Bonuses {
    private ArrayList<Point> coins;
    private ArrayList<Point> fruits;

    public Bonuses(int height, int length, Wall wall) {
        this.coins=new ArrayList<Point>();
        this.fruits=new ArrayList<Point>();
        addBonuses(height,length,wall);
    }

    private void addBonuses(int height, int length, Wall wall) {
        Random random = new Random();
        for (int i= 2; i < length - 1; i++) {
            for (int j = 2; j < length - 1; j++) {
                Point bonus = new Point(i, j);
                if (!wall.getBriks().contains(bonus)) {
                    coins.add(bonus);
                    if (i %2==0 && j%2==0 && random.nextInt(15) == 0) {
                        fruits.add(bonus);
                    }
                }
            }
        }
    }
    public List<Point> getCoins() {
        return this.coins;
    }
    public List<Point> getFruits() {
        return this.fruits;
    }
    public void removeBonuses(int x, int y) {
        Point toBeRemoved = new Point(x,y);
        this.coins.remove(toBeRemoved);
        this.fruits.remove(toBeRemoved);
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
