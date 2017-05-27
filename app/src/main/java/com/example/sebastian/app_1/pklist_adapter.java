package com.example.sebastian.app_1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Sebastian on 22-05-2017.
 */

public class pklist_adapter extends ArrayAdapter<pklist> {
    Context context;
    int layoutresourceid;
    List<pklist> datos;
    public pklist_adapter(Context context, int layoutresourceid, List<pklist> data, String team){
        super(context, layoutresourceid,data);
        this.context = context;
        this.layoutresourceid = layoutresourceid;
        this.datos = data;
        if(team.equals("Team Kawaii")){
            datos.add(new pklist(R.drawable.gengar,"Gengar",R.drawable.ghost,R.drawable.poison,"Cursed Body"));
            datos.add(new pklist(R.drawable.froslass,"Froslass",R.drawable.ice,R.drawable.ghost,"Snow Cloak"));
            datos.add(new pklist(R.drawable.gliscor,"Gliscor",R.drawable.ground,R.drawable.flying,"Poison Heal"));
            datos.add(new pklist(R.drawable.sceptile,"Sceptile",R.drawable.grass,R.drawable.nulo,"Unburden"));
            datos.add(new pklist(R.drawable.togekiss,"Togekiss",R.drawable.fairy,R.drawable.flying,"Serene Grace"));
            datos.add(new pklist(R.drawable.magnezone,"Magnezone",R.drawable.electric,R.drawable.steel,"Magnet Pull"));
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
        //pklist pklista = data[position];
        pklist pklista = datos.get(position);
        holder.icon.setImageResource(pklista.icon);
        holder.name.setText(pklista.name);
        holder.type1.setImageResource(pklista.type1);
        holder.type2.setImageResource(pklista.type2);
        holder.ability.setText(pklista.ability);
        return row;
    }
    static class pklist_holder{
        ImageView icon;
        TextView name;
        ImageView type1;
        ImageView type2;
        TextView ability;
    }
}
