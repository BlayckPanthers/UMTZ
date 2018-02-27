package com.ingesup.docblayck.umtz;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ingesup.docblayck.umtz.Entities.Infrastructure;
import com.ingesup.docblayck.umtz.Entities.Service;
import com.ingesup.docblayck.umtz.Global.GlobalData;
import com.ingesup.docblayck.umtz.Tasks.AsyncTaskCheckService;
import com.ingesup.docblayck.umtz.Tasks.AsyncTaskServers;

import java.util.List;

public class ServerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.server_layout);
        Infrastructure infrastructure = (Infrastructure) getIntent().getParcelableExtra("server_parcelable_extra");
        // ListView affichant la liste des serveurs
        ListView mListView = (ListView) findViewById(R.id.serviceList);


        //afficher les info du serveur
        TextView server_name_detail=(TextView)findViewById(R.id.server_name_detail);
        TextView server_ip_detail=(TextView)findViewById(R.id.server_ip_detail);
        Button button_server_status_detail=(Button) findViewById(R.id.button_server_status_detail);

        server_name_detail.setText(infrastructure.getServer_name());
        server_ip_detail.setText(infrastructure.getServer_ip());
        button_server_status_detail.setText(String.valueOf(infrastructure.getServer_status()).equals("1")?"up":"Down");


        // Liste contenant les serveurs générés.
        List<Service> myServices;
        try{
            myServices = new AsyncTaskCheckService(this).execute("http://174.138.7.116:8080/CWS/api/checkService",String.valueOf(infrastructure.getServer_id())).get();
            ServiceAdapter adapter = new ServiceAdapter(ServerActivity.this, myServices);

            mListView.setAdapter(adapter);
            Toast.makeText(getApplicationContext(), GlobalData.getInstance().getUser().getEmail(),Toast.LENGTH_SHORT).show();
        }catch (Exception e){

        }

    }
}
