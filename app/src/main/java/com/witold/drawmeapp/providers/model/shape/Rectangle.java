package com.witold.drawmeapp.providers.model.shape;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;

import com.snatik.polygon.Point;
import com.snatik.polygon.Polygon;
import com.witold.drawmeapp.providers.model.FillColor;

import java.io.Serializable;

import timber.log.Timber;

/**
 * Created by Witold on 10.03.2018.
 */

public class Rectangle extends Shape implements Serializable {
    private float width;
    private float height;
    private float canvasHeight;

    public Rectangle(int x, int y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Canvas canvas) {
        Path path = new Path();
        Paint paint = new Paint();
        this.canvasHeight = canvas.getHeight();
        path.moveTo(getX(),   getY());
        path.lineTo(getX() + getWidth(),  getY());
        path.lineTo(getX() + getWidth(), (getY() + getHeight()));
        path.lineTo(getX(),  (getY() + getHeight()));
        path.lineTo(getX(), getY());
        if(this.fillColor!=null){
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(this.fillColor.getColor());
            canvas.drawPath(path, paint);
        }
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5.0f);
        canvas.drawPath(path, paint);

    }

    @Override
    public boolean contains(float x, float y, FillColor fillColor) {
        if(buildPolygon().contains(new Point(x, this.canvasHeight - y ))){
            this.fillColor = fillColor;
            return true;
        }
        return false;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    private Polygon buildPolygon(){
        Polygon polygon = Polygon.Builder()
                .addVertex(new Point(getX(),this.canvasHeight - getY()))
                .addVertex(new Point(getX() + getWidth(),this.canvasHeight - getY()))
                .addVertex(new Point(getX() + getWidth(), this.canvasHeight - (getY() + getHeight())))
                .addVertex(new Point(getX(), this.canvasHeight - (getY() + getHeight())))
                .build();
        Timber.d(polygon.toString());
        return polygon;
    }
}
