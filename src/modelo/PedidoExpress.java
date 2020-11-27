package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class PedidoExpress extends Pedido{
	public double taxaentrega;
	public PedidoExpress(int id, LocalDateTime datahora, double valortotal, String entregador, boolean pago,
			ArrayList<Produto> produtos, Cliente cliente, double taxaentrega) {
		super(id, datahora, valortotal, entregador, pago, produtos, cliente);
		this.taxaentrega = taxaentrega;
	}
	public double getTaxaentrega() {
		return taxaentrega;
	}
	public void setTaxaentrega(double taxaentrega) {
		this.taxaentrega = taxaentrega;
	}
	
}
