package com.witold.drawmeapp;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.witold.drawmeapp.fill_color_list.FillColorListHolder;
import com.witold.drawmeapp.fill_color_list.FillColorListRecyclerViewAdapter;
import com.witold.drawmeapp.providers.FillColorProvider;
import com.witold.drawmeapp.providers.ShapeProvider;
import com.witold.drawmeapp.providers.model.FillColor;
import com.witold.drawmeapp.providers.model.shape.Shape;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements FillColorListHolder {
    public static final String CURRENT_FILL_COLOR = "currentFillColor";
    public static final String SHAPE_LIST = "shapeList";
    private ShapeProvider shapeProvider;
    private FillColorProvider fillColorProvider;
    private List<FillColor> colorList;
    private ArrayList<Shape> shapes;

    private DrawingSurfaceView drawingSurfaceView;
    private RecyclerView recyclerView;
    private FrameLayout frameLayoutColorHolder;
    private FillColor currentFillColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.injectFields();
        this.initializeComponents();
        this.getColorList();
        this.initializeFillColorList();
        if(savedInstanceState!=null){
            this.currentFillColor =(FillColor) savedInstanceState.getSerializable(CURRENT_FILL_COLOR);
            if(this.currentFillColor!=null){
                this.frameLayoutColorHolder.setBackgroundColor(this.currentFillColor.getColor());
            }

            this.shapes =(ArrayList<Shape>) savedInstanceState.getSerializable(SHAPE_LIST);
            if(this.shapes!=null){
                this.drawingSurfaceView.setShapeList(this.shapes);
            }
        }
    }

    @Override
    public void onFillColorClick(FillColor fillColor) {
        frameLayoutColorHolder.setBackgroundColor(fillColor.getColor());
        this.currentFillColor = fillColor;
        this.drawingSurfaceView.setFillColor(fillColor);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(CURRENT_FILL_COLOR, this.currentFillColor);
        outState.putSerializable(SHAPE_LIST, drawingSurfaceView.getShapeList());
        super.onSaveInstanceState(outState);
    }

    private void injectFields() {
        DrawMeApplication drawMeInjector = (DrawMeApplication) getApplicationContext();
        this.shapeProvider = drawMeInjector.provideShapeProvider();
        this.fillColorProvider = drawMeInjector.provideFillColorProvider();
    }

    private void getColorList() {
        this.colorList = fillColorProvider.getAllColors();
        this.currentFillColor = this.colorList.get(0);
        frameLayoutColorHolder.setBackgroundColor(currentFillColor.getColor());
    }

    private void initializeComponents() {
        this.recyclerView = findViewById(R.id.recyclerView);
        this.frameLayoutColorHolder = findViewById(R.id.mainActivityFrameLayout);
        this.drawingSurfaceView = findViewById(R.id.drawingSurfaceView);
    }

    private void initializeFillColorList() {
        FillColorListRecyclerViewAdapter fillColorListRecyclerViewAdapter = new FillColorListRecyclerViewAdapter(this, this.colorList);
        LinearLayoutManager layoutManager;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager
                    = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        } else {
            layoutManager
                    = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(fillColorListRecyclerViewAdapter);
    }
}
