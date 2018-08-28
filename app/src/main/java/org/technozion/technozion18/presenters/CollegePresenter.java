package org.technozion.technozion18.presenters;

import org.technozion.technozion18.MyApplication;
import org.technozion.technozion18.common.EntitiesCallback;
import org.technozion.technozion18.common.OnEntitiesReceivedListener;
import org.technozion.technozion18.models.College;

public class CollegePresenter {

    public void getColleges(int cityNo, OnEntitiesReceivedListener<College> listener){
        MyApplication.getInstance().getRepository().getColleges(cityNo, new EntitiesCallback<>(listener));
    }
}
