/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman.domain;

import java.util.List;

/**
 *
 * @author simone
 */
public interface Enrichable {
    
    public void earn();
    public void earn(int coins);
    public int getCoins();
    public boolean runsIntoCoin(List<Point> coins);
}
