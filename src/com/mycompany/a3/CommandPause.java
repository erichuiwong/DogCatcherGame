package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandPause extends Command {
	private GameWorld gw = new GameWorld();
	private Game game;
	private boolean toggle;
	public CommandPause() {
		super("Pause");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		toggle = !toggle;
		if (toggle) {
			game.cancel();
			game.control(false);
			game.pauseButton.setText("Play");
		} else {
			game.start();
			game.control(true);
			game.pauseButton.setText("Pause");
		}
	}

	public void setTarget(Game game) {
		this.game = game;

	}
}
