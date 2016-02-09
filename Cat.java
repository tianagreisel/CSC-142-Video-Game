import java.lang.Math;
public class Cat extends Dog {
	
	
	
	public Cat() {
		
		this.name = "Fluffy";
		this.age = 1;
		
	}
	
	//overwriting the Parent class Pet's speak
	public void speak(){
		
		//50% chance of purring or meowing.
		/*String noise;
		int r = (int) Math.random() * 2;
		if(r == 1){
			noise = "purring";
		}
		
		else{
		noise = "meowing";
		}
		System.out.println(noise);
	}*/
String[] noises;
noises = new String[2];
noises[0] = "prrrr.....";
noises[1] = "Meow!!!!!";

System.out.println(noises[(int)Math.random() * 2]);

}
public static void main(String[] args) {
		Cat c = new Cat();
		Pet cx = new Cat();
		//Cat g = new Dog();
		Dog y = new Cat();
		c.test();
		((Pet) c).speak();
		cx.speak();
	}
}
