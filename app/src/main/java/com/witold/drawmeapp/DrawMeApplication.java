package com.witold.drawmeapp;

import android.app.Application;

import com.witold.drawmeapp.providers.FillColorProvider;
import com.witold.drawmeapp.providers.ShapeProvider;

import timber.log.Timber;

/**
 * Created by Witold on 09.03.2018.
 */

public class DrawMeApplication extends Application {
    private FillColorProvider fillColorProvider;
    private ShapeProvider shapeProvider;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeProviders();
        Timber.plant(new Timber.DebugTree());
    }

    private void initializeProviders() {
        this.shapeProvider = new ShapeProvider();
        this.fillColorProvider = new FillColorProvider();
    }

    public FillColorProvider provideFillColorProvider() {
        return fillColorProvider;
    }

    public ShapeProvider provideShapeProvider() {
        return shapeProvider;
    }
}
