package com.tcc.exemplo.easymarket;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
public class getLocal extends AsyncTask<String,String,String> {
    Activity ac;
    String sql = "";
    ProgressDialog dialog;

    public getLocal(String sql, Activity ac) {
        this.ac = ac;
        this.sql = sql;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = new ProgressDialog(ac);
        dialog.setTitle("Aguarde...");
        dialog.setMessage("Coletando informações...");
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setIndeterminate(true);
        dialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        String res = "";
        try {
            URL url = new URL("http://easymarket.pe.hu/getLocal.php");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-type", "application/x-www-form-urlencoded");

            DataOutputStream printer = new DataOutputStream(con.getOutputStream());
            printer.writeBytes("sql=" + sql);
            printer.flush();
            printer.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                res += line;
            }
        } catch (Exception e) {
            res += "erro =" + e;
        }
        return res;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        dialog.dismiss();
        //$row["cod"].";".$row["nome"].";".
        // $row["cidade"].";".$row["bairro"].
        // ";".$row["rua"].";".$row["numero"].";".
        // $row["cep"]."&";
        //Toast.makeText(ac',""+s,Toast.LENGTH_SHORT).show();

        try {
            //https://maps.googleapis.com/maps/api/staticmap?center=13178101&markers=13178101&zoom=16&size=640x400&
            String pieces[] = s.split("&");
            final String partes[] = pieces[0].split(";");
            String msg = "Cidade:" + partes[3] + "\n";
            msg += "Rua:" + partes[4] + "\n";
            msg += "Cep:" + partes[5] + "\n";
            ImageView img = new ImageView(ac);
            new LoadImage(ac, img).execute("https://maps.googleapis.com/maps/api/staticmap?center=" + partes[5].replace("-", "") + "&markers=" + partes[5].replace("-", "") + "&zoom=19&size=1280x720&");
            AlertDialog.Builder detalhes = new AlertDialog.Builder(ac);
            detalhes.setTitle(partes[1]);
            detalhes.setView(img);
            detalhes.setMessage(msg);
            detalhes.setIcon(R.mipmap.ic_launcher);
            detalhes.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            detalhes.setPositiveButton("VER NO MAPS", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/?q=" + partes[6]+","+partes[7]));
                    ac.startActivity(i);
                    dialog.dismiss();
                }
            });
            detalhes.show();
        } catch (Exception e) {

        }
    }
}

