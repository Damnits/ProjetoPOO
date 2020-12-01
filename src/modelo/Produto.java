package modelo;

import java.util.ArrayList;

public class Produto {
		public int id;
		public String nome;
		public double preco;
		public ArrayList<Pedido> pedidos;
		public Produto(int id,String nome, double preco, ArrayList<Pedido> pedidos){
			this.id = id;
			this.nome = nome;
			this.preco = preco;
			this.pedidos = pedidos;
		}
		public int getId() {
		return id;
		}
		public void setId(int id) {
		this.id = id;
		}
		
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public double getPreco() {
			return preco;
		}
		public void setPreco(double preco) {
			this.preco = preco;
		}
		public ArrayList<Pedido> getPedido() {
			return pedidos;
		}
		public void setPedido(ArrayList<Pedido> pedidos) {
			this.pedidos = pedidos;
		}
		@Override
		public String toString() {
			return "Produto [nome=" + nome + ", preco=" + preco + ", pedidos=" + "]";
		}
		
}
