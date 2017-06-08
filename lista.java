package com.tcc.exemplo.easymarket;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class lista extends AppCompatActivity {
    ListView lista;
    TextView pague,sao,good;
    String selecionado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        lista = (ListView) findViewById(R.id.listView7);
        pague = (TextView) findViewById(R.id.textView23);
        sao = (TextView) findViewById(R.id.textView26);
        good = (TextView) findViewById(R.id.textView28);
        String valor = "SELECT * FROM tudo;";
        new getTudo(lista.this, lista, valor).execute("");
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter list = (ArrayAdapter<String>) lista.getAdapter();
                selecionado = list.getItem(position).toString();
                AlertDialog.Builder dialog = new AlertDialog.Builder(lista.this);
                dialog.setIcon(R.drawable.carrinho);
                dialog.setTitle("Carrinho:");
                dialog.setSingleChoiceItems(new String[]{"Adicionar", "Remover"}, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            String data = "SELECT * FROM paguemenos WHERE Nome='"+ selecionado +"'";
                            new getPreco3(data,pague,lista.this).execute("");
                            String data2 = "SELECT * FROM saovicente WHERE Nome='"+ selecionado +"'";
                            new getPreco3(data2,sao,lista.this).execute("");
                            String data3 = "SELECT * FROM goodbom WHERE Nome='"+ selecionado +"'";
                            new getPreco3(data3,good,lista.this).execute("");
                        } else if (which == 1) {
                            String data = "SELECT * FROM paguemenos WHERE Nome='"+ selecionado +"'";
                            new getPreco4(data,pague,lista.this).execute("");
                            String data2 = "SELECT * FROM saovicente WHERE Nome='"+ selecionado +"'";
                            new getPreco4(data2,sao,lista.this).execute("");
                            String data3 = "SELECT * FROM goodbom WHERE Nome='"+ selecionado +"'";
                            new getPreco4(data3,good,lista.this).execute("");
                        }
                        dialog.dismiss();
                    }
                });
                dialog.show();
                return false;
            }
        });
    }
}
