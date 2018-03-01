package com.ingesup.docblayck.umtz.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.ingesup.docblayck.umtz.Entities.User;

/**
 * Created by Najib on 28/02/2018.
 */

public class UserDao extends DAOBase {
    public static final String TABLE_NAME = "user";
    public static final String USER_EMAIL = "email_user";
    public static final String USER_PASSWORD = "password_user";

    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
            USER_EMAIL + " TEXT PRIMARY KEY, " +
            USER_PASSWORD + " TEXT);";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public UserDao(Context pContext) {
        super(pContext);
    }

    /**
     * @param u le métier à ajouter à la base
     */
    public void ajouter(User u) {
        ContentValues value = new ContentValues();
        value.put(UserDao.USER_EMAIL, u.getEmail());
        value.put(UserDao.USER_PASSWORD, u.getPassword());
        mDb.insert(UserDao.TABLE_NAME, null, value);
    }

    /**
     * @param email l'identifiant du métier à supprimer
     */
    public void supprimer(String email) {
        mDb.delete(TABLE_NAME, USER_EMAIL + " = ?", new String[] {email});
    }

    /**
     * @param u le métier modifié
     */
    public void modifier(User u) {
        ContentValues value = new ContentValues();
        value.put(UserDao.USER_EMAIL, u.getEmail());
        mDb.update(TABLE_NAME, value, USER_EMAIL  + " = ?", new String[] {u.getEmail()});

    }

    /**
     * @param email l'identifiant du métier à récupérer
     */
    public User selectionner(String email) {
        Cursor c = mDb.rawQuery("select * from " + TABLE_NAME, null);
        User u = new User();
        while (c.moveToNext()) {
            u.setEmail(c.getString(0));
            u.setPassword(c.getString(1));
        }
        c.close();
        return u;
    }

    public User selectionnerTout() {
        User u = new User();
        try{
            Cursor c = mDb.rawQuery("select * from " + TABLE_NAME, null);
            if(c.getCount()!=0){
                while (c.moveToNext()) {
                    u.setEmail(c.getString(0));
                    u.setPassword(c.getString(1));
                }
            }
            else
                u=null;
            c.close();
        }catch (Exception e){
            u=null;
        }
        return u;
    }
}
