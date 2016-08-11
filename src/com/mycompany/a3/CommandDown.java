package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandDown extends Command {
	private GameWorld gw = new GameWorld();
	public CommandDown() {
		super("Down");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.moveDown();
	}
	
	public void setTarget(GameWorld gw) {
		this.gw = gw;
	}
}
