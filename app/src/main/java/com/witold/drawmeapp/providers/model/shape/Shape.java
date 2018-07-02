package com.witold.drawmeapp.providers.model.shape;

import android.graphics.Canvas;

import com.witold.drawmeapp.providers.model.FillColor;

import java.io.Serializable;

/**
 * Created by Witold on 09.03.2018.
 */

public abstract class Shape implements Serializable{
    private int x;
    private int y;
    protected FillColor fillColor;

    public Shape(int x, int y){
        this.x = x;
        this.y = y;
        this.fillColor = new FillColor("#FFFFFF");
    }

    public abstract void draw(Canvas canvas);
    public abstract boolean contains(float x, float y, FillColor fillColor);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
