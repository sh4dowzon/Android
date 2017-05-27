package com.example.sebastian.app_1;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 5/27/17.
 */

public class PokemonSearchAdapter extends ArrayAdapter<String> {

    private final Activity context;

    private List<String> itemname;

    private List<Bitmap> imagenes;

    private List<String> tipo1, tipo2;

    public PokemonSearchAdapter(Activity context, List<String> itemname) {
        super(context, R.layout.pokemon_search_list, itemname);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.itemname = itemname;

        imagenes = new ArrayList<Bitmap>();
        tipo1 = new ArrayList<String>();
        tipo2 = new ArrayList<String>();
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.pokemon_search_list, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);

        txtTitle.setText(itemname.get(position));


        imageView.setImageBitmap(imagenes.get(position));
        extratxt.setText(tipo1.get(position) + " - " + tipo2.get(position));
        return rowView;

    }

    public void addPokemon(String name, Bitmap image, String tipo1, String tipo2) {


        itemname.add(0, name);
        imagenes.add(0, image);
        this.tipo1.add(0, tipo1);
        this.tipo2.add(0, tipo2);

    }


}