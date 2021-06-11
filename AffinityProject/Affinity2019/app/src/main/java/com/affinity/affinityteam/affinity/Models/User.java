package com.affinity.affinityteam.affinity.Models;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;


public class User {
    @NonNull
    public  String username;
    public  String nombre;
    public  int edad;
    public  List<Topic> topics = new ArrayList<Topic>();
    public  double radioBusqueda;
    public  String universidadOrigen;
    public  String universidadDestino;
    public  int user_type;
    public  String paisOrigen;
    public  String paisDestino;
    public  String carrera;
    //public  List<Logro> logros = new ArrayList<Logro>();
    public  String ciudadActual;
    public  String descripcion;
    public  double userLat; //latitud
    public  double userLon; //longitud
    public  String imageURL; //for the moment the user will have static images...
    public  String status;
    public boolean userState;
    //20 atributes

    public User() {
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public double getRadioBusqueda() {
        return radioBusqueda;
    }

    public void setRadioBusqueda(double radioBusqueda) {
        this.radioBusqueda = radioBusqueda;
    }

    public String getUniversidadOrigen() {
        return universidadOrigen;
    }

    public void setUniversidadOrigen(String universidadOrigen) {
        this.universidadOrigen = universidadOrigen;
    }

    public String getUniversidadDestino() {
        return universidadDestino;
    }

    public void setUniversidadDestino(String universidadDestino) {
        this.universidadDestino = universidadDestino;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public String getPaisDestino() {
        return paisDestino;
    }

    public void setPaisDestino(String paisDestino) {
        this.paisDestino = paisDestino;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getCiudadActual() {
        return ciudadActual;
    }

    public void setCiudadActual(String ciudadActual) {
        this.ciudadActual = ciudadActual;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getUserLat() {
        return userLat;
    }

    public void setUserLat(double userLat) {
        this.userLat = userLat;
    }

    public double getUserLon() {
        return userLon;
    }

    public void setUserLon(double userLon) {
        this.userLon = userLon;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isUserState() {
        return userState;
    }

    public void setUserState(boolean userState) {
        this.userState = userState;
    }
}
