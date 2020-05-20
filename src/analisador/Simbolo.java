package analisador;

public class Simbolo {
	protected String lexema, tipo, valor;
	protected int linha;
	
	public Simbolo(String tipo, String lexema, String valor, int linha){
		this.lexema = lexema;
		this.tipo = tipo;
		this.valor = valor;
		this.linha = linha;
	}
	public String getLexema() {
		return lexema;
	}
	public void setLexema(String lexema) {
		this.lexema = lexema;
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
	public int getLinha() {
		return linha;
	}
	public void setLinha(int linha) {
		this.linha = linha;
	}
}
