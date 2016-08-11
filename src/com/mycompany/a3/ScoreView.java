package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;

/*
 * Class for managing and displaying the score
 */
public class ScoreView extends Container implements Observer {
	private GameWorld gw;
	private Label tScoreLabel = new Label("Total Score:");
	private Label tDogsCapLabel = new Label("Dogs Captured:");
	private Label tCatsCapLabel = new Label("Cats Captured:");
	private Label tDogsRemLabel = new Label("Dogs Remaining:");
	private Label tCatsRemLabel = new Label("Cats Remaining:");
	private Label tSoundLabel = new Label("Sound:");
	private Label scoreLabel = new Label(" 0   ");	//Having problems showing all the digits sometimes
	private Label dogsCapLabel = new Label(" 0  ");
	private Label catsCapLabel = new Label(" 0  ");
	private Label dogsRemLabel = new Label(" 0  ");
	private Label catsRemLabel = new Label(" 0  ");
	private Label soundLabel = new Label("OFF");
	private Label[] labels = {tScoreLabel, tDogsCapLabel, tCatsCapLabel, tDogsRemLabel,
			tCatsRemLabel, tSoundLabel, scoreLabel, dogsCapLabel, catsCapLabel,
			dogsRemLabel, catsRemLabel, soundLabel};

	public ScoreView() {
		this.setLayout(new FlowLayout(Component.CENTER));
		//Set Labels to Blue

		for(int i = 0; i < labels.length; i++) {
			labels[i].getUnselectedStyle().setFgColor(ColorUtil.BLUE);
			labels[i].getUnselectedStyle().setBgTransparency(255);
			labels[i].getUnselectedStyle().setBgColor(ColorUtil.WHITE);
		}

		//Add to View
		this.add(tScoreLabel);
		this.add(scoreLabel);
		this.add(tDogsCapLabel);
		this.add(dogsCapLabel);
		this.add(tCatsCapLabel);
		this.add(catsCapLabel);
		this.add(tDogsRemLabel);
		this.add(dogsRemLabel);
		this.add(tCatsRemLabel);
		this.add(catsRemLabel);
		this.add(tSoundLabel);
		this.add(soundLabel);
	}

	@Override
	public void update(Observable observable, Object data) {
		//Update method of observer that looks for changes in data and updates labels
		gw = (GameWorld) observable;
		int score = gw.getScore();
		int dogsCaptured = gw.getDogsCaptured();
		int catsCaptured = gw.getCatsCaptured();
		int dogsRemain = gw.getDogsLeft();
		int catsRemain = gw.getCatsLeft();
		boolean sound = gw.getSound();
		this.scoreLabel.setText(score+"");
		this.dogsCapLabel.setText(dogsCaptured+"");
		this.catsCapLabel.setText(catsCaptured+"");
		this.dogsRemLabel.setText(dogsRemain+"");
		this.catsRemLabel.setText(catsRemain+"");
		if (sound) {
			this.soundLabel.setText("ON");
		} else {
			this.soundLabel.setText("OFF");
		}
	}
}
