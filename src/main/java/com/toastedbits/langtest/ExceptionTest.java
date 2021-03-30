
public class ExceptionTest {
	public static void main(String[] args) {
		try {
			throw new Exception("Error");
		}
		catch(Exception e) {
			System.out.println("Caught Exception");
		}
		finally {
			System.out.println("Inside finally block");
		}
	}
}
