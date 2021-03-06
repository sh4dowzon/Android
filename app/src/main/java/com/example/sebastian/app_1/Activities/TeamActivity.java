package com.example.sebastian.app_1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sebastian.app_1.Adapters.TeamAdapter;
import com.example.sebastian.app_1.R;
import com.example.sebastian.app_1.Utils.DBHelper;
import com.example.sebastian.app_1.Utils.Pokemon;

import java.util.List;


public class TeamActivity extends AppCompatActivity {


    ListView lv;



    private int team_id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent_in = getIntent();
        Bundle extras = intent_in.getExtras();
        if (extras!=null) {

            //GET TEAM FROM ID
            team_id = extras.getInt("TEAM_ID");
            DBHelper db = new DBHelper(this);

            //GET POKEMONS FROM TEAM
            List<Pokemon> pokemons = db.getPokemonsFromTeam(team_id);

            //LOAD POKEMONS IN LIST ADAPTER
            TeamAdapter adapter = new TeamAdapter(this,R.layout.poke_list_listview_item_row,pokemons);
            lv = (ListView) findViewById(R.id.lv);
            View header = (View) getLayoutInflater().inflate(R.layout.poke_list_header_row,null);
            lv.addHeaderView(header);
            lv.setAdapter(adapter);


        }

        //ELEMENT (POKEMON) OF LIST TOUCHED
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView x = (TextView)view.findViewById(R.id.elem_tv);
                Intent intent = new Intent(TeamActivity.this,SinglePokemonActivity.class);
                String v = x.getText().toString();
                if(v.equals("Gengar")){
                    intent.putExtra("NAME","Gengar");
                }
                else if(v.equals("Sceptile")){
                    intent.putExtra("NAME","Sceptile");
                }
                else if(v.equals("Togekiss")){
                    intent.putExtra("NAME","Togekiss");
                }
                else if(v.equals("Magnezone")){
                    intent.putExtra("NAME","Magnezone");
                }
                else if(v.equals("Gliscor")){
                    intent.putExtra("NAME","Gliscor");
                }
                else if(v.equals("Froslass")){
                    intent.putExtra("NAME","Froslass");
                }
                startActivity(intent);
            }
        });
        Button pokemon = (Button) findViewById(R.id.button_add_poke);
        pokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeamActivity.this,PokemonSearchActivity.class);

                intent.putExtra("TEAM_ID",team_id);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        Log.d("BACK","BACK");
        if (requestCode == 1) {

            if(resultCode == RESULT_OK){

                //GET TEAM FROM ID

                Bundle extras = intent.getExtras();
                if (extras!=null) {

                    //GET TEAM FROM ID
                    team_id = extras.getInt("TEAM_ID");
                    DBHelper db = new DBHelper(this);

                    //GET POKEMONS FROM TEAM
                    List<Pokemon> pokemons = db.getPokemonsFromTeam(team_id);

                    //LOAD POKEMONS IN LIST ADAPTER
                    TeamAdapter adapter = new TeamAdapter(this,R.layout.poke_list_listview_item_row,pokemons);
                    lv = (ListView) findViewById(R.id.lv);
                    View header = (View) getLayoutInflater().inflate(R.layout.poke_list_header_row,null);
                    lv.addHeaderView(header);
                    lv.setAdapter(adapter);


                }
            }
            if (resultCode == RESULT_CANCELED) {
                //Do nothing?
            }
        }
    }//onActivityResult
}
