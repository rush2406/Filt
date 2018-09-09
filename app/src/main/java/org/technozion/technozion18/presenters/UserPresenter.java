package org.technozion.technozion18.presenters;

import org.technozion.technozion18.MyApplication;
import org.technozion.technozion18.api_services.responses.ProfileResponse;
import org.technozion.technozion18.common.EntityCallback;
import org.technozion.technozion18.common.OnEntityReceivedListener;
import org.technozion.technozion18.models.User;
import org.technozion.technozion18.models.UserProfile;

import java.util.Map;

public class UserPresenter {

    public void getCurrentUserProfile(OnEntityReceivedListener<ProfileResponse> listener){
        listener.showLoader();
        MyApplication.getInstance().getRepository().getCurrentUserProfile(new EntityCallback<>(listener));
    }

    public void updateUserProfile(UserProfile userProfile, OnEntityReceivedListener<UserProfile> listener){
        listener.showLoader();
        MyApplication.getInstance().getRepository().updateUserProfile(userProfile, new EntityCallback<>(listener));
    }

    public void updateUserProfile(Map<String,String> map, OnEntityReceivedListener<UserProfile> listener){
        listener.showLoader();
        MyApplication.getInstance().getRepository().updateUserProfile(map, new EntityCallback<>(listener));
    }
}
