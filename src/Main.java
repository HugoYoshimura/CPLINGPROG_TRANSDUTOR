import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		String input = scan.nextLine();
		
		input += "#";
		
		Transdutor obj = new Transdutor(input);
		
	}

}
