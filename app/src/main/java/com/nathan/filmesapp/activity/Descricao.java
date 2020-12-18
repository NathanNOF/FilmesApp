package com.nathan.filmesapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nathan.filmesapp.R;
import com.nathan.filmesapp.model.Filme;

public class Descricao extends AppCompatActivity {
    Button btnAssistir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao);
        btnAssistir = findViewById(R.id.btnAssistir);
        TextView textAno = (TextView)findViewById(R.id.textAno);
        TextView textGenero = (TextView)findViewById(R.id.textGenero);
        TextView textDescricao = (TextView)findViewById(R.id.textDescricao);
        ImageView imageDescricao = (ImageView)findViewById(R.id.imageDescricao);

        Filme filme =(Filme) getIntent().getExtras().getSerializable("filme");
        textAno.setText(filme.getAno()+"");
        textDescricao.setText(filme.getDescricao());
        textGenero.setText(filme.getGenero());
        Glide.with(getApplicationContext()).load(filme.getPoster()).into(imageDescricao);




        btnAssistir.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               Intent t = new Intent(getApplicationContext(), PlayerActivity.class);
                                               startActivity(t);
                                           }
                                       }
        );


    }
}