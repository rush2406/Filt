package org.technozion.technozion18.common;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

public abstract class OnEntitiesReceivedListener<T> implements BaseViewAction{

    Context context;

    public OnEntitiesReceivedListener(Context context){
        this.context = context;
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
}
