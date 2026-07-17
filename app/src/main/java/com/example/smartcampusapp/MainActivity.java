package com.example.smartcampusapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnViewMap = findViewById(R.id.btnViewMap);
        Button btnAbout = findViewById(R.id.btnAbout);

        btnViewMap.setOnClickListener(view -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    MapActivity.class
            );
            startActivity(intent);
        });

        btnAbout.setOnClickListener(view -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    AboutActivity.class
            );
            startActivity(intent);
        });
    }
}