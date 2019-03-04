package com.example.olivi.revisionmaster2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class RevisionMasterDatabaseHelper extends SQLiteOpenHelper {

    //name of the database
    private static final String DB_NAME = "RevisionMaster";
    //version of the database
    private static final int DB_VERSION = 1;
    //constructor
    RevisionMasterDatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        CreateTableOfTables(db);
        CreateDemoTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        RecyclerInitiator initiator = new RecyclerInitiator();
        ArrayList<String> tableNames = initiator.ReadDataToArray(db, "TABLE_OF_NAMES", "NAME");
        for (String table : tableNames){
            String dropQuery = "DROP TABLE IF EXISTS " + "'" + table + "';";
            db.execSQL(dropQuery);

        }
        db.execSQL("DROP TABLE IF EXISTS TABLE_OF_NAMES;");

        onCreate(db);
    }


    public void CreateTableOfTables (SQLiteDatabase db){

        db.execSQL("CREATE TABLE TABLE_OF_NAMES (NUMBER INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT);");
    }

    //przykladowa pierwsza tabela w bazie danych
    public void CreateDemoTable(SQLiteDatabase db){
        String tName = "English Numbers Demo";
        CreateTable(tName, db);
        InsertRow(tName, "1", "one", db);
        InsertRow(tName, "2", "two", db);
        InsertRow(tName, "3", "three", db);
        InsertRow(tName, "4", "four", db);
        InsertRow(tName, "5", "five", db);
        InsertRow(tName, "6", "six", db);
        InsertRow(tName, "7", "seven", db);
        InsertRow(tName, "8", "eight", db);
        InsertRow(tName, "9", "nine", db);
        InsertRow(tName, "10", "ten", db);

    }

    public void CreateTable(String tableName, SQLiteDatabase db){

        //tworzymy tabelę o zadanej nazwie i kolumnach: numer, termin i definicja
        db.execSQL("CREATE TABLE" + " '" + tableName + "' " + "( NUMBER INTEGER PRIMARY KEY AUTOINCREMENT, TERM TEXT, DEFINITION TEXT);");
        ContentValues values = new ContentValues(); //nie daje id bo samo sie wygeneruje
        values.put("NAME", tableName);
        db.insert("TABLE_OF_NAMES", null, values);

    }

    public void InsertRow(String tableName, String term, String definition,SQLiteDatabase db  ){

        ContentValues values = new ContentValues(); //nie daje id bo samo sie wygeneruje
        values.put("TERM", term);
        values.put("DEFINITION", definition);
        db.insert("'" + tableName + "'", null, values);

    }

    public void DropTable(SQLiteDatabase db, String tableName){
        db.execSQL("DELETE FROM TABLE_OF_NAMES WHERE NAME='"+tableName+"';");
        db.execSQL("DROP TABLE IF EXISTS '" + tableName + "';");
    }

    public void DeleteRow(SQLiteDatabase db, String tableName, String termName){
        db.execSQL("DELETE FROM " + "'" + tableName + "' WHERE TERM='" + termName + "';" );
    }
}
