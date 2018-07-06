package com.example.ranggarizky.tugas_akhir.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Fajar GBP on 12/4/2017.
 */

public class SessionManager {
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    SharedPreferences.Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "Bidansession";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_IS_TOKEN= "token";
    private static final String KEY_LAST_LOGIN= "last_login";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn) {

        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.commit();
    }

    public void setToken(String token) {

        editor.putString(KEY_IS_TOKEN, token);
        editor.commit();
    }

    public void setLastLogin(String lastLogin) {

        editor.putString(KEY_LAST_LOGIN, lastLogin);
        editor.commit();
    }



    public Boolean isLogin() {
    return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public String getToken() {
        return pref.getString(KEY_IS_TOKEN,null);
    }

    public String getLastLogin() {
        return pref.getString(KEY_LAST_LOGIN,null);
    }
}
