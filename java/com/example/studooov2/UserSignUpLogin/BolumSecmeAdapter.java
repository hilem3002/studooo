package com.example.studooov2.UserSignUpLogin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studooov2.R;
import com.example.studooov2.UserSignUpLogin.Models.Bolum;

import java.util.ArrayList;

public class BolumSecmeAdapter extends RecyclerView.Adapter<BolumSecmeAdapter.BolumSecmeViewObjectHolder> {

    private Context context;
    private ArrayList<Bolum> bolums;

    private BolumSecmeRecyclerviewInterface bolumSecmeRecyclerviewInterface;

    public void setFilteredList(ArrayList<Bolum> bolums){
        this.bolums = bolums;
        notifyDataSetChanged();
    }

    public BolumSecmeAdapter(Context context, ArrayList<Bolum> bolums, BolumSecmeRecyclerviewInterface bolumSecmeRecyclerviewInterface) {
        this.context = context;
        this.bolums = bolums;
        this.bolumSecmeRecyclerviewInterface = bolumSecmeRecyclerviewInterface;
    }

    @NonNull
    @Override
    public BolumSecmeViewObjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bolum_secme_card_design, parent, false);
        return new BolumSecmeViewObjectHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BolumSecmeViewObjectHolder holder, int position) {
        holder.textViewBolumIsmi.setText(bolums.get(position).getBolumName());
    }

    @Override
    public int getItemCount() {
        return bolums.size();
    }

    public class BolumSecmeViewObjectHolder extends RecyclerView.ViewHolder {

        private TextView textViewBolumIsmi;


        public BolumSecmeViewObjectHolder(@NonNull View itemView) {
            super(itemView);
            textViewBolumIsmi = itemView.findViewById(R.id.textViewBolumIsmi);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(bolumSecmeRecyclerviewInterface != null){
                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION){
                            bolumSecmeRecyclerviewInterface.onItemClick(position);
                        }
                    }
                }
            });

        }
    }



}
