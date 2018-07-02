package com.witold.drawmeapp.providers.model.shape;

import com.snatik.polygon.Point;

import java.io.Serializable;

/**
 * Created by Witold on 14.03.2018.
 */

public class SerializablePoint implements Serializable {
    private int pointX;
    private int pointY;

    public SerializablePoint(int pointX, int pointY) {
        this.pointX = pointX;
        this.pointY = pointY;
    }

    public int getPointX() {
        return pointX;
    }

    public int getPointY() {
        return pointY;
    }

    public Point read(){
        return new Point(pointX, pointY);
    }
}
