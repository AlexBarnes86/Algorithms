import java.net.InetAddress;

public class NetworkTest {
	public static void main(String[] args) throws Exception {
		 InetAddress   in  = InetAddress.getLocalHost();
		 InetAddress[] all = InetAddress.getAllByName(in.getHostName());
		 System.out.println();
		 for (int i=0; i<all.length; i++) {
		     System.out.println(all[i].getHostAddress());
		 }
	}
}
