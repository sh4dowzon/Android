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

    public String teamName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //INIT
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //INTENT

        Intent intent = getIntent();
        Bundle datos = intent.getExtras();
        if(datos != null){
            teamName = datos.getString("TEAM_NAME");
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
                TextView x = (TextView)view.findViewById(R.id.item);
                //Toast.makeText(getApplicationContext(),v.getText(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PokemonSearchActivity.this,MainActivity.class);
                intent.putExtra("POKEMON_NAME",x.getText().toString().substring(0,1).toUpperCase() + x.getText().toString().substring(1));
                intent.putExtra("TEAM_NAME","Breco's Team");
                x = (TextView)view.findViewById(R.id.textView1);
                String[] types = x.getText().toString().split("-");
                intent.putExtra("TYPE_1",types[0].toLowerCase().trim());
                if(types.length == 2){
                    intent.putExtra("TYPE_2",types[1].toLowerCase().trim());
                }
                else{
                    intent.putExtra("TYPE_2","");
                }


                startActivity(intent);
            }
        });
    }
}
