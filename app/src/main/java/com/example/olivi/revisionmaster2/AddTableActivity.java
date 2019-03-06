package com.example.olivi.revisionmaster2;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddTableActivity extends Activity {
    public static final String NEW_TABLE_NAME = "Table name";
    ArrayList<String> tableNamesExisting = new ArrayList<>();
    SQLiteOpenHelper RevisionMasterHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_table);
        Intent intent = getIntent();
        tableNamesExisting = intent.getStringArrayListExtra(NEW_TABLE_NAME);
    }

    /**
     * This method is called when the button is clicked to add a new table with a name given by the user.
     * @param view allows to assign this method to the appropriate button in xml.
     */
    public void onAddTableClicked(View view){
        EditText enteredName = findViewById(R.id.editText_tableName);
        String nameT = enteredName.getText().toString();
        TableHelper addHelper = new TableHelper();

        if(addHelper.checkIfExists(nameT, tableNamesExisting)){

            Toast.makeText(this, "This title already exists!", Toast.LENGTH_SHORT).show();
        }else{
            RevisionMasterHelper = new RevisionMasterDatabaseHelper(this);
            SQLiteDatabase db = RevisionMasterHelper.getWritableDatabase();
            ((RevisionMasterDatabaseHelper) RevisionMasterHelper).CreateTable(nameT, db);
            Toast.makeText(this, "Table added successfully!", Toast.LENGTH_SHORT).show();
            db.close();
            finish();
        }



    }



}
