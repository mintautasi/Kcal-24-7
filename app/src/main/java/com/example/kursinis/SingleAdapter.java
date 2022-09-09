package com.example.kursinis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kursinis.MyAdapterSmall;
import com.example.kursinis.R;
import com.example.kursinis.produktas;

import java.util.ArrayList;

public class SingleAdapter extends RecyclerView.Adapter<SingleAdapter.SingleViewHolder> {


    private Context contextSmall;
    private ArrayList<Item> items;
    private int checkedPosition = 0;

    public SingleAdapter(Context contextSmall, ArrayList<Item> items) {
        this.contextSmall = contextSmall;
        this.items = items;

    }

    public void SetItems(ArrayList<Item> items){
        this.items=items;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public SingleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(contextSmall).inflate(R.layout.single_item,parent,false);
        return new SingleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SingleViewHolder holder, int position) {

        holder.bind(items.get(position));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class SingleViewHolder extends RecyclerView.ViewHolder{
        private TextView single_small, single_kal, single_angl, single_bal, single_rieb;
        private ImageView imageView;




        public SingleViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.tick);
            single_small = itemView.findViewById(R.id.single_small);
            single_kal = itemView.findViewById(R.id.single_kal);
            single_angl = itemView.findViewById(R.id.single_angl);
            single_bal = itemView.findViewById(R.id.single_bal);
            single_rieb = itemView.findViewById(R.id.single_rieb);
        }

        void bind(final Item items) {
        if (checkedPosition == -1) {
            imageView.setVisibility(View.GONE);
        }else{
            if (checkedPosition == getAdapterPosition()){
                imageView.setVisibility(View.VISIBLE);
            }else{
                imageView.setVisibility(View.GONE);
            }
        }
        single_small.setText(items.getMaisto_prod());
        single_kal.setText("Kcal:"+items.getKalorijos()+ "   g/100g");
        single_angl.setText("Angl:"+items.getAngliavandeniai()+ "   g/100g");
        single_bal.setText("Balt:"+items.getBaltymai()+ "   g/100g");
        single_rieb.setText("Rieb:"+items.getRiebalai()+ "   g/100g");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setVisibility(View.VISIBLE);
               if (checkedPosition != getAdapterPosition()){
                   notifyItemChanged(checkedPosition);
                  checkedPosition =getAdapterPosition();


            }

            }
        });
        }
    }
    public Item getSelected(){
        if (checkedPosition != -1){
            return  items.get(checkedPosition);
        }
        return null;
    }
}
