package com.example.sebastian.app_1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sebastian.app_1.Adapters.TeamListAdapter;
import com.example.sebastian.app_1.R;
import com.example.sebastian.app_1.Utils.DBHelper;
import com.example.sebastian.app_1.Utils.TeamList;

import java.util.ArrayList;

//LAUNCHER//
public class TeamListActivity extends AppCompatActivity {
    ListView tlv;
    public TeamListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_list_main);


        DBHelper db = new DBHelper(this);
        db.getWritableDatabase();
        //db.dropTables();
        //db.createTables();
        //db.addTeam("Team Kawaii");
        //db.addTeam("Team Johto");
        //db.addTeam("Team Sinnoh");
        //db.addTeam("Team Unova");
        //db.addTeam("Team Kanto");
        //db.addTeam("Team Legendario");

        ArrayList<TeamList> team_lists_data = db.getTeams();

        adapter = new TeamListAdapter(this,R.layout.team_list_listview_item_row,team_lists_data);
        tlv = (ListView) findViewById(R.id.team_lv);
        View header = (View) getLayoutInflater().inflate(R.layout.team_list_header_row,null);
        tlv.addHeaderView(header);
        tlv.setAdapter(adapter);
        tlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView x = (TextView)view.findViewById(R.id.team_name);

                Intent intent = new Intent(TeamListActivity.this,TeamActivity.class);
                String v = x.getText().toString();
                TeamList selectedTeam = adapter.data.get(position-1);
                intent.putExtra("TEAM_ID",selectedTeam.id);

                startActivity(intent);
            }
        });





    }



}
