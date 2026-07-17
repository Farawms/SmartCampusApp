package com.example.smartcampusapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
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

        View cardMember1 = findViewById(R.id.cardMember1);
        View cardMember2 = findViewById(R.id.cardMember2);
        View cardMember3 = findViewById(R.id.cardMember3);
        View cardMember4 = findViewById(R.id.cardMember4);
        View cardMember5 = findViewById(R.id.cardMember5);

        addMemberCardEffect(cardMember1);
        addMemberCardEffect(cardMember2);
        addMemberCardEffect(cardMember3);
        addMemberCardEffect(cardMember4);
        addMemberCardEffect(cardMember5);

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

    private void addMemberCardEffect(View memberCard) {

        memberCard.setOnTouchListener((view, event) -> {

            if (event.getAction() == MotionEvent.ACTION_DOWN) {

                view.animate()
                        .scaleX(1.04f)
                        .scaleY(1.04f)
                        .translationY(-6f)
                        .translationZ(18f)
                        .setDuration(120)
                        .start();

            } else if (event.getAction() == MotionEvent.ACTION_UP
                    || event.getAction() == MotionEvent.ACTION_CANCEL) {

                view.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .translationY(0f)
                        .translationZ(0f)
                        .setDuration(160)
                        .start();

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    view.performClick();
                }
            }

            return true;
        });
    }
}