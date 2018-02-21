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

public class ObjectTrackSend {

    private Retrofit retrofit;
    private ObjectTrackAPI objectTrackAPI;
    private List tracks = new ArrayList<>();


    public void sendObjectTrack(List objectTrackList){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(ObjectTrackAPI.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        objectTrackAPI = retrofit.create(ObjectTrackAPI.class);

        parseArray(objectTrackList);

        ObjectTrackSerialized objectTrackSerialized = new ObjectTrackSerialized(tracks);


        Call<ServerResponse> call = objectTrackAPI.login(objectTrackSerialized);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });

      //  return rootView;
    }

    public void parseArray(List<ObjectTrack> list){
        for(int i = 0; i < list.size(); i++) {
            tracks.add(new ObjectTrack(list.get(i).getTrackName(),
                    list.get(i).getTrackArtist(),
                    list.get(i).getTrackTime(),
                    list.get(i).getTrackURL()));
        }
    }

}
