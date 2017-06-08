package com.tcc.exemplo.easymarket;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Matheus on 10/11/2016.
 */
public class getProdutos2 extends AsyncTask<String,String,String> {
    Context context;
    Spinner sp;
    String data = "";
    String res = "";
    public getProdutos2(Context context,Spinner sp, String data)
    {
        this.context = context;
        this.sp = sp;
        this.data = data;
    }

    @Override
    protected String doInBackground(String... string) {
        try {
            URL url = new URL("http://easymarket.pe.hu/listProdutos.php");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-type", "application/x-www-form-urlencoded");

            DataOutputStream printer = new DataOutputStream(con.getOutputStream());
            printer.writeBytes("sql="+data);
            printer.flush();
            printer.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                res = line;
            }
        } catch (Exception e) {
            res = "" + e;
        }
        return res;
    }
    protected void onPostExecute(String s)
    {

        String partes[] = s.split("&");
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_dropdown_item,partes);
        sp.setAdapter(adapter2);
    }
}

