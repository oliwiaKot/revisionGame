package com.example.olivi.revisionmaster2;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;

public class TableHelper {

    public boolean checkIfExists(String name, ArrayList<String> list) {
        boolean exists = false;
        int length = list.size();

        for (int i = 0; i < length; i++) {
            if (list.get(i).equals(name)) {
                exists = true;

            }
        }

        return exists;
    }

    public void delete(String tableName, String row, Context context){

        Intent intent = new Intent(context, DeleteActivity.class);
        intent.putExtra("TABLE", tableName);
        intent.putExtra("ROW", row);
        context.startActivity(intent);

    }

    public void edit(String tableName, Context context){
        Intent intent = new Intent(context, EditTableActivity.class);
        intent.putExtra(EditTableActivity.SELECTED_TABLE_NAME, tableName);
        context.startActivity(intent);
    }

    public void revise(String tableName, Context context){
        Intent intent = new Intent(context, RevisionMenuActivity.class);
        intent.putExtra(RevisionMenuActivity.SELECTED_TABLE_NAME, tableName);
        context.startActivity(intent);
    }

    public void deleteRow(String tableName, String row, Context context){
        SQLiteOpenHelper helper = new RevisionMasterDatabaseHelper(context);
        ((RevisionMasterDatabaseHelper) helper).DeleteRow(helper.getWritableDatabase(), tableName, row);

    }

    public void deleteTable( String row, Context context){
        SQLiteOpenHelper helper = new RevisionMasterDatabaseHelper(context);
        ((RevisionMasterDatabaseHelper) helper).DropTable(helper.getWritableDatabase(), row);

    }




}
