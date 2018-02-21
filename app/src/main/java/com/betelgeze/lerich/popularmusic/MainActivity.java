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


    private Realm realm;

    private EjectSongsFromBase ejectSongsFromBase = new EjectSongsFromBase();
    private Presenter presenter = new Presenter();

    public List popularSongs = new ArrayList();
    public List radioList = new ArrayList();

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

      //  deleteTables();
       // updateSongsList();
       // updateRadioList();


        ObjectTrackSend objectTrackSend = new ObjectTrackSend();
        ObjectRadioSend objectRadioSend = new ObjectRadioSend();
/*
        getPopularSongs();
        objectTrackSend.sendObjectTrack(popularSongs);
        */
        getRadioList();
        objectRadioSend.sendObjectRadio(radioList);

      //      Log.e("RxJavaX", "Данные обновлены" + ejectSongsFromBase.ejectSongsFromBase(ObjectTrack.class).get(0).getTrackName());






        Log.e("Botter", "C = " + ejectSongsFromBase.ejectSongsFromBase(ObjectTrack.class));
     //   Log.d("Pony", "" + presenter.getTrackList());


    }

    /**
     * Обновление списка песен
     * @return true = идёт процесс обновления
     */

    public boolean updateSongsList() {
        presenter.updateSongsList();
        return presenter.progressUpdateList;
    }

    /**
     * Оновление списка Радио станций
     * @return true = идёт процесс обновления
     */

    public boolean updateRadioList() {
        presenter.updateRadioList();
        return presenter.progressUpdateList;
    }

    /**
     * Возвращает список радиостанций
     * @return List
     */

    public List getRadioList() {
        radioList = ejectSongsFromBase.ejectSongsFromBase(ObjectRadio.class);
        return radioList;
    }

    /**
     * Возвращает список песен
     * @return List
     */

    public List getPopularSongs() {
        popularSongs = ejectSongsFromBase.ejectSongsFromBase(ObjectTrack.class);
        return popularSongs;
    }

    /**
     * Получить обьект Realm
     * @return
     */

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

     //  realm.close();
    }
}
