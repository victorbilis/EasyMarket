package com.tcc.exemplo.easymarket;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Matheus on 10/11/2016.
 */
public class getPreco extends AsyncTask<String,String,String> {
    Context context;
    String data = "";
    String res = "";
    public getPreco(String data,Context context)
    {
        this.context = context;
        this.data = data;
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
        String text = "" + partes[0];
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Preço");
        builder.setMessage(text);
        builder.show();


    }
}

