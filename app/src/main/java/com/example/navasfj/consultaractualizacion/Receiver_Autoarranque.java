package com.example.navasfj.consultaractualizacion;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class Receiver_Autoarranque extends BroadcastReceiver {

    public Receiver_Autoarranque() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Servicio iniciado desde BroadcastReceiver", Toast.LENGTH_LONG).show();

        context.startService(new Intent(context, Servicio_Consultar.class));
    }
}
