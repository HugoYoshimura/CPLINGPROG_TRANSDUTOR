
public class Transdutor {
	// tabela de variaveis
	private char[][] variaveis;
	// entrada
	private char[] input;
	// variaveis temporarias
	private String tempS;
	private int tempN, pos, lastInput;
	
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
		// inicia o transdutor
		e0();
	}
	
	public void e0() {
		// verifica se acabou a string de entrada 
		if(pos >= input.length) {
			System.exit(0);
		}
		// verifica byte 0 na posicao atual, se existir finaliza o prog.
		if(input[pos] != '\0') {
			// caso nao exista entao
			if(input[pos] == ' '){
				// se ' ' continua no estado e0
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
				pos++;
				e1();
			}
		} else {
			System.exit(0);
		}
	}
	
	public void e1() {
		// verifica se acabou a string de entrada 
		if(pos >= input.length) {
			// imprime e volta para e0 onde vai ser finalizado
			System.out.println("V(" + enderecamento() + ")");
			tempS="";
			pos++;
			e0();
		\}
		// converte o char par interio na ASCII
		int charAscii = input[pos];
		// se ele for um acracter
		if((charAscii > 47 && charAscii < 58) || (charAscii > 64 && charAscii < 91) || (charAscii > 96 && charAscii < 123)) {
			// continuo em e1
			tempS += input[pos];
			pos++;
			e1();
		} else {
			// volto para e0
			System.out.println("V(" + enderecamento() + ")");
			tempS="";
			pos++;
			e0();
		}
	}
	
	public void e2() {
		// verifica se acabou a string de entrada 
		if(pos >= input.length) {
			// imprime o resultado e volta para e0
			System.out.println("N(" + tempN + ")");
			tempN = 0;
			pos++;
			e0();
		}
		if(Character.isDigit(input[pos])) {
			// se for outro digito entao concatena e continua em e2()
			int temp = input[pos];
			int zero = '0';
			tempN = (tempN * 10) + (temp - zero);
			pos++;
			e2();
		} else {
			// caso contrario imprime o valor de tempN e volta a e0
			System.out.println("N(" + tempN + ")");
			tempN = 0;
			pos++;
			e0();
		}
	}
	
	public int enderecamento() {
		// insere o tempS caso nao exista e retorna o indice na tabela
		// de variaveis
		for(int i = 0; i <= lastInput; i++) {
			if(existe(tempS, variaveis[i])) {
				return i;
			}
		}
		lastInput++;
		variaveis[lastInput] = tempS.toCharArray();
		return lastInput;
	}

	public boolean existe(String var, char[] tab) {
		// verifica se a variavel existe na tambela para o metodo enderecamento
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
}
