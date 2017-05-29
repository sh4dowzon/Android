package com.example.sebastian.app_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

//lista_de_pokes//
public class MainActivity extends AppCompatActivity {

    TextView team_name;
    ImageView team_icon1;
    ImageView team_icon2;
    ImageView team_icon3;
    ImageView team_icon4;
    ImageView team_icon5;
    ImageView team_icon6;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Pokemon>  test = new ArrayList<Pokemon>();
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
            String dato = extras.getString("TEAM_NAME");
            PokemonListAdapter adapter = new PokemonListAdapter(this,R.layout.poke_list_listview_item_row,test,dato);
            lv = (ListView) findViewById(R.id.lv);
            View header = (View) getLayoutInflater().inflate(R.layout.poke_list_header_row,null);
            lv.addHeaderView(header);
            lv.setAdapter(adapter);

            // Cuando te devuelves de haber seleccionado un pokemon de la busqueda y lo agregas al team
            String pokemon = extras.getString("POKEMON_NAME");
            String type1 = extras.getString("TYPE_1");
            String type2 = extras.getString("TYPE_2");
            if(pokemon != null){
                Log.d("POKEMON",pokemon);
                //adapter.addPokemonToTeam(pokemon,"Normal","Dragon");
                //
                //
                adapter.addPokemonToTeam(pokemon,AsyncPokemon.type1,AsyncPokemon.type2);
                //
                //
            }
        }


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView x = (TextView)view.findViewById(R.id.elem_tv);
                //Toast.makeText(getApplicationContext(),v.getText(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,SinglePokemonActivity.class);
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
                Intent intent = new Intent(MainActivity.this,PokemonSearchActivity.class);
                intent.putExtra("TEAM_NAME","Team Kawaii");
                startActivity(intent);

            }
        });
    }
}
