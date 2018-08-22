package org.technozion.technozion18.presenters;

import org.technozion.technozion18.MyApplication;
import org.technozion.technozion18.api_services.Repository;
import org.technozion.technozion18.common.EntitiesCallback;
import org.technozion.technozion18.common.EntityCallback;
import org.technozion.technozion18.common.OnEntitiesReceivedListener;
import org.technozion.technozion18.common.OnEntityReceivedListener;
import org.technozion.technozion18.models.Event;

import java.util.HashMap;

public class EventPresenter {

    Repository repository = MyApplication.getInstance().getRepository();

    public void getEvents(OnEntitiesReceivedListener<Event> listener){
        repository.getEvents(new HashMap<String, String>(), new EntitiesCallback<>(listener));
    }

    public void getEvent(int id, OnEntityReceivedListener<Event> listener){
        repository.getEvent(id, new EntityCallback<Event>(listener));
    }
}
