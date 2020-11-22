package atividade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TestaProduto {
	public static void main(String[] args) {
		ArrayList<Produto> produtos = new ArrayList<>();
		ArrayList<Produto> compras = new ArrayList<>();
		Scanner teclado;
		String palavra = new String();
		int cont;
		for(int i=0; i<30;i++ ) {
			if(i%5 ==0)
			produtos.add(new Produto("A", i*2, 0*i));
			else
			produtos.add(new Produto("B", i*2, i));			
		}
		for(Produto n: produtos) {
			if(n.getEstoque()== 0) {
				compras.add(n);
			}
			n.setPreco(n.getPreco()*2);
			System.out.println(n.toString());
		}
		System.out.println('\n');
		for(Produto n: compras) {
			System.out.println(n.toString());
		}
		HashMap<String, Integer> hashs = new HashMap<>();	

		while(true) {
			System.out.println("Escreva a palavra:");
			teclado = new Scanner(System.in);
			palavra = teclado.next().toUpperCase();
			if(palavra.contentEquals("FIM") == true) {
				teclado.close();
				break;
				}
			cont=+1;
			Integer contador = Integer.valueOf(cont);
			hashs.put(palavra, contador);
		}
		for(String show: hashs.keySet()) {
			System.out.print(show + " ");
		}
		System.out.println(hashs.size());
	}
	
	
}
