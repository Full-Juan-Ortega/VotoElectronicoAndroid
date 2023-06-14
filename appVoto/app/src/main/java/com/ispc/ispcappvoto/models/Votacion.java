package com.ispc.ispcappvoto.models;

public class Votacion {

    private String nombre;

    private String descripcion;

    private int valoracion;

    private long id; // El ID de la BD

    public Votacion(String nombre, String descripcion, int valoracion) {
        this.nombre= nombre;
        this.descripcion = descripcion;
        this.valoracion= valoracion;
    }

    // Constructor para cuando instanciamos desde la BD
    public Votacion(String nombre, String descripcion, int valoracion, long id) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valoracion= valoracion;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    @Override
    public String toString() {
        return "Votacion{" +
                "nombre='" + nombre + '\'' +
                ", descripción=" + descripcion + '\'' +
                ", valoración="  + valoracion +
                '}';
    }
}
