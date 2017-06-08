package com.tcc.exemplo.easymarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class adm2 extends AppCompatActivity {
    public String nome_mercado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm2);
        nome_mercado = getIntent().getStringExtra("nome_mercado");
        ImageView img = (ImageView) findViewById(R.id.imageView2);
        if(nome_mercado.equals("paguemenos"))
        {
            img.setImageResource(R.drawable.paguemenos);

        }
        else if(nome_mercado.equals("goodbom"))
        {
            img.setImageResource(R.drawable.good);
        }
        else if(nome_mercado.equals("saovicente")) {
            img.setImageResource(R.drawable.sao);
        }
    }
    public void inserir(View view)
    {
        Intent i = new Intent(adm2.this,insert_prod.class);
        i.putExtra("nome_mercado", nome_mercado);
        startActivity(i);
    }
    public void deletar(View view)
    {
        Intent i = new Intent(adm2.this,deletar_prod.class);
        i.putExtra("nome_mercado", nome_mercado);
        startActivity(i);
    }
    public void editar(View view)
    {
        Intent i = new Intent(adm2.this,editar_prod.class);
        i.putExtra("nome_mercado", nome_mercado);
        startActivity(i);
    }
}
