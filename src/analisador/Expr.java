package analisador;

public abstract class Expr extends Astnode{
	protected String lexema;

	public String getLexema() {
		return lexema;
	}
	public void setLexema(String lexema) {
		this.lexema = lexema;
	}
}
