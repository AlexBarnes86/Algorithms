import java.io.FileOutputStream;
import java.io.PrintWriter;


public class SizeTest {
	public static void main(String[] args) throws Exception {
		PrintWriter pw = new PrintWriter(new FileOutputStream("size_test"));
		int cols = 104;
		String padding = "Padding   Padding   Padding   Padding   Padding   ";
		for(long i = 0; i < 10000000; ++i) {
			StringBuffer sb = new StringBuffer(String.valueOf(i) + " ");
			for(int j = 0; j < cols; ++j) {
				sb.append(padding);
				if(j != cols -1)
					sb.append(", ");
			}
			System.out.println(i);
			pw.write(sb.toString());
		}
	}
}
