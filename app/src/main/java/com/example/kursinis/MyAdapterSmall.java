package com.example.kursinis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterSmall extends RecyclerView.Adapter<MyAdapterSmall.MyViewHolderSmall> implements Filterable{

    Context contextSmall;
    ArrayList<produktas> listFullSmall;
    ArrayList<produktas> listSmall;


    public MyAdapterSmall(Context context, ArrayList<produktas> list) {
        this.contextSmall = context;
        this.listFullSmall = list;
        this.listSmall = new ArrayList<>(listFullSmall);
    }

    @Override
    public Filter getFilter() {
        return addProduktas;
    }

    @NonNull
    @Override
    public MyAdapterSmall.MyViewHolderSmall onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(contextSmall).inflate(R.layout.cardholder_small,parent,false);

        return new MyViewHolderSmall(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderSmall holder, int position) {
        produktas produktas = listSmall.get(position);
        holder.prod_small.setText(produktas.getMaisto_prod());
        holder.prod_kal.setText(produktas.getKalorijos() + "   g/100g");
        holder.prod_angl.setText(produktas.getAngliavandeniai() + "   g/100g");
        holder.prod_bal.setText(produktas.getBaltymai() + "   g/100g");
        holder.prod_rieb.setText(produktas.getRiebalai() + "   g/100g");
    }

    @Override
    public int getItemCount() {
        return listSmall.size();
    }



    public static class MyViewHolderSmall extends RecyclerView.ViewHolder {
        TextView prod_small, prod_kal, prod_angl, prod_bal, prod_rieb;



        public MyViewHolderSmall(@NonNull View itemView) {
            super(itemView);
            prod_small = itemView.findViewById(R.id.prod_small);
            prod_kal = itemView.findViewById(R.id.prod_kal);
            prod_angl = itemView.findViewById(R.id.prod_angl);
            prod_bal = itemView.findViewById(R.id.prod_bal);
            prod_rieb = itemView.findViewById(R.id.prod_rieb);

        }
    }
    private final Filter addProduktas = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            ArrayList<produktas> filteredAddProduktas = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {

            }else{
                String filterPatternSmall = charSequence.toString().toString().toLowerCase().trim();
                for (produktas produktas : listFullSmall) {
                    if (produktas.Maisto_prod.toLowerCase().contains(filterPatternSmall))
                        filteredAddProduktas.add(produktas);
                }

            }
            FilterResults results = new FilterResults();
            results.values = filteredAddProduktas;
            results.count = filteredAddProduktas.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            listSmall.clear();

            listSmall.addAll((ArrayList) filterResults.values);
            notifyDataSetChanged();

        }
    };

}
