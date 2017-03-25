package com.nacho91.panningviewsample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.Toast;

import com.nacho91.panningview.HorizontalPanning;
import com.nacho91.panningview.PanningView;
import com.nacho91.panningview.VerticalPanning;

/**
 * Created by ignacio on 31/10/16.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final PanningView subway = (PanningView) findViewById(R.id.train);

        subway.setPanningListener(new PanningView.PanningListener() {
            @Override
            public void onPanningEnd(PanningView panningView) {
                Toast.makeText(MainActivity.this, "Finish", Toast.LENGTH_SHORT).show();
            }
        });

        HorizontalPanning subwayPanning = new HorizontalPanning(HorizontalPanning.RIGHT_TO_LEFT);
        subwayPanning.setStartXOffset(1);
        subwayPanning.setEndXOffset(0.5f);
        subwayPanning.setInterpolator(new DecelerateInterpolator());

        subway.setPanning(subwayPanning);
   }
}
