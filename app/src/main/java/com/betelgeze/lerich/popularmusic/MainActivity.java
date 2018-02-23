package com.betelgeze.lerich.popularmusic;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observer;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;

public class MainActivity extends AppCompatActivity {


  //  private Realm realm;

    private EjectSongsFromBase ejectSongsFromBase = new EjectSongsFromBase();


    protected RealmConfiguration realmConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(this);
        realmConfig = new RealmConfiguration.Builder()
                .name("musicService.realm")
                .schemaVersion(0)
                .build();

        Realm.setDefaultConfiguration(realmConfig);Realm.init(this);


        Presenter presenter = new Presenter("");
      //  deleteTables();


     //   Log.e("Botter", "C = " + ejectSongsFromBase.ejectSongsFromBase(ObjectTrack.class));
     //   Log.d("Pony", "" + presenter.getTrackList());


    }

    /**
     * Получить обьект Realm
     * @return
     *//*
    public Realm getRealm() {
        return realm;
    }

    /**
     * Удаляет таблицы из БД
     */
    public void deleteTables(){
        Realm.deleteRealm(realmConfig); // Удаление таблиц
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

     //   realm.close();
    }
}
