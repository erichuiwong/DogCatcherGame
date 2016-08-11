package com.mycompany.a3;

import java.util.ArrayList;
import java.util.Observable;
import com.codename1.ui.geom.Dimension;
/*
 * This class does most of the interactions and logic in the game world.
 *
 * 3/17/16 - Problems with array out of bounds using iterator
 * 4/25/16 - Problems with jumping to animals that do not exit anymore
 */
public class GameWorld extends Observable {
	private GameObjectCollection objectCollection = new GameObjectCollection();
	private MapView mv;
	private ArrayList<Dog> dogs = new ArrayList<Dog>();
	private ArrayList<Cat> cats = new ArrayList<Cat>();
	private Net net;
	private int numOfDogs = 2;
	private int numOfCats = 3;
	private int score = 0;
	private int catValue = 10;
	private int dogsCaught = 0;
	private int catsCaught = 0;
	private int maxCats = 30;
	private boolean sound = false;
	private Sound catSound;
	private Sound hissSound;
	private Sound dogSound;
	private SoundBG bgSound;

	public void initLayout() { //Initializes initial cats and dogs into the game
	    for (int i = 0; i < numOfDogs; i++) {
	    	dogs.add(new Dog());
	    }
	    for (int i = 0; i < numOfCats; i++) {
	    	cats.add(new Cat());
	    }
	}

	public void init() {	//New init for a2
		net = new Net();
		objectCollection.add(net);
		for (int i = 0; i < numOfDogs; i++) {
			objectCollection.add(new Dog());
		}
		for (int i = 0; i < numOfCats; i++) {
			objectCollection.add(new Cat());
		}
		catSound = new Sound("meow.wav");
		hissSound = new Sound("hiss.wav");
		dogSound = new Sound("bark.wav");
		bgSound = new SoundBG("bg.wav");
	}

	//Displays the GameObjectCollection
	public void displayCollection() {
		IIterator theElements = objectCollection.getIterator();
		while (theElements.hasNext()) {
			GameObject go = (GameObject) theElements.getNext();
			System.out.println(go.toString());
		}
		System.out.println();
	}

	public GameObjectCollection getObjects() {
		return objectCollection;
	}

	//Updates observers
	public void setChange() {
		setChanged();
		notifyObservers();
		//map();
	}

	public int getScore() {
		return this.score;
	}

	public void addScore() {
		this.score++;
		setChange();
	}

	public boolean getSound() {
		return this.sound;
	}
	
	public void setSound(boolean newSound) {
		this.sound = newSound;
		if (!sound) {
			bgSound.pause();
		} else {
			bgSound.play();
		}
		setChange();
	}

	public int getDogsCaptured() {
		return dogsCaught;
	}

	public int getCatsCaptured() {
		return catsCaught;
	}

	public int getDogsLeft() {
		return numOfDogs;
	}

	public int getCatsLeft() {
		return numOfCats;
	}

	public void healDog() {	//Heal works for dogs hit once but doesn't work when it's at 5+
		IIterator theElements = objectCollection.getIterator();
		while (theElements.hasNext()) {
			GameObject go = (GameObject) theElements.getNext();
			if (go instanceof Dog && ((Dog) go).isSelected()) {
				((Dog) go).setScratches(0);
				((Animal) go).setSpeed(5);
			}
		}
		setChange();
	}

	public void scratchDog() {				//Might have to check if a dog already has 5 scratches
	    /*randomDog = r.nextInt(dogs.size());		//Finds a random dog, scratches it, and decreases its speed
		dogs.get(randomDog).setScratches(dogs.get(randomDog).getScratches() + 1);
		dogs.get(randomDog).setSpeed(dogs.get(randomDog).getSpeed() - 1);*/

		Dog randomDog = (Dog) objectCollection.returnRandomDog();
		randomDog.setScratches(randomDog.getScratches() + 1);
		randomDog.setSpeed(randomDog.getSpeed() - 1);
		if (sound) {
			hissSound.play();
		}
	}

