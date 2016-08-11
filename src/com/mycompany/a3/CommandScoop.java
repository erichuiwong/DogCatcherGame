package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandScoop extends Command {
	private GameWorld gw = new GameWorld();
	public CommandScoop() {
		super("Scoop");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.scoop();
	}
	
	public void setTarget(GameWorld gw) {
		this.gw = gw;
	}
}
