package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.mycompany.a3.Animal;
/*
 * Concrete class for Dogs
 */
public class Dog extends Animal implements ISelectable{
	private int scratches;
	private int color;
	private int value = 10;
	private int[] colors = {ColorUtil.rgb(127, 255, 0), ColorUtil.rgb(0, 255, 0), ColorUtil.rgb(34, 139, 34), ColorUtil.rgb(0, 128, 0), ColorUtil.rgb(0,100,0), ColorUtil.rgb(0, 0, 0)}; //Array containing colors
	private boolean isSelected;

	public Dog() {
		super();
		scratches = 0;
		color = colors[0];
	}

	public int getScratches() {
		return scratches;
	}

	public void setScratches(int newScratch) {
		if (scratches >= 5) {		//Prevents over 5 scratches
			scratches = 5;
		} else {
			scratches = newScratch;
			setColor(colors[scratches]); //Changes the color of the dog from each scratch
		}
	}

	public int getColor() {
		return color;
	}

	public void setColor(int newColor) {
		color = newColor;
	}

	protected int getScore() {
		return value - scratches;
	}

	public String toString() {
		String s = "Dog: loc=" + getLocX()
				+ "," + getLocY()
				+ " color=" + getColorString()
				+ " speed=" + getSpeed()
			    + " size=" + getSize()
			    + " dir=" + getDirection()
			    + " scratches=" + getScratches();
		return s;
	}


	@Override
	public void setSelected(boolean yesNo) {
		isSelected = yesNo;

	}

	@Override
	public boolean isSelected() {
		return isSelected;
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(getColor());
		int xLoc = (int) (pCmpRelPrnt.getX() + getLocX());
		int yLoc = (int) (pCmpRelPrnt.getY() + getLocY());
		if (isSelected()) {
			g.drawArc(xLoc, yLoc, getSize(), getSize(), 0, 360);
		} else {
			g.drawArc(xLoc, yLoc, getSize(), getSize(), 0, 360);
			g.fillArc(xLoc, yLoc, getSize(), getSize(), 0, 360);
		}

	}

	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		int px = pPtrRelPrnt.getX();
		int py = pPtrRelPrnt.getY();
		int locX = (int) (pCmpRelPrnt.getX() + getLocX());
		int locY = (int) (pCmpRelPrnt.getY() + getLocY());
		if ((px >= locX) && (px <= locX + getSize()) && (py >= locY) && (py <= locY + getSize())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean collidesWith(ICollider otherObject) {
		boolean result = false;
		int thisCenterX = (int) (this.getLocX() + getRadius());
		int thisCenterY = (int) (this.getLocY() + getRadius());
		int otherCenterX = (int) ((Animal) otherObject).getLocX();
		int otherCenterY = (int) ((Animal) otherObject).getLocY();

		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;
		int distBetweenCentersSqr = (dx*dx + dy*dy);
		int thisRadius = getRadius();
		int otherRadius = ((Animal) otherObject).getRadius();
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius + otherRadius*otherRadius);

		if (distBetweenCentersSqr <= radiiSqr) {
			result = true;
		}

		return result;
	}

	@Override
	public void handleCollision(ICollider otherObject) {
		this.setScratches(this.getScratches() + 1);
		this.setSpeed(this.getSpeed() - 1);
	}

	@Override
	public void move() {

	}
}
