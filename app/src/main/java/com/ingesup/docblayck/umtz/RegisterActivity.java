package com.ingesup.docblayck.umtz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.ingesup.docblayck.umtz.Entities.User;
import com.ingesup.docblayck.umtz.Services.FirebaseIDService;
import com.ingesup.docblayck.umtz.Tasks.AsyncTaskConnexion;
import com.ingesup.docblayck.umtz.Tasks.AsyncTaskRegister;
import com.ingesup.docblayck.umtz.Tools.EmailValidator;
import com.ingesup.docblayck.umtz.Tools.EncryptPassword;
import com.ingesup.docblayck.umtz.Tools.IPAddressValidator;

import java.util.concurrent.ExecutionException;

public class RegisterActivity extends Activity {

    private TextInputLayout edtEmailWrapper, edtPasswordWrapper, edtConfirmPasswordWrapper,
            edtUserCentreonWrapper, edtPasswordCentreonWrapper, edtIPCentreonWrapper;

    private IPAddressValidator ipValidator;
    private EmailValidator emailValidator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        ipValidator = new IPAddressValidator();
        emailValidator = new EmailValidator();
        edtEmailWrapper = (TextInputLayout) findViewById(R.id.register_edt_emailWrapper);
        edtPasswordWrapper = (TextInputLayout) findViewById(R.id.register_edt_passwordWrapper);
        edtConfirmPasswordWrapper = (TextInputLayout) findViewById(R.id.register_edt_confirm_passwordWrapper);
        edtUserCentreonWrapper = (TextInputLayout) findViewById(R.id.register_edt_user_centreonWrapper);
        edtPasswordCentreonWrapper = (TextInputLayout) findViewById(R.id.register_edt_centreon_passwordWrapper);
        edtIPCentreonWrapper = (TextInputLayout) findViewById(R.id.register_edt_ip_centreonWrapper);


    }

    public void buttonAlreadyMemberClick(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void buttonSendFormRegister(View view){
        hideKeyboard();

        String sEmail = edtEmailWrapper.getEditText().getText().toString();
        String sPassword = edtPasswordWrapper.getEditText().getText().toString();
        String sConfirmPassword = edtConfirmPasswordWrapper.getEditText().getText().toString();
        String sUserCentreon = edtUserCentreonWrapper.getEditText().getText().toString();
        String sPasswordCentreon = edtPasswordCentreonWrapper.getEditText().getText().toString();
        String sIPCentreon = edtIPCentreonWrapper.getEditText().getText().toString();



        if(!sEmail.equals("") && !sPassword.equals("") && !sConfirmPassword.equals("")
                && !sUserCentreon.equals("") && !sPasswordCentreon.equals("") && !sIPCentreon.equals("")){


            if(emailValidator.validate(sEmail)){
                edtEmailWrapper.setError(null);

                if(sPassword.equals(sConfirmPassword)){
                    edtConfirmPasswordWrapper.setError(null);
                    Log.i("VAR 1",sPassword);

                    if(ipValidator.validateIpAddress(sIPCentreon)){
                        edtIPCentreonWrapper.setError(null);
                        Log.i("ACCEPT","Tout est OK, envois des données");
                        User user = new User(sEmail,sPassword,sUserCentreon,sPasswordCentreon,sIPCentreon);
                        try {
                            String result = new AsyncTaskRegister(RegisterActivity.this, user)
                                    .execute("http://174.138.7.116:8080/CWS/api/createUser").get();

                            if(result.equals("-1")){
                                edtEmailWrapper.setError("Login déjà utilisé");
                            }
                            else if(result.equals("-2")){
                                edtIPCentreonWrapper.setError("Adresse IP ou compte Centreon non reconnu");
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }

                    }
                    else{
                        edtIPCentreonWrapper.setError("Adresse IP non valide");
                    }
                }
                else{
                    edtConfirmPasswordWrapper.setError("Les mots de passes doivent être identiques");
                }
            }
            else {
                edtEmailWrapper.setError("Adresse Email non valide");
            }
        }
        else {
            Toast.makeText(getApplicationContext(),"Veuillez remplir tous les champs",Toast.LENGTH_SHORT).show();
        }


    }


    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
