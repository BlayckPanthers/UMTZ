package com.ingesup.docblayck.umtz;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ingesup.docblayck.umtz.Dao.UserDao;
import com.ingesup.docblayck.umtz.Global.GlobalData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GlobalData.getInstance().setUserDao(new UserDao(getApplicationContext()));
        GlobalData.getInstance().getUserDao().open();
        Intent intent = new Intent(this, LoginActivty.class);
        startActivity(intent);
    }
}
