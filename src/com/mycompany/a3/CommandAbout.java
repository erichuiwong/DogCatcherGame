package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class CommandAbout extends Command {
	private GameWorld gw = new GameWorld();
	public CommandAbout() {
		super("About");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String s = "Eric Wong\n"
				+ "CSC 133\n"
				+ "Version a2";
		Dialog.show("About", s, "OK", null);
	}
	
	public void setTarget(GameWorld gw) {
		this.gw = gw;
	}
}
