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

    //metoda do ustalenia kolejności testowanych pojęć
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

    //metoda wypełniająca pozostałe 2 niepoprawne odpowiedzi
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

    public boolean isTheAnswerCorrect(String myAnswer, String correctAnswer){
        if(myAnswer.equals(correctAnswer)){
            return true;
        }else{
            return false;
        }
    }


}
