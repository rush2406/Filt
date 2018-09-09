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
    private static final String USER_ID = "user_id";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void saveString(String key, String value){
        editor.putString(key, value);
        editor.commit();
    }

    public void logout(){
        editor.remove(USER_AUTH_TOKEN);
        editor.commit();
    }

    public String getString(String key){
        return pref.getString(key, null);
    }

    public void saveUserId(int id){
        editor.putInt(USER_ID, id);
        editor.commit();
    }

    public int getUserId(){
        return pref.getInt(USER_ID, -1);
    }

    public void saveUserAuthToken(String token){
        saveString(USER_AUTH_TOKEN, token);
    }

    public String getAuthToken(){
        return pref.getString(USER_AUTH_TOKEN, null);
    }

    public Boolean isLoggedIn(){
        return pref.contains(USER_AUTH_TOKEN);
    }

}
