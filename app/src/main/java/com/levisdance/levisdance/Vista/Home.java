package com.levisdance.levisdance.Vista;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.levisdance.levisdance.Control.LogicDataBase;
import com.levisdance.levisdance.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.levisdance.levisdance.MESSAGE";

    private com.levisdance.levisdance.Modelo.Usuario usuario;

    private LogicDataBase dataBase;

    ListView listView;
    List list = new ArrayList();
    Adaptador adapter;
    ArrayList<Publicacion> imageList;
    private static int RESULT_LOAD_IMAGE = 1;
    private StorageReference mStorageRef;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dataBase=new LogicDataBase(this);
        mStorageRef = FirebaseStorage.getInstance().getReference().child("Publicaciones");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference();


        Intent intent=new Intent();
        String usuario=intent.getStringExtra(EXTRA_MESSAGE);
        try {
            com.levisdance.levisdance.Modelo.Usuario u=dataBase.buscarUsuario(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Boton para refrescar activity
        ImageButton btn1 = (ImageButton) findViewById(R.id.iraHome);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(v.getContext(),Home.class);
                startActivity(intent);

            }
        });
        //Boton ir a usuario
        ImageButton btn2 = (ImageButton) findViewById(R.id.iraUsuario);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Usuario.class);
                startActivity(intent);

            }
        });

        //Boton para ir a cambio de contraseña



        //Publicaciones
        imageList = new ArrayList<Publicacion>();

        //ejemplo de creación de objeto

        Publicacion temp1 = new Publicacion(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fotodo), 1, "Edward Figueroa", "Cali - Valle del cauca", "#THISISMYTITLE", "subido hace 8 min");
        Publicacion temp2 = new Publicacion(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fotouno), 1, "Edward Figueroa", "Cali - Valle del cauca", "#THISISMYTITLE", "subido hace 8 min");

        imageList.add(temp1);
        imageList.add(temp2);


        ArrayAdapter<Publicacion> adapter = new Adaptador(this, 0, imageList);

        //Find list view and bind it with the custom adapter
        listView = (ListView) findViewById(R.id.items_list);
        listView.setAdapter(adapter);
    }

    public void user(View view){

        Intent intentLog = new Intent(this, Usuario.class);
        startActivity(intentLog);

    }

    public void openCamera(View view) {
        Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(i);
    }

    public void agregarFoto(View view){
        try {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, RESULT_LOAD_IMAGE);
        }catch (Exception e){
            Log.i("Error", e.toString());
        }
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if (resultCode == RESULT_OK) try{
            final Uri imageUri = data.getData();
            String url=imageUri.getPath();
            final InputStream imageStream = getContentResolver().openInputStream(imageUri);
            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            ImageView imagenCargada= new ImageView(this);
            imagenCargada.setImageBitmap(selectedImage);
            Drawable ima = imagenCargada.getDrawable();
            Publicacion temp9 = new Publicacion(ima, 1, "Edward Figueroa", "Cali - Valle del cauca", "#THISISMYTITLE", "subido hace 8 min");
            imageList.add(temp9);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        else {
        }
    }
    public void cambiarContrasenia(View view){
        Intent intent=new Intent(this, CambioContrasena.class);
        intent.putExtra(EXTRA_MESSAGE, usuario.getCorreo());
        startActivity(intent);

    }



    public void realtime(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                ArrayList<com.levisdance.levisdance.Vista.Publicacion> value = dataSnapshot.getValue(ArrayList.class);
                //Log.d(TAG, "Value is: " + value);
                imageList = (ArrayList<Publicacion>) value;


                //Find list view and bind it with the custom adapter
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }

}
