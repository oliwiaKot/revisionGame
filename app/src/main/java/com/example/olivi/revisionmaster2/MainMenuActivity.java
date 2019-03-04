package com.example.olivi.revisionmaster2;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainMenuActivity extends Activity {

    ArrayList<String> tableNames = new ArrayList<>();
    SQLiteDatabase db;
    SQLiteOpenHelper RevisionMasterHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
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
        RecyclerView recyclerView = findViewById(R.id.recycler_View_Main);
        RecyclerInitiator initiator = new RecyclerInitiator();
        tableNames.clear();
        tableNames = initiator.ReadDataToArray(db, "TABLE_OF_NAMES", "NAME");

        RecyclerViewMainAdapter adapter = new RecyclerViewMainAdapter( tableNames, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void onAddTable(View view){
        Intent intent = new Intent(MainMenuActivity.this, AddTableActivity.class);
        intent.putExtra(AddTableActivity.NEW_TABLE_NAME, tableNames);
        startActivity(intent);

    }

    public void onHelp(View view){
        Intent intent = new Intent(MainMenuActivity.this, HelpActivity.class);
        startActivity(intent);
    }
}
