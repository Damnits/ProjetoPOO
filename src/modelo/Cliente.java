package modelo;

import java.util.ArrayList;

public class Cliente {

	public String telefone;
	public String nome;
	public String endereco;
	public ArrayList<Pedido> pedidos;
	public Cliente(String telefone, String nome, String endereco, ArrayList<Pedido> pedidos) {
		this.telefone = telefone;
		this.nome = nome;
		this.endereco = endereco;
		this.pedidos = pedidos;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	@Override
	public String toString() {
		return "Cliente [telefone=" + telefone + ", nome=" + nome + ", endereco=" + endereco + ", pedidos=" 
				+ "]";
	}
}
