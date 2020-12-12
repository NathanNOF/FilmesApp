package com.nathan.filmesapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.nathan.filmesapp.R;

public class CadastroActivity extends AppCompatActivity {

    private TextInputEditText campoNome, campoEmail, campoSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Toast.makeText(this,"add Branch",Toast.LENGTH_SHORT).show();

        campoNome = findViewById(R.id.inputCadastroNome);
        campoEmail = findViewById(R.id.inputCadastroEmail);
        campoSenha = findViewById(R.id.inputCadastroSenha);

    }
    public void valiadarUsuario(View view){

        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        if(!nome.isEmpty()){

        }else{
            Toast.makeText(getApplicationContext(),"preeencha o nome!", Toast.LENGTH_SHORT).show();
        }
    }
}

