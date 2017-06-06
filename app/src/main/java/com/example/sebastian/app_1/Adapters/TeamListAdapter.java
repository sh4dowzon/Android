package com.example.sebastian.app_1.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sebastian.app_1.R;
import com.example.sebastian.app_1.Utils.TeamList;

import java.util.ArrayList;

/**
 * Created by Sebastian on 22-05-2017.
 */

public class TeamListAdapter extends ArrayAdapter<TeamList> {
    Context context;
    int layoutresourceid;
    public ArrayList<TeamList> data = null;
    public TeamListAdapter(Context context, int layoutresourceid, ArrayList<TeamList> data){
        super(context, layoutresourceid,data);
        this.context = context;
        this.layoutresourceid = layoutresourceid;
        this.data = data;
    }
    public View getView(int position, View convertview, ViewGroup parent){
        View row = convertview;
        team_list_holder holder = null;
        if(holder==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutresourceid,parent,false);
            holder = new team_list_holder();
            holder.team_name = (TextView) row.findViewById(R.id.team_name);
            holder.team_icon1 = (ImageView)row.findViewById(R.id.team_icon1);
            holder.team_icon2 = (ImageView)row.findViewById(R.id.team_icon2);
            holder.team_icon3 = (ImageView)row.findViewById(R.id.team_icon3);
            holder.team_icon4 = (ImageView)row.findViewById(R.id.team_icon4);
            holder.team_icon5 = (ImageView)row.findViewById(R.id.team_icon5);
            holder.team_icon6 = (ImageView)row.findViewById(R.id.team_icon6);
            row.setTag(holder);
        }
        else{
            holder = (team_list_holder)row.getTag();
        }
        TeamList list = data.get(position);
        holder.team_name.setText(list.team_name);

        ArrayList<Bitmap> images = new ArrayList<Bitmap>();
        for(int i = 0; i < list.pokemons.size();i++){
            if(list.pokemons.get(i) == null){
                images.add(BitmapFactory.decodeResource(null, R.drawable.pokeball));
            }
            else{
                images.add(list.pokemons.get(i));
            }
        }

        for(int i = images.size(); i< 6;i++){
            images.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.pokeball));
        }

        holder.team_icon1.setImageBitmap(images.get(0));
        holder.team_icon2.setImageBitmap(images.get(1));
        holder.team_icon3.setImageBitmap(images.get(2));
        holder.team_icon4.setImageBitmap(images.get(3));
        holder.team_icon5.setImageBitmap(images.get(4));
        holder.team_icon6.setImageBitmap(images.get(5));

        return row;
    }
    static class team_list_holder{
        TextView team_name;
        ImageView team_icon1;
        ImageView team_icon2;
        ImageView team_icon3;
        ImageView team_icon4;
        ImageView team_icon5;
        ImageView team_icon6;
    }
}
