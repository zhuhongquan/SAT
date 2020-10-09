public class Test1{
	public static void main(String args[]) {
		A a = new A();
		A b = new A();
		a.f=10;
		b = a;
		b.f = 20;
		System.out.println(a.f);

		int n1=1;
		int n2=2;
		n2=n1*10;
		System.out.println(n2);
	}
}

class A{
	int f;
}