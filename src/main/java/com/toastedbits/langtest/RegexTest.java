import java.util.Scanner;


public class RegexTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line = "";
		while((line = sc.nextLine()) != null) {
			if (line.matches(".*\\.cop$"))
				System.out.println("True");
			else
				System.out.println("False");
		}
	}
}
