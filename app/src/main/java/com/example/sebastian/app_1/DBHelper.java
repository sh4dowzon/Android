package com.example.sebastian.app_1;

/**
 * Created by victor on 5/27/17.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "pokemon.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_TEAM = "create table if not exists team"
            + "("
            + "_id" + " integer primary key autoincrement,"
            + "name" + " text not null " + ")";

    private static final String CREATE_TABLE_POKEMON = "create table if not exists pokemon"
            + "("
            + "_id" + " integer primary key autoincrement, "
            + "name" + " text not null, "
            + "type1" + " int not null, "
            + "type2" + " int not null, "
            + "ability" + " text not null, "
            + "team_id" + " integer,"
            + "FOREIGN KEY(team_id) REFERENCES team(_id)" + ")";



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db2) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(CREATE_TABLE_TEAM);
        db.execSQL(CREATE_TABLE_POKEMON);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("some sql statement to do something");
    }
    public void createTables(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(CREATE_TABLE_TEAM);
        db.execSQL(CREATE_TABLE_POKEMON);
    }

        public void dropTables(){
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL("DROP TABLE IF EXISTS pokemon");
            db.execSQL("DROP TABLE IF EXISTS team");
        }


    public void addPokemon(String name, int type1,int type2, String ability,int team_id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO pokemon (name,type1,type2,ability,team_id) VALUES " +
                "(" +
                "'"+name + "',"+
                type1 + ","+
                type2 + ","+
                "'"+ability + "',"+
                team_id +
                ")");
    }
    public void addTeam(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(
                "INSERT INTO team (name) VALUES "
                        + "('"
                        + name
                        + "')"
        );
    }
    public void getTeams(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM team",null);
    }

    public void getTeam(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM team where _id = "+id+"",null);
        if(cursor != null){
            cursor.moveToFirst();
            Log.d("CURSOR",cursor.getString(1));
        }
    }
    public ArrayList<Pokemon> getPokemonsFromTeam(int team_id){
        ArrayList<Pokemon> pokemons = new ArrayList<Pokemon>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM pokemon where team_id = " + Integer.toString(team_id),null);
        if (cursor.moveToFirst()) {
            do {
                Pokemon pokemon = new Pokemon();
                pokemon.id = Integer.parseInt(cursor.getString(0));
                pokemon.name = cursor.getString(1);
                pokemon.type1 = Integer.parseInt(cursor.getString(2));
                
                pokemon.type2 = Integer.parseInt(cursor.getString(3));
                pokemon.ability = cursor.getString(4);
                pokemon.team_id = Integer.parseInt(cursor.getString(5));
                // Adding contact to list
                pokemons.add(pokemon);
            } while (cursor.moveToNext());
        }
        return pokemons;
    }

}
