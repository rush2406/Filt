package org.technozion.technozion18.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "technozion18";

    private static final String USER_AUTH_TOKEN = "user_auth_token";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void saveString(String key, String value){
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key){
        return pref.getString(key, null);
    }

    public void saveUserAuthToken(String token){
        editor.putString(USER_AUTH_TOKEN, token);
    }

    public String getAuthToken(){
        return pref.getString(USER_AUTH_TOKEN, null);
    }

    public Boolean isLoggedIn(){
        return pref.contains(USER_AUTH_TOKEN);
    }

}
