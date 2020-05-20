package analisador;

import java.io.IOException;
import java.util.Hashtable;

public class Analisador {
	static Token[] tokens = new Token[1000];
	static int cont_tokens = 0;
	static int linha = 1;
	static Hashtable<String, Simbolo> tabelaSimbolos = new Hashtable<String, Simbolo>(); 
	static Simbolo[] simbolos = new Simbolo[100];
	static String guardaTipo = "";
	
	
	public static void main(String[] args) throws IOException {
		Analisador automato = new Analisador();
		automato.iniciar();
		//System.out.println("Analise Lexica concluída");
		IO escrever = new IO();
		escrever.abrir();
		for(int i = 0; i<cont_tokens; i++){
			escrever.escrever(tokens[i]);
		}
		Analisador sintatico = new Analisador();
		sintatico.analiseSintatica();
		//System.out.println("Analise Sintática concluída");
	}
	IO teste = new IO();
	//le a primeira entrada
	char entrada = teste.getNextChar();
	int i = 0;
	
	public void iniciar(){
		//cria uma palavra vazia que vai ser a concatenação das entradas, formando os tokens
		String palavra = new String();
		 //chama o estado inicial
		q0(palavra);
	}
	
	public void q0(String palavra){
		//enquanto não ler o final do arquivo (se ler EOF o getNextChar() retorna 0)
		while(entrada != 0){ 
			//se ler um \n adiciona uma linha
			if(entrada == 10){
				linha++;
				//sempre que for feita uma leitura e comparação, le a proxima entrada
				entrada = teste.getNextChar();
				if(entrada == 0){
					//se for lido o EOF logo após o final de uma linha quebra o loop e encerra a função
					break; 
				}
			}
			//se for lido um espaço ou um tab não é necessário fazer nada, apenas ler a próxima entrada
			while(entrada == 32 || entrada == 9){ 
				entrada = teste.getNextChar();
				if(entrada == 10){
					linha++;
				}
			}
			//compara o valor do char lido com o correspondente na tabela asc ii
			if(entrada == 40){ 
				//caso a entrada seja um ( cria um token para o left bracket
				tokens[cont_tokens] = new Token("LBRACKET", "(", linha); 
				//sempre que for criado um token novo, adiciona 1 no contador de tokens
				cont_tokens++; 
				entrada = teste.getNextChar();
				q0("");
			}
			if(entrada == 41){ //compara entrada com ")"
				tokens[cont_tokens] = new Token("RBRACKET", ")", linha);
				cont_tokens++;
				entrada = teste.getNextChar();
				q0("");
			}
			if(entrada == 123){ //compara entrada com "{" e assim sucessivamente
				tokens[cont_tokens] = new Token("LBRACE", "{", linha);
				cont_tokens++;
				entrada = teste.getNextChar();
				q0("");
			}
			if(entrada == 125){
				tokens[cont_tokens] = new Token("RBRACE", "}", linha);
				cont_tokens++;
				entrada = teste.getNextChar();
				q0("");
			}
			if(entrada == 93){
				tokens[cont_tokens] = new Token("RCOL", "]", linha);
				cont_tokens++;
				entrada = teste.getNextChar();
				q0("");
			}
			if(entrada == 61){
				entrada = teste.getNextChar();
				//comparação feita para saber se foi lido um equals ou apenas uma atribuição
				if(entrada == 61){ 
					tokens[cont_tokens] = new Token ("EQ", "==", linha);
					cont_tokens++;
					entrada = teste.getNextChar();
				}else{
				tokens[cont_tokens] = new Token("ATTR", "=", linha);
				cont_tokens++;
				q0("");
				}
			}
			if(entrada == 47){
				tokens[cont_tokens] = new Token("DIV", "/", linha);
				cont_tokens++;
				entrada = teste.getNextChar();
				q0("");
			}
			if(entrada == 42){
				tokens[cont_tokens] = new Token("MULT", "*", linha);
				cont_tokens++;
				entrada = teste.getNextChar();
				q0("");
			}
			if(entrada == 45){
				tokens[cont_tokens] = new Token("MINUS", "-", linha);
				cont_tokens++;
				entrada = teste.getNextChar();
				q0("");
			}
			if(entrada == 43){
				tokens[cont_tokens] = new Token("PLUS", "+", linha);
				cont_tokens++;
				entrada = teste.getNextChar();
				q0("");
			}
			if(entrada == 59){
				tokens[cont_tokens] = new Token("PCOMMA", ";", linha);
				cont_tokens++;
				entrada = teste.getNextChar();
				q0("");
			}
			if(entrada == 44){
				tokens[cont_tokens] = new Token("COMMA", ",", linha);
				cont_tokens++;
				entrada = teste.getNextChar();
				q0("");
			}
			if(entrada == 124){
				entrada = teste.getNextChar();
				//se for lido um || cria o seu token, mas se for lido apenas um | cria um token inválido
				if(entrada == 124){ 
					tokens[cont_tokens] = new Token("OR", "||", linha);
					cont_tokens++;
				}else{
					tokens[cont_tokens] = new Token ("INVALID ID", "|", linha);
					cont_tokens++;
				}
				entrada = teste.getNextChar();
				q0("");
			}
			if(entrada == 38){
				entrada = teste.getNextChar();
				if(entrada == 38){
					tokens[cont_tokens] = new Token("AND", "&&", linha);
					cont_tokens++;
				}else{
					tokens[cont_tokens] = new Token ("INVALID ID", "&", linha);
					cont_tokens++;
				}
				entrada = teste.getNextChar();
				q0("");
			}
			if(entrada == 60){
				entrada = teste.getNextChar();
				//verifica se foi lido um "<", se a proxima entrada for um "=" então foi lido um LE
				if(entrada == 61){ 
					tokens[cont_tokens] = new Token("LE", "<=", linha);
					cont_tokens++;
					entrada = teste.getNextChar();
				}else{ 
					//caso contrario com certeza foi lido um LT ja que o "<" foi lido
					tokens[cont_tokens] = new Token("LT", "<", linha);
					cont_tokens++;
					entrada = teste.getNextChar();
				}
			}
			if(entrada == 62){
				entrada = teste.getNextChar();
				if(entrada == 61){
					tokens[cont_tokens] = new Token("GE", ">=", linha);
					cont_tokens++;
					entrada = teste.getNextChar();
				}else{
					tokens[cont_tokens] = new Token("GT", ">", linha);
					cont_tokens++;
					entrada = teste.getNextChar();
				}
			}
			//verifica se a entrada é um número
			if(entrada >= 48 && entrada <= 57){
				//enquanto estiver lendo números concatena um ao outro
				while(entrada >= 48 && entrada <= 57){
					palavra = palavra + entrada;
					entrada = teste.getNextChar();
				}if(entrada != 46){ 
					//se nao ler nenhum . cria o INT_CONST
					tokens[cont_tokens] = new Token("INT_CONST", palavra, linha); 
					cont_tokens++;
					q0("");
				}else if(entrada == 46){
					//se ler um "." em algum momento do numero, vai ser lido um número decimal
					palavra = palavra + entrada; //concatena o . e lê a próxima entrada
					entrada = teste.getNextChar();
					while(entrada >= 48 && entrada <= 57){
						//concatena o . e lê a próxima entrada
						palavra = palavra + entrada;
						entrada = teste.getNextChar();
					}
					//quando ler algo que não seja um número cria um FLOAT_CONST
					tokens[cont_tokens] = new Token("FLOAT_CONST", palavra, linha);
					cont_tokens++;
					if(entrada == 59){
						//caso o número seja seguido do ";" um token PCOMMA
						tokens[cont_tokens] = new Token("PCOMMA", ";", linha); 
						cont_tokens++;
					}
					entrada = teste.getNextChar();
					q0("");
				}
			}
			while((entrada >= 65 && entrada <= 90) || (entrada >= 97 && entrada <= 122)){
				//apos ter feito as comparações com símbolos e números, quando a entrada for uma letra, 
				//começa uma concatenação para verificar qual token vai representar a palavra
				palavra = palavra + entrada;
				entrada = teste.getNextChar();
			}
			//chama o próximo estado que vai verificar qual é o token que representa a palavra lida
			q1(palavra);
		}
	}
	
