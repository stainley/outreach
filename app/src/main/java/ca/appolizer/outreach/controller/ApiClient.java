package ca.appolizer.outreach.controller;

import java.util.concurrent.TimeUnit;

import ca.appolizer.outreach.service.UserService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * API client to manage connection to the API
 *
 */
public class ApiClient {

    private final String token;

    public ApiClient(String token) {
        this.token = token;
    }

    private static Retrofit getRetrofit() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://nevisco.ca/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    private static Retrofit getRetrofitWithToken(String token) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(chain -> {
                    Request newRequest = chain.request().newBuilder()
                            .addHeader("Authorization", "Bearer " + token)
                            .build();
                    return chain.proceed(newRequest);
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://nevisco.ca/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public static UserService getUserService() {
        UserService userService = getRetrofit().create(UserService.class);
        return userService;
    }

    public static UserService getUserServiceWithToken(String token) {
        UserService userService = getRetrofitWithToken(token).create(UserService.class);
        return userService;
    }

}
