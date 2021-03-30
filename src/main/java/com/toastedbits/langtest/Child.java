
public class Child extends Parent {
	
	@Override
	public void recur() {
		super.recur();
		System.out.println("Called Child");
		recur();
	}
	
	public static void main(String[] args) {
		Child c = new Child();
		c.recur();
	}

}
