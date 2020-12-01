package aplicacao;
import java.time.LocalDate;
import java.util.ArrayList;

import fachada.Fachada;
import modelo.Cliente;
import modelo.Pedido;
import modelo.Produto;

public class AplicacaoConsole1 {
	private Produto pizza,sushi,coca, guarana, suco ;
	private Cliente paulo,maria,pedro,ana, katia ;	
	private Pedido pedido1,pedido2,pedido3,pedido4, pedido5;

	public AplicacaoConsole1() {
		try {
			System.out.println("Cadastrar clientes e produtos");
			paulo = Fachada.cadastrarCliente("988881111", "paulo", "Rua dos Tronos, 1");
			maria = Fachada.cadastrarCliente("988882222", "maria","Rua da Justiça, 2");			
			pedro = Fachada.cadastrarCliente("988883333", "pedro","Rua da Pesca, 3");			
			ana = Fachada.cadastrarCliente("988884444", "ana","Rua do Silencio, 4");		
			katia = Fachada.cadastrarCliente("988885555", "katia","Rua da Paz, 5");		

			pizza = Fachada.cadastrarProduto("pizza", 30.0);
			sushi = Fachada.cadastrarProduto("sushi", 40.0);
			coca = Fachada.cadastrarProduto("coca-cola", 10.0);
			guarana = Fachada.cadastrarProduto("guarana", 9.0);	
			suco = Fachada.cadastrarProduto("suco", 4.0);	

			System.out.println("Criar pedidos");
			pedido1 = Fachada.criarPedido("988881111");
			pedido2 = Fachada.criarPedido("988882222");
			pedido3 = Fachada.criarPedido("988883333");
			pedido4 = Fachada.criarPedido("988881111");
			pedido5 = Fachada.criarPedidoExpress("988881111", 10.0);

			System.out.println("Adicionar produtos");		
			Fachada.adicionarProdutoPedido(1, 1);
			Fachada.adicionarProdutoPedido(1, 1);
			Fachada.adicionarProdutoPedido(1, 3);	

			Fachada.adicionarProdutoPedido(2, 2);	
			Fachada.adicionarProdutoPedido(2, 2);
			Fachada.adicionarProdutoPedido(2, 4);
			Fachada.removerProdutoPedido(2, 2);	

			Fachada.adicionarProdutoPedido(3, 1);	
			Fachada.adicionarProdutoPedido(3, 2);
			Fachada.adicionarProdutoPedido(3, 3);	
			Fachada.adicionarProdutoPedido(3, 4);
			//Fachada.removerProdutoPedido(3, 4);	

			//pedido 4 nao possui produtos

			Fachada.adicionarProdutoPedido(5, 1);	
			Fachada.adicionarProdutoPedido(5, 2);
			Fachada.adicionarProdutoPedido(5, 3);	
			Fachada.adicionarProdutoPedido(5, 4);
			Fachada.adicionarProdutoPedido(5, 5);
			
			listarProdutos("");
			listarProdutos("i"); //contem a letra i  (contains)
			listarClientes();
			listarPedidos();
			listarPedidos("988881111",1);	//pagos
			listarPedidos("988881111",2);	//nao pagos
			listarPedidos("988881111",3);	//todos

			System.out.println("\nPagar pedidos");				
			Fachada.pagarPedido(1, "joao");		//nome do entregador
			Fachada.pagarPedido(2, "jose");
			Fachada.pagarPedido(5, "jose");
			System.out.println("Cancelar pedido:");
			Fachada.cancelarPedido(3);

			System.out.println("\nconsultar pedido 1="+ Fachada.consultarPedido(1)); //idpedido
			System.out.println("consultar pedido 2="+ Fachada.consultarPedido(2));
			System.out.println("consultar pedido 5="+ Fachada.consultarPedido(5));

			int dia = LocalDate.now().getDayOfMonth();
			double arrec = Fachada.consultarArrecadacao(dia);  //somente pedidos pagos
			System.out.println("Arrecadacao do dia "+ dia);
			System.out.println(arrec);

//				double calculo = 	3*pizza.getPreco()+
//									2*sushi.getPreco()+
//									2*coca.getPreco()+
//									2*guarana.getPreco()+
//									1*suco.getPreco() + 10.0;
//			
//				if(arrec != calculo)
//				System.out.println("arrecadacao diferente de "+calculo);

			System.out.println("Produtos TOP");
			ArrayList<Produto> tops = Fachada.consultarProdutoTop();
			for(Produto p : tops)
				System.out.println("produto Top: "+ p);

			//---------------------------------------------
			listarProdutos("");
			listarProdutos("i"); //contem a letra i
			listarClientes();
			listarPedidos();
			listarPedidos("988881111",1);	//pagos
			listarPedidos("988881111",2);	//nao pagos
			listarPedidos("988881111",3);	//todos

			//**************
			testarExcecoes();
			//**************

		} catch (Exception e) {
			System.out.println("--->"+e.getMessage());
		}		
	}

	public  void listarClientes() {
		System.out.println("\nListagem de clientes:");
		ArrayList<Cliente> clientes = Fachada.listarClientes();
		for(Cliente c : clientes)
			System.out.println(c);
	}

	public  void listarProdutos(String texto) {
		System.out.println("\nListagem de produtos:"+texto);
		ArrayList<Produto> produtos = Fachada.listarProdutos(texto);
		for(Produto p : produtos)
			System.out.println(p);
	}

	public  void listarPedidos() {
		System.out.println("\nListagem de pedidos:");
		ArrayList<Pedido> pedidos = Fachada.listarPedidos();
		for(Pedido p : pedidos)
			System.out.println(p);
	}

	public  void listarPedidos(String telefone, int tipo) {
		System.out.println("\nListagem de pedidos de um cliente: - tipo:" + tipo);
		ArrayList<Pedido> pedidos = Fachada.listarPedidos(telefone, tipo);
		for(Pedido p : pedidos)
			System.out.println(p);
	}

	public static void testarExcecoes() {
		System.out.println("\n-------EXCEÇÕES LANÇADAS--------");
		try {
			Fachada.cadastrarProduto("pizza", 30.0);
			System.out.println("*************1Nao lançou exceção para: cadastro de produto existente "); 
		}catch (Exception e) {System.out.println("1ok--->"+e.getMessage());}

		try {
			Fachada.adicionarProdutoPedido(99, 1);	//pedido 99
			System.out.println("*************2Nao lançou exceção para: pedido inexistente"); 
		}catch (Exception e) {System.out.println("2ok--->"+e.getMessage());}

		try {
			Fachada.adicionarProdutoPedido(4, 99);	//produto 99
			System.out.println("*************3Nao lançou exceção para: pedido de produto inexistente"); 
		}catch (Exception e) {System.out.println("3ok--->"+e.getMessage());}

		try {
			Fachada.pagarPedido(2,"entregador");	//pedido 2 ja foi pago
			System.out.println("*************4Nao lançou exceção para: pagar pedido ja pago"); 
		}catch (Exception e) {System.out.println("4ok--->"+e.getMessage());}
	}


	public static void main (String[] args) {
		AplicacaoConsole1 aplicacaoConsole1 = new AplicacaoConsole1();
	}
}