package analisador;

public class If extends Astnode{
	private Expr e;
	private Astnode c_true;
	private Astnode c_false;
	
	public If(){
		this.nome = "If";
		this.e = null;
		this.c_true = null;
		this.c_false = null;
	}
	public Expr getE() {
		return e;
	}
	public void setE(Expr e) {
		this.e = e;
	}
	public Astnode getC_true() {
		return c_true;
	}
	public void setC_true(Astnode c_true) {
		this.c_true = c_true;
	}
	public Astnode getC_false() {
		return c_false;
	}
	public void setC_false(Astnode c_false) {
		this.c_false = c_false;
	}
	public void addFilho(Astnode filho) {
		if (c_true == null)
			c_true = filho;
		else
			c_false = filho;
	}
	
	public String toString () {
		String resultado = "";
		resultado += e + " - " + c_true + " - " + c_false;
		return resultado;
	}
	public String geraArvore(int level){
		String arvore = "";
		for (int i = 0; i < level * 4; i++) 
			arvore += " ";
		arvore += "<If>\n";
		arvore += e.geraArvore(level + 1);
		arvore += c_true.geraArvore(level + 1);
		if (c_false != null)
			arvore += c_false.geraArvore(level + 1);
		for (int i = 0; i < level * 4; i++) 
			arvore += " ";
		arvore += "</If>\n";
		return arvore;
	}

	public String geraCodigo(int level){
		String codigo = "";
		for (int i = 0; i < level * 4; i++)
			codigo += " ";

		codigo += "if " + e.geraCodigo(0) +":\n";
		codigo += c_true.geraCodigo(level + 1);
		
		if (c_false != null){
			for (int i = 0; i < level * 4; i++)
				codigo += " ";
			codigo += "else:\n";
			codigo += c_false.geraCodigo(level + 1) + "\n";
		}
		return codigo;
	}
}
