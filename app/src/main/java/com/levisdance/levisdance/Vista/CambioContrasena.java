package com.levisdance.levisdance.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.levisdance.levisdance.Control.LogicDataBase;
import com.levisdance.levisdance.R;

public class CambioContrasena extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.levisdance.levisdance.MESSAGE";

    private LogicDataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambio_contrasena);
        dataBase=new LogicDataBase(this);

        //Boton que vuelve al home
        ImageButton btn = (ImageButton) findViewById(R.id.iratras);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Home.class);
                startActivityForResult(intent,0);
            }
        });

    }
    public void confirmarContrasena(View view){
        EditText contra1 = (EditText) findViewById(R.id.nueva);
        EditText contra2 = (EditText) findViewById(R.id.confirmar);
        Intent intent=new Intent();
        if(contra1.getText().toString().equals(contra2.getText().toString())){
            String usuario=intent.getStringExtra(EXTRA_MESSAGE);
            try {
                com.levisdance.levisdance.Modelo.Usuario u=dataBase.buscarUsuario(usuario);
                //u.setContrasena(contra1.getText().toString());
                dataBase.modificarUsuario(u);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
