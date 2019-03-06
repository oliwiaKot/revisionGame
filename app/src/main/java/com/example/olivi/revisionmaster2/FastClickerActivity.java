package com.example.olivi.revisionmaster2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class FastClickerActivity extends Activity {
    ArrayList<String> tableTerms = new ArrayList<>();
    ArrayList<String> tableDefs = new ArrayList<>();
    GameHelper gameHelper;
    int[] termsOrderArray;
    int currentTerm;
    private ProgressBar progressBar;
    CountDownTimer gameTimer;
    Animation animFadeIn;
    int playingTimeLeft = 7000; //czas gry


    public static final String TABLE_TERMS = "table_terms";
    public static final String TABLE_DEFS = "table_defs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_clicker);
        Intent intent = getIntent();
        tableTerms = intent.getStringArrayListExtra(TABLE_TERMS);
        tableDefs = intent.getStringArrayListExtra(TABLE_DEFS);
        progressBar =  findViewById(R.id.progressBar);
        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        currentTerm = 0;

        gameHelper = new GameHelper(tableTerms, tableDefs);
        termsOrderArray = gameHelper.getOrder();
        assignButtons();
        gameTimer = getNewTimer();
        gameTimer.start();
    }

    /**
     * This method assigns the available answers to 3 buttons on the screen.
     * It first calls the getTwoOtherButtons method from GameHelper class to select at random the two wrong terms.
     * Then it selects at random the button with the correct answer.
     * After that, the getTwoOtherButtons method is called again, to select at random which of the remaining two buttons will be assigned to which one of the "wrong" terms.
     * Then, for each button, the method setButtonsText is called.
     * Then the text of the tested definition is animated to appear in the central TextView on the screen.
     */
    public void assignButtons(){
        int[] otherTwoButtons = gameHelper.getTwoOtherButtons(termsOrderArray[currentTerm], tableTerms.size());
        Random random = new Random();
        //gdzie będzie poprawna odpowiedź
        int buttonWithCorrectAnswer = random.nextInt(3);
        setButtonsText(buttonWithCorrectAnswer, tableTerms.get(termsOrderArray[currentTerm]) );
        int[] numbersOfOtherButtons = gameHelper.getTwoOtherButtons(buttonWithCorrectAnswer, 3);
        setButtonsText(numbersOfOtherButtons[0], tableTerms.get(termsOrderArray[otherTwoButtons[0]]));
        setButtonsText(numbersOfOtherButtons[1], tableTerms.get(termsOrderArray[otherTwoButtons[1]]));
        TextView flashCard = findViewById(R.id.textView_flashCard);
        flashCard.setText(tableDefs.get(termsOrderArray[currentTerm]));
        flashCard.startAnimation(animFadeIn);


    }

    /**
     * This method is used to put appropriate text on a selected button.
     * @param i is the button number.
     * @param text is the text to be written on the button.
     */

    public void setButtonsText(int i, String text){
        Button button;

        switch (i){
            case 0:
                button = findViewById(R.id.button1);
                button.setText(text);
                break;
            case 1:
                button = findViewById(R.id.button2);
                button.setText(text);
                break;
            case 2:
                button = findViewById(R.id.button3);
                button.setText(text);
                break;
                default:
                    break;

        }

    }


    /**
     * This method is called when the user clicks one of the answer buttons. It detects which button was clicked.
     * Then it checks if the answer is correct by calling the isTheAnswerCorrect method from the GameHelper class.
     * If the answer is correct,  the user gets extra time and an appropriate message in the textview.
     * If the answer is not correct, no time is added and an appropriate message is displayed.
     * It then increases the currenTerm value and calls the assignButtons method again to test the next definition.
     * @param view allows the assignment of this method to the appropriate buttons in xml.
     */
    public void onAnswerClicked(View view){
        Button buttonClicked = findViewById(view.getId());
        TextView textView = findViewById(R.id.textView_correct);
        if(gameHelper.isTheAnswerCorrect(buttonClicked.getText().toString(),tableTerms.get(termsOrderArray[currentTerm]))){
            textView.setText("Correct!");
            gameTimer.cancel(); //zatrzymuje odliczanie, bo chce dodac czas
            //dodaje czas
            if(playingTimeLeft<6000){
                playingTimeLeft = playingTimeLeft + 1000;
            }else{
                playingTimeLeft = 7000;
            }
            //przestawiam progress bar
            int progressToBeSet = (100 - (playingTimeLeft/70));
            progressBar.setProgress(progressToBeSet);
            //rozpoczynam nowe odliczanie
            gameTimer = getNewTimer();
            gameTimer.start();
        }else{
            textView.setText("Wrong!");
        }

        if(currentTerm<(tableTerms.size() - 1)){
            currentTerm++;
            assignButtons();

        }else{
            gameTimer.cancel();
            finish();
        }

    }

    /**
     * This method is called when the activity starts or when the user answers correctly.
     * The countdown timer updates the progress bar on the screen, telling the user how much time they have left.
     * @return this method returns a new countdown timer
     *
     */
    public CountDownTimer getNewTimer(){
        return new CountDownTimer( playingTimeLeft, 70) {

            @Override
            public void onTick(long millisUntilFinished) {
                if(playingTimeLeft!=0){
                    playingTimeLeft = playingTimeLeft - 70;

                    progressBar.incrementProgressBy(1);
                }
            }

            @Override
            public void onFinish() {
                finish();
            }
        };

    }
}
