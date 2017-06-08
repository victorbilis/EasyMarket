package com.tcc.exemplo.easymarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class mercados extends AppCompatActivity {
    public String nome_mercado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mercados);
    }
    public void login_paguemenos(View view)
    {
        nome_mercado = "paguemenos";
        Intent i = new Intent(mercados.this,ADM.class);
        i.putExtra("nome_mercado", nome_mercado);
        startActivity(i);
    }
    public void login_goodbom(View view)
    {
        nome_mercado = "goodbom";
        Intent i = new Intent(mercados.this,ADM.class);
        i.putExtra("nome_mercado",nome_mercado);
        startActivity(i);
    }
    public void login_sv(View view)
    {
        nome_mercado = "saovicente";
        Intent i = new Intent(mercados.this,ADM.class);
        i.putExtra("nome_mercado", nome_mercado);
        startActivity(i);
    }
}
