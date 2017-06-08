package com.tcc.exemplo.easymarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class editar_prod extends AppCompatActivity {
    public String nome_mercado;
    public String nome_produto;
    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_prod);
        lista = (ListView) findViewById(R.id.listView);
        nome_mercado = getIntent().getStringExtra("nome_mercado");
        String valor = "SELECT * FROM "+nome_mercado+" ORDER BY Nome ASC;";
        new getProdutos(editar_prod.this, lista, valor).execute("");
       lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               ArrayAdapter list = (ArrayAdapter<String>) lista.getAdapter();
               nome_produto = list.getItem(position).toString();
               Intent i = new Intent(editar_prod.this,editar_prod2.class);
               i.putExtra("nome_mercado", nome_mercado);
               i.putExtra("nome_produto", nome_produto);
               startActivity(i);
               return false;
           }
       });
    }
}
