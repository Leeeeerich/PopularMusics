package com.betelgeze.lerich.popularmusic;


import io.realm.RealmObject;

/**
 * Объект треков
 */
public class ObjectTrack extends RealmObject {

    private String trackName;
    private String trackArtist;
    private String trackTime;
    private String trackURL;

    public ObjectTrack() {

    }

    public ObjectTrack(
                       String trackName,
                       String trackArtist,
                       String trackTime,
                       String trackURL) {

        this.trackName = trackName;
        this.trackArtist = trackArtist;
        this.trackTime = trackTime;
        this.trackURL = trackURL;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName() {
        this.trackName = trackName;
    }

    public String getTrackArtist() {
        return trackArtist;
    }

    public void setTrackArtist() {
        this.trackArtist = trackArtist;
    }

    public String getTrackTime() {
        return trackTime;
    }

    public void setTrackTime() {
        this.trackTime = trackTime;
    }

    public String getTrackURL() {
        return trackURL;
    }

    public void setTrackURL() {
        this.trackURL = trackURL;
    }
}
