/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman.gui;

import Pakman.domain.Point;
import Pakman.pakman.World;
import inTheGame.Bonuses;
import inTheGame.Enemy;
import inTheGame.Hero;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;


public class GraphicBoard extends JPanel implements Updatable  {
    private World world;
    private int pointLength;

    public GraphicBoard(World world, int pointLenght) {
        this.world=world;
        this.pointLength=pointLenght/3*2+3;
    }
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        setBackgroundColour(graphics);
        setCoinsColour(graphics);
        setFruitsColour(graphics);
        setWallsColour(graphics);
        setHerosColour(graphics);
        setEnemy1Colour(graphics);
        setEnemy2Colour(graphics);
    }
    private void setBackgroundColour(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,
                0, 
                this.pointLength*(world.getLength()+1), 
                this.pointLength*(world.getHeight()+1));
    }

    private void setHerosColour(Graphics graphics) {
        Hero hero=this.world.getHero();
        graphics.setColor(Color.YELLOW);
        graphics.fillOval(
                this.pointLength*hero.getX(), 
                this.pointLength*hero.getY(),
                this.pointLength*2, 
                this.pointLength*2);
    }

    private void setEnemy1Colour(Graphics graphics) {
        Enemy enemy=this.world.getEnemy1();
        graphics.setColor(Color.BLUE);
        graphics.fillArc(
                this.pointLength*enemy.getX(), 
                this.pointLength*enemy.getY(),
                this.pointLength*2, 
                this.pointLength*2,
                320, 270);
    }
    private void setEnemy2Colour(Graphics graphics) {
        Enemy enemy=this.world.getEnemy2();
        graphics.setColor(Color.BLUE);
        graphics.fillArc(
                this.pointLength*enemy.getX(), 
                this.pointLength*enemy.getY(),
                this.pointLength*2, 
                this.pointLength*2,
                320, 270);
    }

    private void setWallsColour(Graphics graphics) {
        graphics.setColor(Color.GRAY);
        for (Point brick : this.world.getWall().getBriks()) {
            graphics.fillRect(this.pointLength*brick.getX()+1, 
                    this.pointLength*brick.getY()+1, 
                    this.pointLength-2, 
                    this.pointLength-2);
        }
    }

    @Override
    public void update() {
        if (world.gamesEnd()) {
            end();
        } else {
            super.repaint();
        }
    }

    private void end() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("It should be sleeping");
            }
        setBackgroundColour(super.getGraphics());
    }

    private void setCoinsColour(Graphics graphics) {
        Bonuses coins=world.getBonuses();
        graphics.setColor(Color.WHITE);
        for (Point coin : coins.getCoins()) {
            graphics.fillOval(this.pointLength*coin.getX()+10, 
                    this.pointLength*coin.getY()+10, 
                    this.pointLength-18, 
                    this.pointLength-18);
        }
    }

    private void setFruitsColour(Graphics graphics) {
        Bonuses coins=world.getBonuses();
        graphics.setColor(Color.RED);
        for (Point coin : coins.getFruits()) {
            graphics.fillOval(this.pointLength*coin.getX(), 
                    this.pointLength*coin.getY(), 
                    this.pointLength, 
                    this.pointLength);
        }
    }
    
}
