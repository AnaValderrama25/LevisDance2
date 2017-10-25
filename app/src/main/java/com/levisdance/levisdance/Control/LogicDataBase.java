package com.levisdance.levisdance.Control;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.levisdance.levisdance.Modelo.Usuario;
import com.levisdance.levisdance.Modelo.Publicacion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by aboni on 12/09/2017.
 */

public class LogicDataBase extends SQLiteOpenHelper {

    private static final int VERSION_BASEDATOS=1;
    private static final String NOMBRE_BASEDATOS="HowToDance.db";

    public LogicDataBase(Context context){
        super(context,NOMBRE_BASEDATOS,null,VERSION_BASEDATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataBase.SQL_CREATE_TABLE_USUARIOS);
       db.execSQL(DataBase.SQL_CREATE_TABLE_PUBLICACIONES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    public void insertarUsuario(Usuario usuario){
        SQLiteDatabase db=getWritableDatabase();
        if(db !=null){
            ContentValues valores=new ContentValues();
            valores.put(DataBase.DatosColumnasUsuario.USUARIO_NOMBRE,usuario.getNombre());
            valores.put(DataBase.DatosColumnasUsuario.USUARIO_CORREO,usuario.getCorreo());
            valores.put(DataBase.DatosColumnasUsuario.USUARIO_FOTO_PERFIL, usuario.getFotoPerfil());
            db.insert(DataBase.TABLA_USUARIOS, null, valores);
            db.close();
        }
    }

    public void modificarUsuario(Usuario usuario){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues valores=new ContentValues();
        valores.put(DataBase.DatosColumnasUsuario.USUARIO_NOMBRE,usuario.getNombre());
        valores.put(DataBase.DatosColumnasUsuario.USUARIO_CORREO,usuario.getCorreo());
        valores.put(DataBase.DatosColumnasUsuario.USUARIO_FOTO_PERFIL, usuario.getFotoPerfil());
        db.update(DataBase.TABLA_USUARIOS, valores, DataBase.DatosColumnasUsuario.USUARIO_CORREO+"="+usuario.getCorreo(),null);
        db.close();
    }

    public void borrarUsuario(Usuario usuario){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(DataBase.TABLA_USUARIOS,DataBase.DatosColumnasUsuario.USUARIO_CORREO+"="+usuario.getCorreo(), null);
    }
    public Usuario buscarUsuario(String correo)throws Exception{
        SQLiteDatabase db=getReadableDatabase();
        String[] parameter= {correo};
        String[] campos={DataBase.DatosColumnasUsuario.USUARIO_NOMBRE,
                DataBase.DatosColumnasUsuario.USUARIO_APELLIDO,
                DataBase.DatosColumnasUsuario.USUARIO_CONTRASENA,
                DataBase.DatosColumnasUsuario.USUARIO_CORREO,
                DataBase.DatosColumnasUsuario.USUARIO_FOTO_PERFIL};


        Cursor cursor=db.query(DataBase.TABLA_USUARIOS,campos,DataBase.DatosColumnasUsuario.USUARIO_CORREO+"=?",parameter,null,null,null);

        if(cursor!=null){
            cursor.moveToFirst();
            Usuario usuarioRetornado=new Usuario(
                    cursor.getString(cursor.getColumnIndex(DataBase.DatosColumnasUsuario.USUARIO_NOMBRE)),
                    cursor.getString(cursor.getColumnIndex(DataBase.DatosColumnasUsuario.USUARIO_CORREO)),
                    cursor.getString(cursor.getColumnIndex(DataBase.DatosColumnasUsuario.USUARIO_FOTO_PERFIL)));
            db.close();
            cursor.close();
            return usuarioRetornado;
        }else {
            db.close();
            cursor.close();
            throw new Exception("El usuario no existe");
        }

    }

    public void cerrarBD(){
        SQLiteDatabase db=getReadableDatabase();
        db.close();
    }

    public void insertarPublicacion(Usuario usuario, Publicacion publicacion){
        SQLiteDatabase db=getWritableDatabase();
        if(db !=null){

            ContentValues valores=new ContentValues();
            valores.put(DataBase.DatosColumnasPublicacion.PUBLICACION_TITULO,publicacion.getTitulo());
            valores.put(DataBase.DatosColumnasPublicacion.PUBLICACION_FECHA, publicacion.getFecha().toString());
            valores.put(DataBase.DatosColumnasPublicacion.PUBLICACION_UBICACION,publicacion.getUbicacion());
            valores.put(DataBase.DatosColumnasPublicacion.PUBLICACION_FOTO, publicacion.getFoto());
            valores.put(DataBase.DatosColumnasPublicacion.PUBLICACION_USUARIO, usuario.getCorreo());
            db.insert(DataBase.TABLA_PUBLICACIONES, null, valores);
            db.close();
        }
    }

    public void modificarPublicacion(Publicacion publicacion){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues valores=new ContentValues();
        valores.put(DataBase.DatosColumnasPublicacion.PUBLICACION_TITULO,publicacion.getTitulo());
        valores.put(DataBase.DatosColumnasPublicacion.PUBLICACION_FECHA, publicacion.getFecha().toString());
        valores.put(DataBase.DatosColumnasPublicacion.PUBLICACION_UBICACION,publicacion.getUbicacion());
        valores.put(DataBase.DatosColumnasPublicacion.PUBLICACION_FOTO, publicacion.getFoto());
        db.update(DataBase.TABLA_PUBLICACIONES, valores, DataBase.DatosColumnasPublicacion.PUBLICACION_TITULO+"="+publicacion.getTitulo(),null);
        db.close();
    }

    public void borrarPublciacion(Publicacion publicacion){
        SQLiteDatabase db=getWritableDatabase();
        db.delete(DataBase.TABLA_PUBLICACIONES,DataBase.DatosColumnasPublicacion.PUBLICACION_TITULO+"="+publicacion.getTitulo(), null);
    }
    public ArrayList<Publicacion> buscarPublicacion(Usuario usuario)throws Exception{
        SQLiteDatabase db=getReadableDatabase();
        String[] parameter= {usuario.getCorreo()};
        String[] campos={DataBase.DatosColumnasPublicacion.PUBLICACION_TITULO,
                DataBase.DatosColumnasPublicacion.PUBLICACION_FOTO,
                DataBase.DatosColumnasPublicacion.PUBLICACION_FECHA,
                DataBase.DatosColumnasPublicacion.PUBLICACION_UBICACION};


        Cursor cursor=db.query(DataBase.TABLA_PUBLICACIONES,campos,DataBase.DatosColumnasPublicacion.PUBLICACION_USUARIO+"=?",parameter,null,null,null);

        if(cursor!=null){
            cursor.moveToFirst();
            ArrayList<Publicacion> publicaciones=new ArrayList<>();
            while(cursor.moveToNext()){
                Publicacion publicacionRetornada=new Publicacion(
                        cursor.getString(cursor.getColumnIndex(DataBase.DatosColumnasPublicacion.PUBLICACION_TITULO)),
                        new Date(cursor.getString(cursor.getColumnIndex(DataBase.DatosColumnasPublicacion.PUBLICACION_FECHA))),
                        cursor.getString(cursor.getColumnIndex(DataBase.DatosColumnasPublicacion.PUBLICACION_UBICACION)),
                        cursor.getString(cursor.getColumnIndex(DataBase.DatosColumnasPublicacion.PUBLICACION_FOTO)));
                publicaciones.add(publicacionRetornada );
            }


            db.close();
            cursor.close();
            return publicaciones;
        }else {
            db.close();
            cursor.close();
            throw new Exception("El usuario no existe");
        }
    }
}
