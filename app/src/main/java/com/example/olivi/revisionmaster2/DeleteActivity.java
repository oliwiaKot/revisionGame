package com.example.olivi.revisionmaster2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteActivity extends Activity {

    String tableName;
    String rowSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        Intent intent = getIntent();
        tableName = intent.getStringExtra("TABLE");
        rowSelected = intent.getStringExtra("ROW");

        initiateTextView();
    }

    public void initiateTextView(){

        TextView text = findViewById(R.id.textViewDelete);
        if(tableName.equals("TABLE_OF_NAMES")){
            text.setText("Are you sure you want to delete table " + rowSelected + "?");


        }else {
            text.setText("Are you sure you want to delete term " + rowSelected + " from table " + tableName + "?");
        }
    }

    public void onDeleteConfirmed(View view){
        TableHelper helper = new TableHelper();
        if(tableName.equals("TABLE_OF_NAMES")){
            helper.deleteTable(rowSelected, this);
            Toast.makeText(this, "Table deleted successfully", Toast.LENGTH_SHORT).show();
        }else {
            helper.deleteRow(tableName, rowSelected, this);
            Toast.makeText(this, "Term deleted successfully", Toast.LENGTH_SHORT).show();

        }
        finish();
    }
}
