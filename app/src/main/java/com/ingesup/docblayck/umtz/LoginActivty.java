package com.ingesup.docblayck.umtz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ingesup.docblayck.umtz.Entities.User;
import com.ingesup.docblayck.umtz.Tasks.AsyncTaskConnexion;

/**
 * Created by Najib on 21/02/2018.
 */

public class LoginActivty  extends AppCompatActivity {

    private EditText edtEmail,edtPassword ;
    private TextView txvLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.login_layout);
        edtEmail = (EditText) findViewById(R.id.editTextMail);
        edtPassword = (EditText) findViewById(R.id.editTextPassword);
        txvLink = (TextView) findViewById(R.id.textViewLink);

    }

    public void buttonConnexionClick(View v){
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        if(email.equals("") || password.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Veuillez remplir les champs",Toast.LENGTH_SHORT).show();
        }
        else
        {
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            new AsyncTaskConnexion(LoginActivty.this, user,true).execute("http://172.20.10.3:8080/CentreonWebService/api/verifUser");
        }

    }

    public void buttonLinkClick(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}