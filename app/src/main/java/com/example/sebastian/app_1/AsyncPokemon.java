package com.example.sebastian.app_1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by victor on 5/27/17.
 */

public class AsyncPokemon extends AsyncTask<String, Integer, Bitmap> {

    ImageView bmImage;
    PokemonSearchAdapter adapter;
    public static String type1 = "", type2 = "";
    public AsyncPokemon(ImageView image, PokemonSearchAdapter adapter){
        bmImage = image;
        this.adapter = adapter;
    }



    @Override
    protected Bitmap doInBackground(String... params) {
        try {
            Log.d("INPUT",params[0]);

            //get pokemon ID

            Document doc = Jsoup.connect("http://pokemondb.net/pokedex/" + params[0]).get();
            Element test = doc.getElementsByClass("vitals-table").first();
            Element subtest = test.select("strong").first();
            Element tipo1 = test.getElementsByClass("type-icon").first();
            Element tipo2 = null;
            if(test.getElementsByClass("type-icon").size() != 1){
                tipo2 = test.getElementsByClass("type-icon").get(1);
            }



            //get pokemon icon
            Document doc2 = Jsoup.connect("http://archives.bulbagarden.net/wiki/File:"+subtest.text()+"MS.png").get();
            test = doc2.getElementsByClass("fullImageLink").first();
            subtest = test.select("a").first();



            //return pokemon icon
            String src = subtest.absUrl("href");
            URL url = new URL(src);
            String stringTipo1 = tipo1.text();
            type1 = tipo1.text();
            String stringTipo2 = "nulo";
            if(tipo2 != null){
                stringTipo2 = tipo2.text();
                type2 = tipo2.text();
            }
            Bitmap imagen = loadFromURL(src);
            adapter.addPokemon(params[0].substring(0,1).toUpperCase()+params[0].substring(1), imagen, stringTipo1,stringTipo2);


            return imagen;




        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    protected Bitmap loadFromURL(String url){
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;

    }
    protected void onPostExecute(Bitmap result){
        //
        //
        //bmImage.setImageBitmap(result);
        //
        adapter.notifyDataSetChanged();
    }
}