	public void checkScratch(int i, int j) {	//Method for collision check between a dog and cat
		while (objectCollection.getIterator().hasNext()) {
			Object obj = objectCollection.getIterator().getNext();
			if (obj instanceof Dog) {
				dogs.add((Dog) obj);
			} else if (obj instanceof Cat) {
				cats.add((Cat) obj);
			}
		}

		//Cat on left and above Dog
		if (cats.get(i).getLocX() < dogs.get(j).getLocX() && cats.get(i).getLocY() > dogs.get(j).getLocY()) {
			if (cats.get(i).getLocX() + cats.get(i).getRadius() >= dogs.get(j).getLocX() - dogs.get(j).getRadius()  && dogs.get(j).getLocY() + dogs.get(j).getRadius() >= cats.get(i).getLocY() - cats.get(i).getRadius()) {
				dogs.get(j).setScratches(dogs.get(j).getScratches() + 1);
				dogs.get(j).changeDirection();
				cats.get(i).changeDirection();
			}

		}
		//Cat on left and below Dog
		if (cats.get(i).getLocX() < dogs.get(j).getLocX() && cats.get(i).getLocY() < dogs.get(j).getLocY()) {
			if (cats.get(i).getLocX() + cats.get(i).getRadius() >= dogs.get(j).getLocX() - dogs.get(j).getRadius() && cats.get(i).getLocY() + cats.get(i).getRadius() >= dogs.get(j).getLocY() - dogs.get(j).getRadius()) {
				dogs.get(j).setScratches(dogs.get(j).getScratches() + 1);
				dogs.get(j).changeDirection();
				cats.get(i).changeDirection();
			}

		}
		//Cat on right and above Dog
		if (cats.get(i).getLocX() > dogs.get(j).getLocX() && cats.get(i).getLocY() > dogs.get(j).getLocY()) {
			if (cats.get(i).getLocX() - cats.get(i).getRadius() <= dogs.get(j).getLocX() + dogs.get(j).getRadius() && dogs.get(j).getLocY() + dogs.get(j).getRadius() >= cats.get(i).getLocY() - cats.get(i).getRadius()) {
				dogs.get(j).setScratches(dogs.get(j).getScratches() + 1);
				dogs.get(j).changeDirection();
				cats.get(i).changeDirection();
			}
		}

		//Cat on right and below Dog
		if (cats.get(i).getLocX() > dogs.get(j).getLocX() && cats.get(i).getLocY() < dogs.get(j).getLocY()) {
			if (cats.get(i).getLocX() - cats.get(i).getRadius() <= dogs.get(j).getLocX() + dogs.get(j).getRadius() && cats.get(i).getLocY() + cats.get(i).getRadius() >= dogs.get(j).getLocY() - dogs.get(j).getRadius()) {
				dogs.get(j).setScratches(dogs.get(j).getScratches() + 1);
				dogs.get(j).changeDirection();
				cats.get(i).changeDirection();
			}
		}
	}

	public void createKitten(int i) {	//Method for collision check between cats to create a kitten
		/*cats.add(new Cat());
		cats.get(cats.size()-1).setLoc(cats.get(i).getLocX(), cats.get(i).getLocY());
		*/

		objectCollection.add(new Cat());
	}

	public void createKitten() {
		if (numOfCats <= 30) {
			objectCollection.add(new Cat());
			numOfCats++;
		} else {
			//System.out.println("Too many cats on the field!");
		}
	}

	public void checkKitten(int i, int k) {
		IIterator theElements = objectCollection.getIterator();
		while (theElements.hasNext()) {
			Object obj = objectCollection.getIterator().getNext();
			if (obj instanceof Cat && numOfCats < maxCats) {
				cats.add((Cat) obj);
			} else {
				System.out.println("Maximum amount of cats");
			}
		}

		//Cat on left and above Cat
		if (cats.get(i).getLocX() < cats.get(k).getLocX() && cats.get(i).getLocY() > cats.get(k).getLocY()) {
			if (cats.get(i).getLocX() + cats.get(i).getRadius() >= cats.get(k).getLocX() - cats.get(k).getRadius()  && cats.get(k).getLocY() + cats.get(k).getRadius() >= cats.get(i).getLocY() - cats.get(i).getRadius()) {
				createKitten(i);
				numOfCats++;
			}

		}
		//Cat on left and below Cat
		if (cats.get(i).getLocX() < cats.get(k).getLocX() && cats.get(i).getLocY() < cats.get(k).getLocY()) {
			if (cats.get(i).getLocX() + cats.get(i).getRadius() >= cats.get(k).getLocX() - cats.get(k).getRadius() && cats.get(i).getLocY() + cats.get(i).getRadius() >= cats.get(k).getLocY() - cats.get(k).getRadius()) {
				createKitten(i);
				numOfCats++;
			}

		}
		//Cat on right and above Cat
		if (cats.get(i).getLocX() > cats.get(k).getLocX() && cats.get(i).getLocY() > cats.get(k).getLocY()) {
			if (cats.get(i).getLocX() - cats.get(i).getRadius() <= cats.get(k).getLocX() + cats.get(k).getRadius() && cats.get(k).getLocY() + cats.get(k).getRadius() >= cats.get(i).getLocY() - cats.get(i).getRadius()) {
				createKitten(i);
				numOfCats++;
			}
		}

		//Cat on right and below Cat
		if (cats.get(i).getLocX() > cats.get(k).getLocX() && cats.get(i).getLocY() < cats.get(k).getLocY()) {
			if (cats.get(i).getLocX() - cats.get(i).getRadius() <= cats.get(k).getLocX() + cats.get(k).getRadius() && cats.get(i).getLocY() + cats.get(i).getRadius() >= cats.get(k).getLocY() - cats.get(k).getRadius()) {
				createKitten(i);
				numOfCats++;
			}
		}
	}

