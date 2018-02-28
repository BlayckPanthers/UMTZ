package com.ingesup.docblayck.umtz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.ingesup.docblayck.umtz.Entities.User;
import com.ingesup.docblayck.umtz.Services.FirebaseIDService;
import com.ingesup.docblayck.umtz.Tasks.AsyncTaskConnexion;
import com.ingesup.docblayck.umtz.Tools.EmailValidator;
import com.ingesup.docblayck.umtz.Tools.EncryptPassword;

import java.util.concurrent.ExecutionException;

/**
 * Created by Najib on 21/02/2018.
 */

public class LoginActivty  extends AppCompatActivity {

    private EmailValidator emailValidator;
    private EditText edtEmail,edtPassword ;
    private TextView txvLink;
    private TextInputLayout mailWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.login_layout);

        emailValidator = new EmailValidator();
        mailWrapper = (TextInputLayout) findViewById(R.id.login_TIL_Mailwrapper);
        edtEmail = (EditText) findViewById(R.id.editTextMail);
        edtPassword = (EditText) findViewById(R.id.editTextPassword);
        txvLink = (TextView) findViewById(R.id.textViewLink);

        Intent intent = getIntent();
        Bundle extra = getIntent().getExtras();

        // Si on vient d'un Intent (RegisterActivity)
        if(extra != null) {
            if(extra.containsKey("login") && extra.containsKey("password")){
                String login = extra.getString("login");
                String password = extra.getString("password");

                this.edtEmail.setText(login);
                this.edtPassword.setText(password);
            }
        }
    }
    @Override
    public void onBackPressed() {

    }
    public void buttonConnexionClick(View v){
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        if(!email.equals("") && !password.equals(""))
        {
            if(emailValidator.validate(email)){
                mailWrapper.setError(null);
                User user = new User();
                user.setEmail(email);
                user.setPassword(password);
                new AsyncTaskConnexion(LoginActivty.this, user,mailWrapper)
                            .execute("http://174.138.7.116:8080/CWS/api/verifUser");

            }
            else{
                mailWrapper.setError("Adresse email non valide");
            }

        }
        else
        {
            Toast.makeText(getApplicationContext(),"Veuillez remplir les champs",Toast.LENGTH_SHORT).show();
        }

    }

    public void buttonLinkClick(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
