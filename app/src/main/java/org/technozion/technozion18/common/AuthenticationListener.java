package org.technozion.technozion18.common;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.widget.Toast;

import org.technozion.technozion18.R;

public abstract class AuthenticationListener implements BaseViewAction {

    Context context;
    private CustomProgressDialog dialog;


    public AuthenticationListener(Context context){

        this.context = context;
        dialog = new CustomProgressDialog(context);
    }

    public abstract void onAuthenticated(String token);

    public abstract void onFailure();


    public void showMessage(String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void showNetworkError(String message){

    }

    public void showLoader(){
        if(!dialog.isShowing())
            dialog.show();
    }

    public void hideLoader(){
        if(dialog.isShowing())
            dialog.cancel();
    }

}
