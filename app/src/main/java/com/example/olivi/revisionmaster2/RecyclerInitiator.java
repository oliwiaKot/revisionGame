package com.example.olivi.revisionmaster2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class RecyclerInitiator {


    public ArrayList<String> ReadDataToArray(SQLiteDatabase db, String tableName, String columnName){
        ArrayList<String> array = new ArrayList<>();
        Cursor cursor = db.query("'" + tableName + "'", new String[] {columnName}, null, null, null, null, null, null);
        if(cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                array.add(cursor.getString(cursor.getColumnIndex(columnName)));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return array;

    }


}
