package space.doky.e21040601;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import space.doky.e21040601.R;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        Button button = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "돌아가기 버튼을 눌렀어요", Toast.LENGTH_LONG).show();

                Intent intent = new Intent();
                intent.putExtra("name", "Mike");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}