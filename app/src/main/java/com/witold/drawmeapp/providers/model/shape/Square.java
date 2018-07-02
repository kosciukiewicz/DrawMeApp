package com.witold.drawmeapp.providers.model.shape;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import com.snatik.polygon.Point;
import com.snatik.polygon.Polygon;
import com.witold.drawmeapp.providers.model.shape.Rectangle;
import com.witold.drawmeapp.providers.model.shape.Shape;

import timber.log.Timber;
public class Square extends Rectangle {
    public Square(int x, int y, int side) {
        super(x, y, side, side);
    }
}
