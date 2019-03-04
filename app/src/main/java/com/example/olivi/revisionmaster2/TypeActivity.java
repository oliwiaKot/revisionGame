package com.example.olivi.revisionmaster2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class TypeActivity extends Activity {
    ArrayList<String> tableTerms = new ArrayList<>();
    ArrayList<String> tableDefs = new ArrayList<>();
    GameHelper gameHelper;
    public static final String TABLE_TERMS = "table_terms";
    public static final String TABLE_DEFS = "table_defs";
    int[] testedOrder;
    int currentTerm;
    TextView definition;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);
        animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        Intent intent = getIntent();
        tableTerms = intent.getStringArrayListExtra(TABLE_TERMS);
        tableDefs = intent.getStringArrayListExtra(TABLE_DEFS);
        gameHelper = new GameHelper(tableTerms, tableDefs);
        testedOrder = gameHelper.getOrder();
        currentTerm = 0;
        definition = findViewById(R.id.textView_definition);
        definition.setText(tableDefs.get(testedOrder[currentTerm]));
        definition.startAnimation(animation);

    }

    public void onConfirmAnswer(View view){
        EditText answer = findViewById(R.id.editTextAnswer);
        TextView textView = findViewById(R.id.textViewisAnswerCorrect);
        if(gameHelper.isTheAnswerCorrect(answer.getText().toString().toLowerCase(), tableTerms.get(testedOrder[currentTerm]).toLowerCase())){

            textView.setText("Correct!");
            if(currentTerm<(tableTerms.size()-1)) {
                currentTerm++;
                definition.setText(tableDefs.get(testedOrder[currentTerm]));
                definition.startAnimation(animation);

            }else{
                finish();
            }
        }else{
            textView.setText("Wrong!");
        }
        answer.setText("");


    }
}
