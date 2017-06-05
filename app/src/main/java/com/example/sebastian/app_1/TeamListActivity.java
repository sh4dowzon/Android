package com.example.sebastian.app_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

//LAUNCHER//
public class TeamListActivity extends AppCompatActivity {
    ListView tlv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_list_main);

        //get team lists from db and save them into an array
        TeamList team_lists_data[] = new TeamList[]{
                new TeamList("Team Kawaii",R.drawable.gengar,R.drawable.froslass,R.drawable.gliscor,R.drawable.sceptile, R.drawable.togekiss,R.drawable.magnezone),
                new TeamList("Team Johto",R.drawable.ampharos,R.drawable.feraligatr,R.drawable.scizor,R.drawable.shuckle, R.drawable.typhlosion,R.drawable.tyranitar),
                new TeamList("Team Sinnoh",R.drawable.garchomp,R.drawable.gallade,R.drawable.porygon_z,R.drawable.weavile, R.drawable.abomasnow,R.drawable.darkrai),
                new TeamList("Team Unova",R.drawable.chandelure,R.drawable.cofagrigus,R.drawable.galvantula,R.drawable.ferrothorn, R.drawable.volcarona,R.drawable.serperior),
                new TeamList("Team Kanto",R.drawable.charizard,R.drawable.gyarados,R.drawable.ditto,R.drawable.raichu, R.drawable.nidoking,R.drawable.dragonite),
                new TeamList("Team Legendario",R.drawable.mewtwo,R.drawable.raikou,R.drawable.deoxys_normal,R.drawable.giratina, R.drawable.reshiram,R.drawable.zekrom),
        };
        TeamListAdapter adapter = new TeamListAdapter(this,R.layout.team_list_listview_item_row,team_lists_data);
        tlv = (ListView) findViewById(R.id.team_lv);
        View header = (View) getLayoutInflater().inflate(R.layout.team_list_header_row,null);
        tlv.addHeaderView(header);
        tlv.setAdapter(adapter);
        tlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView x = (TextView)view.findViewById(R.id.team_name);
                //Toast.makeText(getApplicationContext(),v.getText(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TeamListActivity.this,TeamActivity.class);
                String v = x.getText().toString();
                if(v.equals("Team Kawaii")){
                    intent.putExtra("TEAM_NAME","Team Kawaii");
                    intent.putExtra("TEAM_ID",1);
                }
                else if(v.equals("Team Johto")){
                    intent.putExtra("TEAM_ID",2);
                }
                else if(v.equals("Team Sinnoh")){

                    intent.putExtra("TEAM_ID",3);
                }
                else if(v.equals("Team Unova")){
                    intent.putExtra("TEAM_ID",4);
                }
                else if(v.equals("Team Kanto")){
                    intent.putExtra("TEAM_ID",5);
                }
                else if(v.equals("Team Legendario")){
                    intent.putExtra("TEAM_ID",6);
                }
                startActivity(intent);
            }
        });

        DBHelper db = new DBHelper(this);
        db.getWritableDatabase();
        //db.dropTables();
        db.createTables();
        db.addTeam("Team Kawaii");
        db.addTeam("Team Johto");
        db.addTeam("Team Sinnoh");
        db.addTeam("Team Unova");
        db.addTeam("Team Kanto");
        db.addTeam("Team Legendario");


    }
}
