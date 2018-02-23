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
                if(sb.toString().equals("true")){
                    GlobalData.getInstance().setUser(user);
                    Intent intent = new Intent(activity.getApplicationContext(), ServerListActivity.class);
                    activity.getApplicationContext().startActivity(intent);
                }else{
                    Log.e("RETURN","FALSE");
                }
                pDialog.dismiss();
                //user.setToken((new JSONObject(sb.toString())).getString("token"));

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
