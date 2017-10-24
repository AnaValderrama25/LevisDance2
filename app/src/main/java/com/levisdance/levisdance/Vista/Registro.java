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
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.levisdance.levisdance.Control.LogicDataBase;
import com.levisdance.levisdance.Modelo.Usuario;
import com.levisdance.levisdance.R;

public class Registro extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.levisdance.levisdance.MESSAGE";

    private com.levisdance.levisdance.Modelo.Usuario usuario;
    //Base de datos local
    //private LogicDataBase dataBase;

    //Firebase
    private FirebaseAuth mAuth;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        setContentView(R.layout.activity_registro);
        //dataBase=new LogicDataBase(getApplicationContext());
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
        final EditText nombre=(EditText)findViewById(R.id.Nombre);
        final EditText correo=(EditText)findViewById(R.id.Correo);
        final EditText apellido=(EditText)findViewById(R.id.apellido);
        EditText contrasenia=(EditText)findViewById(R.id.contrasenia);
        final Intent intent=new Intent(this, Home.class);

        if(!nombre.getText().toString().equals("")&&!apellido.getText().toString().equals("")&&!correo.getText().toString().equals("")&&
                !contrasenia.getText().toString().equals("")){
            //metodo firebase authentication
            mAuth.createUserWithEmailAndPassword(correo.toString(), contrasenia.toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                mFirebaseAnalytics.setUserProperty("Nombre", nombre.toString());
                                mFirebaseAnalytics.setUserProperty("Apellido", apellido.toString());
                                mFirebaseAnalytics.setUserProperty("Correo", correo.toString());

                                //Contraseña no en firebase
                                startActivity(intent);
                                finish();


                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(Registro.this, "Autenticacion fallida",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }
            //Aquí adentro va lo que hicimos de la base de datos y el paso a la otra activity de Home
//            usuario=new Usuario(nombre.getText().toString(),
//                    apellido.getText().toString(),
//                    correo.getText().toString(),
//                    contrasenia.getText().toString(),
//                    null
//            );

            //dataBase.insertarUsuario(usuario);


        else{
            Toast.makeText(this, "Todos los campos deben estar completos", Toast.LENGTH_SHORT).show();
            nombre.setText("");
            apellido.setText("");
            correo.setText("");
            contrasenia.setText("");
        }


    }

}
