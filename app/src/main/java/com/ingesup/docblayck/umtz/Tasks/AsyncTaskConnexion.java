package com.ingesup.docblayck.umtz.Tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.ingesup.docblayck.umtz.Entities.User;
import com.ingesup.docblayck.umtz.Global.GlobalData;
import com.ingesup.docblayck.umtz.R;
import com.ingesup.docblayck.umtz.ServerActivity;
import com.ingesup.docblayck.umtz.ServerListActivity;
import com.loopj.android.http.RequestParams;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;


/**
 * Created by Najib on 16/02/2018.
 */

public class AsyncTaskConnexion extends AsyncTask<String,String,String> {


    private ProgressDialog pDialog;
    private Activity activity;
    private User user;
    private Boolean isSignIn = false;

    public AsyncTaskConnexion(Activity activity, User user, Boolean isSignIn) {

        this.activity = activity;
        this.user = user;
        this.isSignIn = isSignIn;

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

        try {

            URL url = new URL(strings[0]); // here is your URL path

            JSONObject postDataParams = new JSONObject();
            postDataParams.put("login", user.getEmail());
            postDataParams.put("password", user.getPassword());
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
                user.setToken((new JSONObject(sb.toString())).getString("token"));
                GlobalData.getInstance().setUser(user);
                pDialog.dismiss();
                Intent intent = new Intent(activity.getApplicationContext(), ServerListActivity.class);
                activity.getApplicationContext().startActivity(intent);
                in.close();
                return sb.toString();
            } else {
                user = null;
                return new String("false : " + responseCode);
            }
        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }

    }
}