package com.witold.drawmeapp.providers.model.shape;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.snatik.polygon.Point;
import com.witold.drawmeapp.providers.model.FillColor;

import java.io.Serializable;

/**
 * Created by Witold on 10.03.2018.
 */

public class Circle extends Shape implements Serializable {
    private int radius;

    public Circle(int x, int y, int radius) {
        super(x, y);
        this.radius = radius;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();

        if(this.fillColor!=null){
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(this.fillColor.getColor());
            canvas.drawCircle(getX(), getY(), this.radius, paint);
        }
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5.0f);
        canvas.drawCircle(getX(), getY(), this.radius, paint);
    }

    @Override
    public boolean contains(float x, float y, FillColor fillColor) {
        double distanceX = x - getX();
        double distanceY = y - getY();
        if(Math.pow(distanceX,2.0) + Math.pow(distanceY,2.0) < Math.pow(this.radius,2.0)){
            this.fillColor = fillColor;
            return true;
        }
        return false;
    }
}
