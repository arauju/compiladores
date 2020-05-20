package analisador;

public class Token {
	
	private String nome, lexema, tipo, valor;
	private int linha;
	
	public Token(){
		this.nome = "";
		this.lexema = "";
		this.linha = -1;
	}
	public Token(String nome, String lexema, int linha){
		this.nome = nome;
		this.lexema = lexema;
		this.linha = linha;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLexema() {
		return lexema;
	}
	public void setLexema(String lexema) {
		this.lexema = lexema;
	}
	public int getLinha() {
		return linha;
	}
	public void setLinha(int linha) {
		this.linha = linha;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
}
