package com.example.sebastian.app_1.Utils;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by Sebastian on 21-05-2017.
 */

public class TeamList {
    public int id;
    public String team_name;
    public int team_icon1;
    public int team_icon2;
    public int team_icon3;
    public int team_icon4;
    public int team_icon5;
    public int team_icon6;

    public ArrayList<Bitmap> pokemons;

    public TeamList(){
        super();
        pokemons = new ArrayList<Bitmap>();
    }
    public TeamList(String team_name, int team_icon1, int team_icon2, int team_icon3, int team_icon4, int team_icon5, int team_icon6){
        super();
        pokemons = new ArrayList<Bitmap>();
        this.team_name=team_name;
        this.team_icon1=team_icon1;
        this.team_icon2=team_icon2;
        this.team_icon3=team_icon3;
        this.team_icon4=team_icon4;
        this.team_icon5=team_icon5;
        this.team_icon6=team_icon6;
    }
}