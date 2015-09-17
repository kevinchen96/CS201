
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pet d = new Dog();
		Pet c = new Cat();
		System.out.println(d.speak());
		System.out.println(c.speak());
		
		if(d instanceof Nameable ){
			System.out.println("This is Nameable");
		}
		if(d instanceof Dog ){
			System.out.println("This is a dog");
		}
		if(!(d instanceof Cat) ){
			System.out.println("This is not a cat");
		}
		if(d instanceof Pet ){
			System.out.println("This is a pet");
		}
	}

}
