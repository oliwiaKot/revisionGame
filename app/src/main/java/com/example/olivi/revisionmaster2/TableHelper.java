package com.example.olivi.revisionmaster2;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;

public class TableHelper {
    /**
     * This method chcecks if user input is already saved to an arraylist.
     * @param name is the user input value.
     * @param list is the arraylist to be searched.
     * @return this method returns true when user input is already found in the arraylist and false otherwise.
     */
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

    /**
     * This method allows the user to delete a table or a row in a table by clicking a button. It starts a new activity.
     * @param tableName is the name of the table from which one row is to be deleted. When the user chooses to delete a table, the value of this parameter is "TABLE_OF_TABLES".
     * @param row is the row to be deleted from the table. When the user chooses to delete a table, the value of this parameter is the name of the table to be deleted.
     * @param context
     */
    public void delete(String tableName, String row, Context context){

        Intent intent = new Intent(context, DeleteActivity.class);
        intent.putExtra("TABLE", tableName);
        intent.putExtra("ROW", row);
        context.startActivity(intent);

    }

    /**
     * This method is called when the user chooses to edit selected table contents. It starts a new activity.
     * @param tableName is the name of the table to be altered.
     * @param context
     */
    public void edit(String tableName, Context context){
        Intent intent = new Intent(context, EditTableActivity.class);
        intent.putExtra(EditTableActivity.SELECTED_TABLE_NAME, tableName);
        context.startActivity(intent);
    }

    /**
     * This method is called when the user chooses to view selected table contents. It starts a new activity.
     * @param tableName is the name of the selected table.
     * @param context
     */
    public void revise(String tableName, Context context){
        Intent intent = new Intent(context, RevisionMenuActivity.class);
        intent.putExtra(RevisionMenuActivity.SELECTED_TABLE_NAME, tableName);
        context.startActivity(intent);
    }

    /**
     * This method is called when the user chooses to delete a table row and confirms their choice. It deletes the selected row by calling the DeleteRow method in the RevisionMasterDatabaseHelper class.
     * @param tableName is the name of the table from which the row is to be deleted.
     * @param row is the value of a column in the row to be deleted.
     * @param context
     */
    public void deleteRow(String tableName, String row, Context context){
        SQLiteOpenHelper helper = new RevisionMasterDatabaseHelper(context);
        ((RevisionMasterDatabaseHelper) helper).DeleteRow(helper.getWritableDatabase(), tableName, row);

    }

    /**
     * This method is called when the user chooses to delete a table and confirms their choice. It deletes the selected table by calling the DeleteTable method in the RevisionMasterDatabaseHelper class.

     * @param row is the name of the table to be deleted.
     * @param context
     */
    public void deleteTable( String row, Context context){
        SQLiteOpenHelper helper = new RevisionMasterDatabaseHelper(context);
        ((RevisionMasterDatabaseHelper) helper).DropTable(helper.getWritableDatabase(), row);

    }




}
