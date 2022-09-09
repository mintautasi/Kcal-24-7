package com.example.kursinis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable {
    Context context;
    ArrayList<produktas> listFull;
    ArrayList<produktas> list;

    public MyAdapter(Context context, ArrayList<produktas> list) {
        this.context = context;
        this.listFull = list;
        this.list = new ArrayList<>(listFull);

    }


    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardholder, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        produktas produktas = list.get(position);
        holder.name.setText(produktas.getMaisto_prod());
        holder.desc1.setText(produktas.getKalorijos() + "   g/100g");
        holder.desc2.setText(produktas.getAngliavandeniai() + "   g/100g");
        holder.desc3.setText(produktas.getBaltymai() + "   g/100g");
        holder.desc4.setText(produktas.getRiebalai() + "   g/100g");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return produktasFilter;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, desc1, desc2, desc3, desc4;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.prod_name);
            desc1 = itemView.findViewById(R.id.prod_desc1);
            desc2 = itemView.findViewById(R.id.prod_desc2);
            desc3 = itemView.findViewById(R.id.prod_desc3);
            desc4 = itemView.findViewById(R.id.prod_desc4);
        }

    }

    private final Filter produktasFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            ArrayList<produktas> filteredProduktasList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {

                filteredProduktasList.addAll(listFull);

            } else {

                String filterPattern = constraint.toString().toLowerCase().trim();
                for (produktas produktas : listFull) {

                    if (produktas.Maisto_prod.toLowerCase().contains(filterPattern))
                        filteredProduktasList.add(produktas);

                }

            }

            FilterResults results = new FilterResults();
            results.values = filteredProduktasList;
            results.count = filteredProduktasList.size();
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            list.clear();
            list.addAll((ArrayList) results.values);
            notifyDataSetChanged();

        }
    };

}




