/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman.inTheGame;

import Pakman.AbstractAndSuper.Direction;
import Pakman.AbstractAndSuper.HuntingDog;
import java.util.Random;

/**
 *
 * @author simone
 */
public class ChiefEnemy extends Enemy implements HuntingDog {
    
    public ChiefEnemy(int x, int y) {
        super(x,y);
    }
    /*
     * The ChiefEnemy moves as a normal Enemy, but he also follows the Hero when the Hero and the Chief are on
     * the same axis. However, if the hero and the enemy are on the same axes
     * but there is the wall between them, the Chief changes direction.
     */
    public void moves(Hero hero) {
        super.mayTurnIfPossible();
        follows(hero);
        super.changesDirectionIfCantMove();
        super.forward();
    }
    /*
     * The ChiefEnemy follows the Hero when the Hero and the Chief are on
     * the same axis. If the hero and the enemy are on the same axes
     * but there is the wall between them, the Chief changes direction.  
     * However, it would be extremely difficult to escape from ChiefEnemy,
     * unless the chances to chase the hero were not only two out of three.
     */
    @Override
    public void follows(Hero hero) {
        Random random = new Random();
        if (random.nextInt(3)>0) {
            if (hero.getX() == super.getX()) {
                if (hero.getY()>super.getY()) {
                    super.setDirection(Direction.DOWN);
                } else if (hero.getY()<super.getY()) {
                    super.setDirection(Direction.UP);
                }
            } else if (hero.getY()==super.getY()) {
                if (hero.getX()>super.getX()) {
                    super.setDirection(Direction.RIGHT);
                } else if (hero.getX()<super.getX()) {
                    super.setDirection(Direction.LEFT);
                }
            }
        }
    }
}
