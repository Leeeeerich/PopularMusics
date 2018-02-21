package com.betelgeze.lerich.popularmusic;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;



public class EjectSongsFromBase {

    public List<PopSongs> ejectSongsNamesFromBase(Class class_name){

        Realm realm = Realm.getDefaultInstance();

        RealmResults<PopSongs> result = realm.where(class_name)

                .findAll();

        return  result;
    }

    public List<ObjectTrack> ejectSongsFromBase(Class class_name){

        Realm realm = Realm.getDefaultInstance();

        RealmResults<ObjectTrack> result = realm.where(class_name)

                .findAll();

        return  result;
    }



}
