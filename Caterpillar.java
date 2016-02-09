import uwcse.graphics.*;
import java.awt.Point;
import java.util.*;
import java.awt.Color;

/**
 * A Caterpillar is the representation and the display of a caterpillar
 */

public class Caterpillar implements CaterpillarGameConstants {
	
	// The body of a caterpillar is made of Points stored
	// in an ArrayList
	private ArrayList<Point> body = new ArrayList<Point>();

	// Store the graphical elements of the caterpillar body
	private ArrayList<Rectangle> bodyUnits = new ArrayList<Rectangle>();

	// The window the caterpillar belongs to
	private GWindow window;

	// Direction of motion of the caterpillar 
	private int dir = EAST;
	
	//color of caterpillar units
	Color c;
	
	//arraylist of different colors when caterpillar eats a psych cabbage
	ArrayList<Color> psychColor;

	
	//boolean for if caterpillar has eaten a psych cabbage or not 
	public boolean isEating = false;

	
	/**
	 * Constructs a caterpillar
	 * 
	 * @param window
	 *            the graphics where to draw the caterpillar.
	 */
	public Caterpillar(GWindow window) {
		
		// Initialize the graphics window for this Caterpillar
		this.window = window;

		// Create the caterpillar (10 points initially)
		// First point
		Point p = new Point();
		p.x = 5;
		p.y = WINDOW_HEIGHT / 2;
		body.add(p);

		// Other points
		for (int i = 0; i < 9; i++) {
			Point q = new Point(p);
			q.translate(STEP, 0);
			body.add(q);
			p = q;
		}
		
		
		//ArrayList of Colors caterpillar changes to when eaten psych cabbage
		psychColor = new ArrayList<Color>();
		
		psychColor.add(Color.BLUE);
		psychColor.add(Color.RED);
		psychColor.add(Color.WHITE);
		psychColor.add(Color.YELLOW);
		
		
		
		//draws caterpillar and displays to screen
		this.draw();
	

	}
	
	
	//draws the caterpillar and adds to display window
	public void draw(){
		
		//width and height of caterpillar body units
		int bodyUnitHeight = CATERPILLAR_WIDTH;
		int bodyUnitWidth = STEP;
		
		
		//width and height of rectangle making up body unit.  Changes based on direction caterpillar is moving
		int width = 0;
		int height = 0;
		
		
		//iterates through list of body points making up caterpillar
		for(int i = 0; i < body.size() - 1; i++){
			
			//gets first two points
			Point q = body.get(i);
			Point p = body.get(i + 1);
			
			
			// Upper left corner of the rectangle
	       int x = Math.min(q.x,p.x)-bodyUnitWidth/2;
	       int y = Math.min(q.y,p.y)-bodyUnitWidth/2;
	        
	       
	        //if horizontal
	        if(q.y == p.y){
	        	width = bodyUnitWidth;
	        	height = bodyUnitHeight;
	        	
	        }
			
	        //if vertical
	        if(q.x == p.x){
	        	width = bodyUnitHeight;
	        	height = bodyUnitWidth;
	        }
	        
	        //sets up color of caterpillar
	        
	        //if caterpillar has eaten a psych cabbage
	        if(isEating){
	        	
	        	//use changeColors() method to change body unit segment colors of caterpillar (rotates colors in array list)
	        	changeColors();
	        	
	        	//gets color at position of index 0 and sets that for the next body unit of caterpillar
	        	c = psychColor.get(0);
	        }
	        
	        //if it hasn't eaten a psych cabbage
	       else{
	        	
	    	   //set color of body of caterpillar to red
	        	c = Color.RED;
	       }
	        
	        //create a rectangle object of x, y coordinates determined above and width and height determined above
	        Rectangle bodyUnit = new Rectangle(x,y, width, height, c, true);
	        
	        //add rectangle to bodyUnits arrayList
	        bodyUnits.add(bodyUnit);
	        
			
		}
		
		//iterate through bodyUnits arrayList and add each caterpillar bodyUnit to screen
		for(int j = 0; j < bodyUnits.size(); j++){
			
			window.add(bodyUnits.get(j));
			
	}	
		
		
		
	}

