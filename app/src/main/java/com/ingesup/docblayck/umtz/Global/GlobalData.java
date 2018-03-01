package com.ingesup.docblayck.umtz.Global;

import android.app.Application;

import com.ingesup.docblayck.umtz.Dao.UserDao;
import com.ingesup.docblayck.umtz.Entities.User;

/**
 * Created by Najib on 21/02/2018.
 */

public class GlobalData  {
    private static GlobalData instance;

    // Global variable
    private User user;
    private UserDao userDao;

    // Restrict the constructor from being instantiated
    private GlobalData(){}

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

    public UserDao getUserDao() { return userDao; }

    public void setUserDao(UserDao userDao) {this.userDao = userDao;}

    public static synchronized GlobalData getInstance(){
        if(instance==null){
            instance=new GlobalData();
        }
        return instance;
    }
}
