package com.ingesup.docblayck.umtz;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.ingesup.docblayck.umtz.Entities.Infrastructure;
import com.ingesup.docblayck.umtz.Global.GlobalData;

import java.util.ArrayList;
import java.util.List;

public class ServerListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infrastructure_layout);

        // ListView affichant la liste des serveurs
        ListView mListView = (ListView) findViewById(R.id.serverList);

        // Liste contenant les serveurs générés.
        List<Infrastructure> myInfras = genererInfra();

        InfrastructureAdapter adapter = new InfrastructureAdapter(ServerListActivity.this, myInfras);

        mListView.setAdapter(adapter);
        Toast.makeText(getApplicationContext(), GlobalData.getInstance().getUser().getEmail(),Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),"Vous étes deja conncté",Toast.LENGTH_SHORT).show();
    }

    // TODO: Fonction de recuperation de la liste des serveurs en GET via Centreon
    // Actuellement la liste est générée en dur. vert : Color.rgb(34,139,34) ; rouge :
    private List<Infrastructure> genererInfra(){
        List<Infrastructure> myList = new ArrayList<Infrastructure>();

        myList.add(new Infrastructure("ns2307123.ovh.net","13.55.69.192",R.drawable.ic_green_button,0,0,0));
        myList.add(new Infrastructure("ns2307456.ovh.net","13.55.69.193",R.drawable.ic_green_button,0,0,0));
        myList.add(new Infrastructure("ns2307789.ovh.net","13.55.69.194",R.drawable.ic_green_button,0,0,0));
        myList.add(new Infrastructure("ns2307112.ovh.net","13.55.69.191",R.drawable.ic_red_button,0,0,0));

        return myList;
    }
}
