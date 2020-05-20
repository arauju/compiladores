package analisador;

public class Attr extends Astnode{
	private Id id;
	private Expr e;
	
	public Attr() {
		this.nome = "Attr";
		this.id = null;
		this.e = null;
	}
	public Attr(Id id, Expr e) {
		this.nome = "Attr";
		this.id = id;
		this.e = e;
	}
	public Id getId() {
		return id;
	}
	public void setId(Id id) {
		this.id = id;
	}
	public Expr getE() {
		return e;
	}
	public void setE(Expr e) {
		this.e = e;
	}
	public String geraArvore(int level){
		String arvore = "";
		for (int i = 0; i < level * 4; i++) 
			arvore += " ";
		arvore+= "<Attr>\n";
		arvore+= id.geraArvore(level + 1);
		arvore+= e.geraArvore(level + 1);
		for (int i = 0; i < level * 4; i++) 
			arvore+=" ";
		arvore += "</Attr>\n";
		return arvore;
	}
	
	public String geraCodigo(int level){
		String codigo = "";
		for (int i = 0; i < level * 4; i++)
			codigo += " ";
		codigo += id.geraCodigo(0) + " = " + e.geraCodigo(0);
		return codigo;
	}
}
