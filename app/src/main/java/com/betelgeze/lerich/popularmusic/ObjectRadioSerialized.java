package com.betelgeze.lerich.popularmusic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lerich on 12.02.18.
 */

public class ObjectRadioSerialized {

    @SerializedName("Radio")
    @Expose
    private List<ObjectRadio> radio = null;

    ObjectRadioSerialized(List<ObjectRadio> radio){

        this.radio = radio;

    }


    public List<ObjectRadio> getRadios() {
        return radio;
    }

    public void setRadios(List<ObjectRadio> radio) {
        this.radio = radio;
    }

}


