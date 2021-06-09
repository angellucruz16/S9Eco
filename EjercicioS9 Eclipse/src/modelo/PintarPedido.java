package modelo;

import processing.core.PApplet;
import processing.core.PImage;

public class PintarPedido {
 private String horaPedido, contenidoPedido, ip, puerto; 
 private int posX, posY, numeroPedido;
 private PApplet app;
 
 private PImage juguito, yogurt, hotdog, sandwichito;

public PintarPedido(String horaPedido, String contenidoPedido, String ip, String puerto, int posX, int posY,
		int numeroPedido, PApplet app) {
	super();
	this.horaPedido = horaPedido;
	this.contenidoPedido = contenidoPedido;
	this.ip = ip;
	this.puerto = puerto;
	this.posX = posX;
	this.posY = posY;
	this.numeroPedido = numeroPedido;
	this.app = app;
	
	juguito = app.loadImage("./imagenes/juguito.jpg");

	hotdog = app.loadImage("./imagenes/hotdog.jpg");

	sandwichito = app.loadImage("./imagenes/sandwich.jpg");

	yogurt = app.loadImage("./imagenes/yogurt.jpg");
}

public String getHoraPedido() {
	return horaPedido;
}

public void setHoraPedido(String horaPedido) {
	this.horaPedido = horaPedido;
}

public String getContenidoPedido() {
	return contenidoPedido;
}

public void setContenidoPedido(String contenidoPedido) {
	this.contenidoPedido = contenidoPedido;
}

public String getIp() {
	return ip;
}

public void setIp(String ip) {
	this.ip = ip;
}

public String getPuerto() {
	return puerto;
}

public void setPuerto(String puerto) {
	this.puerto = puerto;
}

public int getPosX() {
	return posX;
}

public void setPosX(int posX) {
	this.posX = posX;
}

public int getPosY() {
	return posY;
}

public void setPosY(int posY) {
	this.posY = posY;
}

public int getNumeroPedido() {
	return numeroPedido;
}

public void setNumeroPedido(int numeroPedido) {
	this.numeroPedido = numeroPedido;
}

public void pintarFotos() {
	// TODO Auto-generated method stub
	switch (contenidoPedido) {
	
	case "unJuguito" :
	cambiarTamañoImagen(juguito);
		break;
	case "unHotdog" :
		cambiarTamañoImagen(hotdog);
			break;
	case "unSandwichito" :
		cambiarTamañoImagen(sandwichito);
			break;
	case "unYogurt" :
		cambiarTamañoImagen(yogurt);
			break;
	}
	
}

public void cambiarTamañoImagen (PImage comida) {
	comida.resize(30, 50);
	app.image(comida, posX, posY);
	app.fill(0);
	app.textSize(13);
	app.text("Pedido: " + numeroPedido, posX+40, posY+10);
	app.text("Hora: " + horaPedido, posX+40, posY + 26);
}

 
}
