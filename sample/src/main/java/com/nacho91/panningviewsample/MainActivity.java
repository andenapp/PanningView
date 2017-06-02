package com.nacho91.panningviewsample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.Toast;

import com.nacho91.panningview.HorizontalPanning;
import com.nacho91.panningview.Panning;
import com.nacho91.panningview.PanningView;
import com.nacho91.panningview.VerticalPanning;

/**
 * Created by ignacio on 31/10/16.
 */

public class MainActivity extends AppCompatActivity {

    private PanningView subway;

    private Button start;
    private Button pause;
    private Button resume;

    private Button horizontal;
    private Button vertical;

    @Override
    protected void onCreate(@org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subway = (PanningView) findViewById(R.id.train);

        subway.setPanningListener(new PanningView.PanningListener() {
            @Override
            public void onPanningStart(PanningView panningView) {
                Toast.makeText(MainActivity.this, "Panning Start", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPanningEnd(PanningView panningView) {
                Toast.makeText(MainActivity.this, "Panning Finish", Toast.LENGTH_SHORT).show();
            }
        });

        start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subway.start();
            }
        });

        pause = (Button) findViewById(R.id.pause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subway.pause();
            }
        });

        resume = (Button) findViewById(R.id.resume);
        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subway.resume();
            }
        });

        vertical = (Button) findViewById(R.id.vertical);
        vertical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VerticalPanning verticalPanning = new VerticalPanning(VerticalPanning.BOTTOM_TO_TOP);
                verticalPanning.setStartYOffset(0.5f);
                verticalPanning.setEndYOffset(0.5f);

                subway.setPanning(verticalPanning );
            }
        });

        horizontal = (Button) findViewById(R.id.horizontal);
        horizontal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HorizontalPanning verticalPanning = new HorizontalPanning(HorizontalPanning.LEFT_TO_RIGHT);
                verticalPanning.setStartYOffset(0.5f);
                verticalPanning.setEndYOffset(0.5f);

                subway.setPanning(verticalPanning );
            }
        });
    }
}
