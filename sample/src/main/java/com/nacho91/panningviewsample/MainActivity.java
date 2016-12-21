package com.nacho91.panningviewsample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nacho91.panningview.HorizontalPanning;
import com.nacho91.panningview.PanningView;

/**
 * Created by ignacio on 31/10/16.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PanningView panningView = (PanningView) findViewById(R.id.panning);

        HorizontalPanning horizontalPanning = new HorizontalPanning(HorizontalPanning.RIGHT_TO_LEFT);
        horizontalPanning.setStartXOffset(0.1f);
        horizontalPanning.setEndXOffset(0.9f);

        panningView.setPanning(horizontalPanning);
    }
}
