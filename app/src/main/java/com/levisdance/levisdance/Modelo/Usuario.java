package com.levisdance.levisdance.Modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by User on 12/09/2017.
 */

public class Usuario implements Serializable {

    //Atributos
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasena;
    private String fotoPerfil;
    private ArrayList<Publicacion> publicaciones;

    public Usuario(String nombre, String apellido, String correo, String contrasena, String fotoPerfil) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contrasena = contrasena;
        this.fotoPerfil=fotoPerfil;
        publicaciones = new ArrayList<Publicacion>();
    }

    public void setPublicaciones(ArrayList<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public ArrayList<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
}