	public void tick(Dimension dCmpSize) { 	//Tick method that moves all the animals
		/*for (int i = 0; i < cats.size(); i++) {
			cats.get(i).move();
			for (int j = 0; j < dogs.size(); j++) {
				checkScratch(i,j);
			}
			if (cats.size() > 0) {
				for (int k = 1; k < cats.size(); k++) {
					checkKitten(i,k);
				}
			}
		}
		for (int i = 0; i < dogs.size(); i++) {
			dogs.get(i).move();
			if (dogs.get(i).getScratches() >= 5) {
				dogs.get(i).setSpeed(0);
			}
		}
		map();*/

		IIterator theElements = objectCollection.getIterator();
		while (theElements.hasNext()) {
			GameObject go = (GameObject) theElements.getNext();
			if (go instanceof Net) {

			} else if (go instanceof Cat) {
				((Cat) go).move(dCmpSize);
			} else if (go instanceof Dog) {
				((Dog) go).move(dCmpSize);
			}
		}
		IIterator iter = objectCollection.getIterator();
		while (iter.hasNext()) {
			ICollider curObj = (ICollider) iter.getNext();
			IIterator iter2 = objectCollection.getIterator();
			while (iter2.hasNext()) {
				ICollider otherObj = (ICollider) iter2.getNext();
				if (otherObj != curObj && !(otherObj instanceof Net) && !(curObj instanceof Net)) {
					if (curObj.collidesWith(otherObj)) {
						//System.out.println("Collision");
						if (curObj instanceof Cat && otherObj instanceof Dog) {
							//Scratch the dog
							if (!((Animal) curObj).checkList(otherObj)) {
								//Other object isn't on the list
								((Animal) curObj).addTo(otherObj);  
								otherObj.handleCollision(curObj);
								if (sound) {
									catSound.play();
								}
							} else {
								//Do nothing since already handled collision
							}
							if (sound) {
								hissSound.play();
							}
						} else if (curObj instanceof Cat && otherObj instanceof Cat) {
							//Spawn a kitten
							if (!((Animal) curObj).checkList(otherObj)) {
								//Other object isn't on the list
								((Animal) curObj).addTo(otherObj);
								createKitten();   //Spawns too many cats
								if (sound) {
									catSound.play();
								}
							} else {
								//Do nothing since already handled collision
							}
						} else if (curObj instanceof Dog && otherObj instanceof Cat) {
							//Scratch the dog
							if (!((Animal) curObj).checkList(otherObj)) {
								//Other object isn't on the list
								((Animal) curObj).addTo(otherObj);  
								curObj.handleCollision(otherObj);
								if (sound) {
									catSound.play();
								}
							} else {
								//Do nothing since already handled collision
							}
							if (sound) {
								hissSound.play();
							}
						} else {
							//Do nothing so dogs don't scratch each other
						}
						//((Animal) curObj).changeDirection();
						//((Animal) otherObj).changeDirection();
						//((Animal) curObj).removeFrom(otherObj);
					}
				}
			}
		}
		setChange();
	}

	public void expand() {
		net.expand();
		//tick();
		setChange();
	}

