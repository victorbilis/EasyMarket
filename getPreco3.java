package com.tcc.exemplo.easymarket;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

/**
 * Created by Matheus on 10/11/2016.
 */
public class getPreco3 extends AsyncTask<String,String,String> {
    Context context;
    String data = "";
    String res = "";
    TextView text;
    public getPreco3(String data, TextView text, Context context)
    {
        this.context = context;
        this.data = data;
        this.text = text;
    }
    @Override
    protected String doInBackground(String... strings) {
        try{
            URL url = new URL("http://easymarket.pe.hu/listPreco.php");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-type","application/x-www-form-urlencoded");

            DataOutputStream printer = new DataOutputStream(con.getOutputStream());
            printer.writeBytes("sql="+data);
            printer.flush();
            printer.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
            String line = "";

            while((line=reader.readLine()) != null)
            {
                res=line;
            }
        }
        catch(Exception e)
        {
            res = ""+e;
        }
        return res;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        String partes[] = s.split("&");
        if(partes[0].equals(""))
        {
            AlertDialog.Builder detalhes = new AlertDialog.Builder(context);
            detalhes.setTitle("Alerta");
            detalhes.setMessage("Esse produto não existe em todos os mercados");
            detalhes.setIcon(R.drawable.carrinho);
            detalhes.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }
        else {
            double novo = Double.parseDouble(partes[0].toString());
            double antigo = Double.parseDouble(text.getText().toString());
            double res = antigo + novo;
            DecimalFormat df = new DecimalFormat("#.00");
            String res2 = df.format(res);
            text.setText("" + res2);
        }

    }
}

