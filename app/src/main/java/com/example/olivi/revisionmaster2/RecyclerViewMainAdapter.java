package com.example.olivi.revisionmaster2;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.olivi.revisionmaster2.RecyclerViewMainAdapter.ViewHolderMain.*;


public class RecyclerViewMainAdapter extends RecyclerView.Adapter <RecyclerViewMainAdapter.ViewHolderMain> {

    private ArrayList<String> table_names = new ArrayList<>();
    private Context context;

    public RecyclerViewMainAdapter(ArrayList<String> table_names, Context context){
        this.table_names = table_names;
        this.context = context;
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public RecyclerViewMainAdapter.ViewHolderMain onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_main_itemlayout, viewGroup, false);
        ViewHolderMain holder = new ViewHolderMain(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewMainAdapter.ViewHolderMain viewHolderMain, int position) {

        viewHolderMain.name_textview.setText(table_names.get(position));
        viewHolderMain.button_Revise.setText(R.string.button_Revise);
        viewHolderMain.button_Edit.setText(R.string.button_Edit);
        viewHolderMain.button_Delete.setText(R.string.button_Delete);
    }

    @Override
    public int getItemCount() {
        return table_names.size();
    }

    public class ViewHolderMain extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button button_Revise;
        Button button_Edit;
        Button button_Delete;
        TextView name_textview;
        RelativeLayout relative_item_layout;



        public ViewHolderMain(@NonNull View itemView ) {
            super(itemView);

            button_Revise = itemView.findViewById(R.id.button_Revise);
            button_Edit = itemView.findViewById(R.id.button_Edit);
            button_Delete = itemView.findViewById(R.id.button_Delete);
            name_textview = itemView.findViewById(R.id.name_textview);
            relative_item_layout = itemView.findViewById(R.id.relative_item_main_layout);


            button_Revise.setOnClickListener(this);
            button_Delete.setOnClickListener(this);
            button_Edit.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            TableHelper tableHelper = new TableHelper();
            RevisionMasterDatabaseHelper helper = new RevisionMasterDatabaseHelper(v.getContext());
            switch (v.getId()){
                case R.id.button_Delete:
                    tableHelper.delete("TABLE_OF_NAMES", table_names.get(this.getLayoutPosition()), context);

                    break;
                case R.id.button_Edit:
                    tableHelper.edit(table_names.get(this.getLayoutPosition()), context);

                    break;
                case R.id.button_Revise:
                    tableHelper.revise(table_names.get(this.getLayoutPosition()), context);

                    break;

                    default:
                        break;


            }


        }
    }
}
