package org.technozion.technozion18.common;

import android.content.Context;
import android.widget.Toast;

public abstract class OnEntityReceivedListener<T> implements BaseViewAction {

    Context context;
    CustomProgressDialog dialog;

    public OnEntityReceivedListener(Context context) {
        this.context = context;
        dialog = new CustomProgressDialog(context);
    }

    public abstract void onReceived(T entity);

    @Override
    public void showMessage(String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
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