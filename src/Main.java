import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		String input = scan.nextLine();
		
		// No final da texto, inserir '#' para indicar que o vetor acabou.
		input += "#";
		
		Transdutor obj = new Transdutor(input);
		
	}

}
