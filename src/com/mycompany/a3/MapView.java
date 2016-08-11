package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Graphics;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer {
	private GameWorld gw;
	//private int iPx = 0;
	//private int iPy = 0;

	public MapView(GameWorld gw) {
		this.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLUE));
		this.gw = gw;
	}

	public void update(Observable observable, Object data) {
		gw = (GameWorld) observable;
		if (gw.getDogsLeft() <= 0) {
			if (Dialog.show("Game Over", "Final Score: " + gw.getScore(), "Exit" , null)) {
				System.exit(0);
			}
		}
		this.repaint();
	}

	@Override
	public void paint(Graphics g) {
		// Iterate through each game object and draw them, Dogs = circle, Cats =
		// Triangle, Net = Square
		super.paint(g);
		GameObjectCollection objectCollection = gw.getObjects();
		IIterator theElements = objectCollection.getIterator();
		Point pCmpRelPrnt = new Point(getX(), getY());
		while (theElements.hasNext()) {
			GameObject go = (GameObject) theElements.getNext();
			go.draw(g, pCmpRelPrnt);
		}
		/*g.setColor(ColorUtil.BLACK);
		g.drawRect(iPx-getParent().getAbsoluteX(), iPy-getParent().getAbsoluteY(), 20, 40);
		g.fillRect(iPx, iPy, 20, 40);*/
	}

	@Override
	public void pointerPressed(int x, int y) {
		x = x - getParent().getAbsoluteX();
		y = y - getParent().getAbsoluteY();
		Point pPtrRelPrnt = new Point(x, y);
		Point pCmpRelPrnt = new Point(getX(), getY());
		GameObjectCollection objectCollection = gw.getObjects();
		IIterator theElements = objectCollection.getIterator();
		while (theElements.hasNext()) {
			GameObject go = (GameObject) theElements.getNext();
			if (go instanceof ISelectable) {
				if (((ISelectable) go).contains(pPtrRelPrnt, pCmpRelPrnt)) {
					((ISelectable) go).setSelected(true);
				} else {
					((ISelectable) go).setSelected(false);
				}
			}
		}
		//iPx = x;
		//iPy = y;
		repaint();
	}

}