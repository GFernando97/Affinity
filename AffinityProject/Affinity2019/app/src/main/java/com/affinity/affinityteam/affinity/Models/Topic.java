package com.affinity.affinityteam.affinity.Models;

public class Topic {
    String nombre;
    int peso;

    public Topic() {
    }

    public Topic(String nombre, int peso) {
        this.nombre = nombre;
        this.peso = peso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
}
