package org.technozion.technozion18.api_services;

import org.technozion.technozion18.models.Event;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface ApiEndpoint {

    @GET("events/")
    Call<List<Event>> getEvents(@QueryMap Map<String, String> map);

    @GET("events/{id}")
    Call<Event> getEventById(@Path("id") int id);
}
