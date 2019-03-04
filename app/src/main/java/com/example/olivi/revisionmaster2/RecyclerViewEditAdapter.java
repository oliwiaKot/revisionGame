package com.example.olivi.revisionmaster2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewEditAdapter extends RecyclerView.Adapter<RecyclerViewEditAdapter.ViewHolderEdit> {

    private ArrayList<String> table_terms = new ArrayList<>();
    private ArrayList<String> table_defs = new ArrayList<>();
    private Context context;
    String tName;

    public RecyclerViewEditAdapter(ArrayList<String> table_terms, ArrayList<String> table_defs, String tName, Context context){
        this.table_terms = table_terms;
        this.table_defs = table_defs;
        this.context = context;
        this.tName = tName;
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public RecyclerViewEditAdapter.ViewHolderEdit onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_edit_itemlayout, viewGroup, false);
        ViewHolderEdit holderAdd = new ViewHolderEdit(view);
        return holderAdd;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewEditAdapter.ViewHolderEdit viewHolderEdit, int i) {
        viewHolderEdit.definition.setText(table_defs.get(i));
        viewHolderEdit.term.setText(table_terms.get(i));

        viewHolderEdit.button_Delete.setText("Delete");
    }

    @Override
    public int getItemCount() {
        return table_terms.size();
    }

    public class ViewHolderEdit extends RecyclerView.ViewHolder implements View.OnClickListener{
        Button button_Delete;
        TextView term;
        TextView definition;
        RelativeLayout relative_item_layout;
        public ViewHolderEdit(@NonNull View itemView) {
            super(itemView);
            button_Delete = itemView.findViewById(R.id.button_deleteRow);

            term = itemView.findViewById(R.id.term_text);
            definition = itemView.findViewById(R.id.definition_text);
            relative_item_layout = itemView.findViewById(R.id.relative_item_edit_layout);
            button_Delete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.button_deleteRow){
                TableHelper helper = new TableHelper();
                helper.delete(tName, table_terms.get(this.getLayoutPosition()), context );

            }
        }
    }
}