	public void q1(String palavra){
		//compara a palavra lida com as palavras reservadas e se forem iguais cria o token
		if(palavra.equals("int")){
			tokens[cont_tokens] = new Token("INT", palavra, linha);
			cont_tokens++;
			entrada = teste.getNextChar();
			q0("");
		}else if(palavra.equals("main")){
			tokens[cont_tokens] = new Token("MAIN", palavra, linha);
			cont_tokens++;
			if(entrada == 40){
				tokens[cont_tokens] = new Token("LBRACKET", "(", linha);
				cont_tokens++;
				entrada = teste.getNextChar();
			}else{
				entrada = teste.getNextChar();
			}
			q0("");
		}else if(palavra.equals("float")){
			tokens[cont_tokens] = new Token("FLOAT", palavra, linha);
			cont_tokens++;
			entrada = teste.getNextChar();
			q0("");
		}else if(palavra.equals("if")){
			tokens[cont_tokens] = new Token("IF", palavra, linha);
			cont_tokens++;
			if(entrada == 40){
				tokens[cont_tokens] = new Token("LBRACKET", "(", linha);
				cont_tokens++;
				entrada = teste.getNextChar();
			}else{
				entrada = teste.getNextChar();
			}
			q0("");
		}else if(palavra.equals("else")){
			tokens[cont_tokens] = new Token("ELSE", palavra, linha);
			cont_tokens++;
			entrada = teste.getNextChar();
			if(entrada == 32) {
				linha++;
				entrada = teste.getNextChar();
			}
			q0("");
		}else if(palavra.equals("while")){
			tokens[cont_tokens] = new Token("WHILE", palavra, linha);
			cont_tokens++;
			if(entrada == 40){
				tokens[cont_tokens] = new Token("LBRACKET", "(", linha);
				cont_tokens++;
				entrada = teste.getNextChar();
			}else{
				entrada = teste.getNextChar();
			}
			q0("");
		}else if(palavra.equals("for")){
			tokens[cont_tokens] = new Token("FOR", palavra, linha);
			cont_tokens++;
			if(entrada == 40){
				tokens[cont_tokens] = new Token("LBRACKET", "(", linha);
				cont_tokens++;
				entrada = teste.getNextChar();
			}else{
				entrada = teste.getNextChar();
			}
			q0("");
		}else if(palavra.equals("read")){
			tokens[cont_tokens] = new Token("READ", palavra, linha);
			cont_tokens++;
			if(entrada == 40){
				tokens[cont_tokens] = new Token("LBRACKET", "(", linha);
				cont_tokens++;
				entrada = teste.getNextChar();
			}else{
				entrada = teste.getNextChar();
			}
			q0("");
		}else if(palavra.equals("print")){
			tokens[cont_tokens] = new Token("PRINT", palavra, linha);
			cont_tokens++;
			if(entrada == 40){
				tokens[cont_tokens] = new Token("LBRACKET", "(", linha);
				cont_tokens++;
				entrada = teste.getNextChar();
			}else if(entrada == 91){
				tokens[cont_tokens] = new Token("LCOL", "[", linha);
				cont_tokens++;
				entrada = teste.getNextChar();
			}else{
				entrada = teste.getNextChar();
			}
			q0("");
		}else if(palavra.equals("") || palavra.equals(" ")){
			//Essa comparação serve para caso seja lido uma combinação de espaços e \t para não criar um ID vazio
			entrada = teste.getNextChar();
			q0("");
		}else if(palavra.charAt(0) >= 97 && palavra.charAt(0) <= 122){
			//se a palavra não for igual a nenhuma palavra reservada, que dizer que é um ID
			//mas o ID precisa começar especificamente com uma letra minúscula
			tokens[cont_tokens] = new Token("ID", palavra, linha);
			cont_tokens++;
			if(entrada == 59){
				tokens[cont_tokens] = new Token("PCOMMA", ";", linha);
				cont_tokens++;
			}
			if(entrada == 44){
				tokens[cont_tokens] = new Token("COMMA", ",", linha);
				cont_tokens++;
			}
			if(entrada == 61){
				tokens[cont_tokens] = new Token("ATTR", "=", linha);
				cont_tokens++;
			}
			if(entrada == 41){
				tokens[cont_tokens] = new Token("RBRACKET", ")", linha);
				cont_tokens++;
			}
			if(entrada == 43){
				tokens[cont_tokens] = new Token("PLUS", "+", linha);
				cont_tokens++;
			}
			if(entrada == 45){
				tokens[cont_tokens] = new Token("MINUS", "-", linha);
				cont_tokens++;
			}
			if(entrada == 42){
				tokens[cont_tokens] = new Token("MULT", "*", linha);
				cont_tokens++;
			}
			if(entrada == 47){
				tokens[cont_tokens] = new Token("DIV", "/", linha);
				cont_tokens++;
			}
			if(entrada == 91){
				tokens[cont_tokens] = new Token("LCOL", "[", linha);
				cont_tokens++;
			}
			if(entrada == 60){
				entrada = teste.getNextChar();
				//como o if de fora verifica se foi lido um "<", caso a proxima entrada seja um "=" então foi lido um LE
				if(entrada == 61){ 
					tokens[cont_tokens] = new Token("LE", "<=", linha);
					cont_tokens++;
					entrada = teste.getNextChar();
				}else{ 
					//caso contrario com certeza foi lido um LT ja que o "<" foi lido
					tokens[cont_tokens] = new Token("LT", "<", linha);
					cont_tokens++;
					entrada = teste.getNextChar();
				}
			}
			if(entrada == 62){
				entrada = teste.getNextChar();
				//verifica se foi lido um ">", caso a proxima entrada seja um "=" então foi lido um GE
				if(entrada == 61){ 
					tokens[cont_tokens] = new Token("GE", "<=", linha);
					cont_tokens++;
					entrada = teste.getNextChar();
				}else{ 
					//caso contrario foi lido um GT ja que o ">" foi lido
					tokens[cont_tokens] = new Token("GT", ">", linha);
					cont_tokens++;
					entrada = teste.getNextChar();
				}
			}
			entrada = teste.getNextChar();
			q0("");
		}
	}

