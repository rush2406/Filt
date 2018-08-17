package org.technozion.technozion18.api_services;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class Repository {
    private static final String TAG = Repository.class.getSimpleName();
    ApiEndpoint apiService = ApiClient.getClient().create(ApiEndpoint.class);

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
}