	/**
	 * Moves the caterpillar in the current direction (complete)
	 */
	public void move() {
		
		
		move(dir);
		
	}

	/**
	 * Move the caterpillar in the direction newDir. <br>
	 * If the new direction is illegal, select randomly a legal direction of
	 * motion and move in that direction.<br>
	 * 
	 * @param newDir
	 *            the new direction.
	 */
	public void move(int newDir) {
		
		//gets the head point of the caterpillar
		Point head = new Point((Point) body.get(body.size() - 1));
		
		//direction EAST
		if(newDir == 2){
			
			
				//move caterpillar x coordinate in positive x direction by STEP amount
				head.x = head.x + STEP;
			
				//set current direction caterpillar is moving to newDir
				dir = newDir;
				
				
			}
			
		//direction SOUTH
			if(newDir == 4){
				
					//move caterpillar in positive y direction by STEP amount 
					head.y = head.y + STEP;
					
					//set current direction caterpillar is moving to newDir
					dir = newDir;
					
				}
				
			//direction WEST
				if(newDir == 3){
					
						//move caterpillar in negative x direction by STEP amount
						head.x = head.x - STEP;
						
						//set current direction caterpillar is moving to newDir
						dir = newDir;
					}
				
				//direction NORTH
				if(newDir == 1){
					
						//move caterpillar in negative y direction by STEP amount
						head.y = head.y - STEP;
						
						//set current direction caterpillar is moving to newDir
						dir = newDir;
						
					
				}
				
				
				//if the head point is valid, i.e. not on the fence of the garden, etc.
				if(isPointValid(head)){
			
					//add point to body array at last position to be the new head point
					body.add(body.size(), head);
					
					//remove the tail to simulate motion
					body.remove(0);
					
					//remove caterpillar from screen
					remove();
					
					//draw caterpillar 
					draw();
				}
				
				//if generated point is not valid (hit a wall of display or fence side)
				else{
					
					//pick a new direction 
					dir = changeDirection();
					
					//move caterpillar in that new direction
					move(dir);
					
				}
				
	}
	
	
	
	//changes the direction of the caterpillar, randomly picking North, East, South, or West
	public int changeDirection(){
		
		dir = (int) (Math.random() * 4) + 1;
		
		return dir;
		
		
	}
	
	
	

		
	
	//test if point is valid (i.e. not on the fence or at edge of display window)
	public boolean isPointValid(Point p){
		
		
		//width and height of garden
		int gardenWidth = (WINDOW_WIDTH/3 * 2);
		int gardenHeight = WINDOW_HEIGHT;
		
		
		  
		//boolean conditions representing display window
		
		//conditions regarding if x coordinate is in the display window
		boolean condition5 = p.x > (WINDOW_WIDTH - (10 + STEP));
		boolean condition6 = p.x < 0;
		
		//conditions regarding if y coordinate is in the display window
		boolean condition7 = p.y < 0;
		boolean condition8 = p.y > WINDOW_HEIGHT;
		
		
		
		
		//boolean conditions representing inside of the garden 
		
		//boolean conditions for bottom fence in garden
		boolean condition9 = (p.y >= (WINDOW_HEIGHT - 10) && (p.y <= WINDOW_HEIGHT));
		boolean condition11 = (p.y >= 0 && p.y <= 10);
		
		//boolean conditions for top fence in garden
		boolean condition10 = (p.x >= ((WINDOW_WIDTH - gardenWidth) + 10) && (p.x <= (WINDOW_WIDTH - (10 + STEP))));
		
		
		//on or in the outer left top fence of garden
			boolean condition1 = p.x >= (WINDOW_WIDTH - gardenWidth) && p.x <= (WINDOW_WIDTH - gardenWidth) + 10;
			boolean condition2 = p.y >= 0 && p.y <= (gardenHeight/2 - 60);
				
			//on or in the outer left bottom fence of garden
			boolean condition4 = p.y >= gardenHeight - (gardenHeight/2 - 60) && p.y <= WINDOW_HEIGHT;
			
			
			
			//booleans put together 
			
			//if caterpillar on any sides of the fence or at edges of display window, returns false
			if((condition1 && condition2) || (condition1 && condition4) || 
						(condition5) || (condition6) || (condition7) || 
						(condition8) || (condition10 && condition9) || (condition10 && condition11)){
					
					return false;
				}
			
			
				//returns true if it is a valid point and not on fence or at edges of display window
				return true;
		
		
	}
	
	
	//removes caterpillar body from screen and from bodyUnit ArrayList
	public void remove(){
		for(int i = 0; i < bodyUnits.size(); i++){
			
			//remove Caterpillar bodyUnits from window
			window.remove(bodyUnits.get(i));
			
			//remove Caterpillar bodyUnits from bodyUnits ArrayList
			bodyUnits.remove(i);
		}
		
	}
	


