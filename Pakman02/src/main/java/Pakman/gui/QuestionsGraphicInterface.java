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
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 *
 * @author simone
 */
public class QuestionsGraphicInterface  implements Runnable {

    private JFrame frame;
    private int hsk;
    private final Random random = new Random();
    private final int numberOfQuestions = 5;
    
    public QuestionsGraphicInterface(int hsk) {
        this.hsk=hsk;
    }
    /**
     * 
     * @return boolean Returns true if the player answers right to the character question
     */
    public boolean ask() {
        ArrayList<String[]> randomCharacters = getRandomQuestions();
        int right = random.nextInt(numberOfQuestions);
        String[]question = randomCharacters.get(right);
        String[] possibleAnswers = getPossibleAnswers(randomCharacters);
        int answer = testTheStudent(question, possibleAnswers, randomCharacters);
        if (right != answer) {
            shameOnYou(right,randomCharacters);
        }
        return right == answer;
    }

    /**
     * 
     * @return JFrame The graphic interface question frame 
     */
    public JFrame getFrame() {
        
        return frame;
    }

    /**
     * 
     * @return ArrayList<String[]> An ArrayList with the six possible characters to choose among
     */
    private ArrayList<String[]> getRandomQuestions() {
        /** Question outlook.
         *  question[0] is composed of two components: a number from 1 to 6 representing the HSK level, 
         *              and an index number, for instance 1-276.
         *  question[1] is composed of a Chinese word, from one to three characters.
         *  question[2] is composed of the word pronunciation in Pinyin transliteration system.
         *  question[3] is composed of the meaning of the Chinese word.
         */
        
        ArrayList<String[]> words = getWordsFromFile();
        ArrayList<String[]> randomQuestions = new ArrayList<String[]>();

        for (int i = 0; i<numberOfQuestions;i++) {
            String[] question =words.get(random.nextInt(words.size()));
            while (question[0].charAt(0)!=Character.forDigit(hsk,10)) {
                question=words.get(random.nextInt(words.size()));
            }
            randomQuestions.add(question);
        }
        
        return randomQuestions;
    }

    /**
     * 
     * @return ArrayList<String[]> Returns all the Chinese words in the document, regardless of how difficult they are
     * @throws FileNotFoundException If the document containing the Chinese characters is not found
     */
    private ArrayList<String[]> getWordsFromFile() {
        ArrayList<String[]> words = new ArrayList<String[]>(); 
        Scanner scanner = getScanner();
        while (scanner.hasNext()) {
            words.add(scanner.nextLine().split("\t"));
        }
        return words;
    }
    private Scanner getScanner() {
        Scanner scanner = null;
        try {
            File file = new File(("HSK6.csv"));
            scanner = new Scanner(file);
        } catch (FileNotFoundException ex) {
            System.out.println("The file containing the questions was not found."
                    +"The file HSK.csv should be cointained in the directory Pakman02");
            System.out.println("This message occured in Pakman.gui>QuestionsGraphicInterface>getScanner");
        }
        return scanner;
    }
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private int testTheStudent(String[] question, String[] possibleAnswers, ArrayList<String[]> randomCharacters) {
        String windowTitle = "How do you say in Chinese?"; 
        return JOptionPane.showOptionDialog(frame,
                // The english word which will be aksed
                question[3],
                windowTitle,
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                possibleAnswers,
                // The default answer
                randomCharacters.get(0));
    }

    private String[] getPossibleAnswers(ArrayList<String[]> randomCharacters) { 
        ArrayList<String> possibleAnswers = new ArrayList<String>();
        
        for (int i = 0; i<numberOfQuestions;i++) {
            String question = randomCharacters.get(i)[1]+"\n"+randomCharacters.get(i)[2];
//            question = "<html><font size='5'>"+question+"</font></html>";
            question = setFont(question,5);
            possibleAnswers.add(question);
        }
        
        String[] simpleArray = new String[possibleAnswers.size()];
        possibleAnswers.toArray(simpleArray);
        
        return simpleArray;
    }

    private void shameOnYou(int right,ArrayList<String[]> randomCharacters) {
        String message = "The right answer was "+randomCharacters.get(right)[1]+" "+randomCharacters.get(right)[2];
        message = setFont(message, 6);
        JOptionPane.showMessageDialog(null,message,
                "Shame On You!", JOptionPane.ERROR_MESSAGE); 
    }

    private String setFont(String string, int size) {
        return "<html><font size='"+size+"'>"+string+"</font></html>";
    }
}