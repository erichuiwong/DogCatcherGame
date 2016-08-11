package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandTick extends Command {
	private GameWorld gw = new GameWorld();
	public CommandTick() {
		super("Tick");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gw.tick(null);
	}

	public void setTarget(GameWorld gw) {
		this.gw = gw;
	}
}
