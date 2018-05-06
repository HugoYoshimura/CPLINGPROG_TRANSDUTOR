
public class Transdutor {
	private char[][] variaveis;
	private char[] input;
	private String tempS;
	private int tempN, pos, lastInput;
	// Character.isDigit(x)
	
	public Transdutor(String input) {
		this.input = input.toCharArray();
		pos = 0;
		lastInput = -1;
		variaveis = new char[50][50];
		tempS = "";
		tempN = 0;
		e0();
	}
	
	public void e0() {
		if(pos >= input.length) {
			System.exit(0);
		}
		if(input[pos] != '\0') {
			if(input[pos] == ' '){
				pos++;
				e0();
			} else if(Character.isDigit(input[pos])) {
				int temp = input[pos];
				int zero = '0';
				tempN = temp - zero;
				pos++;
				e2();
			} else {
				tempS += input[pos];
				pos++;
				e1();
			}
		} else {
			System.exit(0);
		}
	}
	
	public void e1() {
		if(pos >= input.length) {
			System.out.println("V(" + enderecamento() + ")");
			tempS="";
			pos++;
			e0();
		}
		int charAscii = input[pos];
		if((charAscii > 47 && charAscii < 58) || (charAscii > 64 && charAscii < 91) || (charAscii > 96 && charAscii < 123)) {
			tempS += input[pos];
			pos++;
			e1();
		} else {
			System.out.println("V(" + enderecamento() + ")");
			tempS="";
			pos++;
			e0();
		}
	}
	
	public void e2() {
		if(pos >= input.length) {
			System.out.println("N(" + tempN + ")");
			tempN = 0;
			pos++;
			e0();
		}
		if(Character.isDigit(input[pos])) {
			int temp = input[pos];
			int zero = '0';
			tempN = (tempN * 10) + (temp - zero);
			pos++;
			e2();
		} else {
			System.out.println("N(" + tempN + ")");
			tempN = 0;
			pos++;
			e0();
		}
	}
	
	public int enderecamento() {
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
