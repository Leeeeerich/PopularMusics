package com.betelgeze.lerich.popularmusic;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class Presenter {

    private List<ObjectTrack> listSongsNames = new ArrayList<>();
    private List popularSongs = new ArrayList();
    private List radioList = new ArrayList();

    private boolean updateSongsNamesBase = false;
    private EjectSongsFromBase ejectSongsFromBase = new EjectSongsFromBase();
    private InsertToBase insertToBase = new InsertToBase();
    private ParserPopularTracks parserPopularTracks = new ParserPopularTracks();
    private ParserPageZFFM parserPageZFFM = new ParserPageZFFM();
    private ParserRadioList parserRadioList = new ParserRadioList();
    private ObjectTrackSend objectTrackSend = new ObjectTrackSend();
    private ObjectRadioSend objectRadioSend = new ObjectRadioSend();
    private int numNamesSong = 1;
    private int sizeOfList;
    public boolean progressUpdateList = false;

    public Presenter() {}

    public Presenter(String s) {/*
        updateSongsList();
        sendTrackList(getPopularSongs());
        updateRadioList();
        sendRadioList(getRadioList());*/
    }

    /**
     * Обновление списка популярных треков
     * @return - Возвращает прогресс
     */
    public boolean updateSongsList(){
        progressUpdateList = true;
        listSongsNames();
        Log.e("ParserPageZFFM", "15: " + listSongsNames);
        return progressUpdateList;

    }

    /**
     * Получение списка популярных треков
     *
     * onComplete - Вызывает listSong и
     *              передаёт первое название
     *              песни из списка (keywords)
     */
    private void listSongsNames (){

        Observer<List<PopSongs>> observer = new Observer<List<PopSongs>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("RxJavaX", "onSubscribe: ");
            }

            @Override
            public void onNext(List<PopSongs> value) {

                if (ejectSongsFromBase.ejectSongsNamesFromBase(PopSongs.class).equals(value)){
                    Log.e("RxJavaX", "Данные не обновлены");
                    updateSongsNamesBase = false;
                } else {

                    insertToBase.insertSongsNamesToBase(value);
                    String ist = "10", iht = "10";

                    Log.e("RxJavaX", "Данные обновлены" + ejectSongsFromBase.ejectSongsNamesFromBase(PopSongs.class).get(0).getTrackName() +
                            "\n  " + ejectSongsFromBase.ejectSongsNamesFromBase(PopSongs.class).size() +
                            "\n  " + value.size() +
                            "\n  " + ejectSongsFromBase.ejectSongsNamesFromBase(PopSongs.class).hashCode() +
                            "\n  " + value.hashCode() +
                            "\n  " + ist.hashCode() +
                            "\n  " + iht.hashCode());

                    updateSongsNamesBase = true;
                }

                Log.e("RxJavaX", "onNext: " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("RxJavaX", "onError: ");
            }

            @Override
            public void onComplete() {

                insertToBase.deleteObjectFromBase(ObjectTrack.class);
                 if(updateSongsNamesBase == true){
                    listSong(ejectSongsFromBase.ejectSongsNamesFromBase(PopSongs.class).get(0).getTrackArtist() + " " + ejectSongsFromBase.ejectSongsNamesFromBase(PopSongs.class).get(0).getTrackName());
                 }

                sizeOfList = ejectSongsFromBase.ejectSongsNamesFromBase(PopSongs.class).size();
                Log.e("RxJavaX", "onComplete: All Done!" + ejectSongsFromBase.ejectSongsFromBase(PopSongs.class));
            }
        };
//Create our subscription//
        parserPopularTracks.getTrackList().subscribe(observer);
    }

    /**
     * Получение трека по слову (keywords)
     * @param keywords - название трека
     * onComplete - Рекурсия, от количества
     *              треков в списке
     */
    private void listSong (String keywords){

        Log.e("RxJavaX", "keywords: " + keywords);


        Observer<List<ObjectTrack>> observer = new Observer<List<ObjectTrack>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("RxJavaX", "onSubscribe: ");
            }

            @Override
            public void onNext(List value) {

                insertToBase.insertSongsToBase(value);

                Log.e("RxJavaX", "onNext: " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("RxJavaX", "onError: Names");
            }

            @Override
            public void onComplete() {

                if(numNamesSong < sizeOfList){

                    listSong(ejectSongsFromBase.ejectSongsNamesFromBase(PopSongs.class).get(numNamesSong).getTrackArtist() + " " + ejectSongsFromBase.ejectSongsNamesFromBase(PopSongs.class).get(numNamesSong).getTrackName());
                    numNamesSong++;
                }

                Log.e("RxJavaX", "onComplete: All Done! " + ejectSongsFromBase.ejectSongsFromBase(ObjectTrack.class));
                progressUpdateList = false;
            }
        };
//Create our subscription//
        parserPageZFFM.getTrackList(Constants.ZF_FM, keywords).subscribe(observer);
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
     * Обновление списка популярных радиостанций
     * @return - Возвращает прогресс выполнения
     */
    public boolean updateRadioList(){
        progressUpdateList = true;
        listRadio();
        return progressUpdateList;
    }

    /**
     * Получение списка популярных радиостанций
     */
    private void listRadio (){

        Observer<List<ObjectRadio>> observer = new Observer<List<ObjectRadio>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("RxJavaX", "onSubscribe: ");
            //    d.dispose();
            }

            @Override
            public void onNext(List<ObjectRadio> value) {
                insertToBase.deleteObjectFromBase(ObjectRadio.class);
                insertToBase.insertSongsToBase(value);
                Log.e("RxJavaX", "onNext: " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("RxJavaX", "onError: ");
            }

            @Override
            public void onComplete() {
                progressUpdateList = false;
                Log.e("RxJavaX", "onComplete: " + ejectSongsFromBase.ejectSongsFromBase(ObjectRadio.class));

            }
        };
//Create our subscription//
        parserRadioList.getTrackList().subscribe(observer);
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
     * Отправить на сервер список популярных треков
     * @param popularSongs - список отправляемых треков
     */
    public void sendTrackList(List popularSongs){
        objectTrackSend.sendObjectTrack(popularSongs);
    }

    /**
     * Отправить на сервер список радиостанций
     * @param radioList - список отправляемых радиостанций
     */
    public void sendRadioList(List radioList){
        objectRadioSend.sendObjectRadio(radioList);
    }

}