	//ANALISE SINTATICA	
	public void analiseSintatica(){
		programa();
	}
	
	public Id match(String esperado){
		
		if(tokens[i].getNome().equals(esperado)){
			if(i<=cont_tokens){
				//caso o token que esta sendo avaliado seja o mesmo que o esperado, a função vai casar e ler o próximo token, se houver
				i++; 
			}
		}else{
			//caso nao seja o mesmo, avisa do erro
			System.out.println("ERRO leu: "+tokens[i].getNome()+", era para ter lido "+esperado+"na linha "+tokens[i].getLinha()); 
			if(i<(cont_tokens-1)){
				//caso ainda existam tokens na entrada, le o proximo e tenta casar com o esperado, realizando a sincronizacao
				i++;
				match(esperado);
			}else{
				System.out.println("Acabou a entrada");
				System.exit(1);
			}
		}
		//Insere na tabela de simbolos se o token é um ID
		if(esperado.equals("ID")){
			String tipo = "null";
			if(tokens[i-1].getNome().equals("INT"))
				tipo="INT";
			if(tokens[i-1].getNome().equals("FLOAT"))
				tipo="FLOAT";
			
			if(tipo == "null" && tabelaSimbolos.containsKey(tokens[i-1].getLexema()))
				tipo = tabelaSimbolos.get(tokens[i-1].getLexema()).getTipo();
			if(tipo == "null" && !tabelaSimbolos.containsKey(tokens[i-1].getLexema()) &&
					tabelaSimbolos.containsKey(tokens[i-3].getLexema()))
				tipo = tabelaSimbolos.get(tokens[i-3].getLexema()).getTipo();
			//System.out.println(tokens[i-1].getLexema()+tokens[i-3].getLexema()+tipo);	
			
			Simbolo novoSimbolo = new Simbolo(tipo, tokens[i-1].getLexema(), "null", tokens[i-1].getLinha());
			if(tokens[i].getNome().equals("ATTR")){
				novoSimbolo.setValor(tokens[i+1].getLexema());
			}
			if(tokens[i-2].getNome().equals("INT")){
				novoSimbolo.setTipo("INT");
			}else if(tokens[i-2].getNome().equals("FLOAT")){
				novoSimbolo.setTipo("FLOAT");
			}
			//adiciona na tabela
			tabelaSimbolos.put(novoSimbolo.getLexema(), novoSimbolo);
			Id idnode = new Id(novoSimbolo);
			return idnode;
			 
		}
		return null;
	}
	
