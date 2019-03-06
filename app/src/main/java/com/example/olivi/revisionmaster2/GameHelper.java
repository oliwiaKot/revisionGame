package com.example.olivi.revisionmaster2;

import java.util.ArrayList;
import java.util.Random;

public class GameHelper {
    ArrayList<String> tableTerms;
    ArrayList<String> tableDefs ;

    public GameHelper(ArrayList<String> tableTerms, ArrayList<String> tableDefs){
        this.tableDefs = tableDefs;
        this.tableTerms = tableTerms;
    }

    /**
     * This method is used to get a random order of the terms in a selected table without repetition.
     * @return orderArray - this method returns a table of integers, containing indexes of terms in a selected table, in a random order, without repetition.
     */
    public int[] getOrder(){
        int[] orderArray = new int[tableTerms.size()];
        boolean[] isThisIndexUsed = new boolean[tableTerms.size()];

        Random random = new Random();
        int index = random.nextInt(tableTerms.size());

        for (int i = 0; i<tableTerms.size(); i++){
            isThisIndexUsed[i] = false;
        }

        for(int i = 0; i<tableTerms.size(); i++){

            while (isThisIndexUsed[index]){
                index = random.nextInt(tableTerms.size());

            }
            orderArray[i] = index;
            isThisIndexUsed[index] = true;


        }
        return orderArray;

    }

    /**
     * This method allows to obtain two random integers in a given interval, excluding one value, without repetition.
     * @param indexOfCorrect is the integer to be excluded.
     * @param size is the interval in which the two random integers are to be in.
     * @return otherButtonsIndexes - this method returns a table of integers, containing the two random integers.
     */
    public int[] getTwoOtherButtons(int indexOfCorrect, int size){
        int[] otherButtonsIndexes = new int[2];
        otherButtonsIndexes[0] = indexOfCorrect;
        otherButtonsIndexes[1] = indexOfCorrect;
        Random random = new Random();

        while(otherButtonsIndexes[0] == indexOfCorrect){
            otherButtonsIndexes[0] = random.nextInt(size);
        }

        while ((otherButtonsIndexes[1] == otherButtonsIndexes[0]) || (otherButtonsIndexes[1] == indexOfCorrect)){
            otherButtonsIndexes[1] = random.nextInt(size);
        }

        return otherButtonsIndexes;
    }

    /**
     * This method checks if user input matches the required value.
     * @param myAnswer is the user input.
     * @param correctAnswer is the required value that the user input is compared to.
     * @return - this method returns true when user input matches the required value and false otherwise.
     */
    public boolean isTheAnswerCorrect(String myAnswer, String correctAnswer){
        if(myAnswer.equals(correctAnswer)){
            return true;
        }else{
            return false;
        }
    }


}
