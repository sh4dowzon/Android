package com.example.sebastian.app_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        List<pklist>  test = new ArrayList<pklist>();
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
            pklist_adapter adapter = new pklist_adapter(this,R.layout.poke_list_listview_item_row,test,dato);
            lv = (ListView) findViewById(R.id.lv);
            View header = (View) getLayoutInflater().inflate(R.layout.poke_list_header_row,null);
            lv.addHeaderView(header);
            lv.setAdapter(adapter);
        }
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView x = (TextView)view.findViewById(R.id.elem_tv);
                //Toast.makeText(getApplicationContext(),v.getText(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,single_poke_main.class);
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
    }
}
