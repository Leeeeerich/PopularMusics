package com.betelgeze.lerich.popularmusic;

import android.util.Log;

import java.util.List;

import io.realm.Realm;


public class InsertToBase {




    public void insertSongsNamesToBase(List list){

        Realm realm = Realm.getDefaultInstance();
      //  PopSongs popSongs = realm.createObject(PopSongs.class); // Create a new object
        realm.beginTransaction();
        realm.delete(PopSongs.class);

        realm.insert(list);
        realm.commitTransaction();

        Log.e("Botter", "insert " + list);

    }

    public void insertSongsToBase(List list){

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
     //   ObjectTrack objectTrack = realm.createObject(ObjectTrack.class); // Create a new object
        realm.insert(list);
        realm.commitTransaction();
        Log.e("Botter", "insert " + list);

    }

    public void insertSongsToBase(ObjectTrack objectTrack){

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        //   ObjectTrack objectTrack = realm.createObject(ObjectTrack.class); // Create a new object
        realm.insert(objectTrack);
        realm.commitTransaction();
        Log.e("Botter", "insert " + objectTrack);

    }

    public void deleteObjectFromBase(Class class_names) {

        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        realm.delete(class_names);
        realm.commitTransaction();

    }


}
