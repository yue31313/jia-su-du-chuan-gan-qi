package com.example.drt_3axis_acc;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView textViewInfo = null;
	private TextView textViewX = null;
	private TextView textViewY = null;
	private TextView textViewZ = null;
	private SensorManager sensorManager = null;
	private Sensor sensor = null;
	private float gravity[] = new float[3];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textViewInfo = (TextView) findViewById(R.id.TextView01);
		textViewX = (TextView) findViewById(R.id.TextView02);
		textViewY = (TextView) findViewById(R.id.TextView03);
		textViewZ = (TextView) findViewById(R.id.TextView04);
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		
		textViewInfo.setText("手机Accelerometer sensor详细信息：\n" +
				"设备名称：  " + sensor.getName() + "\n" +
				"设备供应商：  " + sensor.getVendor() + "\n" +
				"设备功率：  " + sensor.getPower()+ "\n");
	}

	private SensorEventListener listener = new SensorEventListener() {
		@Override
		public void onAccuracyChanged(Sensor arg0, int arg1) {
			
		}
		@Override
		public void onSensorChanged(SensorEvent e) {
			gravity[0] = e.values[0];
			gravity[1] = e.values[1];
			gravity[2] = e.values[2];
			textViewX.setText("X方向上加速度：       " + gravity[0] + "m/s^2");
			textViewY.setText("Y方向上加速度：       " + gravity[1] + "m/s^2");
			textViewZ.setText("Z方向上加速度：       " + gravity[2] + "m/s^2");
		}
	};
		
	@Override
	protected void onResume() {
		super.onResume();
		sensorManager.registerListener(listener, sensor,
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onStop() {
		super.onStop();
		sensorManager.unregisterListener(listener);
	}
		
}
