package com.example.sebastian.app_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;
//LAUNCHER//
public class team_list_main extends AppCompatActivity {
    ListView tlv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_list_main);
        team_list team_lists_data[] = new team_list[]{
                new team_list("Team Kawaii",R.drawable.gengar,R.drawable.froslass,R.drawable.gliscor,R.drawable.sceptile, R.drawable.togekiss,R.drawable.magnezone),
                new team_list("Team Johto",R.drawable.ampharos,R.drawable.feraligatr,R.drawable.scizor,R.drawable.shuckle, R.drawable.typhlosion,R.drawable.tyranitar),
                new team_list("Team Sinnoh",R.drawable.garchomp,R.drawable.gallade,R.drawable.porygon_z,R.drawable.weavile, R.drawable.abomasnow,R.drawable.darkrai),
                new team_list("Team Unova",R.drawable.chandelure,R.drawable.cofagrigus,R.drawable.galvantula,R.drawable.ferrothorn, R.drawable.volcarona,R.drawable.serperior),
                new team_list("Team Kanto",R.drawable.charizard,R.drawable.gyarados,R.drawable.ditto,R.drawable.raichu, R.drawable.nidoking,R.drawable.dragonite),
                new team_list("Team Legendario",R.drawable.mewtwo,R.drawable.raikou,R.drawable.deoxys_normal,R.drawable.giratina, R.drawable.reshiram,R.drawable.zekrom),
        };
        team_list_adapter adapter = new team_list_adapter(this,R.layout.team_list_listview_item_row,team_lists_data);
        tlv = (ListView) findViewById(R.id.team_lv);
        View header = (View) getLayoutInflater().inflate(R.layout.team_list_header_row,null);
        tlv.addHeaderView(header);
        tlv.setAdapter(adapter);
        tlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView x = (TextView)view.findViewById(R.id.team_name);
                //Toast.makeText(getApplicationContext(),v.getText(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(team_list_main.this,MainActivity.class);
                String v = x.getText().toString();
                if(v.equals("Team Kawaii")){
                    intent.putExtra("TEAM_NAME","Team Kawaii");
                }
                else if(v.equals("Team Johto")){
                    intent.putExtra("TEAM_NAME","Team Johto");
                }
                else if(v.equals("Team Sinnoh")){
                    intent.putExtra("TEAM_NAME","Sinnoh");
                }
                else if(v.equals("Team Unova")){
                    intent.putExtra("TEAM_NAME","Team Unova");
                }
                else if(v.equals("Team Kanto")){
                    intent.putExtra("TEAM_NAME","Team Kanto");
                }
                else if(v.equals("Team Legendario")){
                    intent.putExtra("TEAM_NAME","Team Legendario");
                }
                startActivity(intent);
            }
        });
    }
}
