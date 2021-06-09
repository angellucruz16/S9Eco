package com.example.ejercicios9;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ObserverUdp extends Thread{
    //Es un hilo porque así nos aseguramos de que todo el programa no se pare en caso de no recibir una respuesta.
    private DatagramSocket socket; //Siempre se pone para UDP
    private OnMessageListener observer;
    //No podemos modificarla desde otras clases pero si podemos enviar mensajes y recibir

    private static ObserverUdp singleton;

    public static ObserverUdp getInstance(){
    if (singleton== null){
        singleton= new ObserverUdp();
        singleton.start();
    }
    return singleton;
    }
    private ObserverUdp (){

    }
    public void setObserver (OnMessageListener observer){
        this.observer = observer;
    } //Este metodo lo ponemos en las clases que queremos que se conviertan en un observador. Basicamente llamamos a todas las clases que son de tipo OnMessageLister

public void run (){ //el metodo run pertenece a extend Thread
        //Recibimos los mensajes por el puerto 5000
    try {
        socket = new DatagramSocket(5000);
        while (true){ //que siempre lo haga
            byte[] buffer = new byte[100];
            //empaquetamos el datagrama
            DatagramPacket packet = new DatagramPacket( buffer, buffer.length);
            socket.receive(packet);
            String mensajeEntrante = new String(packet.getData()).trim(); //trim parte el packet.get data
            observer.recibirMensaje(mensajeEntrante);

        }
    } catch (SocketException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }

}

public void enviarMensajes (String mensajeQueSeEnvia){
        new Thread(
                ()-> {
                    Log.e(":::", "hola");
                    try {
                        InetAddress ip = InetAddress.getByName("192.168.1.13");
                        DatagramPacket packet = new DatagramPacket(mensajeQueSeEnvia.getBytes(), mensajeQueSeEnvia.getBytes().length, ip,7000);
                        socket.send(packet);
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
        ).start();
}

}
