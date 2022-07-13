package space.doky.e21051801;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button01 = findViewById(R.id.button01);
        EditText input01 = findViewById(R.id.exitText01);

        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addr = input01.getText().toString().trim();

                ConnectThread thread = new ConnectThread(addr);
                thread.start();

            }
        });
    }

    class ConnectThread extends Thread {
        String hostname;

        public ConnectThread(String addr){
            hostname = addr;
        }

        @Override
        public void run() {
            super.run();
            try {
                int port  = 7777;
                Socket sock = new Socket(hostname, port);

                InputStream in = sock.getInputStream();
                DataInputStream dis = new DataInputStream(in);

                Log.d("MainActivity", "서버에서 받은 메시지: " + dis.readUTF());

                dis.close();
                sock.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}