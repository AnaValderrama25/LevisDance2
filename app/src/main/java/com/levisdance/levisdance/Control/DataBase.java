package com.levisdance.levisdance.Control;


import android.provider.BaseColumns;

/**
 * Created by aboni on 12/09/2017.
 */

public class DataBase {

    public static final String TABLA_USUARIOS="usuarios";
    public static final String TABLA_PUBLICACIONES="publicaciones";


    public static final String SQL_CREATE_TABLE_USUARIOS="CREATE TABLE "
            +DataBase.TABLA_USUARIOS+ " ("
            + DatosColumnasUsuario.USUARIO_NOMBRE+ " TEXT,"
            + DatosColumnasUsuario.USUARIO_APELLIDO+ " TEXT,"
            + DatosColumnasUsuario.USUARIO_CORREO+ " TEXT PRIMARY KEY,"
            + DatosColumnasUsuario.USUARIO_CONTRASENA+ " TEXT,"
            + DatosColumnasUsuario.USUARIO_FOTO_PERFIL+ " TEXT)";

    public static final String SQL_CREATE_TABLE_PUBLICACIONES= "CREATE TABLE "
            +DataBase.TABLA_PUBLICACIONES+ " ("
            + DatosColumnasPublicacion.PUBLICACION_TITULO+ " TEXT,"
            + DatosColumnasPublicacion.PUBLICACION_FECHA+ " DATE,"
            + DatosColumnasPublicacion.PUBLICACION_UBICACION+ " TEXT,"
            + DatosColumnasPublicacion.PUBLICACION_FOTO+ " TEXT,"
            + DatosColumnasPublicacion.PUBLICACION_USUARIO+ " TEXT)"
            ;

    public static abstract class DatosColumnasUsuario implements BaseColumns{
        public static final String USUARIO_NOMBRE="nombre";
        public static final String USUARIO_APELLIDO="apellido";
        public static final String USUARIO_CORREO="correo";
        public static final String USUARIO_CONTRASENA="contrasena";
        public static final String USUARIO_FOTO_PERFIL="imagen";
    }


    public static abstract class DatosColumnasPublicacion implements BaseColumns{
        public static final String PUBLICACION_TITULO="nombre";
        public static final String PUBLICACION_FECHA="fecha";
        public static final String PUBLICACION_UBICACION="ubiación";
        public static final String PUBLICACION_FOTO="Foto";
        public static final String PUBLICACION_USUARIO="Usuario";

    }

}