	public void erroNaLinha(int linha, String esperado){
		System.out.println("Erro na linha: "+linha+" era esperado: "+esperado);
	}
	
	//as funcoes a seguir sao baseadas na Gramatica dada
	//a funcao match é chamada sempre que existe um não terminal no lado direito da produção
	public void programa(){
		match("INT");
		match("MAIN");
		Astnode nodePaizao = new Astnode("AST");
		match("LBRACKET");
		match("RBRACKET");
		match("LBRACE");
		decl_comando(nodePaizao);
		match("RBRACE");
		System.out.println("ÁRVORE DE 3E");
		System.out.println(nodePaizao.geraArvore(0));
		System.out.println("\nCÓDIGO PYTHON");
		System.out.println(nodePaizao.geraCodigo(0));
	}
	
	public void decl_comando(Astnode nodePaizao){
		//descobre o first para saber se chama declaracao ou comando
		if(tokens[i].getNome() == "INT" || tokens[i].getNome() == "FLOAT"){
			declaracao(nodePaizao);
			decl_comando(nodePaizao);
		}else if(tokens[i].getNome() == "LBRACE" || 
				tokens[i].getNome() == "ID" || 
				tokens[i].getNome() == "IF" || 
				tokens[i].getNome() == "WHILE" || 
				tokens[i].getNome() == "READ" || 
				tokens[i].getNome() == "PRINT" || 
				tokens[i].getNome() == "FOR"){
			comando(nodePaizao);
			decl_comando(nodePaizao);
		}
	}
	
