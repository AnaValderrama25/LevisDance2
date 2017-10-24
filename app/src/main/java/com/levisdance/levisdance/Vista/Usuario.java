package com.levisdance.levisdance.Vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.levisdance.levisdance.R;

public class Usuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        ImageButton btn = (ImageButton) findViewById(R.id.configuracion);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),CambioContrasena.class);
                startActivityForResult(intent,0);
            }
        });
        ImageButton btn1 = (ImageButton) findViewById(R.id.iraHome);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Home.class);
                startActivity(intent);

            }
        });
    }
}