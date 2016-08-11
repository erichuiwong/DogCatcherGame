package com.mycompany.a3;
/*
 * Abstract class that contains all the GameObjects
 */
import com.codename1.charts.util.ColorUtil;
public abstract class GameObject implements IDrawable{
	//Attributes and behaviors
	private int color;
	private int size;
	private int speed;
	private int direction;

	public GameObject() {
	}

	public int getColor() {
		return color;
	}

	public void setColor(int newColor) {
		color = newColor;
	}

	public int setSize(int newSize) {
		return size = newSize;
	}

	public int getRadius() { //Sides of the object
		return size/2;
	}

	public int getSpeed() {
		return speed;
	}

	public int getDirection() {
		return direction;
	}

	protected String getColorString() {
		String myDesc = "[" + ColorUtil.red(getColor()) + ","
							+ ColorUtil.green(getColor()) + ","
							+ ColorUtil.blue(getColor()) + "]";
		return myDesc;
	}
}