	public void declaracao(Astnode nodePaizao){
		String x = new String();
		x = tipo();
		match(x);
		Id idnode = match("ID");
		decl2(nodePaizao, idnode);
	}
	
	public String tipo(){
		if(tokens[i].getNome().equals("INT")){
			return "INT";
		}
		if(tokens[i].getNome().equals("FLOAT")){
			return "FLOAT";
		}
		return null;
	}
	
	public void decl2(Astnode nodePaizao, Id idnode){
		if(tokens[i].getNome().equals("COMMA")){
			if(i<cont_tokens-1){
				i++;
			}
			match("ID");
			decl2(nodePaizao, idnode);
		}else if(tokens[i].getNome().equals("PCOMMA")){
			if(i<cont_tokens-1){
				i++;
			}
		}else if(tokens[i].getNome().equals("ATTR")){
			if(i<cont_tokens-1){
				i++;
			}
			Expr exprnode = expressao();
			Attr nodeAttr = new Attr(idnode, exprnode);
			nodePaizao.addFilho(nodeAttr);
			decl2(nodePaizao, idnode);
			
		}else{
			String esperado  = "COMMA ou PCOMMA ou ATTR";
			erroNaLinha(tokens[i].getLinha(), esperado);
			if(i<cont_tokens-1){
				i++;
				decl2(nodePaizao, idnode);
			}else{
				erroNaLinha(tokens[i].getLinha(), esperado);
				System.exit(1);
			}
		}
	}
	
	public Expr expressao(){
		Expr expressaono = adicao();
		RelOp relopnode = new RelOp();
		relopnode.setExpr1(expressaono);
		relopnode.setExpr2(relacaoOpc(relopnode));
		if(relopnode.getExpr2() != null)
				return relopnode;
		return expressaono;
	}
	
	public Expr relacaoOpc(RelOp paizao){
		if(tokens[i].getNome().equals("LT")){
			match("LT");
			paizao.setOp("<");
			RelOp relopnode = new RelOp();
			Expr expr1 = adicao();
			relopnode.setExpr1(expr1);
			Expr expr2 = relacaoOpc(relopnode);
			relopnode.setExpr2(expr2);
			if(expr2 == null)
				return expr1;
			else
				return expr2;
			}else if(tokens[i].getNome().equals("LE")){
			match("LE");
			paizao.setOp("<=");
			RelOp relopnode = new RelOp();
			Expr expr1 = adicao();
			relopnode.setExpr1(expr1);
			Expr expr2 = relacaoOpc(relopnode);
			relopnode.setExpr2(expr2);
			if(expr2 == null)
				return expr1;
			else
				return expr2;
			}if(tokens[i].getNome().equals("GT")){
			match("GT");
			paizao.setOp(">");
			RelOp relopnode = new RelOp();
			Expr expr1 = adicao();
			relopnode.setExpr1(expr1);
			Expr expr2 = relacaoOpc(relopnode);
			relopnode.setExpr2(expr2);
			if(expr2 == null)
				return expr1;
			else
				return expr2;
			}else if(tokens[i].getNome().equals("GE")){
			match("GE");
			paizao.setOp(">=");
			RelOp relopnode = new RelOp();
			Expr expr1 = adicao();
			relopnode.setExpr1(expr1);
			Expr expr2 = relacaoOpc(relopnode);
			relopnode.setExpr2(expr2);
			if(expr2 == null)
				return expr1;
			else
				return expr2;
			}else if(tokens[i].getNome().equals("EQ")){
			match("EQ");
			paizao.setOp("==");
			RelOp relopnode = new RelOp();
			Expr expr1 = adicao();
			relopnode.setExpr1(expr1);
			Expr expr2 = relacaoOpc(relopnode);
			relopnode.setExpr2(expr2);
			if(expr2 == null)
				return expr1;
			else
				return expr2;
			}else if(tokens[i].getNome().equals("OR")){
			match("OR");
			paizao.setOp("||");
			RelOp relopnode = new RelOp();
			Expr expr1 = adicao();
			relopnode.setExpr1(expr1);
			Expr expr2 = relacaoOpc(relopnode);
			relopnode.setExpr2(expr2);
			if(expr2 == null)
				return expr1;
			else
				return expr2;
			}else if(tokens[i].getNome().equals("AND")){
				RelOp relopnode = new RelOp();
				paizao.setOp("&&");
				Expr expr1 = adicao();
				relopnode.setExpr1(expr1);
				Expr expr2 = relacaoOpc(relopnode);
				relopnode.setExpr2(expr2);
				if(expr2 == null)
					return expr1;
				else
					return expr2;
				}else
			return null;
	}

