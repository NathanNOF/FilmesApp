package com.nathan.filmesapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


import com.nathan.filmesapp.R;
import com.nathan.filmesapp.adapter.FilmeAdapter;
import com.nathan.filmesapp.model.Filme;

import java.util.ArrayList;

public class FilmeActivity extends AppCompatActivity {

    private RecyclerView rvFilme;
    private FilmeAdapter adapter;
    private Toolbar toolbar;
    private ArrayList<Filme> listaFilmes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filme);

        getData();

        rvFilme = findViewById(R.id.filme_rv);
        adapter = new FilmeAdapter(getApplicationContext(),listaFilmes);

        toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        adapter.setOnItemClickListerner(new FilmeAdapter.OnItemClickListerner() {
            @Override
            public void onItemClick(int position) {
                Filme filme = listaFilmes.get(position);

                Toast.makeText(getApplicationContext(),filme.getNome(),Toast.LENGTH_SHORT).show();
                ///ABRIR ACtivity de dewscricao
                //ATRAVES INTENTS
                Intent descricaoIntent = new Intent(getApplicationContext(), Descricao.class);
                descricaoIntent.putExtra("filme",filme);
                startActivity(descricaoIntent);
            }
        });


        rvFilme.setLayoutManager(new GridLayoutManager(this, 2));

        rvFilme.setAdapter(adapter);


    }

    public void getData(){
        Filme joker = new Filme("1",R.drawable.poster_joker,"Joker", "Baseado no personagem de mesmo nome da DC Comics, o filme é estrelado por Joaquin Phoenix como o Coringa. Joker é ambientado em 1981, e representa Arthur Fleck, um comediante de stand-up fracassado, que é levado à loucura e se envolve em uma vida de crimes e caos em Gotham City.", 2019, "Suspense");
        joker.setPoster("https://wallpapercave.com/wp/wp4749174.jpg");
        Filme luta=   new Filme("2",R.drawable.poster_luta,"Luta", "Um homem deprimido que sofre de insônia conhece um estranho vendedor chamado Tyler Durden e se vê morando em uma casa suja depois que seu perfeito apartamento é destruído. A dupla forma um clube com regras rígidas onde homens lutam. A parceria perfeita é comprometida quando uma mulher, Marla, atrai a atenção de Tyler.", 1999, "Ação");
        luta.setPoster("https://i.pinimg.com/originals/42/1e/d7/421ed71739a4839a93eaaee0cb5c5e9e.jpg");
        listaFilmes.add(joker);
        listaFilmes.add(luta);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuAdicionar:

                Intent i = new Intent(this,UploadActivity.class);
                startActivity(i);


                break;

            case R.id.menuSair:
                finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
