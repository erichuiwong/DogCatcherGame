package com.mycompany.a3;

import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.mycompany.a3.Animal;
/*
 * Concrete Cat class
 */
public class Cat extends Animal{
	private int color;
	private GameWorld gw;
	public Cat() {
		super();
		color = ColorUtil.rgb(0,0,255);
	}

	public int getColor() {
		return color;
	}

	public void setColor(int newColor) {
		color = newColor;
	}

	public String toString() {
		String s = "Cat: loc=" + getLocX()
				+ "," + getLocY()
				+ " color=" + getColorString()
				+ " speed=" + getSpeed()
			    + " size=" + getSize()
			    + " dir=" + getDirection();
		return s;
	}

	@Override //Real location of cats is bit off...
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(getColor());
		int xLoc = (int) (pCmpRelPrnt.getX() + getLocX());
		int yLoc = (int) (pCmpRelPrnt.getY() + getLocY());
		int[] xPts = {xLoc, xLoc + getSize()/2, xLoc + getSize()};
		int[] yPts = {yLoc, yLoc + getSize(), yLoc};
		g.drawPolygon(xPts, yPts, 3);
		g.fillPolygon(xPts, yPts, 3);

	}
	
	public boolean isKitten() {
		boolean type = false;
		return type;
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

	}

	@Override
	public void move() {
		// TODO Auto-generated method stub

	}

}
