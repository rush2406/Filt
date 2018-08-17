package org.technozion.technozion18;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import org.technozion.technozion18.utils.PrefManager;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MyApplication extends Application {

    private static MyApplication mInstance;
    public PrefManager prefManager;
    //public static NetworkErrorDialogDriver networkErrorDialogDriver;

    @Override
    public void onCreate() {

        super.onCreate();

        if (android.os.Build.VERSION.SDK_INT>19) {
            Log.d("MyApplication","Higher than kitkat");
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath("fonts/DroidSans.ttf")
                    .setFontAttrId(uk.co.chrisjenx.calligraphy.R.attr.fontPath)
                    .build());
        }

        prefManager = new PrefManager(this);
        mInstance = this;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

}
