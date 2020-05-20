package analisador;

public class Print extends Astnode{
	Expr expr;

	public Print(){
		this.expr = null;
		this.nome = "Print";
	}

	public Expr getExpr() {
		return expr;
	}

	public void setExpr(Expr expr) {
		this.expr = expr;
	}
	public String geraArvore(int level){
		String arvore = "";
		for(int i = 0; i < level*4; i++) 
			arvore += " ";
		arvore += "<Print>\n";
		arvore += expr.geraArvore(level + 1);
		for(int i = 0; i < level*4; i++)
			arvore+= " ";
		arvore += "</Print>\n";
		return arvore;
	}
	
	public String geraCodigo(int level) {
		String codigo = "";
		for (int i = 0; i < level * 4; i++)
			codigo += " ";
		if (expr.getNome().equals("Id"))
			codigo += "print(\"Valor da variável "+expr.getLexema()+":\" + str("+ expr.getLexema()+"))";
		else
			codigo += "print(str("+expr.geraCodigo(0)+"))";
		return codigo;
	}
}
