package com.example.smartcampusapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore db;

    private TextView tvNewsTitle;
    private TextView tvNewsDescription;
    private TextView tvNewsMeta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();

        tvNewsTitle = findViewById(R.id.tvNewsTitle);
        tvNewsDescription = findViewById(R.id.tvNewsDescription);
        tvNewsMeta = findViewById(R.id.tvNewsMeta);

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

        loadLatestNews();
    }

    private void loadLatestNews() {

        db.collection("campus_news")
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .limit(1)
                .get()
                .addOnSuccessListener(querySnapshot -> {

                    if (querySnapshot.isEmpty()) {
                        tvNewsTitle.setText("No Campus News");
                        tvNewsDescription.setText(
                                "No campus announcements are currently available."
                        );
                        tvNewsMeta.setText("SmartCampus");
                        return;
                    }

                    DocumentSnapshot document =
                            querySnapshot.getDocuments().get(0);

                    String title =
                            document.getString("title");

                    String description =
                            document.getString("description");

                    String category =
                            document.getString("category");

                    Timestamp timestamp =
                            document.getTimestamp("timestamp");

                    if (title == null || title.trim().isEmpty()) {
                        title = "Campus Update";
                    }

                    if (description == null || description.trim().isEmpty()) {
                        description = "No description available.";
                    }

                    if (category == null || category.trim().isEmpty()) {
                        category = "Announcement";
                    }

                    String dateText = "Date unavailable";

                    if (timestamp != null) {
                        SimpleDateFormat dateFormat =
                                new SimpleDateFormat(
                                        "dd MMM yyyy, hh:mm a",
                                        Locale.getDefault()
                                );

                        dateText = dateFormat.format(timestamp.toDate());
                    }

                    tvNewsTitle.setText(title);
                    tvNewsDescription.setText(description);
                    tvNewsMeta.setText(
                            category + " • " + dateText
                    );
                })
                .addOnFailureListener(exception -> {

                    Log.e(
                            "Firestore",
                            "Failed to retrieve campus news",
                            exception
                    );

                    tvNewsTitle.setText("Unable to Load News");
                    tvNewsDescription.setText(
                            "Please check your internet connection and try again."
                    );
                    tvNewsMeta.setText("SmartCampus");
                });
    }
}