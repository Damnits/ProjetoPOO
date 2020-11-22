package atividade;

public class Produto {
		public String nome;
		public float preco;
		public int estoque;
		public Produto(String nome, float preco, int estoque){
			this.nome = nome;
			this.preco = preco;
			this.estoque = estoque;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public float getPreco() {
			return preco;
		}
		public void setPreco(float preco) {
			this.preco = preco;
		}
		public int getEstoque() {
			return estoque;
		}
		public void setEstoque(int estoque) {
			this.estoque = estoque;
		}
		@Override
		public String toString() {
			return "Produto [nome=" + nome + ", preco=" + preco + ", estoque=" + estoque + "]";
		}
		
}
