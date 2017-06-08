package com.tcc.exemplo.easymarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class paguemenos extends AppCompatActivity {
ListView lista;
    public String nome_mercado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paguemenos);
        lista = (ListView) findViewById(R.id.listView3);
        String valor = "SELECT * FROM paguemenos;";
        new getProdutos(paguemenos.this, lista, valor).execute("");
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter list = (ArrayAdapter<String>) lista.getAdapter();
                String data = "SELECT * FROM paguemenos WHERE Nome='" + list.getItem(position).toString() + "'";
                new getPreco(data, paguemenos.this).execute("");
                return false;
            }
        });
    }
    public void filial(View view)
    {
        nome_mercado = "paguemenos";
        Intent i = new Intent(paguemenos.this,filial.class);
        i.putExtra("nome_mercado", nome_mercado);
        startActivity(i);
    }
}
