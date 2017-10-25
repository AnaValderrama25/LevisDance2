package com.levisdance.levisdance.Vista;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.levisdance.levisdance.Control.LogicDataBase;
import com.levisdance.levisdance.Modelo.*;
import com.levisdance.levisdance.Modelo.Usuario;
import com.levisdance.levisdance.R;
import com.facebook.FacebookSdk;

public class Login extends FragmentActivity {

    //Firebase Auth State
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private CallbackManager callbackManager;


    private com.levisdance.levisdance.Modelo.Usuario usuario;

    public static final String EXTRA_MESSAGE = "com.levisdance.levisdance.MESSAGE";

    //Base de datos
    //private LogicDataBase dataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Inicializar mAuth
        mAuth = FirebaseAuth.getInstance();
        //Facebook
        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
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

                                        mFirebaseDatabase=mFirebaseInstance.getReference("usuarios");

                                        usuario= new Usuario(name,email, ph);
                                        mFirebaseDatabase.child(user.getUid()).setValue(usuario);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    private void handleFacebookAccessToken(AccessToken token) {

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            Usuario nuevo= new Usuario(user.getDisplayName(),user.getEmail(),user.getPhotoUrl().toString());
                            mFirebaseDatabase=mFirebaseInstance.getReference("usuarios");
                            mFirebaseDatabase.child(user.getUid()).setValue(nuevo);
                            Intent intent = new Intent(getActivity(),Home.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }


    public Activity getActivity(){
        return this;
    }

}
