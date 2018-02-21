package com.betelgeze.lerich.popularmusic;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by lerich on 12.02.18.
 */

public interface ObjectTrackAPI {

    public static final String BASE_URL = "http://lerich.mkdeveloper.ru/musicPlayer/";

    @POST("getListTracks.php")
    Call<ServerResponse> login(@Body ObjectTrackSerialized objectTrackSerialized);

}
