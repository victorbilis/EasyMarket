package com.tcc.exemplo.easymarket;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ADM extends AppCompatActivity {
    public String nome_mercado;
    EditText login;
    EditText senha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nome_mercado = getIntent().getStringExtra("nome_mercado");
        ImageView img  = (ImageView) findViewById(R.id.imageView);
        login = (EditText) findViewById(R.id.editText4);
        senha = (EditText) findViewById(R.id.editText3);

        if(nome_mercado.equals("paguemenos"))
        {
            img.setImageResource(R.drawable.paguemenos);
        }
        else if(nome_mercado.equals("goodbom"))
        {
            img.setImageResource(R.drawable.good);
        }
        else if(nome_mercado.equals("saovicente"))
        {
            img.setImageResource(R.drawable.sao);

        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(login.getText().toString().equalsIgnoreCase("123") && senha.getText().toString().equalsIgnoreCase("123")) {

                            Intent i = new Intent(ADM.this, adm2.class);
                            i.putExtra("nome_mercado", nome_mercado);
                            startActivity(i);

                        } else {
                            Toast.makeText(ADM.this, "Senha ou login incorreto!", Toast.LENGTH_SHORT).show();
                        }

            }
        });
        }
    }


