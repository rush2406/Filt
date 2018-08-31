package org.technozion.technozion18.presenters;

import org.technozion.technozion18.MyApplication;
import org.technozion.technozion18.api_services.responses.ProfileResponse;
import org.technozion.technozion18.common.EntityCallback;
import org.technozion.technozion18.common.OnEntityReceivedListener;

public class UserPresenter {

    public void getCurrentUserProfile(OnEntityReceivedListener<ProfileResponse> listener){
        MyApplication.getInstance().getRepository().getCurrentUserProfile(new EntityCallback<>(listener));
    }
}
