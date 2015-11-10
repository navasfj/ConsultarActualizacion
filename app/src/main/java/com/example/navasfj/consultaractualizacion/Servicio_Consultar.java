package com.example.navasfj.consultaractualizacion;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Servicio_Consultar extends Service {


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "Se ha creado un nuevo servicio", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onStart(Intent intent, int startId) {

        isInternetOn();
        notificacion();

    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Servicio destruido", Toast.LENGTH_LONG).show();

    }

    public final boolean isInternetOn(){
        ConnectivityManager connec = (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        if (connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED||
                connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTING||
                connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTING||
                connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED){

                // JUSTIFICAR EL TOAST PARA TEXTOS LARGOS
                Toast toast = Toast.makeText(getApplicationContext(), "Tenemos Conexión a Internet.", Toast.LENGTH_SHORT);
                LinearLayout layout = (LinearLayout) toast.getView();
                if (layout.getChildCount() > 0) {
                    TextView tv = (TextView) layout.getChildAt(0);
                    tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                }
                toast.show();
            return true;
        } else if ( connec.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED||
                connec.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTING){

                // JUSTIFICAR EL TOAST PARA TEXTOS LARGOS
                Toast toast = Toast.makeText(getApplicationContext(), "No hay Conexión a Internet.", Toast.LENGTH_SHORT);
                LinearLayout layout = (LinearLayout) toast.getView();
                if (layout.getChildCount() > 0) {
                    TextView tv = (TextView) layout.getChildAt(0);
                    tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                }
                toast.show();
            return false;
        }

        return false;

    }

    private void notificacion(){
        // Preparamos el intent que será lanzado si la notificación es seleccionada
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intento = new Intent(Servicio_Consultar.this, MainActivity.class);
        intento.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pIntent = PendingIntent.getActivity(Servicio_Consultar.this, 0, intento, 0);

        // Creamos la notificación.
        CharSequence ticker = "Aviso de Carga";
        CharSequence contentTitle = "Calendario Verallia Sevilla";
        CharSequence contentText = "Se ha cargado el servicio correctamente";
        Notification noti = new NotificationCompat.Builder(Servicio_Consultar.this)
                .setContentIntent(pIntent)
                .setTicker(ticker)
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setSound(soundUri)
                .build();
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Ocultamos la notificación si ha sido ya seleccionada
        noti.flags |= Notification.FLAG_AUTO_CANCEL;


        notificationManager.notify(0, noti);
        //Log.i(TAG, "Servicio running");
    }
}
