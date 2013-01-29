/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakman.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author simone
 */
public class QuestionsGraphicInterface  implements Runnable {

    private JFrame frame;
    private char hsk;
    private final Random random = new Random();
    
    public QuestionsGraphicInterface(char hsk) {
        this.hsk=hsk;
    }
    
    public boolean ask() {
        int howManyQuestions = 4;

        ArrayList<String[]> randomQuestions = null;
        try {
            randomQuestions = getRandomQuestions(howManyQuestions);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(QuestionsGraphicInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        int rightAnswer = random.nextInt(howManyQuestions);
        String[]mainQuestion = randomQuestions.get(rightAnswer);

        String[] all = {randomQuestions.get(0)[1]+" "+randomQuestions.get(0)[2],
      // this equals to -->      ChineseCharacter +" "+ Pronunciation
                         randomQuestions.get(1)[1]+" "+randomQuestions.get(1)[2],
                         randomQuestions.get(2)[1]+" "+randomQuestions.get(2)[2],
                         randomQuestions.get(3)[1]+" "+randomQuestions.get(3)[2]};
        
        int answer = JOptionPane.showOptionDialog(frame,
                mainQuestion[3],
                "How do you say in Chinese?",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                all,
                randomQuestions.get(0));
        
        return rightAnswer == answer;
    }


    public JFrame getFrame() {
        
        return frame;
    }

    private ArrayList<String[]> getRandomQuestions(int howManyQuestions) throws FileNotFoundException {
        /** Question outlook.
         *  question[0] is composed of two components: a number from 1 to 6 representing the HSK level, 
         *              and an index number, for instance 1-276.
         *  question[1] is composed of a Chinese word, from one to three characters.
         *  question[2] is composed of the word pronunciation in Pinyin transliteration system.
         *  question[3] is composed of the meaning of the Chinese word.
         */
        
        ArrayList<String[]> words = getWordsFromFile();
        ArrayList<String[]> randomQuestions = new ArrayList<String[]>();

        for (int i = 0; i<howManyQuestions;i++) {
            String[] question =words.get(random.nextInt(words.size()));
            while (question[0].charAt(0)!=hsk) {
                question=words.get(random.nextInt(words.size()));
            }
            randomQuestions.add(question);
        }
        
        return randomQuestions;
    }

    private ArrayList<String[]> getWordsFromFile() throws FileNotFoundException {
        ArrayList<String[]> words = new ArrayList<String[]>(); 
        Scanner scanner = new Scanner(new File("/home/simone/OhHa/Pakman02/src/main/java/Pakman/HSK6.csv"));
        while (scanner.hasNext()) {
            words.add(scanner.nextLine().split("\t"));
        }
        return words;
        
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}