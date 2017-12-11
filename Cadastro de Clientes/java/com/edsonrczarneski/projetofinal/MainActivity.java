package com.edsonrczarneski.projetofinal;

//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Edson Ricardo Czarneski
//******************************************************

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//Activity Principal
public class MainActivity extends AppCompatActivity {

    //Declarando os elementos
    Button btEntar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Método para navegar entre Activity
        btEntar = (Button) findViewById(R.id.btEntrar);
        btEntar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamaTelaCadastro();
            }
        });


    }
    //Método para entrar na Tela de Cadastro
    void chamaTelaCadastro(){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, SecondActivit.class);
        startActivity(intent);
        finish();
    }
}
