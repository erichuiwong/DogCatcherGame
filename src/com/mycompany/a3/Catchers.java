package com.mycompany.a3;

import java.util.*;

/*This abstract class is for all the Catcher objects in this game.
 * So far this game only has the net game object.
 */
public abstract class Catchers extends GameObject implements IGuided {

	private double locX;
	private double locY;
	private int size;
	private int speed = 10;
	private int change = 10;
	private int xMin = 0;	//Border of the Maps
	private int xMax = 750; //1000
	private int yMin = 0;
	private int yMax = 550; //610
	private Random r = new Random();

	public Catchers() {
		super();
		locX = r.nextFloat() + r.nextInt((int) xMax);
		locY = r.nextFloat() + r.nextInt((int) yMax);
		size = 100;
	}

	public double getLocX() {
		return Math.round(locX * 100) / 100;	//Rounds to 2 decimal places
	}

	public double getLocY() {
		return Math.round(locY * 100) / 100;
	}

	public int getSize() {
		return size;
	}


	public void moveLeft() {
		locX -= speed;
	}

	public void moveRight() {
		locX += speed;
	}

	public void moveUp() {
		locY += speed;
	}

	public void moveDown() {
		locY -= speed;
	}

	private void setLoc(double x, double y) {
		locX = x;
		locY = y;
	}

	public void jumpToDog(double x, double y) {
		setLoc(x, y);
	}

	public void jumpToCat(double x, double y) {
		setLoc(x, y);
	}

	public void expand() {	//Method for expanding the net
		if (size >= 1000) {
			System.out.println("Cannot expand anymore!");
		} else {
			size += change;
		}
	}

	public void contract() {	//Method for contracting the net
		if (size <= 50) {
			System.out.println("Cannot contract anymore!");
		} else {
			size -= change;
		}
	}

}
