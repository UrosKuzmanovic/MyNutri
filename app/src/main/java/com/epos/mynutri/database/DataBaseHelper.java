package com.epos.mynutri.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.epos.mynutri.models.Proizvod;


public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(Context context, String db_name) {
        super(context, db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public int brKategorija(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select kategorija from kategorije", null);
        int br = 0;
        while (res.moveToNext()){
            br++;
        }
        return br;
    }

    public String[] spisakKategorija(){
        String[] kat = new String[brKategorija()];
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select kategorija from kategorije", null);
        int i = 0;
        while (res.moveToNext()){
            kat[i] = res.getString(0);
            i++;
        }
        return kat;
    }

    public int getKategorija(String kategorija){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select id from kategorije where kategorija = '" + kategorija + "'", null);
        if (res.moveToNext())
            return Integer.parseInt(res.getString(0));
        return -1;
    }

    public int brElemenata(int kategorija){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select naziv from proizvodi where kategorija = " + kategorija, null);
        int br = 0;
        while (res.moveToNext()){
            br++;
        }
        return br;
    }

    public Proizvod[] getNizProizvoda(int kat){
        Proizvod[] proizvodi = new Proizvod[brElemenata(kat)];
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from proizvodi where kategorija = " + kat + " order by naziv asc", null);
        int i = 0;
        while (res.moveToNext()){
                int id = Integer.parseInt(res.getString(0));
                String naziv = res.getString(1);
                int kategorija = Integer.parseInt(res.getString(2));
                int kalorije = Integer.parseInt(res.getString(3));
                double proteini = Double.parseDouble(res.getString(4));
                double uh = Double.parseDouble(res.getString(5));
                double masti = Double.parseDouble(res.getString(6));
                proizvodi[i] = new Proizvod(id, kategorija, kalorije, naziv, proteini, uh, masti);
                i++;
        }
        return proizvodi;
    }

    public Proizvod getProizvod(String naziv){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from proizvodi where naziv = '" + naziv + "' order by naziv asc", null);

        res.moveToNext();
        int id = Integer.parseInt(res.getString(0));
        int kategorija = Integer.parseInt(res.getString(2));
        int kalorije = Integer.parseInt(res.getString(3));
        double proteini = Double.parseDouble(res.getString(4));
        double uh = Double.parseDouble(res.getString(5));
        double masti = Double.parseDouble(res.getString(6));
        Proizvod proizvod = new Proizvod(id, kategorija, kalorije, naziv, proteini, uh, masti);

        return proizvod;
    }

    public int brElemenata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select naziv from proizvodi", null);
        int br = 0;
        while (res.moveToNext()){
            br++;
        }
        return br;
    }

    public Proizvod[] getProizvode(){
        Proizvod[] proizvodi = new Proizvod[brElemenata()];
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from proizvodi order by naziv asc", null);
        int i = 0;
        while (res.moveToNext()){
            int id = Integer.parseInt(res.getString(0));
            String naziv = res.getString(1);
            int kategorija = Integer.parseInt(res.getString(2));
            int kalorije = Integer.parseInt(res.getString(3));
            double proteini = Double.parseDouble(res.getString(4));
            double uh = Double.parseDouble(res.getString(5));
            double masti = Double.parseDouble(res.getString(6));
            proizvodi[i] = new Proizvod(id, kategorija, kalorije, naziv, proteini, uh, masti);
            i++;
        }
        return proizvodi;
    }
}
