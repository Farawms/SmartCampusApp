package com.example.smartcampusapp;
import android.view.View;
import android.widget.TextView;
import android.os.Bundle;
import android.util.Log;
import com.google.firebase.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class MapActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Sambungkan aplikasi kepada Firestore
        db = FirebaseFirestore.getInstance();

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                View view = getLayoutInflater().inflate(
                        R.layout.custom_info_window,
                        null
                );

                TextView tvHazardTitle =
                        view.findViewById(R.id.tvHazardTitle);

                TextView tvHazardDetails =
                        view.findViewById(R.id.tvHazardDetails);

                tvHazardTitle.setText(marker.getTitle());
                tvHazardDetails.setText(marker.getSnippet());

                return view;
            }
        });
        // Lokasi asal kamera
        LatLng campusLocation = new LatLng(5.76206, 102.27585);

        googleMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(campusLocation, 15)
        );

        // Ambil hazard daripada Firestore
        db.collection("campus_issues")
                .get()
                .addOnCompleteListener(task -> {

                    if (task.isSuccessful()) {

                        for (QueryDocumentSnapshot document : task.getResult()) {

                            Double latitude =
                                    document.getDouble("latitude");

                            Double longitude =
                                    document.getDouble("longitude");

                            String hazardType =
                                    document.getString("hazardType");

                            String locationName =
                                    document.getString("locationName");

                            String description =
                                    document.getString("description");

                            String reporter =
                                    document.getString("reporter");
                            Timestamp timestamp =
                                    document.getTimestamp("timestamp");

                            String dateTime = "Date unavailable";

                            if (timestamp != null) {
                                SimpleDateFormat dateFormat =
                                        new SimpleDateFormat(
                                                "dd MMM yyyy, hh:mm a",
                                                Locale.getDefault()
                                        );

                                dateTime = dateFormat.format(timestamp.toDate());
                            }

                            if (latitude != null && longitude != null) {

                                LatLng hazardLocation =
                                        new LatLng(latitude, longitude);

                                String markerInfo =
                                        locationName
                                                + "\nDescription: " + description
                                                + "\nReported by: " + reporter
                                                + "\nReported on: " + dateTime;

                                googleMap.addMarker(
                                        new MarkerOptions()
                                                .position(hazardLocation)
                                                .title(hazardType)
                                                .snippet(markerInfo)
                                );
                            }
                        }

                    } else {

                        Exception exception = task.getException();

                        if (exception != null) {
                            Log.e(
                                    "Firestore",
                                    "Gagal mendapatkan data hazard",
                                    exception
                            );
                        } else {
                            Log.e(
                                    "Firestore",
                                    "Gagal mendapatkan data hazard"
                            );
                        }
                    }
                });
    }
}