package org.technozion.technozion18.common;

import org.technozion.technozion18.models.User;

import retrofit2.Call;
import retrofit2.Response;

public class UserCallback extends AbstractCallback{

    OnCreateUserListener listener;

    public UserCallback(OnCreateUserListener listener) {
        super(listener);
        this.listener = listener;
    }

    @Override
    public void onResponse(Call call, Response response) {

        listener.hideLoader();

        User user = (User) response.body();
        if(response.code() == 201)
            listener.onUserCreated((User)response.body());
        else {
            if(user.getUsername() != null)
                listener.onFailure(user.getUsername());
            else if(user.getPassword() != null){
                listener.onFailure(user.getPassword());
            } else if(user.getEmail() != null){
                listener.onFailure(user.getEmail());
            }
        }
    }
}
