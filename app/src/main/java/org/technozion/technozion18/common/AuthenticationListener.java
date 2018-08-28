package org.technozion.technozion18.common;

import android.content.Context;
import android.widget.Toast;

public abstract class AuthenticationListener implements BaseViewAction {

    Context context;


    public AuthenticationListener(Context context){
        this.context = context;
    }

    public abstract void onAuthenticated(String token);

    public abstract void onFailure();


    public void showMessage(String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
    public void showNetworkError(String message){

    }

}
