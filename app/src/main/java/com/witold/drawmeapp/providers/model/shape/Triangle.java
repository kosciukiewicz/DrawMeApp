package com.witold.drawmeapp.providers.model.shape;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import com.snatik.polygon.Polygon;
import com.witold.drawmeapp.providers.model.FillColor;

import java.io.Serializable;

import timber.log.Timber;

/**
 * Created by Witold on 10.03.2018.
 */

public class Triangle extends Shape implements Serializable {
    private SerializablePoint point1;
    private SerializablePoint point2;
    private SerializablePoint point3;
    private float canvasHeight;

    public Triangle(Point point1, Point point2, Point point3) {
        super( point1.x, point1.y);
        this.point1 = new SerializablePoint(point1.x, point1.y);
        this.point2 = new SerializablePoint(point2.x, point2.y);
        this.point3 = new SerializablePoint(point3.x, point3.y);
    }

    @Override
    public void draw(Canvas canvas) {
        Path path = new Path();
        Paint paint = new Paint();
        this.canvasHeight = canvas.getHeight();
        path.moveTo((float) this.point1.getPointX(), (float) this.point1.getPointY());
        path.lineTo((float) this.point2.getPointX(), (float) this.point2.getPointY());
        path.lineTo((float) this.point3.getPointX(), (float) this.point3.getPointY());
        path.lineTo((float) this.point1.getPointX(), (float) this.point1.getPointY());
        if (this.fillColor != null) {
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
        Timber.d("asdasd" + x + " " + (this.canvasHeight - y));
        if (buildPolygon().contains(new com.snatik.polygon.Point(x, this.canvasHeight - (int)y))) {
            this.fillColor = fillColor;
            return true;
        }
        return false;
    }

    private Polygon buildPolygon() {
        Polygon polygon = Polygon.Builder()
                .addVertex(new com.snatik.polygon.Point(point1.getPointX(), this.canvasHeight - point1.getPointY()))
                .addVertex(new com.snatik.polygon.Point(point2.getPointX(), this.canvasHeight - point2.getPointY()))
                .addVertex(new com.snatik.polygon.Point(point3.getPointX(), this.canvasHeight - point3.getPointY()))
                .build();
        Timber.d(polygon.toString());
        return polygon;
    }
}
