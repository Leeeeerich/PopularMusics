package com.betelgeze.lerich.popularmusic;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lerich on 12.02.18.
 */

public class ObjectRadioSend {

    private Retrofit retrofit;
    private ObjectRadioAPI objectRadioAPI;
    private List radios = new ArrayList<>();


    public void sendObjectRadio(List objectRadioList){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(ObjectRadioAPI.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        objectRadioAPI = retrofit.create(ObjectRadioAPI.class);

        parseArray(objectRadioList);

        ObjectRadioSerialized objectRadioSerialized = new ObjectRadioSerialized(radios);


        Call<ServerResponse> call = objectRadioAPI.login(objectRadioSerialized);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });

    }

    public void parseArray(List<ObjectRadio> list){
        for(int i = 0; i < list.size(); i++) {
            radios.add(new ObjectRadio(
                    list.get(i).getRadioName(),
                    list.get(i).getRadioCity(),
                    list.get(i).getImgURL(),
                    list.get(i).getRadioURL()));
        }
    //    Log.e("Soryan ", "" + radios.get(5));
    }

}
