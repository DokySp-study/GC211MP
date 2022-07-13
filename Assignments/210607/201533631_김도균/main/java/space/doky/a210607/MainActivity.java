package space.doky.a210607;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Handler handler;
    boolean isStart;
    boolean isPause;
    LinearLayout layoutButtons;
    Button btnPause;
    Button btnRecord;
    Button btnStart;
    Button btnStop;
    TextView txtTime;
    Thread timerThread;

    // Time variable
    int count = 0;

    // Log list
    ArrayList<String> log = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind all widgets
        layoutButtons = findViewById(R.id.layoutButtons);
        btnPause = findViewById(R.id.btnPause);
        btnRecord = findViewById(R.id.btnRecord);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        txtTime = findViewById(R.id.txtTime);

        // Create instance of handler and thread
        handler = new Handler();
        TimerRunnable timerRunnable = new TimerRunnable();

        // UI Control
        setActive(false);

        // Pause button
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Set pause flag variable and button's text
                if(isPause == false){
                    isPause = true;
                    btnPause.setText(R.string.restart);
                } else {
                    isPause = false;
                    btnPause.setText(R.string.pause);
                }

            }
        });

        // Record button
        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add current time to record
                log.add(countToString(count));
            }
        });

        // Start button
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // During timer is running, show NewActivity to show log data
                if(isStart){
                    Intent intent = new Intent(getApplicationContext(), NewActivity.class);
                    intent.putExtra("log", log);
                    startActivity(intent);
                } else {
                    // If timer is not running, start timer thread and change UI
                    setActive(true);

                    // Start timer thread
                    timerThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                while(isStart){

                                    // If, user pause the timer, wait until restart
                                    if(isPause)
                                        while (isPause)
                                            Thread.sleep(10);

                                    // Post to timerRunnable to update count(time variable)
                                    handler.post(timerRunnable);
                                    // Wait 10 milliseconds
                                    Thread.sleep(10);
                                }

                            } catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    });
                    // Start thread
                    timerThread.start();
                }

            }
        });

        // Stop button
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Kill timer thread and reset all variables and UI
                timerThread.interrupt();
                setActive(false);
                log.clear();
            }
        });
    }



    // Runnable class to manipulate UI by thread
    public class TimerRunnable implements Runnable {

        @Override
        public void run() {
            count += 10;
            if(isStart)
                txtTime.setText(countToString(count));
        }
    }



    // Change UI when timer is started or ended
    public void setActive(boolean enable){
        if(enable){
            isStart = true;
            isPause = false;
            btnPause.setText(R.string.pause);

            count = 0;
            layoutButtons.setVisibility(View.VISIBLE);
            btnStart.setText(R.string.more);
            txtTime.setText(countToString(count));
        } else {
            isStart = false;
            isPause = false;
            btnPause.setText(R.string.pause);

            count = 0;
            layoutButtons.setVisibility(View.GONE);
            btnStart.setText(R.string.start);
            txtTime.setText(countToString(count));
        }
    }



    // Change millisecond to hour, minute, second, millisecond format
    public String countToString(int inputCount){
        int hour = ((inputCount/1000) / (60*60)) % 24;
        int minute = ((inputCount/1000) % (60*60)) / 60;
        int second = (inputCount/1000) % (60);
        int millisecond = (inputCount / 10) % 100;

        String result = "";

        if((hour+"").length() == 1)
            result += "0" + hour + ":";
        else
            result += hour + ":";

        if((minute+"").length() != 2)
            result += "0" + minute + ":";
        else
            result += minute + ":";

        if((second+"").length() != 2)
            result += "0" + second + ":";
        else
            result += second + ":";

        if((millisecond+"").length() != 2)
            result += "0" + millisecond + "";
        else
            result += millisecond + "";

        return result;
    }

}