package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandHeal extends Command {
	private GameWorld gw = new GameWorld();
	public CommandHeal() {
		super("Heal");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.healDog();
	}
	
	public void setTarget(GameWorld gw) {
		this.gw = gw;
		
	}
}
