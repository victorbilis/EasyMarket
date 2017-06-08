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
public class getFiliais extends AsyncTask<String,String,String> {
    Context context;
    ListView lista;
    String data = "";
    String res = "";
    public getFiliais(Context context, ListView lista, String data)
    {
        this.context = context;
        this.lista = lista;
        this.data = data;
    }

    @Override
    protected String doInBackground(String... string) {
        try {
            URL url = new URL("http://easymarket.pe.hu/listFiliais.php");
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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,partes);
        lista.setAdapter(adapter);
    }
}

