package com.example.olivi.revisionmaster2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class RecyclerInitiator {

    /**
     * This method allows reading data from a chosen table in the database to an arraylist.
     * @param db is the database from which the data is to be read.
     * @param tableName is the name of the table in the database from which the contents are to be read.
     * @param columnName is the name of the column in the selected table, the contents of which are to be written to an arraylist.
     * @return array - this method returns an array filled with the contents of a selected column in a given table from the database.
     */
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
