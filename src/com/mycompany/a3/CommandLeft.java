package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandLeft extends Command {
	private GameWorld gw = new GameWorld();
	public CommandLeft() {
		super("Left");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.moveLeft();
	}
	
	public void setTarget(GameWorld gw) {
		this.gw = gw;
	}
}
