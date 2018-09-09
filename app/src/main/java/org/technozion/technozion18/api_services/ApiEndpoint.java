package org.technozion.technozion18.api_services;

import org.technozion.technozion18.api_services.responses.AuthenticationResponse;
import org.technozion.technozion18.api_services.responses.ProfileResponse;
import org.technozion.technozion18.models.City;
import org.technozion.technozion18.models.College;
import org.technozion.technozion18.models.Event;
import org.technozion.technozion18.models.User;
import org.technozion.technozion18.models.UserProfile;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiEndpoint {

    @POST("login/")
    @FormUrlEncoded
    Call<AuthenticationResponse> authenticate(@Field("username") String username, @Field("password") String password);

    @POST("users/")
    @Headers(("Content-Type: application/json"))
    Call<User> createUser(@Body User user);

    @GET("users/")
    Call<User> getCurrentUser();

    @POST("users/profile/")
    @Headers("Content-Type: application/json")
    Call<UserProfile> postUserProfile(@Body UserProfile userProfile);

    @POST("users/profile/")
    @FormUrlEncoded
    Call<UserProfile> postUserProfile(@FieldMap Map<String, String> map);

    @GET("users/profile")
    Call<ProfileResponse> getUserProfile();

    @GET("events/")
    Call<List<Event>> getEvents(
            @Query("type") List<String> types,
            @Query("category") List<String> categories,
            @Query("department") List<String> departments,
            @Query("day") List<String> days);

    @GET("events/{id}")
    Call<Event> getEventById(@Path("id") int id);

    @GET("places")
    Call<List<City>> getCities();

    @GET("colleges")
    Call<List<College>> getCollegesByCity(@Query("cityNo") int cityNo);
}
