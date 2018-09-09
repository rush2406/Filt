package org.technozion.technozion18.presenters;

import org.technozion.technozion18.MyApplication;
import org.technozion.technozion18.api_services.responses.AuthenticationResponse;
import org.technozion.technozion18.common.AbstractCallback;
import org.technozion.technozion18.common.AuthenticationListener;
import org.technozion.technozion18.models.User;

import retrofit2.Call;
import retrofit2.Response;

public class AuthenticationPresenter {

    public void authenticate(User user, final AuthenticationListener listener){

        listener.showLoader();

        MyApplication.getInstance().getRepository().signin(user.getUsername(), user.getPassword(), new AbstractCallback(listener) {
            @Override
            public void onResponse(Call call, Response response) {

                listener.hideLoader();

                if(response.code() == 200){
                    AuthenticationResponse authenticationResponse = (AuthenticationResponse) response.body();
                    listener.onAuthenticated(authenticationResponse.getToken());
                } else {
                    listener.onFailure();
                }
            }
        });
    }
}
