package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandDogJump extends Command {
	private GameWorld gw = new GameWorld();
	public CommandDogJump() {
		super("JumpToADog");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.jumpToDog();
	}
	
	public void setTarget(GameWorld gw) {
		this.gw = gw;
	}
}
