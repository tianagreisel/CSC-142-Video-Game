import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import uwcse.graphics.GWindow;
import uwcse.graphics.Oval;
import uwcse.graphics.*;




public class PsychCabbage extends Cabbage{
	
	
	//Oval objects making up psych cabbage
	public Oval psychCab;
	public Oval psychCabFill;
	public Oval psychCabDot;
	public Oval psychCabCenter;
	
	
	//creates a PsychCabbage object
public PsychCabbage(GWindow window, Point center){
		
	//use Cabbage constructor with window and center of point
		super(window, center);
		
		//draw psych cabbage to screen
		this.draw();
		
	}
	
	
	/**
	 * Displays this Cabbage in the graphics window
	 */
	protected void draw(){
		
		//height of bounding rectangle containing oval of cabbage
		int height = 2 * CABBAGE_RADIUS;
		
		//create the oval's that make up the psych cabbage
		psychCab = new Oval((center.x - CABBAGE_RADIUS), (center.y - CABBAGE_RADIUS), height, height, Color.YELLOW, true);
		psychCabFill = new Oval ((center.x - CABBAGE_RADIUS)+2, (center.y - CABBAGE_RADIUS)+2, height-4, height-4, Color.BLUE, true);
		psychCabDot = new Oval((center.x - CABBAGE_RADIUS)+4, (center.y - CABBAGE_RADIUS)+4, height-8, height-8, Color.WHITE, true);
		psychCabCenter = new Oval ((center.x - CABBAGE_RADIUS)+6, (center.y - CABBAGE_RADIUS)+6, height-12, height-12, Color.RED, true);
		
		//add it to the screen
		window.add(psychCab);
		window.add(psychCabFill);
		window.add(psychCabDot);		
		window.add(psychCabCenter);
		
		
	}

	/**
	 * This cabbage is eaten by a caterpillar
	 * 
	 * @param cp
	 *            the caterpillar that is eating this cabbage
	 */
	
	
	public void isEatenBy(Caterpillar cp){
		
		//if cabbage is eaten by caterpillar, remove from screen
		window.remove(psychCab);
		window.remove(psychCabFill);
		window.remove(psychCabDot);
		window.remove(psychCabCenter);
		
	}

	
	
	

}
