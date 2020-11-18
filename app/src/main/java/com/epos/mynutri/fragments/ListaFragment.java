package com.epos.mynutri.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.epos.mynutri.models.Proizvod;
import com.epos.mynutri.R;
import com.epos.mynutri.activities.ProizvodTabela;
import com.epos.mynutri.adapters.ProizvodiAdapter;
import com.epos.mynutri.database.AssetDatabaseOpenHelper;
import com.epos.mynutri.database.DataBaseHelper;

public class ListaFragment extends Fragment {

    Proizvod[] proizvodi;
    String[] nazivi;
    String[] kalorije;
    ListView proizvodiListView;
    private int kategorija;

    DataBaseHelper myDBHelper;
    private static final String DB_NAME = "mynutri.db";

    public ListaFragment(int kategorija){
        this.kategorija = kategorija;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista, container, false);
        proizvodiListView = view.findViewById(R.id.proizvodiListView);

        AssetDatabaseOpenHelper assetDatabaseOpenHelper = new AssetDatabaseOpenHelper(view.getContext(), DB_NAME);
        assetDatabaseOpenHelper.saveDatabase();
        myDBHelper = new DataBaseHelper(view.getContext(), DB_NAME);

        int brElemenata = myDBHelper.brElemenata(kategorija);
        proizvodi = myDBHelper.getNizProizvoda(kategorija);
        nazivi = new String[brElemenata];
        kalorije = new String[brElemenata];
        for (int i = 0; i < brElemenata; i++){
            nazivi[i] = proizvodi[i].getNaziv();
            kalorije[i] = proizvodi[i].getKalorije() + "";
        }

        ProizvodiAdapter proizvodiAdapter = new ProizvodiAdapter(view.getContext(), nazivi, kalorije);
        proizvodiListView.setAdapter(proizvodiAdapter);

        proizvodiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent tabelaActivity = new Intent(view.getContext(), ProizvodTabela.class);
                tabelaActivity.putExtra("com.epos.mynutri.PROIZVOD", nazivi[position]);
                startActivity(tabelaActivity);
            }
        });

        return view;
    }

}
