package com.witold.drawmeapp.providers.model;

import android.graphics.Color;

import java.io.Serializable;

/**
 * Created by Witold on 09.03.2018.
 */

public class FillColor implements Serializable {
    private int color;

    public FillColor(String hexColorString) {
        this.color = Color.parseColor(hexColorString);
    }

    public int getColor() {
        return color;
    }
}
