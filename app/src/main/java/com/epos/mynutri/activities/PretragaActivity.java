package com.epos.mynutri.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import com.epos.mynutri.R;
import com.epos.mynutri.adapters.ProizvodiAdapter;
import com.epos.mynutri.database.AssetDatabaseOpenHelper;
import com.epos.mynutri.database.DataBaseHelper;
import com.epos.mynutri.models.Proizvod;

public class PretragaActivity extends AppCompatActivity {

    EditText pretragaEditText;
    ListView pretragaListView;
    Proizvod[] proizvodi;
    String[] nazivi;
    String[] kalorije;
    String[] naziviPretraga;
    String[] kalorijePretraga;

    DataBaseHelper myDBHelper;
    private static final String DB_NAME = "mynutri.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pretraga);

        pretragaEditText = findViewById(R.id.pretragaEditText);
        pretragaListView = findViewById(R.id.pretragaListView);

        AssetDatabaseOpenHelper assetDatabaseOpenHelper = new AssetDatabaseOpenHelper(this, DB_NAME);
        assetDatabaseOpenHelper.saveDatabase();
        myDBHelper = new DataBaseHelper(this, DB_NAME);

        final int brElemenata = myDBHelper.brElemenata();
        proizvodi = myDBHelper.getProizvode();
        nazivi = new String[brElemenata];
        kalorije = new String[brElemenata];
        for (int i = 0; i < brElemenata; i++){
            nazivi[i] = proizvodi[i].getNaziv();
            kalorije[i] = proizvodi[i].getKalorije() + "";
        }

        ProizvodiAdapter proizvodiAdapter = new ProizvodiAdapter(this, nazivi, kalorije);
        pretragaListView.setAdapter(proizvodiAdapter);



        pretragaEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String pretraga = pretragaEditText.getText().toString();
                if (pretraga != "") {
                    int br = 0;
                    naziviPretraga = new String[brElemenata(pretraga)];
                    kalorijePretraga = new String[brElemenata(pretraga)];
                    for (int i = 0; i < proizvodi.length; i++) {
                        if (proizvodi[i].getNaziv().toLowerCase().contains(pretraga.toLowerCase())) {
                            naziviPretraga[br] = proizvodi[i].getNaziv();
                            kalorijePretraga[br] = proizvodi[i].getKalorije() + "";
                            br++;
                        }
                    }
                    ProizvodiAdapter proizvodiPretragaAdapter = new ProizvodiAdapter(PretragaActivity.this,
                            naziviPretraga, kalorijePretraga);
                    pretragaListView.setAdapter(proizvodiPretragaAdapter);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        pretragaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (naziviPretraga != null) {
                    Intent tabelaActivity = new Intent(view.getContext(), ProizvodTabela.class);
                    tabelaActivity.putExtra("com.epos.mynutri.PROIZVOD", naziviPretraga[position]);
                    startActivity(tabelaActivity);
                } else {
                    Intent tabelaActivity = new Intent(view.getContext(), ProizvodTabela.class);
                    tabelaActivity.putExtra("com.epos.mynutri.PROIZVOD", nazivi[position]);
                    startActivity(tabelaActivity);
                }
            }
        });
    }

    private int brElemenata(String text){
        int brojac = 0;
        for (int i = 0; i < proizvodi.length; i++){
            if (proizvodi[i].getNaziv().toLowerCase().contains(text.toLowerCase()))
                    brojac++;
        }
        return brojac;
    }
}
