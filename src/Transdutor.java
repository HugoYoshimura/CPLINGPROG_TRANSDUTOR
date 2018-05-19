
public class Transdutor {
	// tabela de variaveis
	private char[][] variaveis;
	// entrada
	private char[] input;
	// variaveis temporarias
	private String tempS;
	private int tempN, pos, lastInput;
	
	// variaveis para construcao da tabela de variaveis
	private int maior, tempL;
	
	/**
	 * Metodo construtor
	 * @param input String a ser analisada pelo transdutor
	 */
	public Transdutor(String input) {
		// converte string de entrada para array de chars
		this.input = input.toCharArray();
		// inicia posicao em 0
		pos = 0;
		// deixa indefinido a ultima posicao na tabela
		lastInput = -1;
		// devine que na tabela caberam no maximo 50 variaveis
		variaveis = new char[50][50];
		tempS = "";
		tempN = 0;
		maior = 0;
		tempL = 0;
		// inicia o transdutor
		e0();
	}
	
	/**
	 * Metodo que representa o estado e0.
	 * Estado inicial e final. 
	 */
	public void e0() {
		// Verifica se o tamanho da varievel anterior e o maior.
		if(tempL > maior) {
			maior = tempL;
		}
		tempL = 0;
		
		if(input[pos] == '#') {
			System.out.println(tabelaToString());
			System.exit(0);
		} else if(input[pos] == ' '){
			// se a entrada for espaço (' '), continua no estado e0
			pos++;
			e0();
		} else if(Character.isDigit(input[pos])) {
			// se for um numero vai para o estado e2
			int temp = input[pos];
			int zero = '0';
			tempN = temp - zero;
			pos++;
			e2();
		} else {
			// senao, entao so pode ser um char entao vai para e1
			tempS += input[pos];
			tempL++;
			pos++;
			e1();
		}
	}
	
	/**
	 * Metodo que representa o estado e1.
	 * Estado que identidica variaveis.
	 */
	public void e1() {
		// converte o char para o valor ASCII em int
		int charAscii = input[pos];
		// verifica se acabou a string de entrada 
		if(input[pos] == '#') {
			// imprime a posicao onde esta armazenada e volta para e0
			System.out.println("V(" + enderecamento() + ")");
			tempS="";
			e0();
		}
		// se ele for um caracter ou um numero
		else if((charAscii > 47 && charAscii < 58) || (charAscii > 64 && charAscii < 91) || (charAscii > 96 && charAscii < 123)) {
			// adiciono no tempS e continuo em e1
			tempS += input[pos];
			tempL++;
			pos++;
			e1();
		} else {
			// imprime a posicao onde esta armazenada e volto para e0
			System.out.println("V(" + enderecamento() + ")");
			tempS="";
			pos++;
			e0();
		}
	}
	
	/**
	 * Metodo que representa o estado e2.
	 * Estado que identifica numeros.
	 */
	public void e2() {
		// verifica se acabou a string de entrada 
		if(input[pos] == '#') {
			// imprime o valor do numero e volta para e0
			System.out.println("N(" + tempN + ")");
			tempN = 0;
			e0();
		}
		else if(Character.isDigit(input[pos])) {
			// se a entrada for um numero, calcula-se o numero
			int temp = input[pos];
			int zero = '0';
			tempN = (tempN * 10) + (temp - zero);
			pos++;
			e2();
		} else {
			// caso contrario imprime o valor de tempN e volta a e0
			System.out.println("N(" + tempN + ")");
			tempN = 0;
			int charAscii = input[pos];
			if(!((charAscii > 64 && charAscii < 91) || (charAscii > 96 && charAscii < 123))) {
				pos++;
			} 
			e0();
		}
	}
	
	/**
	 * Metodo que confere se a variavel existe na tabela e caso nao tenha, insere a variavel na tabela.
	 * @return Posicao da variavel
	 */
	public int enderecamento() {
		// insere o tempS caso nao exista e retorna o indice na tabela
		// de variaveis
		for(int i = 0; i <= lastInput; i++) {
			if(igual(tempS, variaveis[i])) {
				return i;
			}
		}
		lastInput++;
		variaveis[lastInput] = tempS.toCharArray();
		return lastInput;
	}

	/**
	 * Metodo que compara a variavel nova com a variavel da tabela
	 * @param var variavel inserida
	 * @param tab variavel da tabela
	 * @return Se for igual, retorna true, caso contrario, falso
	 */
	public boolean igual(String var, char[] tab) {
		// verifica se a variavel existe na tabela de variaveis
		if(tab.length == var.length()) {
			for (int i = 0; i < var.length(); i++) {
				if(tab[i] != var.charAt(i)) {
					return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}
	
	/**
	 * Metodo que imprime a tabela de variaveis
	 * @return String com a tabela de variaveis
	 */
	public String tabelaToString() {
		String result = "\nTabela de Variáveis\n";
		for(int n = 0; n < maior + 9; n++) {
			result += "-";
		}
		result += "\n";
		for(int i = 0; i <= lastInput; i++) {
			result += "| ";
			if(i < 10) {
				result += "0" + i + " | ";
			} else {
				result += i + " | ";
			}
			for(int j = 0; j < variaveis[i].length; j++) {
				result += variaveis[i][j];
			}
			if(variaveis[i].length == maior) {
				result += " |";
			} else {
				for( int m = 0; m < (maior - variaveis[i].length); m++) {
					result += " ";
				}
				result += " |";
			}
			result += " \n";
		}
		for(int n = 0; n < maior + 9; n++) {
			result += "-";
		}
		return result;
	}
}
