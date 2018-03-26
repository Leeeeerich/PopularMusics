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
 * Класс который возвращает список
 * названий новинок треков
 */
public class ParserNoveltyTracks extends Constants {

    private Document documentPage;
    private Elements elements;
    public String keywords = "";
    public String url = "http://nrj.ua/programs/novelties";
    public String userAgent = null;
    public PopSongs popSongs;


    public List objectTrackList = new ArrayList<>();
  //  public objectTrackList = new ArrayList<>();

    public int numberOfItems = 0;


    public ParserNoveltyTracks() {

    }

    /*
     *
     * Наблюдатель "Метод" возвращающий массив (список) треков
     * @param url
     * @param keywords
     * @return
     */
    public Observable getTrackList() {

        final Observable<List<PopSongs>> observable =
                Observable.create(new ObservableOnSubscribe<List<PopSongs>>() {
                    @Override
                    public void subscribe(ObservableEmitter<List<PopSongs>> e) throws Exception {
                        //Use onNext to emit each item in the stream//

                        Log.d("ParserPageZFFM", "1 " + url + keywords);
                        DownloadPage downloadpage = new DownloadPage();
                        documentPage = downloadpage.getPage(url, keywords);
                        Log.d("ParserPageZFFM", "2" + documentPage);
                        Log.e("RxJavaX", "onSubscribe: 12");
                        objectTrackList = trackList(documentPage);
                        Log.e("RxJavaX", "onSubscribe: 123456789" + objectTrackList);
                        e.onNext(objectTrackList);

                        //Once the Observable has emitted all items in the sequence, call onComplete//
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

            Elements searchContainer = documentPage.select("div [class=\"box-hold\"]");
          //  player-in-playlist-holder
         //   Elements elementsNumberOfItems = documentPage.select("b.grey");
        //    numberOfItems = Integer.parseInt(elementsNumberOfItems.text());
        //    Log.e("RxJavaX", "numberOfItems = " + numberOfItems);
            Log.d("ParserPageZFFM", "2.1 " + searchContainer);

            Elements itemTrackElements = searchContainer.select("div [class=\"player-in-playlist-holder\"]");
            Log.d("ParserPageZFFM", "2.2 " + itemTrackElements);

            int numItemTrackElements = searchContainer.select("div [class=\"player-in-playlist-holder\"]").size();
            Log.d("ParserPageZFFM", "num = " + numItemTrackElements);

            for (int i = 0; i < numItemTrackElements; i++) {
                Log.d("ParserPageZFFM", "i = " + i);

                //Блок в котором находятся блоки с названием трека и именем исполнителя
                Element trackFullName = searchContainer.select("div [class=\"jp-title\"]").get(i);

                //Блок названия трека
                Element trackName = trackFullName.select("div [class=\"ajax\"]").first();

                //Блок имени исполнителя
                Element trackArtistName = trackFullName.select("span").first();
/*                Element trackTime = searchContainer.select(TRACK_TIME).get(i);
                Element itemTrackElement = itemTrackElements.get(i);
                String trackURL = itemTrackElement.absUrl(TRACK_URL);
                Log.d("ParserPageZFFM", "4" + trackURL);
                */



                Log.d("ParserPageZFFM", "3 = " + trackFullName.text());
                popSongs = new PopSongs(
                        trackName.text(),
                        trackArtistName.text());
/*
                Uri uri;
                uri = Uri.parse(trackURL);
*/

                Log.d("ParserPageZFFM", "10 " + popSongs.getTrackArtist());
                objectTrackList.add(popSongs);
            }
        }  catch (Exception e) {
        e.printStackTrace();
    }
        Log.d("ParserPageZFFM", "11 " + objectTrackList);
            return objectTrackList;
    }

}