	public Expr adicao(){
		ArithOp adicaonode = new ArithOp();
		Expr expr1 = termo();
		adicaonode.setExpr1(expr1);
		Expr expr2 = adicaoOpc(adicaonode);
		adicaonode.setExpr2(expr2);
		if(expr2 == null)
				return expr1;
		return adicaonode;
	}
	
	public Expr adicaoOpc(ArithOp paizao){
		if(tokens[i].getNome().equals("PLUS")){
			match("PLUS");
			paizao.setOp("+");
			ArithOp arithopnode = new ArithOp();
			Expr expr1 = termo();
			arithopnode.setExpr1(expr1);
			Expr expr2 = adicaoOpc(arithopnode);
			arithopnode.setExpr2(expr2);
			if(expr2 == null)
				return expr1;
			return arithopnode;
			}else if(tokens[i].getNome().equals("MINUS")){
				match("MINUS");
				paizao.setOp("-");
				ArithOp arithopnode = new ArithOp();
				Expr expr1 = termo();
				arithopnode.setExpr1(expr1);
				Expr expr2 = adicaoOpc(arithopnode);
				arithopnode.setExpr2(expr2);
				if(expr2 == null)
					return expr1;
				return arithopnode;
			}else
				return null;
	}

	public Expr termo(){
		ArithOp arithopnode = new ArithOp();
		Expr expr1 = fator();
		arithopnode.setExpr1(expr1);
		
		Expr expr2 = termoOpc(arithopnode);
		arithopnode.setExpr2(expr2);
		
		if(expr2 == null)
			return expr1;
		else
			return arithopnode;
	}
	
	public Expr termoOpc(ArithOp paizao){
		if(tokens[i].getNome().equals("MULT")){
			match("MULT");
			paizao.setOp("*");
			ArithOp arithopnode = new ArithOp();
			Expr expr1 = termo();
			arithopnode.setExpr1(expr1);
			Expr expr2 = termoOpc(paizao);
			if(expr2 == null)
				return expr1;
			return arithopnode;
		}else if(tokens[i].getNome().equals("DIV")){
			match("DIV");
			paizao.setOp("/");
			ArithOp arithopnode = new ArithOp();
			Expr expr1 = termo();
			arithopnode.setExpr1(expr1);
			Expr expr2 = termoOpc(paizao);
			if(expr2 == null)
				return expr1;
			return arithopnode;
		} else
			return null;
	}
	
	public Expr fator(){
		if(tokens[i].getNome().equals("ID")){
			Simbolo simbolo = tabelaSimbolos.get(tokens[i].getLexema());
			match("ID");
			Id idnode = new Id(simbolo);
			return idnode;
		}else if(tokens[i].getNome().equals("INT_CONST")){
			float value = Float.parseFloat(tokens[i].getLexema());
			match("INT_CONST");
			Num numnode = new Num(value, 0);
			
			return numnode;
		}else if(tokens[i].getNome().equals("FLOAT_CONST")){
			float value = Float.parseFloat(tokens[i].getLexema());
			match("FLOAT_CONST");
			Num numnode = new Num(value, 1);
			return numnode;
		}else if(tokens[i].getNome().equals("LBRACKET")){
			match("LBRACKET");
			Expr expressao = expressao();
			match("RBRACKET");
			return expressao;
		}else
			return null;
	}
	