	public void contract() {
		net.contract();
		//tick();
		setChange();

	}

	public void addCat() {
		this.numOfCats++;
		if (sound) {
			catSound.play();
		}
		setChange();
	}


	public void scoop() {
		/*if (!dogs.isEmpty()) {
			checkForDogs();
		} else {
			System.out.println("No dogs left!");
		}
		if (!cats.isEmpty()) {
			checkForCats();
		} else {
			System.out.println("No cats left!");
		}
		tick();
		printPoints();*/

		/*if (numOfDogs > 0) {
			System.out.println("test");
			checkForDogs();
		} else {
			System.out.println("No dogs left!");
		}

		if (numOfCats > 0) {
			checkForCats();
		} else {
			System.out.println("No cats left!");
		}*/

		IIterator theElements = objectCollection.getIterator();
		while (theElements.hasNext()) {
			GameObject go = (GameObject) theElements.getNext(); //Unsure why getting out of bounds error sometimes
			if (go instanceof Cat && numOfCats > 0) {
				if (net.getLocX() == ((Cat) go).getLocX() && net.getLocY() == ((Cat) go).getLocY()) {
					this.catsCaught++;
					this.score -= catValue;
					theElements.remove();
					this.numOfCats--;
					System.out.println("Net scooped a cat!");
					hissSound.play();
				}
			} else if( go instanceof Dog) {
				if (net.getLocX() == ((Dog) go).getLocX() && net.getLocY() == ((Dog) go).getLocY()) {
					this.dogsCaught++;
					this.score += ((Dog) go).getScore();
					theElements.remove();
					this.numOfDogs--;
					System.out.println("Net scooped a dog!");
					dogSound.play();
				}
			} else {

			}
		}

		//tick();
	}

	public void checkForDogs() {	//Method for checking if a Dog is within the net
		IIterator theElements = objectCollection.getIterator();
		while (theElements.hasNext()) {
			Object dog = theElements.getNext();
			if (dog instanceof Dog) {
				if (net.getLocX() == ((Dog) dog).getLocX() && net.getLocY() == ((Dog) dog).getLocY()) {
					this.dogsCaught++;
					this.score += ((Dog) dog).getScore();
					theElements.remove();
					this.numOfDogs--;
					System.out.println("Net scooped a dog!");
					continue;
				}
				//Net left side and above Dog
				/*if (net.getLocX() < ((Dog) dog).getLocX() && net.getLocY() > ((Dog) dog).getLocY()) {
					dogsCaught++;
					score += ((Dog) dog).getScore();
					objectCollection.getIterator().remove();
					numOfDogs--;
				}
				//Net on left side and below Dog
				if (net.getLocX() < ((Dog) dog).getLocX() && net.getLocY() < ((Dog) dog).getLocY()) {
					dogsCaught++;
					score += ((Dog) dog).getScore();
					objectCollection.getIterator().remove();
					numOfDogs--;
				}
				//Net on right side and above Dog
				if (net.getLocX() > ((Dog) dog).getLocX() && net.getLocY() > ((Dog) dog).getLocY()) {
					dogsCaught++;
					score += ((Dog) dog).getScore();
					objectCollection.getIterator().remove();
					numOfDogs--;
				}
				if (net.getLocX() > ((Dog) dog).getLocX() && net.getLocY() < ((Dog) dog).getLocY()) {
					dogsCaught++;
					score += ((Dog) dog).getScore();
					objectCollection.getIterator().remove();
					numOfDogs--;
				}*/
				//dogs.add((Dog) dog);
			} else {
				continue;
			}
		}
		setChange();

		/*for (int i = 0; i < dogs.size(); i++) {
			if (net.getLocX() == dogs.get(i).getLocX() && net.getLocY() == dogs.get(i).getLocY()) {
				dogsCaught++;
				score += dogs.get(i).getScore();
				dogs.remove(i);
				continue;
			}

			//Net on left side and above Dog
			if (net.getLocX() < dogs.get(i).getLocX() && net.getLocY() > dogs.get(i).getLocY()) {
				if (net.getLocX() + net.getRadius() <= dogs.get(i).getLocX() && net.getLocY() - net.getRadius() <= dogs.get(i).getLocY()) {
					dogsCaught++;
					score += dogs.get(i).getScore();
					dogs.remove(i);
				}
			}
			//Net on left side and below Dog
			if (net.getLocX() < dogs.get(i).getLocX() && net.getLocY() < dogs.get(i).getLocY()) {
				if (net.getLocX() + net.getRadius() <= dogs.get(i).getLocX() && net.getLocY() + net.getRadius() <= dogs.get(i).getLocY()) {
					dogsCaught++;
					score += dogs.get(i).getScore();
					dogs.remove(i);
				}
			}
			//Net on right side and above Dog
			if (net.getLocX() > dogs.get(i).getLocX() && net.getLocY() > dogs.get(i).getLocY()) {
				if (net.getLocX() - net.getRadius() <= dogs.get(i).getLocX() && net.getLocY() - net.getRadius() <= dogs.get(i).getLocY()) {
					dogsCaught++;
					score += dogs.get(i).getScore();
					dogs.remove(i);
				}
			}
			//net on right side and below Dog
			if (net.getLocX() > dogs.get(i).getLocX() && net.getLocY() < dogs.get(i).getLocY()) {
				if (net.getLocX() - net.getRadius() <= dogs.get(i).getLocX() && net.getLocY() + net.getRadius() <= dogs.get(i).getLocY()) {
					dogsCaught++;
					score += dogs.get(i).getScore();
					dogs.remove(i);
				}
			}
		}*/
	}

