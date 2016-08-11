package com.mycompany.a3;

import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
/*
 * Concrete Net class
 */
public class Net extends Catchers implements ICollider {
	private int color;
	public Net() {
		super();
		color = ColorUtil.rgb(192, 192, 192);	//Color of Net
	}

	public int getColor () {
		return color;
	}

	public void setColor(int newColor) {
		color = newColor;
	}

	public void jumpToDog() {
		// TODO Auto-generated method stub

	}

	public void jumpToCat() {
		// TODO Auto-generated method stub

	}

	public String toString() {
		String s = "Net: loc=" + getLocX()
		+ "," + getLocY()
		+ " color=" + getColorString()
		+ " size=" + getSize();
		return s;
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(getColor());
		int xLoc = (int) pCmpRelPrnt.getX() + (int) getLocX();
		int yLoc = (int) pCmpRelPrnt.getY() + (int) getLocY();
		g.drawRect(xLoc, yLoc, getSize()/2, getSize()/2);
		//g.fillRect(xLoc, yLoc, getSize()/2, getSize()/2);

	}

	@Override
	public boolean collidesWith(ICollider otherObject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void handleCollision(ICollider otherObject) {
		// TODO Auto-generated method stub

	}


}
