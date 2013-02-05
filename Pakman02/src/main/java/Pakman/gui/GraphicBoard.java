/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman.gui;

import Pakman.pakman.Pakman;
import Pakman.AbstractAndSuper.Point;
import Pakman.pakman.World;
import Pakman.inTheGame.Bonuses;
import Pakman.inTheGame.ChiefEnemy;
import Pakman.inTheGame.Enemy;
import Pakman.inTheGame.Hero;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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
        setEnemiesColour(graphics);
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
            try {
                end();
            } catch (UnsupportedAudioFileException ex) {
            } catch (IOException ex) {
            } catch (LineUnavailableException ex) {
            }
        } else {
            super.repaint();
        }
    }

    private void end() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException ex) {
//                System.out.println("It should be sleeping");
//            }
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

    private void setEnemiesColour(Graphics graphics) {
        ArrayList<Enemy> enemies = this.world.getEnemies();
        
        for (int i = 1; i<enemies.size();i++) {
            Enemy enemy = enemies.get(i);
            graphics.setColor(Color.BLUE);
            graphics.fillArc(
                    this.pointLength*enemy.getX(), 
                    this.pointLength*enemy.getY(),
                    this.pointLength*2, 
                    this.pointLength*2,
                    320, 270);
        }
        
        ChiefEnemy chief = (ChiefEnemy) enemies.get(0);
        graphics.setColor(Color.CYAN);
        graphics.fillArc(
                this.pointLength*chief.getX(), 
                this.pointLength*chief.getY(),
                this.pointLength*2, 
                this.pointLength*2,
                320, 270);
        
    }

    private void nextLevel() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
//        System.exit(0);
//        int level = world.getLevel();
//        Pakman pakman = new Pakman(level);
//        pakman.nextLevel();
    }
    
}
