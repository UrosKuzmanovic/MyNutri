package com.epos.mynutri.activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.epos.mynutri.R;

public class BMIActivity extends AppCompatActivity {

    EditText visinaEditText;
    EditText tezinaEditText;
    EditText godinaEditText;
    RadioGroup radioGroup;
    RadioButton zenskiRadioButton;
    RadioButton muskiRadioButton;
    TextView bmiTextView;
    TextView porukaTextView;
    TextView kalTextView;
    TextView proTextView;
    TextView ughTextView;
    TextView vodaTextView;
    Button izracunajButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        visinaEditText = findViewById(R.id.visinaEditText);
        tezinaEditText = findViewById(R.id.tezinaEditText);
        godinaEditText = findViewById(R.id.godineEditText);
        radioGroup = findViewById(R.id.radioGroup);
        zenskiRadioButton = findViewById(R.id.zenskiRadioButton);
        muskiRadioButton = findViewById(R.id.muskiRadioButton);
        bmiTextView = findViewById(R.id.bmiTextView);
        porukaTextView = findViewById(R.id.porukaTextView);
        kalTextView = findViewById(R.id.kalTextView);
        proTextView = findViewById(R.id.proTextView);
        ughTextView = findViewById(R.id.ughTextView);
        vodaTextView = findViewById(R.id.vodaTextView);
        izracunajButton = findViewById(R.id.izracunajButton);

        izracunajButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (visinaEditText.equals("") || tezinaEditText.equals("") || godinaEditText.equals("") ||
                        (!zenskiRadioButton.isChecked() && !muskiRadioButton.isChecked())) {
                    Toast.makeText(BMIActivity.this, "Molim Vas unesite sva polja", Toast.LENGTH_SHORT).show();
                    return;
                }

                int visina = Integer.parseInt(visinaEditText.getText().toString());
                int tezina = Integer.parseInt(tezinaEditText.getText().toString());
                int godine = Integer.parseInt(godinaEditText.getText().toString());
                boolean musko = muskiRadioButton.isChecked();
                double bmi = ((double)tezina / (Math.pow(((double)visina/100), 2)));
                double kcal;
                double proteini = 0.75 * tezina;
                double uh = 2 * tezina;
                int voda = 30 * tezina;

                if (musko){
                    if (godine <= 18){
                        kcal = tezina * 17.5 + 651;
                    } else if (godine > 18 && godine <= 30){
                        kcal = tezina * 15.3 + 679;
                    } else if (godine > 30 && godine <= 60){
                        kcal = tezina * 11.6 + 879;
                    } else {
                        kcal = tezina * 13.5 + 487;
                    }
                } else {
                    if (godine <= 18){
                        kcal = tezina * 12.2 + 746;
                    } else if (godine > 18 && godine <= 30){
                        kcal = tezina * 14.7 + 479;
                    } else if (godine > 30 && godine <= 60){
                        kcal = tezina * 8.7 + 829;
                    } else {
                        kcal = tezina * 10.5 + 596;
                    }
                }

                bmiTextView.setText(bmi + "");
                kalTextView.setText(kcal + " kcal");
                proTextView.setText(proteini + " g");
                ughTextView.setText(uh + " g");
                vodaTextView.setText(voda + " ml");

                if (bmi < 16){
                    porukaTextView.setText("Ekstremno neuhranjen/a");
                    porukaTextView.setTextColor(Color.RED);
                } else if (bmi >= 16 && bmi < 17){
                    porukaTextView.setText("Neuhranjen/a");
                    porukaTextView.setTextColor(Color.parseColor("#ff9900"));
                } else if (bmi >= 17 && bmi < 18.5){
                    porukaTextView.setText("Blago neuhranjen/a");
                    porukaTextView.setTextColor(Color.YELLOW);
                } else if (bmi >= 18.5 && bmi < 25){
                    porukaTextView.setText("Normalno uhranjen/a");
                    porukaTextView.setTextColor(Color.GREEN);
                } else if (bmi >= 25 && bmi < 30){
                    porukaTextView.setText("Prekomerno uhranjen/a");
                    porukaTextView.setTextColor(Color.YELLOW);
                } else if (bmi >= 30 && bmi < 35){
                    porukaTextView.setText("Gojaznost stepena 1");
                    porukaTextView.setTextColor(Color.parseColor("#ff9900"));
                } else if (bmi >= 35 && bmi < 40){
                    porukaTextView.setText("Gojaznost stepena 2");
                    porukaTextView.setTextColor(Color.RED);
                } else {
                    porukaTextView.setText("Gojaznost stepena 3");
                    porukaTextView.setTextColor(Color.parseColor("#800000"));
                }

                visinaEditText.setText("");
                tezinaEditText.setText("");
                godinaEditText.setText("");
                muskiRadioButton.setChecked(false);
                zenskiRadioButton.setChecked(false);

            }
        });
    }
}
