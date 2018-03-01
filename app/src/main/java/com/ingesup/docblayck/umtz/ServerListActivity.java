package com.ingesup.docblayck.umtz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.ingesup.docblayck.umtz.Adapters.InfrastructureAdapter;
import com.ingesup.docblayck.umtz.Entities.Infrastructure;
import com.ingesup.docblayck.umtz.Global.GlobalData;
import com.ingesup.docblayck.umtz.Tasks.AsyncTaskDeconnexion;
import com.ingesup.docblayck.umtz.Tasks.AsyncTaskServers;

import java.util.Date;
import java.util.List;

public class ServerListActivity extends AppCompatActivity {
    List<Infrastructure> myInfras;
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
                        GlobalData.getInstance().getUserDao().supprimer(GlobalData.getInstance().getUser().getEmail());
                        GlobalData.getInstance().setUser(null);
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
                //TODO : Download PDF
                Intent intent = new Intent(this, PDFViewActivity.class);
                startActivity(intent);
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
    private void genererInfraList(){

    }



}
