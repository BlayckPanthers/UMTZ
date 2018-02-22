package com.ingesup.docblayck.umtz.Tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.ingesup.docblayck.umtz.Entities.User;
import com.ingesup.docblayck.umtz.Global.GlobalData;
import com.ingesup.docblayck.umtz.R;
import com.ingesup.docblayck.umtz.ServerListActivity;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by fabienlebon on 21/02/2018.
 */

public class AsyncTaskRegister  extends AsyncTask<String,String,String> {

    private ProgressDialog pDialog ;
    private Activity activity ;
    private User user ;

    public AsyncTaskRegister(Activity activity, User user) {
        this.activity = activity;
        this.user = user;
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
    protected String doInBackground(String... strings) {

        RequestParams params = new RequestParams();
        params.add("login",user.getEmail());
        params.add("password",user.getPassword());
        params.add("userCentreon",user.getUserCentreon());
        params.add("passwordCentreon",user.getPasswordCentreon());
        params.add("ipCentreon",user.getIpCentreon());

        try {

            URL url = new URL(strings[0]); // here is your URL path

            JSONObject postDataParams = new JSONObject();
            postDataParams.put("login", user.getEmail());
            postDataParams.put("password", user.getPassword());
            postDataParams.put("userCentreon",user.getUserCentreon());
            postDataParams.put("passwordCentreon",user.getPasswordCentreon());
            postDataParams.put("ipCentreon",user.getIpCentreon());
            Log.e("params",postDataParams.toString());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();
            int responseCode=conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {

                BufferedReader in=new BufferedReader(new
                        InputStreamReader(
                        conn.getInputStream()));

                StringBuffer sb = new StringBuffer("");
                String line="";

                while((line = in.readLine()) != null) {

                    sb.append(line);
                    break;
                }
                user.setToken((new JSONObject(sb.toString())).getString("token"));
                GlobalData.getInstance().setUser(user);
                pDialog.dismiss();
                Intent intent = new Intent(activity.getApplicationContext(), ServerListActivity.class);
                activity.getApplicationContext().startActivity(intent);
                in.close();
                return sb.toString();
            }
            else {
                user=null;
                return new String("false : "+responseCode);
            }
        }
        catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }
}
