package com.betelgeze.lerich.popularmusic;

import io.realm.RealmObject;

/**
 * Объект названий популярных треков
 */
public class PopSongs extends RealmObject {

    private String trackName;
    private String trackArtist;


    public PopSongs() {

    }

    public PopSongs(
            String trackName,
            String trackArtist) {

        this.trackName = trackName;
        this.trackArtist = trackArtist;
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

}
