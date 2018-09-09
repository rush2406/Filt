package org.technozion.technozion18.common;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public abstract class OnEntitiesReceivedListener<T> implements BaseViewAction{

    Context context;
    CustomProgressDialog dialog;

    public OnEntitiesReceivedListener(Context context){

        this.context = context;
        dialog = new CustomProgressDialog(context);
    }

    public abstract void onReceived(List<T> entities);

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
        Log.d(OnEntityReceivedListener.class.getSimpleName(),"Dialog dismissed");
        dialog.dismiss();
    }
}
