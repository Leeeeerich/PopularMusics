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
 * Класс возвращающий список радиостанций
 */
public class ParserRadioList extends Constants {

    private Document documentPage;
    public String keywords = "";
    public String url = "http://m.zk.fm/radio";
    public ObjectRadio objectRadio;

    private DownloadPage downloadpage = new DownloadPage();
    public List objectTrackList = new ArrayList<>();


    public ParserRadioList() {

    }

    /**
     *
     * Наблюдатель "Метод" возвращающий массив (список) треков
//     * @param url
//     * @param keywords
     * @return
     */
    public Observable getTrackList() {

        final Observable<List<ObjectTrack>> observable =
                Observable.create(new ObservableOnSubscribe<List<ObjectTrack>>() {
                    @Override
                    public void subscribe(ObservableEmitter<List<ObjectTrack>> e) throws Exception {
                        //Use onNext to emit each item in the stream//
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
            Elements searchContainer = documentPage.select("div [class=\"tracks radio\"]");
            Log.d("ParserPageZFFM", "num = " + searchContainer);


            Elements itemTrackElements = searchContainer.select("div [class=\"tracks-item\"]");
            int numItemTrackElements = searchContainer.select("div [class=\"tracks-item\"]").size();
            Log.d("ParserPageZFFM", "num = " + numItemTrackElements);
            for (int i = 0; i < 155; i++) {
                Log.d("ParserPageZFFM", "i = " + i);

                Element radioName = searchContainer.select("div [class=\"b-line-title\"]").get(i);
                Element radioCity = searchContainer.select("div [class=\"radio-city\"]").get(i);

                Element itemTrackElement = itemTrackElements.get(i);
                String imgURL = itemTrackElement.absUrl("data-icon");

                String radioURL = itemTrackElement.absUrl("data-url");
                Log.d("ParserPageZFFM", "4 " + radioURL + "\n " + imgURL);

                objectRadio = new ObjectRadio(
                        radioName.text(),
                        radioCity.text(),
                        imgURL,
                        radioURL
                          );

                Log.d("ParserPageZFFM", "10 " + objectRadio.toString());
                objectTrackList.add(objectRadio);

            }
        }  catch (Exception e) {
        e.printStackTrace();
    }
            return objectTrackList;
    }


}
