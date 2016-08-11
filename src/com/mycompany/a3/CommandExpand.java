package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandExpand extends Command {
	private GameWorld gw = new GameWorld();
	public CommandExpand() {
		super("Expand");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.expand();
	}
	
	public void setTarget(GameWorld gw) {
		this.gw = gw;
	}
}
