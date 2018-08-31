package org.technozion.technozion18.presenters;

import org.technozion.technozion18.MyApplication;
import org.technozion.technozion18.common.EntitiesCallback;
import org.technozion.technozion18.common.OnEntitiesReceivedListener;
import org.technozion.technozion18.models.City;

public class CityPresenter {
    public void getCities(OnEntitiesReceivedListener<City> listener){
        MyApplication.getInstance().getRepository().getCities(new EntitiesCallback<>(listener));
    }
}
