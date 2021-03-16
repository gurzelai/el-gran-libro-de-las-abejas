package com.example.cursoapicultura;

import java.io.Serializable;
import java.util.List;

public class Apartado implements Serializable {
    String titulo;
    List<String> temas;

    public Apartado(String titulo, List<String> temas){
        this.titulo = titulo;
        this.temas = temas;
    }
    public List<String> getTemas() {
        return temas;
    }
}
