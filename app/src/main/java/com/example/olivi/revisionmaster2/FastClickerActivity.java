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
