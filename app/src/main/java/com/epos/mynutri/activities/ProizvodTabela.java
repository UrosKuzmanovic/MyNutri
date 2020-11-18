package com.epos.mynutri.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.epos.mynutri.models.Proizvod;
import com.epos.mynutri.R;
import com.epos.mynutri.database.AssetDatabaseOpenHelper;
import com.epos.mynutri.database.DataBaseHelper;

public class ProizvodTabela extends AppCompatActivity {

    TextView nazivTextView;
    TextView kalorijeTextView;
    TextView proteiniTextView;
    TextView uhTextView;
    TextView mastiTextView;
    EditText gramiEditText;
    FloatingActionButton dodajFloatingButton;

    int grami;

    DataBaseHelper myDBHelper;
    private static final String DB_NAME = "mynutri.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proizvod_tabela);

        AssetDatabaseOpenHelper assetDatabaseOpenHelper = new AssetDatabaseOpenHelper(this, DB_NAME);
        assetDatabaseOpenHelper.saveDatabase();
        myDBHelper = new DataBaseHelper(this, DB_NAME);

        Intent intent = getIntent();
        String naziv = intent.getStringExtra("com.epos.mynutri.PROIZVOD");
        final Proizvod proizvod = myDBHelper.getProizvod(naziv);

        nazivTextView = findViewById(R.id.nazivTextView);
        kalorijeTextView = findViewById(R.id.kalTextView);
        proteiniTextView = findViewById(R.id.proteiniTextView);
        uhTextView = findViewById(R.id.ughTextView);
        mastiTextView = findViewById(R.id.mastiTextView);
        gramiEditText = findViewById(R.id.gramiEditText);

        nazivTextView.setText(proizvod.getNaziv() + " (" + gramiEditText.getText().toString() + " g)");
        kalorijeTextView.setText(proizvod.getKalorije() + " kcal");
        proteiniTextView.setText(proizvod.getProteini() + " g");
        uhTextView.setText(proizvod.getUh() + " g");
        mastiTextView.setText(proizvod.getMasti() + " g");

        gramiEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int grami;
                if (gramiEditText.getText().toString().equals("")) {
                    grami = 0;
                }
                else if (Integer.parseInt(gramiEditText.getText().toString()) > 9999) {
                    gramiEditText.setText("9999");
                    grami = 9999;
                } else
                    grami = Integer.parseInt(gramiEditText.getText().toString());

                nazivTextView.setText(proizvod.getNaziv() + " (" + grami + " g)");
                kalorijeTextView.setText(proizvod.getKalorije() * grami / 100 + " kcal");
                proteiniTextView.setText(proizvod.getProteini() * grami / 100 + " g");
                uhTextView.setText(proizvod.getUh() * grami / 100 + " g");
                mastiTextView.setText(proizvod.getMasti() * grami / 100 + " g");
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        dodajFloatingButton = findViewById(R.id.dodajFloatingButton);
        dodajFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // upisi u tabelu
            }
        });
    }
}
