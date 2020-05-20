package analisador;

import java.util.Hashtable;

public class Id extends Expr{
	private Simbolo simbolo;
	
	public Id() {
		this.nome = "Id";
		this.simbolo = null;
	}
	public Id(Simbolo simbolo) {
		this.nome = "Id";
		this.simbolo = simbolo;
		this.lexema = simbolo.getLexema();
	}
	public Simbolo getSimbolo() {
		return simbolo;
	}
	public void setSimbolo(Simbolo simbolo) {
		this.simbolo = simbolo;
		this.lexema = simbolo.getLexema();
	}
	public String toString() {
		return nome;
	}
	public String geraArvore(int level){
		String arvore = "";
		String tipoprint = "";
		for (int i = 0; i < level * 4; i++)
			arvore += " ";
		if(simbolo.getTipo() != null && simbolo.getTipo().equals("INT"))
			tipoprint = "integer";
		else if ((simbolo.getTipo() != null && simbolo.getTipo().equals("FLOAT")))
			tipoprint = "float";
		else
			tipoprint = simbolo.getTipo();
		arvore += "<Id lexema='"+simbolo.getLexema()+"' type='"+tipoprint+"' />\n";
		return arvore;
	}
	public String geraCodigo(int level){
		String codigo = "";
		for (int i = 0; i < level * 4; i++) 
			codigo += " ";
		codigo += simbolo.getLexema();
		return codigo;
	}
}

