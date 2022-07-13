package space.doky.a210509;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMain = findViewById(R.id.btnMain);
        Button btnMenu = findViewById(R.id.btnMenu);
        ListView listView = findViewById(R.id.listView);

        MainFragment mainFragment = new MainFragment();
        MenuFragment menuFragment = new MenuFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, mainFragment).commit();

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, mainFragment).commit();
            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, menuFragment).commit();
            }
        });
    }
}