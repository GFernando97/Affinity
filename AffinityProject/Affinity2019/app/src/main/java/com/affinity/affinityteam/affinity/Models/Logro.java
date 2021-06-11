package com.affinity.affinityteam.affinity.Models;

public class Logro {
    private int id_logro;
    private String nombre;
    private int puntaje;

    public Logro(int id_logro, String nombre, int puntaje) {
        this.id_logro = id_logro;
        this.nombre = nombre;
        this.puntaje = puntaje;
    }

    public Logro() {
    }

    public int getId_logro() {
        return id_logro;
    }

    public void setId_logro(int id_logro) {
        this.id_logro = id_logro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
}
