package space.doky.e21052701;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTxt1;
    private SensorManager mSM;
    private Sensor myGravity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTxt1 = (TextView) findViewById(R.id.textView1);
        mSM = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        myGravity = mSM.getDefaultSensor(Sensor.TYPE_GRAVITY);
        mSM.registerListener(mySensorListener, myGravity, SensorManager.SENSOR_DELAY_NORMAL);
    }



    public SensorEventListener mySensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

            if(event.sensor.getType() == Sensor.TYPE_GRAVITY){
                mTxt1.setText(Float.toString(event.values[0]));
                Log.i("TAG", Float.toString(event.values[0]));
            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };
}