package analisador;

public class Read extends Astnode{
	private Id id;

	public Read(){
		this.id = null;
		this.nome = "Read";
	}
	public Id getId() {
		return id;
	}
	public void setId(Id id) {
		this.id = id;
	}
	public String geraArvore(int level){
		String arvore = "";
		for(int i = 0; i < level * 4; i++)
			arvore += " ";
		arvore += "<Read>\n";
		arvore += id.geraArvore(level + 1);
		for(int i = 0; i < level*4; i++)
			arvore += " ";
		arvore += "</Read>\n";
		return arvore;
	}
	public String geraCodigo(int level) {
		String codigo = "";
		for (int i = 0; i < level * 4; i++)
			codigo += " ";
		codigo += id.getLexema()+" = float(input(\"Digite o valor da variável "+id.getLexema() +":\"))";
		return codigo;
	}
}
