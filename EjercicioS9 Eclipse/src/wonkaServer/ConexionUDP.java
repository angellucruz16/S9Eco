package wonkaServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ConexionUDP extends Thread{
    //Es un hilo porque así nos aseguramos de que todo el programa no se pare en caso de no recibir una respuesta.
    private DatagramSocket socket; //Siempre se pone para UDP
    private OnMessageListener observer;
    //No podemos modificarla desde otras clases pero si podemos enviar mensajes y recibir

    private static ConexionUDP singleton;

    public static ConexionUDP getInstance(){
    if (singleton== null){
        singleton= new ConexionUDP();
        singleton.start();
    }
    return singleton;
    }
    private ConexionUDP (){

    }
    public void setObserver (OnMessageListener observer){
        this.observer = observer;
    } //Este metodo lo ponemos en las clases que queremos que se conviertan en un observador. Basicamente llamamos a todas las clases que son de tipo OnMessageLister

public void run (){ //el metodo run pertenece a extend Thread
        //Recibimos los mensajes por el puerto 5000
    try {
        socket = new DatagramSocket(7000);
        while (true){ //que siempre lo haga
            byte[] buffer = new byte[100];
            //empaquetamos el datagrama
            DatagramPacket packet = new DatagramPacket( buffer, buffer.length);
            socket.receive(packet);
            String mensajeEntrante = new String(packet.getData()).trim(); //trim parte el packet.get data
            
            // Reenvio de mensaje version Cristian:
            // Obtener el ip automaticamente
            //dividimos el string por : y obtenemos e[0] la ip y [1] el puerto
            SocketAddress iport= packet.getSocketAddress();
            String i = iport.toString();
            String red = i.replace("/", "");
            String[] portip = red.split(":");
            String ip = portip[0];
            String port = portip[1];
            System.out.println(mensajeEntrante);
            observer.recibirMensaje(mensajeEntrante, ip, port);
           
        }
    } catch (SocketException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }

}

public void enviarMensajes (String mensajeQueSeEnvia, String ip, String port){
        new Thread(
                ()-> {
                    try {
                        InetAddress ipEclipse = InetAddress.getByName(ip);
                        int puerto = Integer.parseInt(port);
                        DatagramPacket packet = new DatagramPacket(mensajeQueSeEnvia.getBytes(), mensajeQueSeEnvia.getBytes().length, ipEclipse,puerto);
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