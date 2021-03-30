import java.io.File;

public class FileTest {
	public static void main(String[] args) {
		File f = new File("idonotexist");
		System.out.println(f.listFiles());
	}
}
