package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.CheckBox;

public class CommandSound extends Command {
	private GameWorld gw = new GameWorld();
	public CommandSound() {
		super("Sound");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (((CheckBox) e.getComponent()).isSelected()) {
			gw.setSound(true);
			
		} else {
			gw.setSound(false);
		}
		SideMenuBar.closeCurrentMenu();
	}
	
	public void setTarget(GameWorld gw) {
		this.gw = gw;
	}
}
