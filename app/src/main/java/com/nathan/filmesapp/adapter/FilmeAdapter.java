package com.nathan.filmesapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nathan.filmesapp.R;
import com.nathan.filmesapp.model.Filme;

import java.util.ArrayList;


public class FilmeAdapter extends RecyclerView.Adapter<FilmeAdapter.FilmeViewHolder> {

    private Context mContext;
    private ArrayList<Filme> listaFilmes;
    private OnItemClickListerner onItemClickListerner;

    public void setOnItemClickListerner(OnItemClickListerner onItemClickListerner) {
        this.onItemClickListerner = onItemClickListerner;
    }

    public FilmeAdapter(Context mContext, ArrayList<Filme> listaFilmes) {
        this.mContext = mContext;
        this.listaFilmes = listaFilmes;
    }

    @NonNull
    @Override
    public FilmeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_filme,parent,false);
        GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams)itemView.getLayoutParams();
        lp.height = parent.getMeasuredHeight() / 2;
        itemView.setLayoutParams(lp);
        //int height = parent.getMeasuredHeight() / 4;
        //itemView.setMinimumHeight(200);

        return new FilmeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmeViewHolder holder, int position) {
        Filme filme = listaFilmes.get(position);
        holder.nomeFilme.setText(filme.getNome());
        holder.posterFilme.setImageResource(filme.getDrawableId());
    }

    @Override
    public int getItemCount() {
        return listaFilmes.size();
    }

    public class FilmeViewHolder extends RecyclerView.ViewHolder{
        ImageView posterFilme;
        TextView nomeFilme;


        public FilmeViewHolder(@NonNull View itemView) {
            super(itemView);
            posterFilme = itemView.findViewById(R.id.posterFilme);
            nomeFilme = itemView.findViewById(R.id.nomeFilme);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onItemClickListerner!=null){
                        int position  = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            onItemClickListerner.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
    public interface OnItemClickListerner{
        void onItemClick(int position);
    }


}
