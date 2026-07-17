package com.example.smartcampusapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    private static final String GITHUB_URL =
            "https://github.com/Farawms/SmartCampusApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Button btnGithub = findViewById(R.id.btnGithub);

        btnGithub.setOnClickListener(view -> {
            try {
                Intent intent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(GITHUB_URL)
                );

                startActivity(intent);

            } catch (ActivityNotFoundException exception) {
                Toast.makeText(
                        AboutActivity.this,
                        "Unable to open the GitHub page.",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}