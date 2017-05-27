package com.example.sebastian.app_1;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static com.example.sebastian.app_1.R.drawable.magnezone;

/**
 * Created by Sebastian on 22-05-2017.
 */

public class PokemonListAdapter extends ArrayAdapter<Pokemon> {
    Context context;
    int layoutresourceid;
    List<Pokemon> datos;
    public PokemonListAdapter(Context context, int layoutresourceid, List<Pokemon> data, String team){
        super(context, layoutresourceid,data);
        this.context = context;
        this.layoutresourceid = layoutresourceid;
        this.datos = data;
        if(team.equals("Team Kawaii")){
            datos.add(new Pokemon(R.drawable.gengar,"Gengar",R.drawable.ghost,R.drawable.poison,"Cursed Body"));
            datos.add(new Pokemon(R.drawable.froslass,"Froslass",R.drawable.ice,R.drawable.ghost,"Snow Cloak"));
            datos.add(new Pokemon(R.drawable.gliscor,"Gliscor",R.drawable.ground,R.drawable.flying,"Poison Heal"));
            datos.add(new Pokemon(R.drawable.sceptile,"Sceptile",R.drawable.grass,R.drawable.nulo,"Unburden"));
            datos.add(new Pokemon(R.drawable.togekiss,"Togekiss",R.drawable.fairy,R.drawable.flying,"Serene Grace"));
            datos.add(new Pokemon(magnezone,"Magnezone",R.drawable.electric,R.drawable.steel,"Magnet Pull"));
            this.notifyDataSetChanged();
        }
    }
    public View getView(int position, View convertview, ViewGroup parent){
        View row = convertview;
        pklist_holder holder = null;
        if(holder==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutresourceid,parent,false);
            holder = new pklist_holder();
            holder.icon = (ImageView) row.findViewById(R.id.elem_icon);
            holder.name = (TextView) row.findViewById(R.id.elem_tv);
            holder.type1 = (ImageView) row.findViewById(R.id.type1);
            holder.type2 = (ImageView) row.findViewById(R.id.type2);
            holder.ability = (TextView) row.findViewById(R.id.ability_dinamic);
            row.setTag(holder);
        }
        else{
            holder = (pklist_holder)row.getTag();
        }
        //Pokemon pklista = data[position];
        Pokemon pklista = datos.get(position);
        holder.icon.setImageResource(pklista.icon);
        holder.name.setText(pklista.name);
        holder.type1.setImageResource(pklista.type1);
        holder.type2.setImageResource(pklista.type2);
        holder.ability.setText(pklista.ability);
        return row;
    }
    public void addPokemonToTeam(String name, String type1,String type2){
        Log.d("POKEMON",name);
        Log.d("TYPE_1",type1);
        Log.d("TYPE_2",type2);
        int id_1 = context.getResources().getIdentifier("drawable/"+type1, null, context.getPackageName());
        int id_2 = context.getResources().getIdentifier("drawable/"+type2, null, context.getPackageName());

        datos.add(new Pokemon(R.drawable.magnezone,name,id_1,id_2,""));
        this.notifyDataSetChanged();
    }
    static class pklist_holder{
        ImageView icon;
        TextView name;
        ImageView type1;
        ImageView type2;
        TextView ability;
    }
}
