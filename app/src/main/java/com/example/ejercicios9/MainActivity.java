package com.example.ejercicios9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements OnMessageListener, View.OnClickListener {

    private Button juguitoBoton, yogurtBoton, hotdogBoton, sandwichitoBoton;
    private ObserverUdp observerUdp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        juguitoBoton = findViewById(R.id.juguitoBoton);
        yogurtBoton = findViewById(R.id.yogurtBoton);
        hotdogBoton = findViewById(R.id.hotdogBoton);
        sandwichitoBoton = findViewById(R.id.sandwichitoBoton);

        observerUdp = ObserverUdp.getInstance();
        observerUdp.setObserver(this);
        //OnClicks
        juguitoBoton.setOnClickListener(this);
        yogurtBoton.setOnClickListener(this);
        hotdogBoton.setOnClickListener(this);
        sandwichitoBoton.setOnClickListener(this);
    }

    @Override
    public void recibirMensaje(String mensaje) {
        Gson gson = new Gson();
        Respuesta respuesta = gson.fromJson(mensaje,Respuesta.class);
    runOnUiThread(
            ()-> {
                Toast.makeText(this, respuesta.getRespuesta(), Toast.LENGTH_SHORT).show();
            }
    );
    }

    public void convertirEnJson(String pedidoComida){

        //Esto nos da la fecha
        Calendar c = Calendar.getInstance();
        Date fecha = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String horaDelPedido = sdf.format(fecha);

        PedidoComidaWonka pedidoWonka = new PedidoComidaWonka(pedidoComida,horaDelPedido);
        Gson gson = new Gson();
        String json =  gson.toJson(pedidoWonka);
        observerUdp.enviarMensajes(json);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.juguitoBoton:
                convertirEnJson("unJuguito");
                break;
            case R.id.yogurtBoton:
                convertirEnJson("unYogurt");
                break;
            case R.id.sandwichitoBoton:
                convertirEnJson("unSandwichito");
                break;
            case R.id.hotdogBoton:
                convertirEnJson("unHotdog");
                break;
        }
    }
}