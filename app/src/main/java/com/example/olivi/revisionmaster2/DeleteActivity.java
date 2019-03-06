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

    /**
     * This method is called when the activity is created and it writes an appropriate message in the TextView depending on whether the user chose to delete a table or to delete a row from a table.
     */
    public void initiateTextView(){

        TextView text = findViewById(R.id.textViewDelete);
        if(tableName.equals("TABLE_OF_NAMES")){
            text.setText("Are you sure you want to delete table " + rowSelected + "?");


        }else {
            text.setText("Are you sure you want to delete term " + rowSelected + " from table " + tableName + "?");
        }
    }

    /**
     * This method is called when the user clicks a button confirming their choice to delete a table or a table row. It then calls the deleteRow or deleteTable from the GameHelper class depending on whether the user chose to delete a table or a table row.
     * @param view allows to assign this method to the appropriate button in xml.
     */
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
