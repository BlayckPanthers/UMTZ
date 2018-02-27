package com.ingesup.docblayck.umtz.Tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.ingesup.docblayck.umtz.Entities.Infrastructure;
import com.ingesup.docblayck.umtz.Entities.Service;
import com.ingesup.docblayck.umtz.Entities.User;
import com.ingesup.docblayck.umtz.Global.GlobalData;
import com.ingesup.docblayck.umtz.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;


/**
 * Created by Najib on 16/02/2018.
 */

public class AsyncTaskCheckService extends AsyncTask<String,String,List<Service>> {


    private ProgressDialog pDialog ;
    private Activity activity ;
    private User user ;

    public AsyncTaskCheckService(Activity activity){

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
    protected List<Service> doInBackground(String... strings) {
        try {
            List<Service> listServices=new ArrayList<>();
            URL url = new URL(strings[0]); // here is your URL path

            JSONObject postDataParams = new JSONObject();
            postDataParams.put("login", GlobalData.getInstance().getUser().getEmail());
            postDataParams.put("idHost", strings[1]);
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
                    int host_id=Integer.parseInt(list.getJSONObject(i).getString("host_id"));
                    String description= list.getJSONObject(i).getString("description");
                    int service_id=Integer.parseInt(list.getJSONObject(i).getString("service_id"));
                    int state=Integer.parseInt(list.getJSONObject(i).getString("state"));
                    String output= list.getJSONObject(i).getString("output");
                    double last_ckeck=Double.parseDouble( list.getJSONObject(i).getString("last_check"));
                    double last_state_change=Double.parseDouble( list.getJSONObject(i).getString("last_state_change"));
                    Service service = new Service(host_id,description,service_id,state,output,last_ckeck,last_state_change);
                    listServices.add(service);
                }
                in.close();
                pDialog.dismiss();
                return listServices;
            } else {
                user = null;
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}