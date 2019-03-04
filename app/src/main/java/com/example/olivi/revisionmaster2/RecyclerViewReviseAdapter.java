package com.example.olivi.revisionmaster2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewReviseAdapter extends RecyclerView.Adapter<RecyclerViewReviseAdapter.ViewReviseHolder> {
    private ArrayList<String> table_terms = new ArrayList<>();
    private ArrayList<String> table_defs = new ArrayList<>();
    private Context context;
    String tName;

    public RecyclerViewReviseAdapter(ArrayList<String> table_terms, ArrayList<String> table_defs, String tName, Context context){
        this.table_terms = table_terms;
        this.table_defs = table_defs;
        this.context = context;
        this.tName = tName;
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public RecyclerViewReviseAdapter.ViewReviseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_revise_itemlayout, viewGroup, false);
        ViewReviseHolder holderRevise = new ViewReviseHolder(view);
        return holderRevise;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewReviseAdapter.ViewReviseHolder viewReviseHolder, int i) {
        viewReviseHolder.definition.setText(table_defs.get(i));
        viewReviseHolder.term.setText(table_terms.get(i));
    }

    @Override
    public int getItemCount() {
        return table_terms.size();
    }

    public class ViewReviseHolder extends RecyclerView.ViewHolder {
        TextView term;
        TextView definition;
        RelativeLayout relative_item_layout;
        public ViewReviseHolder(@NonNull View itemView) {
            super(itemView);

            term = itemView.findViewById(R.id.term_text);
            definition = itemView.findViewById(R.id.definition_text);
            relative_item_layout = itemView.findViewById(R.id.relative_item_revise_layout);
        }
    }
}
