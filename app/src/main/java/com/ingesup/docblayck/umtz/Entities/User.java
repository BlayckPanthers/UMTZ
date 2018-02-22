package com.ingesup.docblayck.umtz.Entities;

/**
 * Created by Najib on 16/02/2018.
 */

public class User {
    private String email;
    private String password;
    private String userCentreon;
    private String passwordCentreon;
    private String ipCentreon;
    private String token;

    public User(){}

    public User(String email, String password, String userCentreon, String passwordCentreon, String ipCentreon)  {
        this.email = email;
        this.password = password;
        this.userCentreon = userCentreon;
        this.passwordCentreon = passwordCentreon;
        this.ipCentreon = ipCentreon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserCentreon() {
        return userCentreon;
    }

    public void setUserCentreon(String userCentreon) {
        this.userCentreon = userCentreon;
    }

    public String getPasswordCentreon() {
        return passwordCentreon;
    }

    public void setPasswordCentreon(String passwordCentreon) {
        this.passwordCentreon = passwordCentreon;
    }

    public String getIpCentreon() {
        return ipCentreon;
    }

    public void setIpCentreon(String ipCentreon) {
        this.ipCentreon = ipCentreon;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
