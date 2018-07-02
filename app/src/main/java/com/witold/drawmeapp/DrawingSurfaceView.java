package com.witold.drawmeapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.witold.drawmeapp.providers.model.FillColor;
import com.witold.drawmeapp.providers.model.shape.Square;
import com.witold.drawmeapp.providers.model.shape.Circle;
import com.witold.drawmeapp.providers.model.shape.Rectangle;
import com.witold.drawmeapp.providers.model.shape.Shape;
import com.witold.drawmeapp.providers.model.shape.Triangle;

import java.util.ArrayList;

import timber.log.Timber;

/**
 * Created by Witold on 10.03.2018.
 */

public class DrawingSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private Context context;
    private Rectangle rectangle1;
    private Rectangle rectangle2;
    private FillColor fillColor;
    private ArrayList<Shape> shapeList;

    public DrawingSurfaceView(Context context) {
        super(context);
        this.context = context;
        this.shapeList = new ArrayList<>();
        this.initializeDrawing();
        setWillNotDraw(false);
    }

    public DrawingSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.shapeList = new ArrayList<>();
        this.initializeDrawing();
        setWillNotDraw(false);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        for(Shape shape : shapeList){
            shape.draw(canvas);
        }
        for(int i =shapeList.size() - 1; i >= 0; i--){
            shapeList.get(i).draw(canvas);
        }
        this.invalidate();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Canvas canvas = null;
        try {
            canvas = this.getHolder().lockCanvas();
            this.draw(canvas);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (canvas != null) {
                // Unlock Canvas.
                this.getHolder().unlockCanvasAndPost(canvas);
            }
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.onTouchEvent(fillColor, event);
        return super.onTouchEvent(event);
    }

    public boolean onTouchEvent(FillColor fillColor, MotionEvent event) {
        Timber.d(event.getX() + " " + event.getY());
        if (fillColor != null) {
            boolean result = false;
            for(int i =0; i < shapeList.size() && !result; i++){
                result = shapeList.get(i).contains(event.getX(), event.getY(), fillColor);
            }
            Canvas canvas = null;
            try {
                canvas = this.getHolder().lockCanvas();
                this.draw(canvas);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    // Unlock Canvas.
                    this.getHolder().unlockCanvasAndPost(canvas);
                }
            }
        }

        return super.onTouchEvent(event);
    }

    private void initializeDrawing() {
        this.getHolder().addCallback(this);
        this.setBackgroundColor(Color.parseColor("#FFFFFF"));
        this.shapeList.add(new Rectangle(70, 800, 100, 200));
        this.shapeList.add(new Square(220, 730, 100));
        this.shapeList.add(new Circle(205, 520, 40));
        this.shapeList.add(new Circle(120, 200, 100));
        this.shapeList.add(new Circle(450, 200, 90));
        this.shapeList.add(new Circle(700, 950, 40));
        this.shapeList.add(new Circle(850, 950, 40));
        this.shapeList.add(new Triangle(new Point(750, 860), new Point(750, 800), new Point(660, 860)));
        this.shapeList.add(new Rectangle(10, 600, 400, 400));
        this.shapeList.add(new Rectangle(600, 860, 150, 60));
        this.shapeList.add(new Rectangle(750, 800, 200, 120));
        this.shapeList.add(new Triangle(new Point(10, 600), new Point(205, 400), new Point(410, 600)));
        this.shapeList.add(new Rectangle(-50, -50, 2000, 600));
        this.shapeList.add(new Rectangle(-50, 550, 2000, 1500));
      /*  this.shapeList.add(new Square(400, 600, 300));
        this.shapeList.add(new Circle(500, 500, 120));*/
    }

    public void setFillColor(FillColor fillColor){
        this.fillColor = fillColor;
    }

    public ArrayList<Shape> getShapeList() {
        return shapeList;
    }

    public void setShapeList(ArrayList<Shape> shapeList) {
        this.shapeList = shapeList;
    }
}
