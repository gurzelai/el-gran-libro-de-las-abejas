package com.example.cursoapicultura;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainTemas extends AppCompatActivity {

    List<Button> botones;
    List<String> temas;
    Button uno,dos,tres,cuatro,cinco, seis, siete, ocho, nueve, diez, volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_temas);

        hideUI();

        temas = ((Apartado) getIntent().getExtras().getSerializable("apartado")).getTemas();

        botones = new ArrayList<>();
        botones.add(uno = (Button) findViewById(R.id.menu11));
        botones.add(dos = (Button) findViewById(R.id.menu22));
        botones.add(tres = (Button) findViewById(R.id.menu3));
        botones.add(cuatro = (Button) findViewById(R.id.menu4));
        botones.add(cinco = (Button) findViewById(R.id.menu5));
        botones.add(seis = (Button) findViewById(R.id.menu6));
        botones.add(siete = (Button) findViewById(R.id.menu7));
        botones.add(ocho = (Button) findViewById(R.id.menu8));
        botones.add(nueve = (Button) findViewById(R.id.menu9));
        botones.add(diez = (Button) findViewById(R.id.menu10));

        volver = (Button) findViewById(R.id.volver);
        volver.setOnClickListener(view -> finish());

        for(int i = 0; i<temas.size(); i++){
            int finalI = i;
            botones.get(i).setOnClickListener(view -> abrirIntent(finalI));
            botones.get(i).setText((1+i)+". "+temas.get(i));
        }
        if(temas.size() < 10){
            int cantEliminar = 10-temas.size();
            for(int i = temas.size(); i < 10; i++){
                botones.get(i).setVisibility(View.INVISIBLE);
            }
        }



    }
    public void hideUI() {
        getSupportActionBar().hide();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    private void abrirIntent(int i) {
        Intent intent = new Intent(getApplicationContext(), Informacion.class);
        intent.putExtra("tema", temas.get(i));
        startActivity(intent);
    }

}