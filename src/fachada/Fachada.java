package fachada;

import java.time.LocalDateTime;
import java.util.ArrayList;

import modelo.Cliente;
import modelo.Pedido;
import modelo.PedidoExpress;
import modelo.Produto;
import repositorio.Repositorio;

public class Fachada {
	
	private static Repositorio repositorio = new Repositorio();
	private static int idproduto=0;
	private static int idpedido=0;
	public static ArrayList<Cliente> listarClientes(){
		ArrayList<Cliente> listaClientes = new ArrayList<>(); 
		listaClientes = repositorio.getClientes();
		return listaClientes;
	}
	public static ArrayList<Produto> listarProdutos(String texto){
		ArrayList<Produto> produtosenc = new ArrayList<>(); 
		ArrayList<Produto> listaProdutos = repositorio.getProdutos();
		if(texto == "") {
			return listaProdutos;
		}
		for(Produto p : listaProdutos) {
			if(p.getNome().contains(texto)) {
				produtosenc.add(p);
			}
		}
		return produtosenc;
	}
	public static ArrayList<Pedido> listarPedidos(){
		ArrayList<Pedido> listaPedidos = repositorio.getPedidos();
		return listaPedidos;
	}
	public static ArrayList<Pedido> listarPedidos(String telefone, int tipo){
		Cliente cliente = repositorio.localizaClientepTelef(telefone);
		ArrayList<Pedido> listapedido = new ArrayList<>();
		ArrayList<Pedido> listapedidos = cliente.getPedidos();
		if(tipo==1) {
			for(Pedido p : listapedidos) {
				if(p.isPago()==true) {
					listapedido.add(p);
				}
			}
			return listapedido;
		}
		else if(tipo==2){
			for(Pedido p : listapedidos) {
				if(p.isPago()==false) {
					listapedido.add(p);
				}
			}
			return listapedido;
		}
		else {
			return listapedido;	
		}
		
	}
	public static Produto cadastrarProduto(String nome, double preco)throws Exception {
		if(repositorio.localizarProduto(nome)!=null)
			throw new Exception("Produto já cadastrado");
		idproduto++;
		ArrayList<Pedido> pedidos = new ArrayList<>();
		Produto novoproduto = new Produto(idproduto, nome, preco, pedidos);
		repositorio.adicionar(novoproduto);
		return novoproduto;
	}
	public static Cliente cadastrarCliente(String telefone, String nome, String endereco)throws Exception {
		
		if(telefone.matches("^[0-9]"))
			throw new Exception("Telefone inválido digite só números.");		
		ArrayList<Pedido> pedidos = new ArrayList<>();
		Cliente novocliente = new Cliente(telefone, nome, endereco, pedidos);
		repositorio.adicionar(novocliente);
		return novocliente;
	}
	
	public static Pedido criarPedido(String telefone)throws Exception {
		idpedido++;
		LocalDateTime data = LocalDateTime.now();  
		String entregador = "";
		boolean pago = false;
		ArrayList<Produto> produtos = new ArrayList<>();
		Cliente cliente = repositorio.localizaClientepTelef(telefone);
		if(cliente==null) 
			throw new Exception("Telefone de cliente não encontrado.");
		double valortotal = 0;
		Pedido novopedido = new Pedido(idpedido, data, valortotal, entregador, pago, produtos, cliente);
		repositorio.adicionar(novopedido);
		return novopedido;
	}
	public static PedidoExpress criarPedidoExpress(String telefone, double taxa)throws Exception {
		idpedido++;
		LocalDateTime data = LocalDateTime.now();
		String entregador = "";
		boolean pago = false;
		ArrayList<Produto> produtos = new ArrayList<>();
		Cliente cliente = repositorio.localizaClientepTelef(telefone);
		if(cliente==null)
			throw new Exception("Telefone de cliente não encontrado.");
		double valortotal =+ taxa;
		PedidoExpress novopedido = new PedidoExpress(idpedido, data, valortotal, entregador, pago, produtos, cliente, taxa);
		repositorio.adicionar(novopedido);
		return novopedido;
	}
	public static void adicionarProdutoPedido(int idproduto, int idpedido)throws Exception {
		Pedido pedido = repositorio.localizarPedido(idpedido);
		if(pedido==null)
			throw new Exception("Pedido de id "+idpedido+" não encontrado!");
		Produto produto = repositorio.localizarProduto(idproduto);
		if(produto==null)
			throw new Exception("Produto de id "+idproduto+" não encontrado!");
		pedido.setValortotal(produto.getPreco()+pedido.getValortotal());
		pedido.getProdutos().add(produto);
	}
	public static void removerProdutoPedido(int idpedido, int idproduto)throws Exception{
		Pedido pedido = repositorio.localizarPedido(idpedido);
		if(pedido==null)
			throw new Exception("Pedido de id "+idpedido+" não encontrado!");			

		ArrayList<Produto> produto = pedido.getProdutos();
		for(Produto p : produto) {
			if(p.getId()==idproduto) {
				pedido.setValortotal(pedido.getValortotal()-p.getPreco());				
				produto.remove(p);
			}
			else {
				throw new Exception("Produto de id "+idproduto+" não encontrado!");
			}
		}
		;
	}
	public static Pedido consultarPedido(int idpedido) {
		return repositorio.localizarPedido(idpedido);
	}
	public static void pagarPedido(int idpedido, String nomeentregador)throws Exception {
		Pedido pedido = repositorio.localizarPedido(idpedido);
		if(pedido.isPago())
			throw new Exception("Pedido já pago.");
		pedido.setEntregador(nomeentregador);
		pedido.setPago(true);
	}
	public static void cancelarPedido(int idpedido) {
		Pedido pedido = repositorio.localizarPedido(idpedido);
		//remover produtos
		ArrayList<Produto> produtospedi = pedido.getProdutos();
		for(Produto prpe : produtospedi) {
			prpe.getPedido().remove(pedido);
		}
		//remover clientes
		Cliente clientepedi = pedido.getCliente();
		clientepedi.getPedidos().remove(pedido);
		repositorio.remover(repositorio.localizarPedido(idpedido));
	}
	public static double consultarArrecadacao(int dia) {
		ArrayList<Pedido> pedidos = repositorio.getPedidos();
		double valortotal=0;
		for(Pedido pe : pedidos) 
			if(pe.getDatahora().getDayOfMonth()==dia)
				valortotal = valortotal + pe.getValortotal();
		
		
		return valortotal;
	}
	public static ArrayList<Produto> consultarProdutoTop(){
		ArrayList<Produto> produtos = repositorio.getProdutos();
		ArrayList<Produto> maiorproduto = new ArrayList<>();
		int contador=0;
		for(Produto p : produtos) {
			if(p.getPedido().size()>contador) 
				contador = p.getPedido().size();	
		}
		for(Produto p : produtos) {
			if(p.getPedido().size()==contador)
				maiorproduto.add(p);
		}
		return maiorproduto;
	}
}
