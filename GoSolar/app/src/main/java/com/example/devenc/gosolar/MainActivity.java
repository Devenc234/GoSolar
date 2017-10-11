package com.example.devenc.gosolar;


import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.text.DecimalFormat;

public class MainActivity extends Activity
        implements SensorEventListener, RadioGroup.OnCheckedChangeListener {

    private SensorFusion sensorFusion;
    private BubbleLevelCompass bubbleLevelCompass;
    private SensorManager sensorManager = null;
    private Orientation mOrien;

    private RadioGroup setModeRadioGroup;
    private TextView azimuthText, pithText, rollText, tvOrien;
    private DecimalFormat d = new DecimalFormat("#.#");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        registerSensorManagerListeners();

        d.setMaximumFractionDigits(1);
        d.setMinimumFractionDigits(1);

        sensorFusion = new SensorFusion();
        sensorFusion.setMode(SensorFusion.Mode.FUSION);

        mOrien = new Orientation(this);

        bubbleLevelCompass = (BubbleLevelCompass) this.findViewById(R.id.SensorFusionView);
        setModeRadioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
        azimuthText = (TextView) findViewById(R.id.azmuth);
        pithText = (TextView) findViewById(R.id.pitch);
        rollText = (TextView) findViewById(R.id.roll);
        tvOrien = (TextView) findViewById(R.id.showOrien);
        setModeRadioGroup.setOnCheckedChangeListener(this);
    }

    public void updateOrientationDisplay() {

        double azimuthValue = sensorFusion.getAzimuth();
        double rollValue =  sensorFusion.getRoll();
        double pitchValue =  sensorFusion.getPitch();

        // Operation to give azimuth of camera principle axis
        int a = (int) (10*azimuthValue);
        int b = 3600;
        int mod = (mod = a % b) < 0 ? a + b : a;
        //System.out.println("mod = " + mod);
        azimuthValue = (double) mod/10;

        azimuthValue = azimuthValue + 90;
        a = (int) (10*azimuthValue);
        mod = (a % b);
        //System.out.println("mod = " + mod);
        azimuthValue = (double) mod/10;

        //Changing roll value
        rollValue = Math.abs(rollValue) - 90;

        //System.out.println(azimuthValue);

        azimuthText.setText(String.valueOf(d.format(azimuthValue)));
        pithText.setText(String.valueOf(d.format(pitchValue)));
        rollText.setText(String.valueOf(d.format(rollValue)));
        tvOrien.setText(mOrien.updateOrientation());
        /*azimuthText.setText(String.valueOf((int)azimuthValue));
        pithText.setText(String.valueOf((int)pitchValue));
        rollText.setText(String.valueOf((int)(rollValue)));*/

        bubbleLevelCompass.setPLeft((int) rollValue);
        bubbleLevelCompass.setPTop((int) pitchValue);
        bubbleLevelCompass.setAzimuth((int) azimuthValue);

    }

    public void registerSensorManagerListeners() {
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);

        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
                SensorManager.SENSOR_DELAY_GAME);

        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        registerSensorManagerListeners();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                sensorFusion.setAccel(event.values);
                sensorFusion.calculateAccMagOrientation();
                break;

            case Sensor.TYPE_GYROSCOPE:
                sensorFusion.gyroFunction(event);
                break;

            case Sensor.TYPE_MAGNETIC_FIELD:
                sensorFusion.setMagnet(event.values);
                break;
        }
        updateOrientationDisplay();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case R.id.radio0:
                sensorFusion.setMode(SensorFusion.Mode.ACC_MAG);
                break;
            case R.id.radio1:
                sensorFusion.setMode(SensorFusion.Mode.GYRO);
                break;
            case R.id.radio2:
                sensorFusion.setMode(SensorFusion.Mode.FUSION);
                break;
        }
    }

}