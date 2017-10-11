package com.example.devenc.gosolar;

/**
 * Created by GILAB on 6/12/2017.
 */


import android.app.Activity;
import android.view.Surface;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by GILAB on 6/2/2017.
 */

public class Orientation {

    private final WindowManager mWindowManager;
    public Orientation(Activity activity) {
        mWindowManager = activity.getWindow().getWindowManager();
        //mSensorManager = (SensorManager) activity.getSystemService(Activity.SENSOR_SERVICE);

        // Can be null if the sensor hardware is not available
        //mRotationSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
    }

    public String updateOrientation() {

        final int worldAxisForDeviceAxisX;
        final int worldAxisForDeviceAxisY;
        String showOrientation="";


        // Remap the axes as if the device screen was the instrument panel,
        // and adjust the rotation matrix for the device orientation.
        switch (mWindowManager.getDefaultDisplay().getRotation()) {
            case Surface.ROTATION_0:
                showOrientation+= " zero";
                break;
            case Surface.ROTATION_90:
                showOrientation+= " ninty";
                break;
            case Surface.ROTATION_180:
                showOrientation+= " one eighty";
                break;
            case Surface.ROTATION_270:
                showOrientation+= " two seventy";
                break;
        }
        return showOrientation;
    }
}
