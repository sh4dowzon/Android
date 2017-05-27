package com.example.sebastian.app_1;

/**
 * Created by victor on 5/27/17.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Map;


public class DBHelper {
    Context context;

    public void init(Context context){
        this.context = context;
        SharedPreferences pref = context.getApplicationContext().getSharedPreferences("team", 0);
        pref = context.getApplicationContext().getSharedPreferences("ability", 0);
        pref = context.getApplicationContext().getSharedPreferences("move", 0);
        pref = context.getApplicationContext().getSharedPreferences("pokemon", 0);
    }

    public void getTeams(){
        SharedPreferences pref = context.getApplicationContext().getSharedPreferences("team", 0);
        Map<String, ?> allEntries = pref.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());
        }
    }

    public void addTeam(String name){
        SharedPreferences pref = context.getApplicationContext().getSharedPreferences("team", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(name,"");
        editor.commit();
    }
    //POKEMON
    public void addPokemon(String team, String pokemon, String pokedata){
        SharedPreferences pref = context.getApplicationContext().getSharedPreferences("pokemon", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(team + pokemon, pokedata);
        editor.commit();

        pref = context.getApplicationContext().getSharedPreferences("", 0);

    }
    public void addPokemon(String team, Pokemon pokemon){
        SharedPreferences pref = context.getApplicationContext().getSharedPreferences("pokemon", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(team+pokemon.name,pokemon.type1+"-"+pokemon.type2+"|"+pokemon.ability+"|"+pokemon.mov1+"|"+pokemon.mov2+"|"+pokemon.mov3+"|"+pokemon.mov4);
        editor.commit();

        pref = context.getApplicationContext().getSharedPreferences("team",0);
        String data = pref.getString(team,"");
        String[] pokes = data.split("|");
        if(pokes.length <=5){
            data = data + "|" + pokemon;
        }
        editor = pref.edit();
        editor.putString(team,data);
        editor.commit();

    }
    public Pokemon getPokemon(String team, String name){
        Pokemon pokemon = new Pokemon();
        SharedPreferences pref = context.getApplicationContext().getSharedPreferences("pokemon", 0);
        String data = pref.getString(team + name, "");
        String[] valores = data.split("|");
        pokemon.name = name;
        pokemon.tipo1 = valores[0].split("-")[0];
        pokemon.tipo2 = valores[0].split("-")[1];
        pokemon.ability = valores[1];
        pokemon.mov1 = valores[2];
        pokemon.mov2 = valores[3];
        pokemon.mov3 = valores[4];
        pokemon.mov4 = valores[5];

        return pokemon;
    }
    public void removePokemon(String team,String pokemon){
        SharedPreferences pref = context.getApplicationContext().getSharedPreferences("pokemon", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(team+pokemon);
        editor.commit();
    }
    //MOVES
    public void addMove(String name, Move move){
        SharedPreferences pref = context.getApplicationContext().getSharedPreferences("move", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(name,move.tipo+"|"+move.category+"|"+move.power+"|"+move.accuracy+"|"+move.description);
        editor.commit();
    }
    public String getMove(String name){
        SharedPreferences pref = context.getApplicationContext().getSharedPreferences("move", 0);
        return pref.getString(name,"");
    }
    //ABILITIES
    public void addAbility(String name, String description){
        SharedPreferences pref = context.getApplicationContext().getSharedPreferences("ability", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(name, description);
    }
    public String getAbility(String name) {
        SharedPreferences pref = context.getApplicationContext().getSharedPreferences("ability", 0);
        return pref.getString(name,"");
    }
}
