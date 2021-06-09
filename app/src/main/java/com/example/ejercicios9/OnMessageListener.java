package com.example.ejercicios9;
//Creamos una interfaz para recibir mensjaes. La función que tiene es la de no crear varios observadores cada que llamo a una clase. Es usar multiherencia
public interface OnMessageListener {
    void recibirMensaje (String mensaje);
}
