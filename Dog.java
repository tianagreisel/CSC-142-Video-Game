
public class Dog extends Pet {
	
	//instance fields
	private int dogID;
	String name;
	int age;
	
	
	//constructors(s)
	public Dog(int a, String n){
		//super()  access constructor of parent class if wanted to
		this.dogID = 
		
		this.age = a;
		this.name = n;
		
		
	}
	public Dog(){
		
		int a = age;
		String n = name;
		
	}
	public void speak(){
		String noise;
		if(speak)  System.out.println("bark");
		
	
	else System.out.println("....");
	}
	public boolean isShedding(){
		
		return true;
	}
	


public static void main(String[] args) {
	//Dog d = new Pet();
	//Pet dc = new Dog(a,n);
	Dog d = new Dog();
	d.test();
	Object f = new Dog();
	
	//f.speak();
	((Dog) f).test();
	//f.test();
	Pet spot = new Dog();
	spot.test();
	spot.speak();
	((Dog)f).test();
	Dog h = new Cat();
	h.test();
	

}
}
