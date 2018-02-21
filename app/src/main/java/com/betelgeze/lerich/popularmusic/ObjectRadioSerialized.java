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
    private List<ObjectRadio> Radio = null;

    ObjectRadioSerialized(List<ObjectRadio> Radio){

        this.Radio = Radio;

    }


    public List<ObjectRadio> getRadios() {
        return Radio;
    }

    public void setRadios(List<ObjectRadio> Radio) {
        this.Radio = Radio;
    }

}


