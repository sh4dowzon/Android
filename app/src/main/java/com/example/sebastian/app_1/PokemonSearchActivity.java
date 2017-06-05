package com.example.sebastian.app_1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PokemonSearchActivity extends AppCompatActivity {
    public ImageView list_type1;
    public ImageView list_type2;

    //TEST

    private int team_id;
    public Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //INIT
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //TEST
        this.context = this;
        //

        //INTENT

        Intent intent = getIntent();
        Bundle datos = intent.getExtras();
        if(datos != null){

            team_id = datos.getInt("TEAM_ID");
        }

        //ADAPTER PARA LISTAS
        List<String> poke_list = new ArrayList<String>();
        final PokemonSearchAdapter adapter = new PokemonSearchAdapter(this, poke_list);
        ListView lista = (ListView) findViewById(R.id.listView);
        lista.setAdapter(adapter);

        //AL TOCAR BOTON BUSCAR
        Button pokemon = (Button) findViewById(R.id.button);
        pokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText text = (EditText) findViewById(R.id.editText);


                //EJECUTAR TASK POR DEBAJO
                new AsyncPokemon((ImageView) findViewById(R.id.imageView),adapter).execute(text.getText().toString());

                //SACAR TECLADO Y LIMPIAR TEXTO
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                text.setText("");


            }
        });

        //AL TOCAR ALGUN ELEMENTO DE LOS RESULTADOS
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView x = (TextView)view.findViewById(R.id.search_poke_name);


                //ADD pokemon to DB

                DBHelper db = new DBHelper(context);

                int type1 = context.getResources().getIdentifier("drawable/"+AsyncPokemon.type1, null, context.getPackageName());
                int type2 = context.getResources().getIdentifier("drawable/"+AsyncPokemon.type2, null, context.getPackageName());

                db.addPokemon(x.getText().toString(),type1,type2,"levitate",team_id);

                //intent
                Intent intent = new Intent(PokemonSearchActivity.this,TeamActivity.class);
                intent.putExtra("TEAM_ID",team_id);
                list_type1 = (ImageView)view.findViewById(R.id.search_poke_type1);
                list_type2 = (ImageView)view.findViewById(R.id.search_poke_type2);
                //x = (TextView)view.findViewById(R.id.textView1);
                //String[] types = x.getText().toString().split("-");
                //placeholders de tipo//


                startActivity(intent);
            }
        });
    }
}
