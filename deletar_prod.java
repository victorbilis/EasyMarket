package com.tcc.exemplo.easymarket;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Spinner;

public class deletar_prod extends AppCompatActivity {
    public String nome_mercado;
    Spinner sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletar_prod);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
         sp = (Spinner) findViewById(R.id.spinner);
        nome_mercado = getIntent().getStringExtra("nome_mercado");
        String data = "SELECT * FROM "+nome_mercado+" ORDER BY Nome ASC;";
        new getProdutos2(deletar_prod.this,sp,data).execute("");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = "DELETE FROM "+nome_mercado+" WHERE Nome='"+sp.getSelectedItem().toString()+"';";
                new send2(data,deletar_prod.this).execute("");
                String data2 = "DELETE FROM tudo WHERE nome='"+sp.getSelectedItem().toString()+"';";
                new send(data2,deletar_prod.this).execute("");
            }
        });
    }

}
