package com.example.navasfj.consultaractualizacion;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btUno = (Button) findViewById(R.id.button);
        btUno.setOnClickListener(this);


        arrancar();

    }

    private void arrancar (){
        startService(new Intent(MainActivity.this,
                Servicio_Consultar.class));
    }


    @Override
    public void onClick(View v) {
        stopService(new Intent(MainActivity.this,
                Servicio_Consultar.class));
        Toast.makeText(this, "Se ha detenido el servicio", Toast.LENGTH_LONG).show();

    }
}
