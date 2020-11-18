package com.epos.mynutri.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.epos.mynutri.R;

public class ProizvodiAdapter extends BaseAdapter {

    String[] proizvodi;
    String[] kalorije;

    LayoutInflater mInflater;

    public ProizvodiAdapter(Context context, String[] proizvodi, String[] kalorije){
        this.proizvodi = proizvodi;
        this.kalorije = kalorije;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return proizvodi.length;
    }

    @Override
    public Object getItem(int position) {
        return proizvodi[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.proizvodi_listview, null);
        TextView proizvodTextView = view.findViewById(R.id.nazivTextView);
        TextView kalorijeTextView = view.findViewById(R.id.kalTextView);

        String proizvod = proizvodi[position];
        String kalorija = kalorije[position];

        proizvodTextView.setText(proizvod);
        kalorijeTextView.setText(kalorija + " kcal");

        return view;
    }
}
