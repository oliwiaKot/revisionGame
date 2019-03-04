package com.example.olivi.revisionmaster2;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class RevisionMenuActivity extends Activity {
    public static final String SELECTED_TABLE_NAME = "selected table";
    String selectedTableName;
    RevisionMasterDatabaseHelper RevisionMasterHelper;
    SQLiteDatabase db;
    ArrayList<String> tableTerms = new ArrayList<>();
    ArrayList<String> tableDefs = new ArrayList<>();
    RecyclerViewReviseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revision_menu);

        Intent intent = getIntent();
        selectedTableName = intent.getStringExtra(SELECTED_TABLE_NAME);

        TextView tableNameView = findViewById(R.id.textView_tableTitle);
        tableNameView.setText(selectedTableName);

        RevisionMasterHelper = new RevisionMasterDatabaseHelper(this);
        db = RevisionMasterHelper.getReadableDatabase();

        initRecyclerView();
    }

    @Override
    protected void onRestart() {

        super.onRestart();
        initRecyclerView();
    }


    public void initRecyclerView(){
        tableDefs.clear();
        tableTerms.clear();
        RecyclerInitiator initiator = new RecyclerInitiator();

        tableTerms = initiator.ReadDataToArray(db, selectedTableName, "TERM");
        tableDefs = initiator.ReadDataToArray(db, selectedTableName, "DEFINITION");


        RecyclerView recyclerView = findViewById(R.id.recyclerView_revision);
        adapter = new RecyclerViewReviseAdapter( tableTerms, tableDefs, selectedTableName, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }



    public void onTypeClicked(View view){
        Intent intent = new Intent(this, TypeActivity.class);
        intent.putExtra(TypeActivity.TABLE_TERMS, tableTerms);
        intent.putExtra(TypeActivity.TABLE_DEFS, tableDefs);
        startActivity(intent);
        finish();
    }

    public void onClickerClicked(View view){
        Intent intent = new Intent(this, FastClickerActivity.class);
        intent.putExtra(FastClickerActivity.TABLE_TERMS, tableTerms);
        intent.putExtra(FastClickerActivity.TABLE_DEFS, tableDefs);
        startActivity(intent);
        finish();

    }
}