	public void comando(Astnode nodePaizao){
		if(tokens[i].getNome().equals("LBRACE")){
			match("LBRACE");
			bloco(nodePaizao);
		}else if(tokens[i].getNome().equals("ID")){
			match("ID");
			atribuicao(nodePaizao);
		}else if(tokens[i].getNome().equals("IF")){
			match("IF");
			comandoSe(nodePaizao);
		}else if(tokens[i].getNome().equals("WHILE")){
			match("WHILE");
			comandoEnquanto(nodePaizao);
		}else if(tokens[i].getNome().equals("READ")){
			match("READ");
			comandoRead(nodePaizao);
		}else if(tokens[i].getNome().equals("PRINT")){
			match("PRINT");
			comandoPrint(nodePaizao);
		}else if(tokens[i].getNome().equals("FOR")){
			match("FOR");
			comandoFor(nodePaizao);
		}
	}
	
	public void bloco(Astnode nodePaizao){
		Astnode nobloco = new Astnode("Bloco");
		decl_comando(nobloco);
		match("RBRACE");
		nodePaizao.addFilho(nobloco);
	}

	public void atribuicao(Astnode nodePaizao){
		Simbolo simbolo = tabelaSimbolos.get(tokens[i-1].getLexema());
		Id noId = new Id(simbolo);
		match("ATTR");
		Expr expressaonode = expressao();
		match("PCOMMA");
		Attr attrnode = new Attr(noId, expressaonode);
		nodePaizao.addFilho(attrnode);
	}
	
	public void comandoSe(Astnode nodePaizao){
		match("LBRACKET");
		If ifnode = new If();
		Expr expressaonode = expressao();
		match("RBRACKET");
		ifnode.setE(expressaonode);
		comando(ifnode);
		comandoSenao(ifnode);
		nodePaizao.addFilho(ifnode);
	}
	
	public void comandoSenao(If nodePaizao){
		if(tokens[i].getNome().equals("ELSE")){
			match("ELSE");
			comando(nodePaizao);
		}
	}

	public void comandoEnquanto(Astnode nodePaizao){
		match("LBRACKET");
		While whilenode = new While();
		Expr expressaonode = expressao();
		whilenode.setE(expressaonode);
		match("RBRACKET");
		comando(whilenode);
		nodePaizao.addFilho(whilenode);
	}

	public void comandoRead(Astnode nodePaizao){
		Simbolo simbolo = tabelaSimbolos.get(tokens[i].getLexema());
		Read readnode = new Read();
		Id idnode = new Id();
		match("ID");
		idnode.setSimbolo(simbolo);
		readnode.setId(idnode);
		match("PCOMMA");
		nodePaizao.addFilho(readnode);
	}

	public void comandoPrint(Astnode nodePaizao){
		Print printnode = new Print();
		match("LBRACKET");
		Expr expressaonode = expressao();
		match("RBRACKET");
		printnode.setExpr(expressaonode);
		match("PCOMMA");
		nodePaizao.addFilho(printnode);
	}

	public void comandoFor(Astnode nodePaizao){
		For fornode = new For();
		match("LBRACKET");
		Attr atribuicaonode = atribuicaoFor(fornode);
		match("PCOMMA");
		fornode.setIni(atribuicaonode);
		
		Expr expressaonode = expressao();
		match("PCOMMA");
		fornode.setCond(expressaonode);
	
		atribuicaonode = atribuicaoFor(fornode);
		fornode.setInc(atribuicaonode);
		match("RBRACKET");
		comando(fornode);
		
		nodePaizao.addFilho(fornode);
	}
	
	public Attr atribuicaoFor(For fornode){
		Simbolo simbolo = tabelaSimbolos.get(tokens[i].getLexema());
		Id idnode = new Id();
		match("ID");
		idnode.setSimbolo(simbolo);
		match("ATTR");
		Expr expressaonode = expressao();
		Attr attrnode = new Attr(idnode, expressaonode);
		return attrnode;
	}
}