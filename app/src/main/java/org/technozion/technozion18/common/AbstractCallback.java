package org.technozion.technozion18.common;

import android.os.Handler;
import android.util.Log;
import retrofit2.Call;
import retrofit2.Callback;


abstract public class AbstractCallback implements Callback {

    BaseViewAction viewAction;
    static Boolean IS_FAILURE = false;
    public static final String TAG = AbstractCallback.class.getSimpleName();

    public AbstractCallback(BaseViewAction viewAction){
        this.viewAction = viewAction;
    }

    @Override
    public void onFailure(Call call, Throwable t) {

        viewAction.hideLoader();

        Log.d(TAG, "ISFAIL : " + IS_FAILURE);
        if(IS_FAILURE)
            return;

        IS_FAILURE = true;     //acquire lock

        //Log.d("Network Failure Log", t.getMessage());
        viewAction.showNetworkError("Unable to connect. Please try again!");    //show message
        Log.e(TAG, t.getMessage());
        new Handler().postDelayed(new Runnable() {  //release lock after 2 seconds
            @Override
            public void run() {

                    IS_FAILURE = false;
            }
        }, 1000);
    }
}
