package com.example.olivi.revisionmaster2;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditTableActivity extends Activity {
    public static final String SELECTED_TABLE_NAME = "Selected table name";
    String selectedTableName;
    RevisionMasterDatabaseHelper RevisionMasterHelper;
    SQLiteDatabase db;
    ArrayList<String> tableTerms = new ArrayList<>();
    ArrayList<String> tableDefs = new ArrayList<>();
   RecyclerViewEditAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_table);

        Intent intent = getIntent();
        selectedTableName = intent.getStringExtra(SELECTED_TABLE_NAME);

        TextView title = findViewById(R.id.textView_title);
        title.setText(selectedTableName);

        RevisionMasterHelper = new RevisionMasterDatabaseHelper(this);
        db = RevisionMasterHelper.getReadableDatabase();

        initRecyclerView();
    }

    @Override
    protected void onRestart() {

        super.onRestart();
        initRecyclerView();
    }

    /**
     * This method initiates a recycler view displaying the contents of the selected table and an option to delete them.
     */
    public void initRecyclerView(){
        tableDefs.clear();
        tableTerms.clear();
        RecyclerInitiator initiator = new RecyclerInitiator();

        tableTerms = initiator.ReadDataToArray(db, selectedTableName, "TERM");
        tableDefs = initiator.ReadDataToArray(db, selectedTableName, "DEFINITION");


        RecyclerView recyclerView = findViewById(R.id.recyclerView_Edit);
        adapter = new RecyclerViewEditAdapter( tableTerms, tableDefs, selectedTableName, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * This method is called when the user chooses to add a new row to the table by clicking the button.
     * @param view this parameter is used to assign this method to the appropriate button in xml.
     */
    public void onAddRowClicked(View view){
        String term;
        String def;
        TableHelper helper = new TableHelper();
        TextView termView = findViewById(R.id.editTextTerm);
        TextView defView = findViewById(R.id.editTextDef);
        term = termView.getText().toString() ;
        def = defView.getText().toString();

        if(helper.checkIfExists(term, tableTerms)){
            Toast.makeText(this, "This term is already defined!", Toast.LENGTH_SHORT).show();
        }else {
            if(def.isEmpty() || term.isEmpty()){
                Toast.makeText(this, "Term or definition missing!", Toast.LENGTH_SHORT).show();

            }else {

                RevisionMasterHelper.InsertRow(selectedTableName, term, def, db);
                initRecyclerView();
                termView.setText("");
                defView.setText("");
            }
        }

    }
}
