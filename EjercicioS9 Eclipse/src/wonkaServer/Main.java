package wonkaServer;

import java.util.ArrayList;

import com.google.gson.Gson;


import modelo.Generico;
import modelo.PedidoEntrante;
import modelo.PintarPedido;
import modelo.Respuesta;
import processing.core.PApplet;

public class Main extends PApplet implements OnMessageListener{

	private ConexionUDP conexionUDP;
	private int numeroPedido;
	private ArrayList<PintarPedido> ordenesDelDia;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("wonkaServer.Main");
	}

	public void recibirMensaje(String pedidoListoMensaje, String ip, String puerto) {
		// TODO Auto-generated method stub
		Gson gson = new Gson ();
		Generico generico = gson.fromJson(pedidoListoMensaje, Generico.class);

		switch (generico.tipoDeMensaje) {
		case "pedido":
			PedidoEntrante pedidoEntrante = gson.fromJson(pedidoListoMensaje, PedidoEntrante.class);
			numeroPedido += 1;
			PintarPedido pintelo = new PintarPedido(pedidoEntrante.getHora(), pedidoEntrante.getPedido(), ip, puerto, 10,10, numeroPedido, this);
			ordenesDelDia.add(pintelo);
			System.out.println(":::" + pedidoEntrante.getPedido());
			break;

		}
	}

	public void settings () {
		size(500,500);
	}

	public void setup () {

		conexionUDP = ConexionUDP.getInstance();	
		conexionUDP.setObserver(this);
		ordenesDelDia = new ArrayList<PintarPedido>(10);
	}

	public void draw () {
		background(255,255,255);

		for (int i = 0; i < ordenesDelDia.size(); i++) {

			if (ordenesDelDia.get(i).getNumeroPedido()>1) {
				ordenesDelDia.get(i).setPosY(i*10 * 7);

			}
			ordenesDelDia.get(i).pintarFotos();
		}
	}

	public void mousePressed () {

		for (int i = 0; i < ordenesDelDia.size(); i++) {
			if (mouseX > ordenesDelDia.get(i).getPosX() && mouseX < ordenesDelDia.get(i).getPosX() + ordenesDelDia.get(i).getPosX()+60
					&& mouseY > ordenesDelDia.get(i).getPosY() && mouseY < ordenesDelDia.get(i).getPosY() + 60) {
				Respuesta respuesta = new Respuesta("Tu pedido está listo :3");
				Gson gson = new Gson();
				String mensajeEnviado = gson.toJson(respuesta);
				conexionUDP.enviarMensajes(mensajeEnviado, ordenesDelDia.get(i).getIp(), ordenesDelDia.get(i).getPuerto());
				for (int j = 0; j < ordenesDelDia.size(); j++) {

					ordenesDelDia.get(j).setPosY(ordenesDelDia.get(j).getPosY()-60);
					numeroPedido = ordenesDelDia.get(ordenesDelDia.size()-1).getNumeroPedido();

				}
				ordenesDelDia.remove(i);
			}
		}

	}
}
