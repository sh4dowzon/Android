package com.example.sebastian.app_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

//lista_de_pokes//
public class TeamActivity extends AppCompatActivity {

    TextView team_name;
    ImageView team_icon1;
    ImageView team_icon2;
    ImageView team_icon3;
    ImageView team_icon4;
    ImageView team_icon5;
    ImageView team_icon6;
    ListView lv;


    //TEST

    private int team_id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        team_name = (TextView) findViewById(R.id.team_name);
        team_icon1 = (ImageView) findViewById(R.id.team_icon1);
        team_icon2 = (ImageView) findViewById(R.id.team_icon2);
        team_icon3 = (ImageView) findViewById(R.id.team_icon3);
        team_icon4 = (ImageView) findViewById(R.id.team_icon4);
        team_icon5 = (ImageView) findViewById(R.id.team_icon5);
        team_icon6 = (ImageView) findViewById(R.id.team_icon6);
        Intent intent_in = getIntent();
        Bundle extras = intent_in.getExtras();
        if (extras!=null) {

            //get team by id
            team_id = extras.getInt("TEAM_ID");
            DBHelper db = new DBHelper(this);

            //get pokemons from team
            List<Pokemon> pokemons = db.getPokemonsFromTeam(team_id);


            //load pokemons in list adapter
            PokemonListAdapter adapter = new PokemonListAdapter(this,R.layout.poke_list_listview_item_row,pokemons);
            lv = (ListView) findViewById(R.id.lv);
            View header = (View) getLayoutInflater().inflate(R.layout.poke_list_header_row,null);
            lv.addHeaderView(header);
            lv.setAdapter(adapter);



            //adapter.addPokemonToTeam(pokemon,AsyncPokemon.type1,AsyncPokemon.type2);

        }


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView x = (TextView)view.findViewById(R.id.elem_tv);
                //Toast.makeText(getApplicationContext(),v.getText(), Toast.LENGTH_SHORT).show();
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
}
