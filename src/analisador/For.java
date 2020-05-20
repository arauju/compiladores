package analisador;

public class For extends Astnode {
	private Astnode c_true;
	private Attr ini;
	private Expr cond;
	private Attr inc;

	public For(){
		this.nome = "For";
		this.c_true = null;
		this.ini = null;
		this.cond = null;
		this.inc = null;
	}
	public Astnode getC_true() {
		return c_true;
	}
	public void setC_true(Astnode c_true) {
		this.c_true = c_true;
	}
	public void addFilho(Astnode filho){
		this.c_true = filho;
	}
	public Attr getIni() {
		return ini;
	}
	public void setIni(Attr ini) {
		this.ini = ini;
	}
	public Expr getCond() {
		return cond;
	}
	public void setCond(Expr cond) {
		this.cond = cond;
	}
	public Attr getInc() {
		return inc;
	}
	public void setInc(Attr inc) {
		this.inc = inc;
	}
	public String geraArvore(int level){
		String arvore = "";
		for (int i = 0; i < level*4; i++) 
			arvore +=" ";
		arvore += "<For>\n";
		arvore += ini.geraArvore(level + 1);
		arvore += cond.geraArvore(level + 1);
		arvore += inc.geraArvore(level + 1);
		arvore +=c_true.geraArvore(level + 1);
		for (int i = 0; i < level * 4; i++) 
			arvore+=" ";
		arvore+= "</For>\n";
		return arvore;
	}

	public String toString(){
		return "" + ini +" "+ cond +" "+ inc +" "+ c_true;
	}
	public String geraCodigo(int level){
		String codigo = "";
		for (int i = 0; i < level * 4; i++)
			codigo += " ";
		codigo += ini.geraCodigo(level) + "\n";
		codigo += "while " + cond.geraCodigo(0) +":\n";
		codigo += c_true.geraCodigo(level + 1); 
		codigo += inc.geraCodigo(level + 1);
		return codigo;
	}
}
