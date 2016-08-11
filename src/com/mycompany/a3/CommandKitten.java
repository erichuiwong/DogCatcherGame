package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandKitten extends Command {
	private GameWorld gw = new GameWorld();
	public CommandKitten() {
		super("Kitten");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.catCollision();
	}
	
	public void setTarget(GameWorld gw) {
		this.gw = gw;
	}
	
}
