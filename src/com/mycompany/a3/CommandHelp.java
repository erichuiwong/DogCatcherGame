package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;

public class CommandHelp extends Command {
	private GameWorld gw = new GameWorld();
	public CommandHelp() {
		super("Help?");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String s = "e - expand the net\n"
				+ "c - contract the net\n"
				+ "s - scoop animals\n"
				+ "r - move net right\n"
				+ "u - move net up\n"
				+ "d - move net down\n"
				+ "a - move net to a cat\n"
				+ "k - produce a kitten\n"
				+ "f - a cat-dog fight\n"
				+ "t - clock ticks\n"
				+ "x - exit game";
		Dialog.show("Commands", s, "OK", null);
	}
	
	public void setTarget(GameWorld gw) {
		this.gw = gw;
	}
}
