package com.example.devenc.gosolar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class create_project extends AppCompatActivity {

    Button button_saveCP;
    EditText cp_name,cp_longitude,cp_latitude,cp_elevation,cp_pressure,cp_temperature,cp_relativeHumidity,cp_year;
    private String projectName="";
    private Double longitude,latitude,elevation,pressure,temperature,relativeHumidity;
    int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_create_project);

        cp_name = (EditText) findViewById(R.id.cp_name);
        cp_longitude = (EditText) findViewById(R.id.cp_longitude);
        cp_latitude = (EditText) findViewById(R.id.cp_latitude);
        cp_elevation = (EditText) findViewById(R.id.cp_elevation);
        cp_pressure = (EditText) findViewById(R.id.cp_pressure);
        cp_temperature = (EditText) findViewById(R.id.cp_temperature);
        cp_relativeHumidity = (EditText) findViewById(R.id.cp_relativeHumidity);
        cp_year = (EditText) findViewById(R.id.cp_year);
        button_saveCP = (Button) findViewById(R.id.button_saveCP);


        button_saveCP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projectName = cp_name.getText().toString().trim();
                year = Integer.parseInt(cp_year.getText().toString());
                longitude = Double.parseDouble(cp_longitude.getText().toString().trim());
                latitude = Double.parseDouble(cp_latitude.getText().toString().trim());
                elevation = Double.parseDouble(cp_elevation.getText().toString().trim());
                pressure = Double.parseDouble(cp_pressure.getText().toString().trim());
                temperature = Double.parseDouble(cp_temperature.getText().toString().trim());
                relativeHumidity = Double.parseDouble(cp_relativeHumidity.getText().toString().trim());
                //Toast.makeText(activity_create_new_project.this," Saved this Project!!! \n" + projectName + "\n"+longitude + " " + latitude + " " + elevation + "\n" + pressure + " " + temperature + " " + relativeHumidity + "\n" + year,Toast.LENGTH_LONG).show();

                Intent intent = new Intent(create_project.this, detail_project.class);
                intent.putExtra("ProjectName",projectName);
                intent.putExtra("Longitude",longitude);
                intent.putExtra("Latitude",latitude);
                intent.putExtra("Elevation",elevation);
                intent.putExtra("Pressure",pressure);
                intent.putExtra("Temperature",temperature);
                intent.putExtra("RelativeHumidity",relativeHumidity);
                intent.putExtra("Year",year);
                startActivity(intent);
            }
        });
    }
}
