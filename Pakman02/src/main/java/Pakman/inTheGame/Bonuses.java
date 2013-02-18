/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman.inTheGame;

import Pakman.AbstractAndSuper.Point;
import Pakman.AbstractAndSuper.Wall;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author simone
 */
public class Bonuses {
    private final int numberOfQuestions = 11;
    private ArrayList<Point> coins;
    private ArrayList<Point> fruits;

    /*
     * There are two kinds of bonuses that the hero tries to grab: coins and fruits.
     * 
     */
    public Bonuses(int height, int length, Wall wall) {
        this.coins=new ArrayList<Point>();
        this.fruits=new ArrayList<Point>();
        addBonuses(height,length,wall);
    }
    /*
     * The different bonuses are added to the ArrayList
     */
    private void addBonuses(int height, int length, Wall wall) {
        for (int i= 2; i < length - 1; i++) {
            for (int j = 2; j < height - 1; j++) {
                Point bonus = new Point(i, j);
                if (!wall.getBriks().contains(bonus)) {
                    coins.add(bonus);
                }
            }
        }
        addFruits();
    }
    /**
     * 
     * @return List<Point> The Coins bonuses
     */
    public List<Point> getCoins() {
        return this.coins;
    }
    /**
     * 
     * @return List<Point> The Fruit bonuses
     */
    public List<Point> getFruits() {
        return this.fruits;
    }
    /*
     * The (x,y) bonus is removed from the Coins and from the Fruits
     */
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
    /*
     * Fruit bonuses are added to the ArrayList
     */
    private void addFruits() {
        Random random = new Random();
        for (int i = 0; i<numberOfQuestions;i++) {
            this.fruits.add(this.coins.get(random.nextInt(coins.size()-1)));
        }
    }
}
