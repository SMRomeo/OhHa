/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;

/**
 *
 * @author simone
 */
public class QuestionsGraphicInterface  implements Runnable {

    private JFrame frame;
    private char hsk;

    public QuestionsGraphicInterface(char hsk) {
        this.hsk=hsk;
    }

    @Override
    public void run() {
        frame = new JFrame("How do you say in Chinese?");
        frame.setPreferredSize(new Dimension(380, 180));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            luoKomponentit(frame.getContentPane());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(QuestionsGraphicInterface.class.getName()).log(Level.SEVERE, null, ex);
        }

        frame.pack();
        frame.setVisible(true);
    }
    private void luoKomponentit(Container container) throws FileNotFoundException {
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);
        String[] randomQuestion = getRandomQuestion();

        String inEnglish = randomQuestion[3];
        container.add(new JLabel(inEnglish));

        JRadioButton question1 = new JRadioButton("去");
        JRadioButton question2 = new JRadioButton("回");
        JRadioButton question3 = new JRadioButton(randomQuestion[1]+" "+randomQuestion[2]);
        JRadioButton question4 = new JRadioButton("很");

        ButtonGroup buttonGroupMiksi = new ButtonGroup();
        buttonGroupMiksi.add(question1);
        buttonGroupMiksi.add(question2);
        buttonGroupMiksi.add(question3);
        buttonGroupMiksi.add(question4);

        container.add(question1);
        container.add(question2);
        container.add(question3);
        container.add(question4);
        
        JButton nappi = new JButton("Answer");
        
        nappi.addActionListener(new CloseListener());
        container.add(nappi);
        
    }


    public JFrame getFrame() {
        return frame;
    }

    private String[] getRandomQuestion() throws FileNotFoundException {
        /** Question outlook.
         *  question[0] is composed of two components: a number from 1 to 6 representing the HSK level, 
         *              and an index number, for instance 1-276.
         *  question[1] is composed of a Chinese word, from one to three characters.
         *  question[2] is composed of the word pronunciation in Pinyin transliteration system.
         *  question[3] is composed of the meaning of the Chinese word.
         */
        
        ArrayList<String[]> words = new ArrayList<String[]>(); 
        Scanner scanner = new Scanner(new File("/home/simone/OhHa/Pakman02/src/main/java/Pakman/HSK6.csv"));
        while (scanner.hasNext()) {
            words.add(scanner.nextLine().split("\t"));
        }
        Random random = new Random();
        String[] question =words.get(random.nextInt(words.size()));
        while (question[0].charAt(0)!=hsk) {
            question=words.get(random.nextInt(words.size()));
        }
        return question;
    }
}