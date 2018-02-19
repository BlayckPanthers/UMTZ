package com.ingesup.docblayck.umtz;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class ServerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);

        Infrastructure serverParcelable = (Infrastructure) getIntent().getParcelableExtra("server_parcelable_extra");


        TextView serverName = (TextView) findViewById(R.id.infrastructure_server_name_detail);
        TextView serverIP = (TextView) findViewById(R.id.infrastructure_server_ip_detail);

        serverName.setText(serverParcelable.getServer_name());
        serverIP.setText(serverParcelable.getServer_ip());

        Toast.makeText(ServerActivity.this, serverParcelable.getServer_ip()
                        + " " + serverParcelable.getServer_name()
                , Toast.LENGTH_SHORT).show();
    }
}
