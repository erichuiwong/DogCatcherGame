package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandUp extends Command {
	private GameWorld gw = new GameWorld();
	public CommandUp() {
		super("Up");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.moveUp();
	}
	
	public void setTarget(GameWorld gw) {
		this.gw = gw;
	}
}
