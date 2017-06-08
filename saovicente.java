package com.tcc.exemplo.easymarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class saovicente extends AppCompatActivity {
ListView lista;
    public String nome_mercado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saovicente);
        lista = (ListView) findViewById(R.id.listView5);
        String valor = "SELECT * FROM saovicente;";
        new getProdutos(saovicente.this, lista, valor).execute("");
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter list = (ArrayAdapter<String>) lista.getAdapter();
                String data = "SELECT * FROM saovicente WHERE Nome='" + list.getItem(position).toString() + "'";
                new getPreco(data, saovicente.this).execute("");
                return false;
            }
        });
    }
    public void filia2(View view)

    {
        nome_mercado = "saovicente";
        Intent i = new Intent(saovicente.this,filial.class);
        i.putExtra("nome_mercado", nome_mercado);
        startActivity(i);
    }
}
