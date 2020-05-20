package analisador;

import java.util.ArrayList;

public class Astnode {
	private ArrayList<Astnode> filhos = new ArrayList<Astnode>();
	protected String nome;
	
	public Astnode() {
		this.nome = "Ast";
	}
	public Astnode(String nome) {
		this.nome = nome;
	}
	public ArrayList<Astnode> getFilhos() {
		return filhos;
	}
	public void setFilhos(ArrayList<Astnode> filhos) {
		this.filhos = filhos;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void addFilho(Astnode filho) {
		filhos.add(filho);
	}
	public String toString() {
		String retorno = nome + "->" ;
		for (Astnode a : filhos) {
			retorno += a.toString() + " - ";
		}
		retorno += "\n";
		return retorno;
	}
	public String geraArvore(int level){
		String arvore = "";
		for (int i = 0; i < level * 4; i++)
			arvore += " ";
		arvore += "<"+nome+">\n";
		for (Astnode a: filhos){
			arvore += a.geraArvore(level + 1);
		}
		for (int i = 0; i < level * 4; i++)
			arvore+=" ";
		arvore+= "</"+nome+">\n";
		return arvore;
	}

	public String geraCodigo(int level){
		String codigo = "";
		for (Astnode a: filhos){
			codigo+= a.geraCodigo(level);
			codigo += "\n";
		}
		return codigo;
	}
}

