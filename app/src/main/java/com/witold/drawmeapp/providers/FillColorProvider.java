package com.witold.drawmeapp.providers;

import android.graphics.Color;

import com.witold.drawmeapp.providers.model.FillColor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Witold on 09.03.2018.
 */

public class FillColorProvider {
    private List<FillColor> colorList;

    public FillColorProvider() {
        this.colorList = new ArrayList<>();
        this.colorList.add(new FillColor("#FFFFFF"));
        this.colorList.add(new FillColor("#EF5350"));
        this.colorList.add(new FillColor("#C62828"));
        this.colorList.add(new FillColor("#AB47BC"));
        this.colorList.add(new FillColor("#7B1FA2"));
        this.colorList.add(new FillColor("#3F51B5"));
        this.colorList.add(new FillColor("#303F9F"));
        this.colorList.add(new FillColor("#1E88E5"));
        this.colorList.add(new FillColor("#00897B"));
        this.colorList.add(new FillColor("#9CCC65"));
        this.colorList.add(new FillColor("#689F38"));
        this.colorList.add(new FillColor("#FFEB3B"));
        this.colorList.add(new FillColor("#FF8F00"));
        this.colorList.add(new FillColor("#D84315"));
        this.colorList.add(new FillColor("#5D4037"));
        this.colorList.add(new FillColor("#212121"));
    }

    public List<FillColor> getAllColors(){
        return this.colorList;
    }
}
