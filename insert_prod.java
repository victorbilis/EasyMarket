package com.tcc.exemplo.easymarket;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class insert_prod extends AppCompatActivity {
    public String nome_mercado;
    EditText nome;
    EditText preco;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_prod);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nome_mercado = getIntent().getStringExtra("nome_mercado");
        nome = (EditText) findViewById(R.id.editText);
        preco = (EditText) findViewById(R.id.editText2);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = "INSERT INTO "+nome_mercado+" VALUES(null,'"+nome.getText()+"','"+preco.getText()+"');";
                new send2(data,insert_prod.this).execute("");
                String data2 = "INSERT INTO tudo VALUES('"+nome.getText()+"');";
                new send(data2,insert_prod.this).execute("");
            }
        });
    }

}
