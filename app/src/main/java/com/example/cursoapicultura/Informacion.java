package com.example.cursoapicultura;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;

public class Informacion extends AppCompatActivity {

    TextView titulo, paragrafo;
    Field fichero;
    String tema, paragrafoString;
    ScrollView scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);

        hideSystemUI();

        titulo = (TextView) findViewById(R.id.titulo);
        paragrafo = (TextView) findViewById(R.id.paragrafo);
        scroll = (ScrollView) findViewById(R.id.scroll);
        tema = ((String) getIntent().getExtras().getSerializable("tema"));
        titulo.setText(tema);

        realFile();
        paragrafo.setText(paragrafoString);
    }

    private void realFile() {
        InputStream fileInputStream = null;

        try {
            int idFichero = this.getResources().getIdentifier(quitarTilde(tema.toLowerCase().replace(" ","")), "raw", this.getPackageName());
            fileInputStream = this.getResources().openRawResource(idFichero);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String lineaTexto;
            StringBuilder stringBuilder = new StringBuilder();
            while((lineaTexto = bufferedReader.readLine())!=null) {
                stringBuilder.append(lineaTexto).append("\n");
            }

            paragrafoString = stringBuilder.toString();

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

    private void hideSystemUI() {
        getSupportActionBar().hide();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    private String quitarTilde(String texto) {
        String original = "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖØÙÚÛÜÝßàáâãäåæçèéêëìíîïðñòóôõöøùúûüýÿ";
        // Cadena de caracteres ASCII que reemplazarán los originales.
        String ascii = "AAAAAAACEEEEIIIIDNOOOOOOUUUUYBaaaaaaaceeeeiiiionoooooouuuuyy";
        for (int i = 0; i < original.length(); i++) {
            // Reemplazamos los caracteres especiales.

            texto = texto.replace(original.charAt(i), ascii.charAt(i));

        }//for i

        return texto;
    }

}