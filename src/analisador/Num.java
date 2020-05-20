package analisador;

public class Num extends Expr{
    private float valor;
    private int tipo;
    
    public Num(){
        this.nome = "Num";
        this.tipo = 0;
        this.valor = 0;
    }
    
    public Num(Float valor, int tipo) {
    	this.nome = "num";
    	this.valor = valor;
    	this.tipo = tipo;
    }
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public String geraArvore(int level){
		String arvore = "";
		String tipoprint = "";
		for (int i = 0; i < level * 4; i++) 
			arvore +=" ";
		if (tipo == 0) {
			tipoprint = "integer";
			arvore += "<Num value="+(int)valor+" type='"+tipoprint+"' />\n";
		} else {
			tipoprint = "float";
			arvore += "<Num value="+valor+" type='"+tipoprint+"' />\n";
		}
		return arvore;
	}

	public String geraCodigo(int level){
		String codigo = "";
		for (int i = 0; i < level * 4; i++) 
			codigo += " ";
		if(tipo == 0)
			codigo += (int)valor;
		else
			codigo += valor;
		return codigo;
	}
}
