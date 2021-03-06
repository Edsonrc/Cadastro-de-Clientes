package com.edsonrczarneski.projetofinal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edson Ricardo Czarneski on 09/12/2017.
 */
public class DBHelper {
    //Declarando os atributos da classe
    private static final String DATABASE_NAME = "clientes.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "pessoas";

    private Context context;
    private SQLiteDatabase db;

    /*Outros atributos necessários para a criação ao suporte para o banco de dados*/

    private SQLiteStatement insertStnt;

    /*Preparando a instrução SQL para que possa ser reaproveitada em qualquer ocasião*/
    private static final String INSERT = "insert into " + TABLE_NAME + " (nome, cpf,idade,telefone,email) values (?,?,?,?,?)";

    /*Criando o consultor DBHelper*/
    public DBHelper(Context context) {
        this.context = context;
        OpenHelper openHelper = new OpenHelper(this.context);
        this.db = openHelper.getWritableDatabase();
        this.insertStnt = this.db.compileStatement(INSERT);
    }

    /*Esse bloco de código será requisitado na Second Activity*/
    public long insert(String nome, String cpf, String idade, String telefone, String email) {
        this.insertStnt.bindString(1, nome);
        this.insertStnt.bindString(2, cpf);
        this.insertStnt.bindString(3, idade);
        this.insertStnt.bindString(4, telefone);
        this.insertStnt.bindString(5, email);

        return this.insertStnt.executeInsert();
    }

    /*Criando uma instrução para apagar os registros*/
    public void deleteAll() {this.db.delete(TABLE_NAME, null, null);
    }

    /*Criando uma instrução para recuperar as informações do banco de dados*/
    /*A instrução retornará os dados em forma de lista*/

    public List<Pessoa> recuperaDados() {

        List<Pessoa> list = new ArrayList<Pessoa>();

        try {
            Cursor cursor = this.db.query(TABLE_NAME, new String[]{"nome", "cpf", "idade", "telefone", "email"},
                    null, null, null, null, null, null);
            int contaRegistros = cursor.getCount();
            if (contaRegistros!=0) {
                cursor.moveToFirst();
                do {
                    Pessoa pessoa = new Pessoa(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                    list.add(pessoa);
                }while(cursor.moveToNext());
               
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                    return list;
                } else {
                    return null;
                }
            }
        }catch (Exception err) {
            return null;
        }
        return list;
    }

    /*Criando innner class*/
    private static class OpenHelper extends SQLiteOpenHelper {

        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        /*Implementando o método void para validar a inner class*/
        public void onCreate(SQLiteDatabase db) {
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (id_recebedados INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, cpf TEXT, idade TEXT, telefone TEXT, email TEXT);";
            db.execSQL(sql);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}
