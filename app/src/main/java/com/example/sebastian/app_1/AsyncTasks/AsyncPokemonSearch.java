package com.example.sebastian.app_1.AsyncTasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.example.sebastian.app_1.Adapters.PokemonSearchAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by victor on 5/27/17.
 */

public class AsyncPokemonSearch extends AsyncTask<String, Integer, Bitmap> {


    PokemonSearchAdapter adapter;
    public static String type1 = "", type2 = "";
    public static Bitmap image;
    public AsyncPokemonSearch(PokemonSearchAdapter adapter){
        this.adapter = adapter;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        try {

            //RESET VARIABLES
            type1 = "";
            type2 = "";
            image = null;

            // REQUEST
            Document doc = Jsoup.connect("http://pokemondb.net/pokedex/" + params[0]).get();

            // GET POKEMON'S TYPYING
            Element table = doc.getElementsByClass("vitals-table").first();
            Element tipo1 = table.getElementsByClass("type-icon").first();
            Element tipo2 = null;
            if(table.getElementsByClass("type-icon").size() != 1){
                tipo2 = table.getElementsByClass("type-icon").get(1);
            }
            String stringTipo1 = tipo1.text();
            type1 = tipo1.text().trim().toLowerCase();
            String stringTipo2 = "nulo";
            if(tipo2 != null){
                stringTipo2 = tipo2.text();
                type2 = tipo2.text().trim().toLowerCase();
            }

            //LOAD POKEMON IMAGE
            image = loadFromURL("http://play.pokemonshowdown.com/sprites/bw/"+params[0]+".png");

            //SEND DATA TO POKEMON SEARCH ADAPTER
            adapter.addPokemon(params[0].substring(0,1).toUpperCase()+params[0].substring(1), image, stringTipo1,stringTipo2);


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
        //UPDATE ADAPTER
        adapter.notifyDataSetChanged();
    }
}

