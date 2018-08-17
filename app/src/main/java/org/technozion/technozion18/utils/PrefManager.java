package org.technozion.technozion18.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "technozion18";

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
}
