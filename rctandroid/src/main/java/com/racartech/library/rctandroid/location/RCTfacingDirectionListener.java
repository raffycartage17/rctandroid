package com.racartech.library.rctandroid.location;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class RCTfacingDirectionListener implements SensorEventListener {

    private final SensorManager sensorManager;
    private final Sensor accelerometer;
    private final Sensor magnetometer;
    private final FacingDirectionListener facingDirectionListener;

    private boolean haveSensor = false;
    private boolean haveAccelerometer = false;
    private boolean haveMagnetometer = false;

    private final float[] accelerometerReading = new float[3];
    private final float[] magnetometerReading = new float[3];

    private final float[] rotationMatrix = new float[9];
    private final float[] orientationAngles = new float[3];

    public interface FacingDirectionListener {
        void onFacingDirectionUpdate(float azimuth_in_degrees);
    }

    public RCTfacingDirectionListener(Context context, FacingDirectionListener listener) {
        this.facingDirectionListener = listener;
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        haveAccelerometer = sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
        haveMagnetometer = sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_GAME);
        haveSensor = haveAccelerometer && haveMagnetometer;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            System.arraycopy(event.values, 0, accelerometerReading, 0, accelerometerReading.length);
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            System.arraycopy(event.values, 0, magnetometerReading, 0, magnetometerReading.length);
        }
        facingDirectionAzimuthCalculator();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        // Not used, but required to implement SensorEventListener
    }

    private void facingDirectionAzimuthCalculator() {
        SensorManager.getRotationMatrix(rotationMatrix, null, accelerometerReading, magnetometerReading);
        SensorManager.getOrientation(rotationMatrix, orientationAngles);

        float azimuthInDegreesFacingDirection = (float) Math.toDegrees(orientationAngles[0]);
        if (facingDirectionListener != null) {
            facingDirectionListener.onFacingDirectionUpdate(azimuthInDegreesFacingDirection);
        }
    }
}


