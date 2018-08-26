package org.technozion.technozion18.api_services;

import org.technozion.technozion18.api_services.responses.AuthenticationResponse;
import org.technozion.technozion18.api_services.responses.ProfileResponse;
import org.technozion.technozion18.models.Event;
import org.technozion.technozion18.models.User;
import org.technozion.technozion18.models.UserProfile;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface ApiEndpoint {

    @POST("login/")
    Call<AuthenticationResponse> authenticate(@Field("username") String username, @Field("password") String password);

    @POST("users/")
    @Headers(("Content-Type: application/json"))
    Call<User> createUser(@Body User user);

    @POST("users/profile")
    @Headers("Content-Type: application/json")
    Call<ProfileResponse> postUserProfile(@Body UserProfile userProfile);

    @GET("users/profile")
    Call<ProfileResponse> getUserProfile();

    @GET("events/")
    Call<List<Event>> getEvents(@QueryMap Map<String, String> map);

    @GET("events/{id}")
    Call<Event> getEventById(@Path("id") int id);
}
