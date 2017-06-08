package com.tcc.exemplo.easymarket;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class editar_prod2 extends AppCompatActivity {
    public String nome_mercado;
    public String nome_produto;
    public String cod_produto;
    EditText nome;
    EditText preco;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_prod2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nome = (EditText) findViewById(R.id.editText5);
        nome_mercado = getIntent().getStringExtra("nome_mercado");
        nome_produto = getIntent().getStringExtra("nome_produto");
        preco = (EditText) findViewById(R.id.editText6);
        nome.setText(""+nome_produto);
        String data = "SELECT * FROM "+nome_mercado+" WHERE Nome='"+nome_produto+"';";
        new getPreco2(data,preco,editar_prod2.this).execute("");
        new getCod(data,editar_prod2.this,cod_produto).execute("");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = "DELETE FROM "+nome_mercado+" WHERE Nome='"+nome_produto+"';";
                new send2(data,editar_prod2.this).execute("");

                String data2 = "INSERT INTO "+nome_mercado+" VALUES("+cod_produto+",'"+nome.getText()+"','"+preco.getText()+"');";
                new send(data2,editar_prod2.this).execute("");

            }
        });
    }

}
