package com.ingesup.docblayck.umtz;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.ingesup.docblayck.umtz.Entities.Infrastructure;
import com.ingesup.docblayck.umtz.Global.GlobalData;
import com.ingesup.docblayck.umtz.Tasks.AsyncTaskConnexion;
import com.ingesup.docblayck.umtz.Tasks.AsyncTaskDeconnexion;
import com.ingesup.docblayck.umtz.Tasks.AsyncTaskServers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ServerListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infrastructure_layout);
        getSupportActionBar().show();
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.color.colorToolbar));
        // ListView affichant la liste des serveurs
        ListView mListView = (ListView) findViewById(R.id.serverList);

        // Liste contenant les serveurs générés.
        //List<Infrastructure> myInfras = genererInfra();
        List<Infrastructure> myInfras;
        try{
            myInfras = new AsyncTaskServers(this).execute("http://174.138.7.116:8080/CWS/api/centreon").get();
            InfrastructureAdapter adapter = new InfrastructureAdapter(ServerListActivity.this, myInfras);
            mListView.setAdapter(adapter);
            Toast.makeText(getApplicationContext(), GlobalData.getInstance().getUser().getEmail(),Toast.LENGTH_SHORT).show();
        }catch (Exception e){

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id){
            case R.id.action_logout :
                //TODO : Dialog menant à la déconnexion

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.dialog_message).setTitle(R.string.dialog_title);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        new AsyncTaskDeconnexion(ServerListActivity.this, GlobalData.getInstance().getUser())
                                .execute("http://174.138.7.116:8080/CWS/api/deconnexion");

                        Intent intent = new Intent(getApplicationContext(), LoginActivty.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {}
                });
                AlertDialog dialog = builder.create();
                dialog.show();


                break;
            case R.id.action_about :
                //TODO : Simple activity for showing the about section

                Toast.makeText(this, "Action clicked 2 ", Toast.LENGTH_LONG).show();
                break;
            case R.id.action_download :


                break;

            default: return true;

        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

    }

    // TODO: Fonction de recuperation de la liste des serveurs en GET via Centreon
    // Actuellement la liste est générée en dur. vert : Color.rgb(34,139,34) ; rouge :
    private List<Infrastructure> genererInfra(){
        List<Infrastructure> myList = new ArrayList<Infrastructure>();

//        myList.add(new Infrastructure("ns2307123.ovh.net","13.55.69.192",R.drawable.ic_green_button,0,0,0));
//        myList.add(new Infrastructure("ns2307456.ovh.net","13.55.69.193",R.drawable.ic_green_button,0,0,0));
//        myList.add(new Infrastructure("ns2307789.ovh.net","13.55.69.194",R.drawable.ic_green_button,0,0,0));
//        myList.add(new Infrastructure("ns2307112.ovh.net","13.55.69.191",R.drawable.ic_red_button,0,0,0));

        return myList;
    }



}
