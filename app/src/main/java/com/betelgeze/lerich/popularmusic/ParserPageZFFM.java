package com.betelgeze.lerich.popularmusic;

import android.util.Log;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Lerich on 22.11.2017.
 */

/**
 * Класс возвращающий список треков
 */
public class ParserPageZFFM extends Constants {

    private Document documentPage;
    public ObjectTrack objectTrack;

    private DownloadPage downloadpage = new DownloadPage();
    public List objectTrackList = new ArrayList<>();

    public ParserPageZFFM() {

    }

    /**
     * Наблюдатель "Метод" возвращающий массив (список) треков
     * @param url
     * @param keywords
     * @return
     */
    public Observable getTrackList(final String url, final String keywords) {

        final Observable<List<ObjectTrack>> observable =
                Observable.create(new ObservableOnSubscribe<List<ObjectTrack>>() {
                    @Override
                    public void subscribe(ObservableEmitter<List<ObjectTrack>> e) throws Exception {

                        objectTrackList.clear();

                        documentPage = downloadpage.getPage(url, keywords);

                        objectTrackList = trackList(documentPage);

                        e.onNext(objectTrackList);

                        documentPage = null;

                        e.onComplete();
                    }
                }
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    /*
        Метод парсинга списка треков
     */
    private List trackList(Document documentPage) {
        try {
            Elements searchContainer = documentPage.select(TRACK_GENERIC_CONTAINER);

            Elements itemTrackElements = searchContainer.select(TRACK_ITEM_CONTAINER);
            int numItemTrackElements = searchContainer.select(TRACK_ITEM_CONTAINER).size();
            Log.d("ParserPageZFFM", "num = " + numItemTrackElements);
            for (int i = 0; i < 1; i++) {
                Log.d("ParserPageZFFM", "i = " + i);

                Element trackName = searchContainer.select(TRACK_NAME).get(i);
                Element trackArtistName = searchContainer.select(TRACK_NAME_ARTIST).get(i);
                Element trackTime = searchContainer.select(TRACK_TIME).get(i);
                Element itemTrackElement = itemTrackElements.get(i);
                String trackURL = itemTrackElement.absUrl(TRACK_URL);
                Log.d("ParserPageZFFM", "4" + trackURL);

                objectTrack = new ObjectTrack(//id,
                        trackName.text(),
                        trackArtistName.text(),
                        trackTime.text(),
                        trackURL);

                Log.d("ParserPageZFFM", "10 " + objectTrack.toString());
                objectTrackList.add(objectTrack);

            }
        }  catch (Exception e) {
        e.printStackTrace();
    }
            return objectTrackList;
    }


}
