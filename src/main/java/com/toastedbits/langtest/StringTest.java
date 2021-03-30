import java.util.Random;


public class StringTest {
    private static String sanitizePhoneNumber(String s) {
        char c;
        StringBuffer sb = new StringBuffer();
        s = s.toLowerCase();

        for( int i=0; i < s.length(); i++ ) {
            c = s.charAt(i);

            switch(c) {
              case '0':
              case '1':
              case '2':
              case '3':
              case '4':
              case '5':
              case '6':
              case '7':
              case '8':
              case '9':
              case 'a':
              case 'b':
              case 'c':
              case 'd':
              case '+':
              case ',':
              case '*':
              case '#':
                sb.append(c);
                break; 
            }
        }

        return sb.toString();
    }
    
    public static String sanitize2(String s) {
    	return s.toLowerCase().replaceAll("[^0-9a-d+,*#]", "");
    }
    
	public static void main(String[] args) throws Exception {
		char table[] = new char[256];
		Random rand = new Random(System.currentTimeMillis());
		for(int i = 0; i < 256; ++i) {
			table[i] = (char)i;
		}
		
		while(true) {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < 10; ++i) {
				sb.append(table[rand.nextInt(256)]);
			}
			String test = sb.toString();
			String oldTest = sanitizePhoneNumber(test);
			String newTest = sanitize2(test);
			if(!oldTest.equals(newTest)) {
				System.out.println("Fail: " + test + " old: " + oldTest + " new: " + newTest);
			}
		}
	}
}
