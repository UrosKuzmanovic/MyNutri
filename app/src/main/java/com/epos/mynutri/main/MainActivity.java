package com.epos.mynutri.main;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.epos.mynutri.activities.BMIActivity;
import com.epos.mynutri.activities.PretragaActivity;
import com.epos.mynutri.fragments.ListaFragment;
import com.epos.mynutri.R;
import com.epos.mynutri.adapters.SectionsPageAdapter;
import com.epos.mynutri.database.AssetDatabaseOpenHelper;
import com.epos.mynutri.database.DataBaseHelper;

public class MainActivity extends AppCompatActivity {

    SectionsPageAdapter mSectionsPageAdapter;
    DataBaseHelper myDBHelper;
    private static final String DB_NAME = "mynutri.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AssetDatabaseOpenHelper assetDatabaseOpenHelper = new AssetDatabaseOpenHelper(this, DB_NAME);
        assetDatabaseOpenHelper.saveDatabase();
        myDBHelper = new DataBaseHelper(this, DB_NAME);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        final ViewPager mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    private SectionsPageAdapter setupViewPager(ViewPager viewPager){
        String[] kategorije = myDBHelper.spisakKategorija();

        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        for (int i = 0; i < kategorije.length; i++){
            adapter.addFragment(new ListaFragment(i+1), kategorije[i]);
        }
        viewPager.setAdapter(adapter);
        return adapter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().equals("Izračunaj BMI")) {
            bmiActivitySwitch();
        } else if (item.getTitle().equals("Pretraži")){
            pretragaActivitySwitch();
        } else if (item.getTitle().equals("Istorija")){
            istorijaActivitySwitch();
        }
        return super.onOptionsItemSelected(item);
    }

    private void bmiActivitySwitch(){
        Intent bmiActivity = new Intent(this, BMIActivity.class);
        startActivity(bmiActivity);
    }

    private void pretragaActivitySwitch(){
        Intent pretragaActivity = new Intent(this, PretragaActivity.class);
        startActivity(pretragaActivity);
    }

    private void istorijaActivitySwitch(){

    }

}
