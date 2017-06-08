package com.tcc.exemplo.easymarket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class filial extends AppCompatActivity {
    public String nome_mercado;
    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filial);
        nome_mercado = getIntent().getStringExtra("nome_mercado");
        lista = (ListView) findViewById(R.id.listView6);
        String data = "SELECT * FROM filiais WHERE nome_mercado='"+nome_mercado+"';";
        new getFiliais(filial.this, lista, data).execute("");
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter list = (ArrayAdapter<String>) lista.getAdapter();
                String data = "SELECT * FROM filiais WHERE nome_filial='"+  list.getItem(position).toString() +"' AND nome_mercado='"+nome_mercado+"';";
                new getLocal(data, filial.this).execute("");
                return false;
            }
        });
    }
}
