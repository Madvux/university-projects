package com.example.projekt4;

import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private PaintView paintView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        paintView = findViewById(R.id.paintView);
        Button button_color_green = findViewById(R.id.button_color_green);
        Button button_color_blue = findViewById(R.id.button_color_blue);
        Button button_color_red = findViewById(R.id.button_color_red);
        Button button_color_yellow = findViewById(R.id.button_color_yellow);
        Button button_clear_screen = findViewById(R.id.button_clear_screen);

        button_color_green.setOnClickListener(view -> paintView.setColor(Color.parseColor("#FF669900")));
        button_color_blue.setOnClickListener(view -> paintView.setColor(Color.parseColor("#FF33B5E5")));
        button_color_red.setOnClickListener(view -> paintView.setColor(Color.parseColor("#FFCC0000")));
        button_color_yellow.setOnClickListener(view -> paintView.setColor(Color.parseColor("#FFFFBB33")));
        button_clear_screen.setOnClickListener(view -> paintView.clear());

        DisplayMetrics displayMetrics = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        paintView.initialise(displayMetrics);

    }
}