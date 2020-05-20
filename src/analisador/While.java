package analisador;

public class While extends Astnode{
	private Expr exp;
	private Astnode c_true;

	public While(){
		this.nome = "While";
		this.exp = null;
		this.c_true = null;
	}

	public Expr getE() {
		return exp;
	}
	public void setE(Expr e) {
		this.exp = e;
	}
	public Astnode getC_true() {
		return c_true;
	}
	public void setC_true(Astnode c_true) {
		this.c_true = c_true;
	}

	public void addFilho(Astnode filho){
		if (this.c_true == null)
			this.c_true = filho;
	}
	
	public String toString() {
		String resultado = "";
		resultado += exp + " - " + c_true;
		return resultado;
	}
	public String geraArvore(int nivel){
		String arvore = "";
		for(int i = 0; i < nivel * 4; i++) 
			arvore += " ";
		arvore += "<While>\n";
		arvore += exp.geraArvore(nivel + 1);
		arvore += c_true.geraArvore(nivel + 1);
		for(int i = 0; i < nivel*4; i++) 
			arvore += " ";
		arvore += "</While>\n";
		return arvore;
	}

	public String geraCodigo(int nivel){
		String codigo = "";
		for (int i = 0; i < nivel * 4; i++)
			codigo += " ";
		codigo += "while " + exp.geraCodigo(0) +":\n";
		codigo += c_true.geraCodigo(nivel + 1); 
		return codigo;
	}
}
