package com.example.cursoapicultura;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button uno,dos,tres,cuatro,cinco;
    List<Apartado> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = new ArrayList<>();
        readFile();
        hideUI();
        TextView titulo = (TextView) findViewById(R.id.titulo);
        titulo.setText(titulo.getText().toString().toUpperCase());
        uno = (Button) findViewById(R.id.menu11);
        dos = (Button) findViewById(R.id.menu22);
        tres = (Button) findViewById(R.id.menu3);
        cuatro = (Button) findViewById(R.id.menu4);
        cinco = (Button) findViewById(R.id.menu5);

        uno.setOnClickListener(view -> abrirIntentMenu(lista.get(0)));
        dos.setOnClickListener(view -> abrirIntentMenu(lista.get(1)));
        tres.setOnClickListener(view -> abrirIntentMenu(lista.get(2)));
        cuatro.setOnClickListener(view -> abrirIntentMenu(lista.get(3)));
        cinco.setOnClickListener(view -> abrirIntentMenu(lista.get(4)));
    }

    private void abrirIntentMenu(Apartado apartado) {
        Intent intent = new Intent(getApplicationContext(), MainTemas.class);
        intent.putExtra("apartado", apartado);
        startActivity(intent);
    }

    public void hideUI() {
        getSupportActionBar().hide();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    public void readFile(){
        InputStream fileInputStream = null;
        try {
            fileInputStream = this.getResources().openRawResource(R.raw.abejas);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String lineaTexto;
            StringBuilder stringBuilder = new StringBuilder();
            while((lineaTexto = bufferedReader.readLine())!=null) {
                stringBuilder.append(lineaTexto);
            }

            String todo = stringBuilder.toString();
            String a[] = todo.split("[%]");
            for(int i = 0; i < a.length; i++){
                String temas [] = a[i].split("[$]");
                String titulo = temas[0];
                String temaCentrado [] = temas[1].split("[&]");
                lista.add(new Apartado(titulo, Arrays.asList(temaCentrado)));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fileInputStream!= null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}