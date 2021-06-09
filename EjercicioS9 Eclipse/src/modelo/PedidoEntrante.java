package modelo;

public class PedidoEntrante {
	private String tipoDeMensaje = "pedido";
    private String pedido, hora;

    public PedidoEntrante(String pedido, String hora) {
        this.pedido = pedido;
        this.hora = hora;
    }

 

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }


    public String getPedido() {
		return pedido;
	}



	public String getHora() {
		return hora;
	}



	public void setHora(String hora) {
        this.hora = hora;
    }
}
