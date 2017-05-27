package com.example.sebastian.app_1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Sebastian on 22-05-2017.
 */

public class TeamListAdapter extends ArrayAdapter<TeamList> {
    Context context;
    int layoutresourceid;
    TeamList data[] = null;
    public TeamListAdapter(Context context, int layoutresourceid, TeamList[] data){
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
        TeamList list = data[position];
        holder.team_name.setText(list.team_name);
        holder.team_icon1.setImageResource(list.team_icon1);
        holder.team_icon2.setImageResource(list.team_icon2);
        holder.team_icon3.setImageResource(list.team_icon3);
        holder.team_icon4.setImageResource(list.team_icon4);
        holder.team_icon5.setImageResource(list.team_icon5);
        holder.team_icon6.setImageResource(list.team_icon6);
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
