package com.levisdance.levisdance.Vista;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.levisdance.levisdance.Control.LogicDataBase;
import com.levisdance.levisdance.Modelo.*;
import com.levisdance.levisdance.Modelo.Usuario;
import com.levisdance.levisdance.R;

public class Login extends AppCompatActivity {

    //Firebase Auth State
    private FirebaseAuth mAuth;


    private com.levisdance.levisdance.Modelo.Usuario usuario;

    public static final String EXTRA_MESSAGE = "com.levisdance.levisdance.MESSAGE";

    //Base de datos
    //private LogicDataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Inicializar mAuth
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_login);
        Button btn = (Button) findViewById(R.id.Registrateahora);
        //dataBase=new LogicDataBase(this);
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

        if(!correo.getText().toString().equals("")||!correo.getText().toString().equals(" ")){

            try {
                //usuario=dataBase.buscarUsuario(correo.getText().toString());
                if(!password.equals("")||!password.equals(" ")){
                    final Intent intent= new Intent(this, Home.class);
                    mAuth.signInWithEmailAndPassword(correo.toString(), password)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                   //Aquí se debe hacer que pasa si el login es correcto
                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (task.isSuccessful()) {
                                        FirebaseUser user= task.getResult().getUser();
                                        String email= user.getEmail();
                                        String name = user.getDisplayName();
                                        String ph = user.getPhotoUrl().toString();

                                        usuario= new Usuario(name,email, ph);
                                        startActivity(intent);
                                    }else{
                                        Toast.makeText(Login.this,"No se pudo realizar la autenticación",
                                                Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "La contraseña no coincide", Toast.LENGTH_SHORT).show();
                    contrasena.setText("");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(this, "El correo no puede estar vacío", Toast.LENGTH_SHORT).show();
        }



    }

}
