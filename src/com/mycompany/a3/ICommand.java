package com.mycompany.a3;

import com.codename1.ui.events.ActionEvent;

public interface ICommand {
	public void actionPerformed(ActionEvent e);
	public void setTarget(GameWorld gw);
}
