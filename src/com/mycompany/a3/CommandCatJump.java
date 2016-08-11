package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandCatJump extends Command {
	private GameWorld gw = new GameWorld();
	public CommandCatJump() {
		super("JumpToACat");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.jumpToCat();
	}
	
	public void setTarget(GameWorld gw) {
		this.gw = gw;
	}
}
