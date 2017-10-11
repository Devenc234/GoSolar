package com.example.devenc.gosolar;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class launch_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_launch_screen);

        // When clicked on camera
        Button buttonContinue = (Button) findViewById(R.id.lauch_continue);
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(launch_activity.this, project_menu.class));
            }
        });
    }


}