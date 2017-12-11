package com.edsonrczarneski.projetofinal;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class SecondActivit extends AppCompatActivity {

    //Declarando os elementos e Classe
    private DBHelper dh;
    Button btVoltar, btInserir, btListar;
    EditText etNome, etCpf, etIdade, etTelefone,etMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //Instanciando DBHelper
        this.dh = new DBHelper(this);

        etNome = (EditText) findViewById(R.id.etNome);
        etCpf = (EditText) findViewById(R.id.etCpf);
        etIdade = (EditText) findViewById(R.id.etIdade);
        etTelefone = (EditText) findViewById(R.id.etTelefone);
        etMail = (EditText) findViewById(R.id.etMail);

        btInserir = (Button) findViewById(R.id.btInserir);
        btListar = (Button) findViewById(R.id.btListar);

        //Criando método para o INSERIR dados no BD
        btInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Verificando se existe algo digitado nos campos
                if (etNome.getText().length()>0 && etCpf.getText().length()>0 && etIdade.getText().length()>0 && etTelefone.getText().length()>0 && etMail.getText().length()>0){
                    dh.insert(etNome.getText().toString(),etCpf.getText().toString(),etIdade.getText().toString(),etTelefone.getText().toString(),etMail.getText().toString());
                    AlertDialog.Builder alerta = new AlertDialog.Builder(SecondActivit.this);
                    alerta.setTitle("Sucesso");
                    alerta.setMessage("Cliente cadastrado com sucesso!");
                    alerta.show();

                    //Limpando os campos após inserção
                    etNome.setText("");
                    etCpf.setText("");
                    etIdade.setText("");
                    etTelefone.setText("");
                    etMail.setText("");

                }else{
                    AlertDialog.Builder alerta = new AlertDialog.Builder(SecondActivit.this);
                    alerta.setTitle("Aviso");
                    alerta.setMessage("Por favor, todos os campos devem ser preenchidos.");
                    alerta.show();

                    //Limpando os campos após inserção
                    etNome.setText("");
                    etCpf.setText("");
                    etIdade.setText("");
                    etTelefone.setText("");
                    etMail.setText("");
                }
            }
        });

        //Listando dados do BD
        btListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Pessoa> pessoa = dh.recuperaDados();
                //Aviso caso nao exista registro no BD
                if (pessoa == null){
                    AlertDialog.Builder alerta = new AlertDialog.Builder(SecondActivit.this);
                    alerta.setTitle("Aviso");
                    alerta.setMessage("Desculpe, mas não temos nenhum registro.");
                    alerta.show();
                    return;
                }
                //Laço que mostrará os registros do BD
                for (int i=0;i<pessoa.size();i++){
                    Pessoa pessoas = (Pessoa) pessoa.get(i);
                    AlertDialog.Builder alerta = new AlertDialog.Builder(SecondActivit.this);
                    alerta.setTitle("Registro" + i);
                    alerta.setMessage("Nome: " + pessoas.getNome()+"\nCPF:"+pessoas.getCpf()+"\nIdade:" + pessoas.getNascimento()+"\nTelefone:" + pessoas.getTelefone() + "\nE-mail:"+ pessoas.getEmail());
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alerta.show();
                }
            }
        });
        //Método para navegar entre Activity
        btVoltar = (Button) findViewById(R.id.btVoltar);
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltaTelaInicial();
            }
        });
    }

    //Método para voltar Tela na Tela de início
    void voltaTelaInicial() {
        Intent intent = new Intent();
        intent.setClass(SecondActivit.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
