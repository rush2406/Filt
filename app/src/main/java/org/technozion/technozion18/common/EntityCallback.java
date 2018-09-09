package org.technozion.technozion18.common;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Response;

public class EntityCallback<T> extends AbstractCallback{

    OnEntityReceivedListener<T> listener;
    public static final String TAG = EntityCallback.class.getSimpleName();

    public EntityCallback(OnEntityReceivedListener<T> listener) {
        super(listener);
        this.listener = listener;
    }

    @Override
    public void onResponse(Call call, Response response) {

        listener.hideLoader();

        Log.d(TAG, response.code() + " " + response.message());
        if(response.code() >= 200&&response.code()<=299) {
            T entity = (T) response.body();
            listener.onReceived(entity);
        }
        else
            listener.showNetworkError("Please check your connection!");

    }
}
