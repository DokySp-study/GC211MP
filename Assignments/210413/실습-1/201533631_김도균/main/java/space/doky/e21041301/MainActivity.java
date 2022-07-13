package space.doky.e21041301;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox chkCream;
    CheckBox chkSugar;
    Button btnPay;

    RadioGroup radCoffeeType;
    RadioButton radDecaf;
    RadioButton radExpresso;
    RadioButton radColombian;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chkCream = findViewById(R.id.chkCream);
        chkSugar = findViewById(R.id.chkSugar);
        btnPay = findViewById(R.id.btnPay);

        radCoffeeType = findViewById(R.id.radGroupCoffeeType);
        radDecaf = findViewById(R.id.radDecaf);
        radExpresso = findViewById(R.id.radExpresso);
        radColombian = findViewById(R.id.radColombian);

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = "Coffee";
                if (chkCream.isChecked())
                    msg += " & Cream";
                if (chkSugar.isChecked())
                    msg += " & Sugar";

                int radioId = radCoffeeType.getCheckedRadioButtonId();
                if(radDecaf.getId() == radioId)
                    msg = "Decaf " + msg;
                if(radExpresso.getId() == radioId)
                    msg = "Expresso " + msg;
                if(radColombian.getId() == radioId)
                    msg = "Colombian " + msg;

                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });


    }
}