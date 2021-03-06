package org.technozion.technozion18.api_services;

import org.technozion.technozion18.common.AbstractCallback;
import org.technozion.technozion18.models.User;
import org.technozion.technozion18.models.UserProfile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class Repository {
    private static final String TAG = Repository.class.getSimpleName();
    ApiEndpoint apiService = ApiClient.getClient().create(ApiEndpoint.class);

    static Repository repository;

    private Repository(){

    }

    synchronized public static Repository getInstance(){
        if(repository == null)
            repository = new Repository();
        return repository;
    }

    /** Utility method to get PartMap for Multipart request **/
    private Map<String, RequestBody> getPartMap(Map<String, String> map){
        Map<String, RequestBody> newMap = new HashMap<>();
        //create request body map for Multipart Request
        for(String key : map.keySet())
            newMap.put(key, map.get(key) != null ? createPartFromString(map.get(key)) : createPartFromString(""));
        return newMap;
    }

    /** Create Part from string **/
    private RequestBody createPartFromString(String text){
        return  RequestBody.create(
                MediaType.parse("multipart/form-data"),  text );

    }

    public void getEvents(List<String> type, List<String> category, List<String> departments, List<String> days, AbstractCallback callback){
        apiService.getEvents(type, category, departments, days).enqueue(callback);
    }

    public void getEvent(int id, AbstractCallback callback){
        apiService.getEventById(id).enqueue(callback);
    }

    public void signup(User user, AbstractCallback callback){
        apiService.createUser(user).enqueue(callback);
    }

    public void signin(String username, String password, AbstractCallback callback){
        apiService.authenticate(username, password).enqueue(callback);
    }

    public void getCurrentUser(AbstractCallback callback){
        apiService.getCurrentUser().enqueue(callback);
    }

    public void getCurrentUserProfile(AbstractCallback callback){
        apiService.getUserProfile().enqueue(callback);
    }

    public void updateUserProfile(UserProfile userProfile, AbstractCallback callback){
        apiService.postUserProfile(userProfile).enqueue(callback);
    }

    public void updateUserProfile(Map<String, String> map, AbstractCallback callback){
        apiService.postUserProfile(map).enqueue(callback);
    }

    public void getColleges(int cityNo, AbstractCallback callback){
        apiService.getCollegesByCity(cityNo).enqueue(callback);
    }

    public void getCities(AbstractCallback callback){
        apiService.getCities().enqueue(callback);
    }
}