	public void checkForCats() { //Method for checking if a Cat is within the net
		IIterator theElements = objectCollection.getIterator();
		while (theElements.hasNext()) {
			GameObject cat = (GameObject) theElements.getNext();
			if (cat instanceof Cat) {
				if (net.getLocX() == ((Cat) cat).getLocX() && net.getLocY() == ((Cat) cat).getLocY()) {
					this.catsCaught++;
					this.score -= catValue;
					theElements.remove();
					this.numOfCats--;
					System.out.println("Net scooped a cat!");
					continue;
				}
				//Net left side and above Cat
				/*if (net.getLocX() < ((Cat) cat).getLocX() && net.getLocY() > ((Cat) cat).getLocY()) {
					this.catsCaught++;
					this.score -= catValue;
					theElements.remove();
					this.numOfCats--;
					continue;
				}
				//Net on left side and below Cat
				if (net.getLocX() < ((Cat) cat).getLocX() && net.getLocY() < ((Cat) cat).getLocY()) {
					this.catsCaught++;
					this.score -= catValue;
					theElements.remove();
					this.numOfCats--;
					continue;
				}
				//Net on right side and above Dog
				if (net.getLocX() > ((Cat) cat).getLocX() && net.getLocY() > ((Cat) cat).getLocY()) {
					this.catsCaught++;
					this.score -= catValue;
					theElements.remove();
					this.numOfCats--;
					continue;
				}
				if (net.getLocX() > ((Cat) cat).getLocX() && net.getLocY() < ((Cat) cat).getLocY()) {
					this.catsCaught++;
					this.score -= catValue;
					theElements.remove();
					this.numOfCats--;
					continue;
				}*/
				//cats.add((Cat) cat);

				setChange();
			} else {
				continue;
			}
		}

		/*for (int i = 0; i < cats.size(); i++) {
			if (net.getLocX() == cats.get(i).getLocX() && net.getLocY() == cats.get(i).getLocY())  {
				catsCaught++;
				score -= catValue;
				cats.remove(i);
				continue;
			}

			//Net on left side and above Cat
			if (net.getLocX() < cats.get(i).getLocX() && net.getLocY() > cats.get(i).getLocY()) {
				if (net.getLocX() + net.getRadius() <= cats.get(i).getLocX() && net.getLocY() - net.getRadius() <= cats.get(i).getLocY()) {
					catsCaught++;
					score -= catValue;
					cats.remove(i);
				}
			}
			//Net on left side and below Cat
			if (net.getLocX() < cats.get(i).getLocX() && net.getLocY() < cats.get(i).getLocY()) {
				if (net.getLocX() + net.getRadius() <= cats.get(i).getLocX() && net.getLocY() + net.getRadius() <= cats.get(i).getLocY()) {
					catsCaught++;
					score -= catValue;
					cats.remove(i);
				}
			}
			//Net on right side and above Cat
			if (net.getLocX() > cats.get(i).getLocX() && net.getLocY() > cats.get(i).getLocY()) {
				if (net.getLocX() - net.getRadius() <= cats.get(i).getLocX() && net.getLocY() - net.getRadius() <= cats.get(i).getLocY()) {
					catsCaught++;
					score -= catValue;
					cats.remove(i);
				}
			}
			//Net on right side and below Cat
			if (net.getLocX() > cats.get(i).getLocX() && net.getLocY() < cats.get(i).getLocY()) {
				if (net.getLocX() - net.getRadius() <= cats.get(i).getLocX() && net.getLocY() + net.getRadius() <= cats.get(i).getLocY()) {
					catsCaught++;
					score -= catValue;
					cats.remove(i);
				}
			}
		}*/
	}

