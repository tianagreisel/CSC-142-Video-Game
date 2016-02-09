public abstract class  Pet {

	//instance fields
	protected String name;
	protected int age;
	
	//constructor(s)
	/*public Pet(String n, int a){
		name = n;
		age = a;
		
		
	}*/
	
	public Pet(){
		name = "";
		age = 0;
		
	}
	
	//instance methods
	public void speak(){
		System.out.print("Bark.  My name is " + name);
		String noise ="";
		System.out.println(noise);
		
	}
	
	//public  abstract boolean takeit();
	
	public void test(){
		System.out.println("yes");
	}
	public static void main(String[] args) {
		

	}

}
