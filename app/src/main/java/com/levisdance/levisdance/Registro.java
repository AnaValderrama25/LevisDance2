package com.levisdance.levisdance;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.levisdance.levisdance.Control.LogicDataBase;
import com.levisdance.levisdance.Modelo.*;
import com.levisdance.levisdance.Modelo.Usuario;

public class Registro extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.levisdance.levisdance.MESSAGE";

    private com.levisdance.levisdance.Modelo.Usuario usuario;
    private LogicDataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        dataBase=new LogicDataBase(getApplicationContext());
        Button btn = (Button) findViewById(R.id.iniciasesion);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Login.class);
                startActivityForResult(intent,0);
            }
        });
    }
    public  void registrarUsuario(View view){
        EditText nombre=(EditText)findViewById(R.id.Nombre);
        EditText correo=(EditText)findViewById(R.id.Correo);
        EditText apellido=(EditText)findViewById(R.id.apellido);
        EditText contrasenia=(EditText)findViewById(R.id.contrasenia);

        if(!nombre.getText().toString().equals("")&&!apellido.getText().toString().equals("")&&!correo.getText().toString().equals("")&&
                !contrasenia.getText().toString().equals("")){
            //Aquí adentro valo que hicimos de la base de datos y el paso a la otra activity de Home
            usuario=new Usuario(nombre.getText().toString(),
                    apellido.getText().toString(),
                    correo.getText().toString(),
                    contrasenia.getText().toString(),
                    null
            );

            dataBase.insertarUsuario(usuario);


            Intent intent=new Intent(this, Home.class);

            intent.putExtra(EXTRA_MESSAGE, usuario.getCorreo());
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this, "Todos los campos deben estar completos", Toast.LENGTH_SHORT).show();
            nombre.setText("");
            apellido.setText("");
            correo.setText("");
            contrasenia.setText("");
        }


    }

}
