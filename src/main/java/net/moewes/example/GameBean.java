package net.moewes.example;

import java.util.Random;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GameBean {

    private String playersName = "nobody";
    private int numberRange = 10;
    private int maxNumberOfTries = 3;

    private int numberToGuess;
    private int numberOfTries;

    private String hint = "";
    private String result = "";
    private boolean ended = false;

    public void startGame() {

        Random random = new Random();
        numberToGuess = random.nextInt(numberRange + 1);
        numberOfTries = 0;
        ended = false;
    }

    public void evaluateGuess(int guess) {

        numberOfTries++;
        if (guess == numberToGuess) {
            ended = true;
            result = playersName + ", you have won!";
        } else {
            if (numberOfTries == maxNumberOfTries) {
                result = playersName + ", you have lost :-(";
                ended = true;
            } else {
                hint = (guess < numberToGuess) ? "Number is greater than " + guess : "Number is lower than " + guess;
            }
        }
    }

    public boolean hasEnded() {
        return ended;
    }

    public String getResult() {
        return result;
    }

    public String getHint() {
        return hint;
    }

    public void setPlayersName(String name) {
        playersName = name;
    }

    public void setNumberRange(int range) {
        numberRange = range;
    }

    public void setMaxNumberOfTries(int maxTries) {
        maxNumberOfTries = maxTries;
    }
}