	public void moveUp() {
		net.moveUp();
		//tick();
		setChange();
	}

	public void moveDown() {
		net.moveDown();
		//tick();
		setChange();
	}

	public void moveLeft() {
		net.moveLeft();
		//tick();
		setChange();
	}

	public void moveRight() {
		net.moveRight();
		//tick();
		setChange();
	}

	public void jumpToDog() {	//Method for jumping the net to a random Dog
		/*randomDog = r.nextInt(dogs.size());
		net.jumpToDog(dogs.get(randomDog).getLocX(), dogs.get(randomDog).getLocY());
		map();*/
		Dog randomDog = (Dog) objectCollection.returnRandomDog();
		net.jumpToDog(randomDog.getLocX(), randomDog.getLocY());
		setChange();
	}

	public void jumpToCat() {	//Method for jumping the net to a random Cat
		/*randomCat = r.nextInt(cats.size());
		net.jumpToCat(cats.get(randomCat).getLocX(), cats.get(randomCat).getLocY());
		map();*/
		Cat randomCat = (Cat) objectCollection.returnRandomCat();
		net.jumpToCat(randomCat.getLocX(), randomCat.getLocY());
		setChange();
	}

	public void catCollision() {	//Creates a "kitten" at a randomly selected Cat
		/*randomCat = r.nextInt(cats.size());
		if (cats.size() > 1) {
			cats.add(new Cat());
			cats.get(cats.size()-1).setLoc(cats.get(randomCat).getLocX(), cats.get(randomCat).getLocY());
		}*/
		if (numOfCats > 0) {
			Cat randomCat = (Cat) objectCollection.returnRandomCat();
			Cat tmpCat = new Cat();
			tmpCat.setLoc(randomCat.getLocX(), randomCat.getLocY());
			objectCollection.add(tmpCat);
			numOfCats++;
			setChange();
		} else {
			System.out.println("No cats on the map!");
		}
	}

	public void causeFight() {	//Placeholder method for real collision
		/*randomDog = r.nextInt(dogs.size());
		randomCat = r.nextInt(cats.size());
		dogs.get(randomDog).setLoc(cats.get(randomCat).getLocX(), cats.get(randomCat).getLocY());*/
		scratchDog();
		setChange();
	}

	public void printPoints() {	//Method that prints the score
		System.out.println("Score: " + score
						 + "\nDogs Caught: " + dogsCaught
						 + "\nCats Caught: " + catsCaught
						 + "\nDogs Left: " + dogs.size()
						 + "\nCats Left: " + cats.size());
		System.out.println();
	}


	public void map() {	//Method that prints the location and attributes of all game objects
		/*net.toString();
		for (int i = 0; i < dogs.size(); i++) {
			System.out.println(
					"Dog: loc=" + dogs.get(i).getLocX()
					+ "," + dogs.get(i).getLocY()
					+ " color=" + dogs.get(i).getColorString()
					+ " speed=" + dogs.get(i).getSpeed()
				    + " size=" + dogs.get(i).getSize()
				    + " dir=" + dogs.get(i).getDirection()
				    + " scratches=" + dogs.get(i).getScratches());
		}
		for (int i = 0; i < cats.size(); i++) {
			System.out.println(
					"Cat: loc=" + cats.get(i).getLocX()
					+ "," + cats.get(i).getLocY()
					+ " color=" + cats.get(i).getColorString()
					+ " speed=" + cats.get(i).getSpeed()
				    + " size=" + cats.get(i).getSize()
				    + " dir=" + cats.get(i).getDirection());
		}
		System.out.println();*/
		displayCollection();
	}

	public void quit() {	//Exits
		System.exit(0);
	}
}
