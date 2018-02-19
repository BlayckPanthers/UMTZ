package com.ingesup.docblayck.umtz;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ingesup.docblayck.umtz.Entities.Infrastructure;

public class ServerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.server_layout);

        Infrastructure serverParcelable = (Infrastructure) getIntent().getParcelableExtra("server_parcelable_extra");


        TextView serverName = (TextView) findViewById(R.id.server_name_detail);
        TextView serverIP = (TextView) findViewById(R.id.server_ip_detail);
        ImageView serverStatus = (ImageView) findViewById(R.id.server_status_detail);

        serverName.setText(serverParcelable.getServer_name());
        serverIP.setText(serverParcelable.getServer_ip());

        Toast.makeText(ServerActivity.this, serverParcelable.getServer_ip()
                        + " " + serverParcelable.getServer_name()
                , Toast.LENGTH_SHORT).show();
    }
}
