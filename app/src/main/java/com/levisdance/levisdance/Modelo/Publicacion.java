package com.levisdance.levisdance.Modelo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by User on 12/09/2017.
 */

public class Publicacion implements Serializable {

    private String titulo;
    private Date fecha;
    private String ubicacion;
    private String foto;

    public Publicacion(String titulo, Date fecha, String ubicacion, String foto) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.foto = foto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
