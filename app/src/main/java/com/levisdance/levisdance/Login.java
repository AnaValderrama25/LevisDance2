package com.levisdance.levisdance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.levisdance.levisdance.Control.LogicDataBase;
import com.levisdance.levisdance.Modelo.*;

public class Login extends AppCompatActivity {


    private com.levisdance.levisdance.Modelo.Usuario usuario;

    public static final String EXTRA_MESSAGE = "com.levisdance.levisdance.MESSAGE";

    private LogicDataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btn = (Button) findViewById(R.id.Registrateahora);
        dataBase=new LogicDataBase(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Registro.class);
                startActivityForResult(intent,0);
            }
        });

    }

    public void login(View view){

        Intent intentLog = new Intent(this, Home.class);
        //EditText editText= (EditText) findViewById(R.id.edit_message);
        //String nombre = editText.getText().toString();
        //intent.putExtra(CLAVE_VALOR, nombre);
        startActivity(intentLog);

    }

    public void iniciarSesion(View view){
        EditText correo= (EditText) findViewById(R.id.correo);
        EditText contrasena= (EditText) findViewById(R.id.contrasena);
        String password= contrasena.getText().toString();

        if(!correo.getText().toString().equals("")){

            try {
                usuario=dataBase.buscarUsuario(correo.getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(usuario.getContrasena().equals(contrasena)){
                Intent intent= new Intent(this, Home.class);
                intent.putExtra(EXTRA_MESSAGE,correo.getText().toString());
                startActivity(intent);
            }else{
                Toast.makeText(this, "La contraseña no coincide", Toast.LENGTH_SHORT).show();
                contrasena.setText("");
            }
        }else{
            Toast.makeText(this, "El correo no puede estar vacío", Toast.LENGTH_SHORT).show();
        }

    }

}
