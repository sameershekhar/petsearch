package com.example.sameershekhar.petsearch.network;

import com.example.sameershekhar.petsearch.BuildConfig;
import com.example.sameershekhar.petsearch.utils.Constant;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient retrofitClient;
    private Retrofit retrofit;
    private OkHttpClient client;
    private ServerEndPoints serverEndPoints;


    public RetrofitClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.addInterceptor(new HttpLoggingInterceptor());

        if (BuildConfig.DEBUG) {
            okHttpBuilder.addInterceptor(loggingInterceptor);
        }

        client = okHttpBuilder.build();

        retrofit = new Retrofit.Builder().baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        serverEndPoints = retrofit.create(ServerEndPoints.class);

    }

    public static RetrofitClient getRetrofitClient(){
        if(retrofitClient == null){
            retrofitClient = new RetrofitClient();
        }
        return retrofitClient;
    }

    public ServerEndPoints getServerEndPoints(){
       return serverEndPoints;
    }
}
