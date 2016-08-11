package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandContract extends Command {
	private GameWorld gw = new GameWorld();
	public CommandContract() {
		super("Contract");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.contract();
	}
	
	public void setTarget(GameWorld gw) {
		this.gw = gw;
	}
}
