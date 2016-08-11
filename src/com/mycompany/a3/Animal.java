package com.mycompany.a3;

import java.util.*;

import com.codename1.ui.geom.Dimension;
/*
 * Abstract animal class that contains the current animals: Dogs and Cats
 */
public abstract class Animal extends GameObject implements IMovable, ICollider {
	private int size;
	private int speed;
	private int direction;
	private double locX;
	private double locY;
	private double loc = 15;
	private Random r = new Random();
	private int xMin = 0;	//Border of the Maps
	private int xMax = 750; //1000
	private int yMin = 0;
	private int yMax = 550; //610
	private ArrayList<ICollider> collisionList = new ArrayList<ICollider>();

	public Animal() {
		size = r.nextInt(21) + 30;
		locX = r.nextInt((int) xMax) + 20;
		locY = r.nextInt((int) yMax) + 20;
		speed = 5;
		direction = r.nextInt(360);
	}

	public void addTo(ICollider obj) {
		collisionList.add(obj);
	}
	
	public void removeFrom(ICollider obj) {
		collisionList.remove(obj);
	}
	
	public boolean checkList(ICollider obj) {
		if (collisionList.contains(obj)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void setLoc(double newX, double newY) {
		locX = newX;
		locY = newY;
	}

	public int getSize() {
		return size;
	}

	public int setSize(int newSize) {
		return size = newSize;
	}

	public int getRadius() {
		return size/2;
	}

	public int getSpeed() {
		return speed;
	}


	public void setSpeed(int newSpeed) {
		if (speed <= 0) {	//Prevents negative speed
			speed = 0;
		} else {
			speed = newSpeed;
		}
	}

	public double getLocX() {
		return Math.round(locX * 100) / 100;	//Rounds to 2 decimal places
	}

	public double getLocY() {
		return Math.round(locY * 100) / 100;
	}

	public int getDirection() {
		return direction;
	}

	//0 - North
	//90 - East
	//180 - South
	//270 - West

	protected void changeDirection() {	//May need some tweaking. This method more toward collision checking between cats and dogs to prevent excess scratching
		direction += 178;	//Changes the direction
		checkDirection();
	}

	protected void checkDirection() {	//Method for checking if direction exceed 359 or goes below 0
		if (direction >= 360) {
			direction -= 360;
		} else if (direction <= 0) {
			direction += 360;
		}
	}

	public void move(Dimension dCmpSize) {
		xMax = dCmpSize.getWidth();
		yMax = dCmpSize.getHeight();
		if (locX  > (xMax-loc) || locY > (yMax-loc)) {	//Checks if object is near the right border or top border
			direction -= 178;
			//changeDirection();
			locX += Math.cos(90 - direction) * speed;
			locY += Math.sin(90 - direction) * speed;
		} else if (locX <= (xMin+loc) || locY <= (yMin+loc)) { //Checks if object is near the left border or bottom border
			direction += 178;
			locX += Math.cos(90 - direction) * speed;
			locY += Math.sin(90 - direction) * speed;
		} else {
			locX += Math.cos(90 - direction) * speed;
			locY += Math.sin(90 - direction) * speed;
		}
		checkDirection();
	}
}
