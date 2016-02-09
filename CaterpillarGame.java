import uwcse.graphics.*;

import java.util.*;
import java.awt.Color;

import javax.swing.JOptionPane;

import java.awt.Point;

/**
 * A CaterpillarGame displays a garden that contains good and bad cabbages and a
 * constantly moving caterpillar. The player directs the moves of the
 * caterpillar. Every time the caterpillar eats a cabbage, the caterpillar
 * grows. The player wins when all of the good cabbages are eaten and the
 * caterpillar has left the garden. The player loses if the caterpillar eats a
 * bad cabbage or crawls over itself.
 */

public class CaterpillarGame extends GWindowEventAdapter implements
		CaterpillarGameConstants
{
	
	// Game window
	private GWindow window;

	// The caterpillar
	private Caterpillar cp;

	// Direction of motion given by the player
	private int dirFromKeyboard;

	// Do we have a keyboard event
	private boolean isKeyboardEventNew = false;

	// The list of all the cabbages
	private ArrayList<Cabbage> cabbages;
	
	//list of GoodCabbages
	ArrayList<GoodCabbage> goodCabbages ;
	
	//list of BadCabbages
	ArrayList<BadCabbage> badCabbages;
	
	//list of PsychCabbages
	ArrayList<PsychCabbage> psychCabbages;
	
	

	//message displayed to user
	private String messageGameOver;
	
	//is the caterpillar eating a psych cabbage?
	public boolean isEatingPsychCabbage = false;
	
	
	//Width of garden
	int gardenWidth = (WINDOW_WIDTH/3 * 2);
	
	//Height of garden
	int gardenHeight = WINDOW_HEIGHT;
	
	//true if its the firstGame.  So instructions shown only for first game
		boolean firstGame = true;
		
		//Counter used to change colors of caterpillar when it has eating psychedelic cabbage
		int psychCount = 0;
		
		

	/**
	 * Constructs a CaterpillarGame
	 */
	public CaterpillarGame() {
		
		
		// Create the graphics window
		window = new GWindow("Caterpillar game", WINDOW_WIDTH, WINDOW_HEIGHT);
		

		// Any key or timer event while the window is active is sent to this
		// CaterpillarGame
		window.addEventHandler(this);

		// Set up the game (fence, cabbages, caterpillar)
		initializeGame();

	}

	/**
	 * Initializes the game (draw the garden, garden fence, cabbages,
	 * caterpillar)
	 */
	private void initializeGame() {
		
		// Clear the window
		window.erase();

		
		//tells the user the instructions of the game 
		if(firstGame){
		JOptionPane.showMessageDialog(null, "Eat all of the non red cabbage heads,\n and exit the garden.\n "
				+ "Don't eat the red cabbage heads.\n Don't crawl over yourself.\n To move left press 'J'.\n"
				+ "To move right press 'K.'\n To move up press 'I'.\n To move down press 'M'.\n", 
				"CATERPILLAR GAME", JOptionPane.INFORMATION_MESSAGE);
		
	}
		// No keyboard event yet
		isKeyboardEventNew = false;

		//constructs a list of goodCabbage objects
		goodCabbages = new ArrayList<GoodCabbage>();
		
		//constructs a list of badCabbage objects
		badCabbages = new ArrayList<BadCabbage>();
		
		//constructs a list of PsychCabbage objects
		psychCabbages = new ArrayList<PsychCabbage>();
		
		// Background (the garden)
		window.add(new Rectangle(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT,
				Color.green, true));
		
		
		
		// Create the fence around the garden
		
		//left display wall fence
		window.add(new Rectangle(WINDOW_WIDTH - 10, 0, 10, gardenHeight, Color.BLACK, true));
		
		//top display wall fence
		window.add(new Rectangle(WINDOW_WIDTH - gardenWidth, 0, gardenWidth, 10, Color.BLACK, true));
		
		//top outer(left) fence leading to garden opening
		window.add(new Rectangle((WINDOW_WIDTH - gardenWidth), 0, 10, gardenHeight/2 - 60, Color.BLACK, true));
		
		//bottom outer(left) fence leading to garden opening
		window.add(new Rectangle((WINDOW_WIDTH - gardenWidth), gardenHeight - (gardenHeight/2 - 60), 10, (gardenHeight/2 - 40), Color.BLACK, true));
		
		//bottom display wall fence
		window.add(new Rectangle((WINDOW_WIDTH - gardenWidth),gardenHeight - 10, gardenWidth, 10, Color.BLACK, true));
		
		
		
		//creates an ArrayList as long as the number of good and bad cabbages
		cabbages = new ArrayList<Cabbage>(N_GOOD_CABBAGES + N_BAD_CABBAGES);
		
	

		// Initialize the elements of the ArrayList = cabbages
		
		//counters used to add the correct number of each cabbage to the display
		int goodCabCount = 0;
		int badCabCount = 0;
		int psychCabCount = 0;
		
		//adds 10 good cabbages to the screen
		while (goodCabCount < 10 ){
		
			//randomly generates x, y coordinates for the good cabbages
			int x = (int) (Math.random() * (gardenWidth - 40)) + (WINDOW_WIDTH - gardenWidth) + 20;
			int y = (int) (Math.random() * (gardenHeight - 40 )) + 20;
			
			
			//use x, y coordinates to create a new point object
			Point p = new Point(x,y);
			
			
			//tests the point to make sure it isn't within a cabbage radius of another cabbage (overlapping with another cabbage)
			if(isOverLapping(p) == false){
			
				
			//creates the GoodCabbage object and add to the cabbages and goodCabbages array
			GoodCabbage goodCab = new GoodCabbage(window, p);
			cabbages.add(goodCab);
			goodCabbages.add(goodCab);
			
			goodCabCount +=1;
			}
			
		}
		//adds 10 bad cabbages to the screen
			while(badCabCount < 10){
				
			//randomly generates x, y coordinates for the good cabbages
			int x = (int) (Math.random() * (gardenWidth - 40)) + (WINDOW_WIDTH - gardenWidth) + 20;
			int y = (int) (Math.random() * (gardenHeight - 40 )) + 20;
			
			Point p = new Point(x,y);
			
			//tests the point to make sure it isn't within a cabbage radius of another cabbage
			if(isOverLapping(p) == false){
			
			//creates the BadCabbage object and add to the cabbages and goodCabbages array
			BadCabbage badCab = new BadCabbage(window, p);
			cabbages.add(badCab);
			badCabbages.add(badCab);
			
			badCabCount +=1;
			}
			}
			
			//adds random number (2 - 5) of psych cabbages to the screen
			while(psychCabCount <= (int) ((Math.random() * 4) + 2)){
				
				//randomly generates x, y coordinates for the good cabbages
				int x = (int) (Math.random() * (gardenWidth - 40)) + (WINDOW_WIDTH - gardenWidth) + 20; 
				int y = (int) (Math.random() * (gardenHeight - 40 )) + 20;
				
				Point p = new Point(x,y);
				
				//tests the point to make sure it isn't within a cabbage radius of another cabbage
				if(isOverLapping(p) == false){
				
				//creates the GoodCabbage object and add to the cabbages and goodCabbages array
				PsychCabbage psychCab = new PsychCabbage(window, p);
				cabbages.add(psychCab);
				psychCabbages.add(psychCab);
				
				psychCabCount +=1;
				
				}
			
			
		}
			
		// Create the caterpillar
		cp = new Caterpillar(window);
		 
		// start timer events (to do the animation)
		this.window.startTimerEvents(ANIMATION_PERIOD);
	}

	/**
	 * Moves the caterpillar within the graphics window every ANIMATION_PERIOD
	 * milliseconds.
	 * 
	 * @param e
	 *            the timer event
	 */
	public void timerExpired(GWindowEvent e) {
		// Did we get a new direction from the user?
		// Use isKeyboardEventNew to take the event into account
		// only once
		if (isKeyboardEventNew) {
			isKeyboardEventNew = false;
			cp.move(dirFromKeyboard);
		} else
			cp.move();

		
		//is the caterpillar eating a good cabbage
		eatingGoodCabbage();
		
		//is the caterpillar eating a bad cabbage
		eatingBadCabbage();
		
		//is the caterpillar crawling over itself?
		if(cp.isCrawlingOverItself()){
			messageGameOver = "DON'T CRAWL OVER YOURSELF";
			endTheGame();
		}
		
		//is the caterpillar eating a psychedelic cabbage
		eatingPsychCabbage();
		
		//if caterpillar is eating psychedelic cabbage
		if(isEatingPsychCabbage){
			
			//changes caterpillar color for psychCount period of time
			psychCount += 1;
			
			
			//if psychCount counter has reached 200, resets counter and boolean isEatingPsychCabbage to go back to normal
			if(psychCount == 200){
				
				isEatingPsychCabbage = false;
				psychCount = 0;
				
				//resets caterpillar to normal red color
				cp.isEatingPsychCabbage();
			
		}
			
			
			
		}	
		
		//checks to see if player has won (eaten all the good cabbages and exited the garden)
		playerWins();
	}

	/**
	 * Moves the caterpillar according to the selection of the user i: NORTH, j:
	 * WEST, k: EAST, m: SOUTH
	 * 
	 * @param e
	 *            the keyboard event
	 */
	public void keyPressed(GWindowEvent e) {
		switch (Character.toLowerCase(e.getKey())) {
		case 'i':
			dirFromKeyboard = NORTH;
			break;
		case 'j':
			dirFromKeyboard = WEST;
			break;
		case 'k':
			dirFromKeyboard = EAST;
			break;
		case 'm':
			dirFromKeyboard = SOUTH;
			break;
		default:
			return;
		}

		// new keyboard event
		isKeyboardEventNew = true;
	}

	/**
	 * The game is over. Starts a new game or ends the application
	 */
	public void endTheGame() {
		window.stopTimerEvents();
		
		
		
		// describes the outcome of the game that just ended
		//asks user if they want to play again
		boolean again = anotherGame(messageGameOver);
		//if user wants to play again, initialize a new game, otherwise exit
		if (again) {
			initializeGame();
		} else {
			System.exit(0);
		}
	}

	/**
	 * Does the player want to play again?
	 */
	private boolean anotherGame(String s) {
		int choice = JOptionPane.showConfirmDialog(null, s
				+ "\nDo you want to play again?", "Game over",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (choice == JOptionPane.YES_OPTION){
			
			//don't need to see instructions again.  Not first game
			firstGame = false;
			
			return true;
		}
		else
			return false;
	}

	/**
	 * Starts the application
	 */
	//has the player won the game?
	public void playerWins(){
		
		//get tail of caterpillar and see if it is outside the garden
		Point tail = cp.getTail();
		
		//if caterpillar outside the garden and it has eaten all the good cabbages then player has won
		if((tail.x < (WINDOW_WIDTH - gardenWidth)) && (goodCabbages.size() == 0)){
			messageGameOver = "CONGRATULATIONS, YOU WIN!!";
			
			endTheGame();
		}
		
		
	}
	
	//tests to make sure cabbages aren't overlapping
	public boolean isOverLapping(Point p){
		
		//goes through cabbages list
		for(int i = 0; i < cabbages.size(); i++){
			
			//get each cabbage in list
			Cabbage c = cabbages.get(i);
			
			//get point of cabbage
			Point q = c.getLocation();
			
			//find the distance between possible point for new cabbage and cabbage already in list
			int distance = (int) Math.sqrt(Math.pow((p.x - q.x), 2) + Math.pow(p.y - q.y, 2));
			
			//if distance between points is less than 2 * radius (if right next to each other) then overlapping is true
			if(distance < (CABBAGE_RADIUS * 2) ){
				return true;
			}
			
			
		}
		
		//return false if not overlapping
		return false;
		}
		
	//tests to see if caterpillar is eating a good cabbage
	public void eatingGoodCabbage(){
		
		//goes through good cabbage array list
		for(int i = 0; i < goodCabbages.size(); i++){
			
			//gets each good cabbage
			GoodCabbage cab = goodCabbages.get(i);
		
			//gets point of caterpillar head
			Point head = cp.getHead();
			
			//if caterpillar head point is in the cabbage, then caterpillar is eating cabbage (ran into it)
			if(cab.isPointInCabbage(head)){
				
				//invoke isEatenBy cabbage method to remove cabbage from display
				cab.isEatenBy(cp);
				
				//remove good cabbage from the goodCabbages array list
				goodCabbages.remove(cab);
				
				//make the caterpillar grow
				cp.grow();
				
				
			
			}
		
		}
		
	}
		
			
	
		//tests to see if caterpillar is eating a bad cabbage
		public void eatingBadCabbage(){
			
			//goes through badCabbages list
			for(int i = 0; i < badCabbages.size(); i++){
				
				//gets eat bad cabbage
				BadCabbage cab = badCabbages.get(i);
			
				//gets point of caterpillar head
				Point head = cp.getHead();
				
				//if caterpillar head point is in the cabbage, then caterpillar is eating cabbage (ran into it)
				if(cab.isPointInCabbage(head)){
					
					//invoke isEatenBy cabbage method to remove cabbage from display
					cab.isEatenBy(cp);
					
					//remove bad cabbage from the goodCabbages array list
					badCabbages.remove(cab);
					
					//make the game over message appropriate to eating a bad cabbage
					messageGameOver = "DON'T EAT THE POISONOUS CABBAGES";
					
					//end the game
					endTheGame();
					
	
				
				}
			
			}	
			
		}
	
	
		//test to see if caterpillar is eating a psychedelic cabbage
		public void eatingPsychCabbage(){
			
			//goes through psychCabbages list
			for(int i = 0; i < psychCabbages.size(); i++ ){
				
				//gets each psychedelic cabbage
				PsychCabbage pCab = psychCabbages.get(i);
				
				//gets point of caterpillar head
				Point head = cp.getHead();
				
				//if caterpillar head point is in the cabbage, then caterpillar is eating cabbage (ran into it)
				if(pCab.isPointInCabbage(head)){
					
					//invoke isEatenBy cabbage method to remove cabbage from display
					pCab.isEatenBy(cp);
					
					//remove psychedelic cabbage from psychCabbages list
					psychCabbages.remove(pCab);
					
					//invoke changeColors()  caterpillar method to change caterpillar colors
					cp.changeColors();
					
					//ensures caterpillar changes color for short period of time
					isEatingPsychCabbage = true;
					
	
				
				}
				

			}
			
		}
		
	//creates a new CaterpillarGame	
	public static void main(String[] args) {
		new CaterpillarGame();
	}
}