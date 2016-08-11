package com.mycompany.a3;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

/*
 * Class for containing and managing all game objects
 */
public class GameObjectCollection implements ICollection {
	
	private Vector<GameObject> theCollection;
	private ArrayList<Dog> dogs = new ArrayList<Dog>();
	private ArrayList<Cat> cats = new ArrayList<Cat>();
	private Random r = new Random();
	private int random; 
	
	public GameObjectCollection() {
		theCollection = new Vector<GameObject>();
	}
	
	public void add(GameObject newObject) {
		theCollection.addElement(newObject);	
	}

	public IIterator getIterator() {
		return new Iterator();
	}

	public void remove(GameObject o) {
		theCollection.remove(o);
	}
	
	public int getSize() {
		return theCollection.size();
	}
		
	private ArrayList<Dog> grabDogs() {
		for (GameObject o : theCollection) {
			if (o instanceof Dog) {
				dogs.add((Dog) o);
			}
		}
		return dogs;
	}
	
	private ArrayList<Cat> grabCats() {
		for (GameObject o : theCollection) {
			if (o instanceof Cat) {
				cats.add((Cat) o);
			}
		}
		return cats;
	}
	
	/*
	 * These random helper methods may be giving locations of objects that have been removed...
	 */
	public GameObject returnRandomDog() {
		ArrayList<Dog> dogList = grabDogs();
		if (dogList.size() > 0 ) {
			random = r.nextInt(dogList.size());
		} else {
			return null;
		}
		return dogList.get(random);
	}
	
	public GameObject returnRandomCat() {
		ArrayList<Cat> catList = grabCats();
		if (catList.size() > 0) {
			random = r.nextInt(catList.size());
		} else {
			return null;
		}
		return catList.get(random);
	}
	
	private class Iterator implements IIterator{
		
		private int currElementIndex;
		
		public Iterator() {
			currElementIndex = -1;
		}
		
		public boolean hasNext() {
			if (theCollection.size() <= 0) return false;
			if (currElementIndex == theCollection.size() - 1) return false;
			return true;
		}
	
		public Object getNext() {
			currElementIndex++;
			return (theCollection.elementAt(currElementIndex));
			
		}
	
		public void remove() {
			theCollection.removeElementAt(currElementIndex);
		}
	}

}