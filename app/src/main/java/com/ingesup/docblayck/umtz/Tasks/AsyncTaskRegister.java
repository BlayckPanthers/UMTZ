package com.ingesup.docblayck.umtz.Tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.ingesup.docblayck.umtz.Entities.User;
import com.ingesup.docblayck.umtz.R;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.Iterator;

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
        params.add("email",user.getEmail());
        params.add("password",user.getPassword());
        params.add("userCentreon",user.getUserCentreon());
        params.add("passwordCentreon",user.getPasswordCentreon());
        params.add("ipCentreon",user.getIpCentreon());


        return null;
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
