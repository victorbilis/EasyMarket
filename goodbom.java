package com.tcc.exemplo.easymarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class goodbom extends AppCompatActivity {
ListView lista;
    public String nome_mercado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodbom);
        lista = (ListView) findViewById(R.id.listView4);
        String valor = "SELECT * FROM goodbom;";
        new getProdutos(goodbom.this, lista, valor).execute("");
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter list = (ArrayAdapter<String>) lista.getAdapter();
                String data = "SELECT * FROM goodbom WHERE Nome='" + list.getItem(position).toString() + "'";
                new getPreco(data, goodbom.this).execute("");
                return false;
            }
        });
    }
    public void filial3(View view)
    {
        nome_mercado = "goodbom";
        Intent i = new Intent(goodbom.this,filial.class);
        i.putExtra("nome_mercado", nome_mercado);
        startActivity(i);
    }
}