	/**
	 * Is the caterpillar crawling over itself?
	 * 
	 * @return true if the caterpillar is crawling over itself and false
	 *         otherwise.
	 */
	public boolean isCrawlingOverItself() {
		
	
			//goes through ArrayList  of body points making up caterpillar
			for(int i =0; i < body.size()-1; i++){
				
				//gets each point in caterpillar body
				Point catepillarBody = body.get(i);
				
				//gets the point making up the head of the caterpillar
				Point head = getHead();
			
				//tests if the distance between the head of the caterpillar is less than or equal to 
				//the body point of the caterpillar.  If it is then caterpillar crawling over self
			if(head.distance((catepillarBody)) <= 0 ){
				
				return true;
			}
			
			
		}
		//return false if not crawling over self
		return false; 
	}

	
	//return caterpillar tail point
	public Point getTail(){
		
		return new Point((Point) body.get(0));
		
		
	}
	
	/**
	 * Return the location of the head of the caterpillar (complete)
	 * 
	 * @return the location of the head of the caterpillar.
	 */
	public Point getHead() {
		return new Point((Point) body.get(body.size() - 1));
	}

	/**
	 * Increase the length of the caterpillar (by GROWTH_SPURT elements) Add the
	 * elements at the tail of the caterpillar.
	 */
	
	
	//when caterpillar eats a good cabbage, it grows by GROWTH_SPURT # of body units
	public void grow() {
		
		//repeats iterations number of bodyUnits equal to growth spurt so caterpillar grows by GROWTH_SPURT # of bodyUnits
		for(int i = 0; i <= GROWTH_SPURT; i++){
			
			
		//get the point at the tail of caterpillar
		Point tail = new Point(body.get(0));
		
		//if direction is EAST
		if(dir == 2){
			
			
			//move the x coordinate point by negative x direction so new body units adding to previous tail
			tail.x = tail.x - STEP;
	
			
		}
		
	//if direction is SOUTH
		if(dir == 4){
			
				//move y coordinate point in negative y direction to add new units to previous tail segment
				tail.y = tail.y - STEP;
				
				
			}
			
		//if direction is WEST
			if(dir == 3){
				
					//move x coordinate point in positive x direction to add new units to previous tail segment
					tail.x = tail.x + STEP;
					
					
				}
			
			//if direction is NORTH
			if(dir == 1){
				
					//move y coordinate in positive y direction to add new units to previous tail segment
					tail.y = tail.y + STEP;
					

					
					
				
			}
			
			//add new tail point to beginning of body ArrayList
			body.add(0, tail);
			
			//draw caterpillar with new segments
			draw();
	}
	}
	
	//changes the color of the caterpillar after eating psych cabbage
	public void changeColors(){
		
		//rotate colors in psychColor ArrayList
		//adds to last position in psychColor list the color of the first
		psychColor.add(psychColor.size(), psychColor.get(0));
		
		//removes first color from list
		psychColor.remove(0);
		
		//sets isEating to true to change colors when bodyUnits of caterpillar drawn
		isEating = true;
		
		
		
		
	}
	
	//boolean used to reset caterpillar to normal color after psych cabbage period of color change
	public boolean isEatingPsychCabbage(){
		
		isEating = false;
		
		return isEating;
		
		
	
	
	
}
}

