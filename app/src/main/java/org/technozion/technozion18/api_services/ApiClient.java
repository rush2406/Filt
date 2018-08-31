package org.technozion.technozion18.api_services;

import android.util.Log;

import com.google.gson.GsonBuilder;

import org.technozion.technozion18.MyApplication;
import org.technozion.technozion18.utils.Constants;
import org.technozion.technozion18.utils.PrefManager;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {


    private static final String BASE_URL = Constants.IP +"api/";
    private static Retrofit retrofit = null;
    private static String token;
    private static final String TAG = ApiClient.class.getSimpleName();
    private static final PrefManager prefManager = MyApplication.getInstance().getPrefManager();


    public static Retrofit getClient() {

        if (retrofit == null || token.equals("")) {

            Log.d(TAG, "Creating retrofit client...");
            Log.d(TAG, prefManager.isLoggedIn() + "");
            final String authHeader = prefManager.isLoggedIn() ? "Token " + prefManager.getAuthToken() : "";
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient defaultHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(
                            new Interceptor() {
                                @Override
                                public Response intercept(Interceptor.Chain chain) throws IOException {
                                    Request request = chain.request().newBuilder()
                                            .addHeader("Accept", "application/JSON")
                                            .addHeader("Authorization", authHeader)
                                            .build();
                                    return chain.proceed(request);
                                }
                            })
                    .addInterceptor(loggingInterceptor)
                    .readTimeout(120, TimeUnit.SECONDS)
                    .writeTimeout(120, TimeUnit.SECONDS)
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .build();


            retrofit =
                    new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .client(defaultHttpClient)
                            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().serializeNulls().create()))
                            .build();

        }


        return retrofit;
    }
}
