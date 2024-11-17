package flashcards;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
 * eddig spagetti a kod
 *
 * nezet es az allapotreprezentacio egybe van
 * allapotreprezentacionak kulon kene lennie
 *
 * modell-nezet architektura legyen alkalmazva
 * modell: cardnumber showquestion stb
 * sajat listajat felto
 *
 *
 * model.good/wronganswer
 * model.toggle()
 * updateDisplayben lekerdezni modellbol mit kell megjeleniteni
 *
 * model.getText()
 *
 * model.getScore model.getNumber (az updateScoreban)
 *
 * model.reset()
 *
 * elso negy deklaraciot is modelbe
 *
 * Model: (CardPackModel)
 * //open(File)
 * //toggle()
 * //goodAnswer()
 * //wrongAnswer()
 * //reset()
 * //getScore()
 * //getCardNumber()
 * //getDescription() // Q/A
 *
 */
public class CardPackModel {
    private ArrayList<Card> cards;
    private int cardNumber;
    private boolean showQuestion;
    private int score;

    public void openFile(File file) throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            cards = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] qa = line.split("~");
                if (qa.length == 2) {
                    cards.add(new Card(qa[0], qa[1]));
                }
            }
        }

    }

    public void reset(){
        score = 0;
        cardNumber = 0;
        showQuestion = true;
    }

    public void toggle(){
        showQuestion = !showQuestion;
    }

    public void goodAnswer(){
        score++;
        cardNumber++;
    }

    public void wrongAnswer(){
        cardNumber++;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public int getScore() {
        return score;
    }

    public boolean isEmpty(){
        return cards==null;
    }

    public boolean isTheEnd(){
        return cardNumber==cards.size();
    }

    public int getCardsSize(){
        return cards.size();
    }

    public String getDescription(){
        if (showQuestion) {
            return cards.get(cardNumber).getQuestion();
        } else {
            return cards.get(cardNumber).getAnswer();
        }

    }
}
