package org.technozion.technozion18.presenters;

import org.technozion.technozion18.MyApplication;
import org.technozion.technozion18.api_services.Repository;
import org.technozion.technozion18.common.EntitiesCallback;
import org.technozion.technozion18.common.EntityCallback;
import org.technozion.technozion18.common.OnEntitiesReceivedListener;
import org.technozion.technozion18.common.OnEntityReceivedListener;
import org.technozion.technozion18.models.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventPresenter {

    Repository repository = MyApplication.getInstance().getRepository();

    public void getEvents(OnEntitiesReceivedListener<Event> listener){
        listener.showLoader();
        repository.getEvents(new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(), new EntitiesCallback<>(listener));
    }

    public void getEvents(List<String> types, List<String> categories, List<String> departments, List<String> days, OnEntitiesReceivedListener<Event> listener){
        listener.showLoader();
        repository.getEvents(types, categories, departments, days, new EntitiesCallback<>(listener));
    }

    public void getEvent(int id, OnEntityReceivedListener<Event> listener){
        listener.showLoader();
        repository.getEvent(id, new EntityCallback<>(listener));
    }
}
