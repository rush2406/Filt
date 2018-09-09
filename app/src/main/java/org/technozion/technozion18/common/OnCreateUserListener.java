package org.technozion.technozion18.common;

import android.content.Context;
import android.widget.Toast;

import org.technozion.technozion18.models.User;

public abstract class OnCreateUserListener implements BaseViewAction {

    Context context;
    CustomProgressDialog dialog;


    public OnCreateUserListener(Context context){
        this.context = context;
        dialog = new CustomProgressDialog(context);
    }

    public abstract void onUserCreated(User user);

    public void onFailure(String error){
        showMessage(error);
    }

    public void showMessage(String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT);
    }
    public void showNetworkError(String message){
        showMessage(message);
    }

    @Override
    public void showLoader(){
        dialog.show();
    }

    @Override
    public void hideLoader(){
        dialog.cancel();
    }
}
