package com.intercambiophoto.aplicacionandroi;

public class DataModel {
    String name;
    String descripcion;
    int id_;
    int image;
    public DataModel(String name, String descripcion, int id_, int image) {
        this.name = name;
        this.descripcion = descripcion;
        this.id_ = id_;
        this.image=image;
    }
    public String getName() {
        return name;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public int getImage() {
        return image;
    }
    public int getId() {
        return id_;
    }
}
