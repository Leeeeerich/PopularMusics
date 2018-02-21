package com.betelgeze.lerich.popularmusic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lerich on 12.02.18.
 */

public class ObjectTrackSerialized {

    @SerializedName("Tracks")
    @Expose
    private List<ObjectTrack> tracks = null;

    ObjectTrackSerialized (List<ObjectTrack> tracks){

        this.tracks = tracks;

    }


    public List<ObjectTrack> getTracks() {
        return tracks;
    }

    public void setTracks(List<ObjectTrack> tracks) {
        this.tracks = tracks;
    }

}


