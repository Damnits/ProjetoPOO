package repositorio;

import java.util.ArrayList;

import modelo.Cliente;
import modelo.Pedido;
import modelo.Produto;

public class Repositorio {
	private ArrayList<Produto> produtos = new ArrayList<>();
	private ArrayList<Pedido> pedidos = new ArrayList<>();
	private ArrayList<Cliente> clientes = new ArrayList<>();
	public void adicionar(Produto p){
		produtos.add(p);
	}
	public void remover(Produto p){
		produtos.remove(p);
	}
	public Produto localizarProduto(int id){
		for(Produto p : produtos){
			if(p.getId()==id)
				return p;
		}
		return null;
	}

	public void adicionar(Pedido p){
		pedidos.add(p);
	}
	public void remover(Pedido p){
		pedidos.remove(p);
	}
	public Pedido localizarPedido(int id){
		for(Pedido p : pedidos){
			if(p.getId()==id)
				return p;
		}
		return null;
	}
	public void adicionar(Cliente c){
		clientes.add(c);
	}
	public void remover(Cliente c){
		clientes.remove(c);
	}
	public Cliente localizaClientepTelef(String telefone){
		for(Cliente c : clientes) {
			if(c.getTelefone().contentEquals(telefone)) 
				return c;
			
		}
		return null;
	}
	public ArrayList<Produto> getProdutos() {
		return produtos;
	}
	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}
	public ArrayList<Cliente> getClientes(){
		return clientes;
	}
	public int getTotalProdutos(){
		return produtos.size();
	}
	public int getTotalPedido(){
		return pedidos.size();
	}



}
