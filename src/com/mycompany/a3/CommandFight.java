package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandFight extends Command {
	private GameWorld gw = new GameWorld();
	
	public CommandFight() {
		super("Fight");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.causeFight();
	}

	public void setTarget(GameWorld gw) {
		this.gw = gw;
	}
}
