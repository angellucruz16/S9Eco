package com.example.ejercicios9;

public class PedidoComidaWonka {
    private String tipoDeMensaje = "pedido";
    private String pedido, hora;

    public PedidoComidaWonka(String pedido, String hora) {
        this.pedido = pedido;
        this.hora = hora;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
