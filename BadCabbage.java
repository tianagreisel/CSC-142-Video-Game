import uwcse.graphics.*;

import java.awt.Color;
import java.awt.Point;


public class BadCabbage extends Cabbage{
	
	//Oval object making up badd cabbage
	Oval badCab;
	
	
	//creates a BadCabbage object
public BadCabbage(GWindow window, Point center){
		
	//use Cabbage constructor with window and center of point
		super(window, center);
		
		//draw bad cabbage to screen
		this.draw();
		
		
	}
	
	
	/**
	 * Displays this Cabbage in the graphics window
	 */
	public void draw(){
		
		//height of bounding rectangle containing oval of cabbage
		int height = 2 * CABBAGE_RADIUS;
		
		//create the oval that is the bad cabbage
		badCab = new Oval((center.x - CABBAGE_RADIUS), (center.y - CABBAGE_RADIUS), height, height, Color.RED, true);
		
		//add it to the screen
		window.add(badCab);
		
	}

	/**
	 * This cabbage is eaten by a caterpillar
	 * 
	 * @param cp
	 *            the caterpillar that is eating this cabbage
	 */
	public void isEatenBy(Caterpillar cp){
		
		//if cabbage is eaten by caterpillar, remove from screen
		window.remove(badCab);
		
	}


	

}

