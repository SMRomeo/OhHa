/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman.unused;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author simone
 */
class CloseListener implements ActionListener {

    public CloseListener() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
