package com.villalobos.appdenunciasproyecto.model;

public class Denuncias {

    private String id;
    private String titulo;
    private String direccion;

    public Denuncias(){

    }

    public Denuncias(String id, String titulo, String direccion) {
        this.id = id;
        this.titulo = titulo;
        this.direccion = direccion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
