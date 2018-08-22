package org.technozion.technozion18.common;

import android.util.Log;

import org.technozion.technozion18.api_services.responses.PaginatedResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class EntitiesCallback<T> extends AbstractCallback {
    OnEntitiesReceivedListener<T> listener;
    public static final String TAG = EntitiesCallback.class.getSimpleName();

    public EntitiesCallback(OnEntitiesReceivedListener<T> listener){
        super(listener);
        this.listener = listener;
    }
    @Override
    public void onResponse(Call call, Response response) {
        Log.d(TAG, response.code() + " " + response.message());
        if(response.code() >= 200 && response.code()<=299) {
            List<T> entities = (List<T>) response.body();
            listener.onReceived(entities);
        }
        else
            listener.showNetworkError("Please check your connection!");
    }
}
