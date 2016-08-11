package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandRight extends Command {
	private GameWorld gw = new GameWorld();
	public CommandRight() {
		super("Right");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.moveRight();
	}
	
	public void setTarget(GameWorld gw) {
		this.gw = gw;
	}
}
