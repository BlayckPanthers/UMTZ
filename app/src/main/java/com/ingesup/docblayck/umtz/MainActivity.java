package com.ingesup.docblayck.umtz;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infrastructure_layout);

        // ListView affichant la liste des serveurs
        ListView mListView = (ListView) findViewById(R.id.serverList);

        // Liste contenant les serveurs générés.
        List<Infrastructure> myInfras = genererInfra();

        InfrastructureAdapter adapter = new InfrastructureAdapter(MainActivity.this, myInfras);

        mListView.setAdapter(adapter);

    }


    // TODO: Fonction de recuperation de la liste des serveurs en GET via Centreon
    // Actuellement la liste est générée en dur. vert : Color.rgb(34,139,34) ; rouge :
    private List<Infrastructure> genererInfra(){
        List<Infrastructure> myList = new ArrayList<Infrastructure>();

        myList.add(new Infrastructure("ns2307123.ovh.net","13.55.69.192",R.drawable.ic_green_button));
        myList.add(new Infrastructure("ns2307456.ovh.net","13.55.69.193",R.drawable.ic_green_button));
        myList.add(new Infrastructure("ns2307789.ovh.net","13.55.69.194",R.drawable.ic_green_button));
        myList.add(new Infrastructure("ns2307112.ovh.net","13.55.69.191",R.drawable.ic_red_button));

        return myList;
    }
}
