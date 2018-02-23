package com.ingesup.docblayck.umtz.Tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.ingesup.docblayck.umtz.Entities.Infrastructure;
import com.ingesup.docblayck.umtz.Entities.User;
import com.ingesup.docblayck.umtz.Global.GlobalData;
import com.ingesup.docblayck.umtz.R;
import com.ingesup.docblayck.umtz.ServerListActivity;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;


/**
 * Created by Najib on 16/02/2018.
 */

public class AsyncTaskServers extends AsyncTask<String,String,List<Infrastructure>> {


    private ProgressDialog pDialog ;
    private Activity activity ;
    private User user ;

    public AsyncTaskServers(Activity activity){

        this.activity = activity ;
        this.user = GlobalData.getInstance().getUser();

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(activity);
        pDialog.setMessage(activity.getResources().getString(R.string.txt_Show));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    protected List<Infrastructure> doInBackground(String... strings) {
        try {
            List<Infrastructure> listServers=new ArrayList<>();
            URL url = new URL(strings[0]); // here is your URL path

            JSONObject postDataParams = new JSONObject();
            postDataParams.put("login", GlobalData.getInstance().getUser().getEmail());
            Log.e("params", postDataParams.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST"); // here you are telling that it is a POST request, which can be changed into "PUT", "GET", "DELETE" etc.
            conn.setRequestProperty("Content-Type", "application/json"); // here you are setting the `Content-Type` for the data you are sending which is `application/json`
            conn.connect();

            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(postDataParams.toString());
            wr.flush();
            wr.close();

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {

                BufferedReader in = new BufferedReader(new
                        InputStreamReader(
                        conn.getInputStream()));

                StringBuffer sb = new StringBuffer("");
                String line = "";

                while ((line = in.readLine()) != null) {

                    sb.append(line);
                    break;
                }
                Log.e("SERVER",sb.toString());
                JSONArray list = new JSONArray(sb.toString());
                for(int i = 0 ; i < list.length() ; i++){

                    String name= list.getJSONObject(i).getString("name");
                    String adress=list.getJSONObject(i).getString("adress");
                    int statusHost=Integer.parseInt(list.getJSONObject(i).getString("statusHost"));
                    int nbOk=Integer.parseInt(list.getJSONObject(i).getString("nbOk"));
                    int nbWarning=Integer.parseInt( list.getJSONObject(i).getString("nbWarning"));
                    int nbCritical=Integer.parseInt(list.getJSONObject(i).getString("nbCritical"));
                    int nbUnknown=Integer.parseInt(list.getJSONObject(i).getString("nbUnknown"));
                    Infrastructure server = new Infrastructure(name,adress,statusHost,nbOk,nbWarning,nbCritical,nbUnknown);
                    listServers.add(server);
                }
                in.close();
                pDialog.dismiss();
                return listServers;
            } else {
                user = null;
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}