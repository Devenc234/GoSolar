package com.example.devenc.gosolar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class detail_project extends AppCompatActivity {

    Button buttonCamera;
    TextView dp_name,dp_longitude,dp_latitude,dp_elevation,dp_pressure,dp_temperature,dp_relativeHumidity,dp_year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detail_project);



        buttonCamera = (Button) findViewById(R.id.button_captureImage);
        dp_name = (TextView) findViewById(R.id.dp_name);
        final String GALLERY_LOCATION = getIntent().getExtras().getString("ProjectName");
        dp_name.setText(GALLERY_LOCATION);

        dp_longitude = (TextView) findViewById(R.id.dp_longitude);
        Double longitude = getIntent().getExtras().getDouble("Longitude");
        dp_longitude.setText(longitude.toString());

        dp_latitude = (TextView) findViewById(R.id.dp_latitude);
        Double latitude = getIntent().getExtras().getDouble("Latitude");
        dp_latitude.setText(latitude.toString());

        dp_elevation = (TextView) findViewById(R.id.dp_elevation);
        Double elevation = getIntent().getExtras().getDouble("Elevation");
        dp_elevation.setText(elevation.toString());

        dp_pressure = (TextView) findViewById(R.id.dp_pressure);
        Double pressure = getIntent().getExtras().getDouble("Pressure");
        dp_pressure.setText(pressure.toString());

        dp_temperature = (TextView) findViewById(R.id.dp_temperature);
        Double temperature = getIntent().getExtras().getDouble("Temperature");
        dp_temperature.setText(temperature.toString());

        dp_relativeHumidity = (TextView) findViewById(R.id.dp_relativeHumidity);
        Double relativeHumidity = getIntent().getExtras().getDouble("RelativeHumidity");
        dp_relativeHumidity.setText(relativeHumidity.toString());

        dp_year = (TextView) findViewById(R.id.dp_year);
        Integer year = getIntent().getExtras().getInt("Year");
        dp_year.setText(year.toString());

        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(detail_project.this, CameraActivity.class);
                intent.putExtra("GalleryLocation",GALLERY_LOCATION);
                startActivity(intent);
            }
        });

    }

}
