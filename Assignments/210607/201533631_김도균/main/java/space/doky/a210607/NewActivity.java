package space.doky.a210607;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class NewActivity extends AppCompatActivity {

    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        btnBack = findViewById(R.id.btnBack);
        TextView txtLog = findViewById(R.id.txtLog);

        // Get log list from MainActivity with using intent
        Intent intent = getIntent();
        ArrayList<String> log = intent.getStringArrayListExtra("log");

        // Display log data
        String logResult = "";
        for(int i = 0; i < log.size(); i++)
            logResult += log.get(i) + "\n";

        txtLog.setText(logResult);

        // Go back to MainActivity
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}