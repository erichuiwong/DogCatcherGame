package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.UITimer;
/*
 * Initializes the GameWorld and the layout
 */
public class Game extends Form implements Runnable {
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private UITimer timer;
	private boolean control = true;

	//Buttons
	Button expandButton = new Button("Expand");
	Button upButton = new Button("Up");
	Button leftButton = new Button("Left");
	Button jumpDogButton = new Button("JumpToADog");
	Button kittenButton = new Button("Kitten");
	Button fightButton = new Button("Fight");
	Button tickButton = new Button("Tick");
	Button contractButton = new Button("Contract");
	Button downButton = new Button("Down");
	Button rightButton = new Button("Right");
	Button jumpCatButton = new Button("JumpToACat");
	Button scoopButton = new Button("Scoop");
	Button pauseButton = new Button("Pause");
	Button healButton = new Button("Heal");
	
	//Commands
	CommandExpand expandCommand = new CommandExpand();
	CommandUp upCommand = new CommandUp();
	CommandLeft leftCommand = new CommandLeft();
	CommandDogJump dogJumpCommand = new CommandDogJump();
	CommandKitten kittenCommand = new CommandKitten();
	CommandFight fightCommand = new CommandFight();
	CommandTick tickCommand = new CommandTick();
	CommandContract contractCommand = new CommandContract();
	CommandDown downCommand = new CommandDown();
	CommandRight rightCommand = new CommandRight();
	CommandCatJump catJumpCommand = new CommandCatJump();
	CommandScoop scoopCommand = new CommandScoop();
	CommandSound soundCommand = new CommandSound();
	CommandAbout aboutCommand = new CommandAbout();
	CommandExit exitCommand = new CommandExit();
	CommandHelp helpCommand = new CommandHelp();
	CommandPause pauseCommand = new CommandPause();
	CommandHeal healCommand = new CommandHeal();
	
	public Game() {
		this.setLayout(new BorderLayout());
		this.setTitle("Dog Catcher Game");
		gw = new GameWorld();	//Create "Observable" GameWorld
		gw.init();
		mv = new MapView(gw);
		sv = new ScoreView();
		gw.addObserver(mv);
		gw.addObserver(sv);
		add(BorderLayout.NORTH, sv);
		add(BorderLayout.CENTER, mv);
		timer = new UITimer(this); //Timer
		timer.schedule(20, true, this);

		//Set Target
		expandCommand.setTarget(gw);
		upCommand.setTarget(gw);
		leftCommand.setTarget(gw);
		dogJumpCommand.setTarget(gw);
		kittenCommand.setTarget(gw);
		fightCommand.setTarget(gw);
		tickCommand.setTarget(gw);
		contractCommand.setTarget(gw);
		downCommand.setTarget(gw);
		rightCommand.setTarget(gw);
		catJumpCommand.setTarget(gw);
		scoopCommand.setTarget(gw);
		soundCommand.setTarget(gw);
		aboutCommand.setTarget(gw);
		exitCommand.setTarget(gw);
		helpCommand.setTarget(gw);
		pauseCommand.setTarget(this);
		healCommand.setTarget(gw);

		//Setting each button to a command
		expandButton.setCommand(expandCommand);
		upButton.setCommand(upCommand);
		leftButton.setCommand(leftCommand);
		jumpDogButton.setCommand(dogJumpCommand);
		kittenButton.setCommand(kittenCommand);
		fightButton.setCommand(fightCommand);
		contractButton.setCommand(contractCommand);
		downButton.setCommand(downCommand);
		rightButton.setCommand(rightCommand);
		jumpCatButton.setCommand(catJumpCommand);
		tickButton.setCommand(tickCommand);
		scoopButton.setCommand(scoopCommand);
		pauseButton.setCommand(pauseCommand);
		healButton.setCommand(healCommand);

		//KeyListeners
		addKeyListener('x', exitCommand);
		addKeyListener('e', expandCommand);
		addKeyListener('c', contractCommand);
		addKeyListener('s', scoopCommand);
		addKeyListener('r', rightCommand);
		addKeyListener('l', leftCommand);
		addKeyListener('u', upCommand);
		addKeyListener('d', downCommand);
		addKeyListener('o', dogJumpCommand);
		addKeyListener('a', catJumpCommand);
		addKeyListener('k', kittenCommand);
		addKeyListener('f', fightCommand);
		addKeyListener('t', tickCommand);

		//Toolbar
		Toolbar toolbar = new Toolbar();
		this.setToolbar(toolbar);
		CheckBox cb = new CheckBox("Sound");
		cb.setCommand(soundCommand);
		cb.getUnselectedStyle().setBgTransparency(255);
		cb.getUnselectedStyle().setBgColor(ColorUtil.LTGRAY);
		soundCommand.putClientProperty("SideComponent", cb);
		toolbar.addCommandToSideMenu(scoopCommand);
		toolbar.addCommandToSideMenu(soundCommand);
		toolbar.addCommandToSideMenu(aboutCommand);
		toolbar.addCommandToSideMenu(exitCommand);
		toolbar.addCommandToRightBar(helpCommand);

		//Left Container
		Container leftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		leftContainer.getAllStyles().setPadding(Component.TOP, 50);
		//Expand Button
		expandButton.getUnselectedStyle().setBgTransparency(255);
		expandButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		expandButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		expandButton.getAllStyles().setPadding(Component.TOP, 5);
		expandButton.getAllStyles().setPadding(Component.BOTTOM, 5);
		leftContainer.add(expandButton);
		//Up Button
		upButton.getUnselectedStyle().setBgTransparency(255);
		upButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		upButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		upButton.getAllStyles().setPadding(Component.TOP, 5);
		upButton.getAllStyles().setPadding(Component.BOTTOM, 5);
		leftContainer.add(upButton);
		//Left Button
		leftButton.getUnselectedStyle().setBgTransparency(255);
		leftButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		leftButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		leftButton.getAllStyles().setPadding(Component.TOP, 5);
		leftButton.getAllStyles().setPadding(Component.BOTTOM, 5);
		leftContainer.add(leftButton);
		//JumpToADog Button
		jumpDogButton.getUnselectedStyle().setBgTransparency(255);
		jumpDogButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		jumpDogButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		jumpDogButton.getAllStyles().setPadding(Component.TOP, 5);
		jumpDogButton.getAllStyles().setPadding(Component.BOTTOM, 5);
		leftContainer.add(jumpDogButton);
		add(BorderLayout.WEST, leftContainer);

		//Right Container
		Container rightContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		rightContainer.getAllStyles().setPadding(Component.TOP, 50);
		//Contract Button
		contractButton.getUnselectedStyle().setBgTransparency(255);
		contractButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		contractButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		contractButton.getAllStyles().setPadding(Component.TOP, 5);
		contractButton.getAllStyles().setPadding(Component.BOTTOM, 5);
		rightContainer.add(contractButton);
		//Down Button
		downButton.getUnselectedStyle().setBgTransparency(255);
		downButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		downButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		downButton.getAllStyles().setPadding(Component.TOP, 5);
		downButton.getAllStyles().setPadding(Component.BOTTOM, 5);
		rightContainer.add(downButton);
		//Right Button
		rightButton.getUnselectedStyle().setBgTransparency(255);
		rightButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		rightButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		rightButton.getAllStyles().setPadding(Component.TOP, 5);
		rightButton.getAllStyles().setPadding(Component.BOTTOM, 5);
		rightContainer.add(rightButton);
		//JumpToACat Button
		jumpCatButton.getUnselectedStyle().setBgTransparency(255);
		jumpCatButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		jumpCatButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		jumpCatButton.getAllStyles().setPadding(Component.TOP, 5);
		jumpCatButton.getAllStyles().setPadding(Component.BOTTOM, 5);
		rightContainer.add(jumpCatButton);
		//Scoop Button
		scoopButton.getUnselectedStyle().setBgTransparency(255);
		scoopButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		scoopButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		scoopButton.getAllStyles().setPadding(Component.TOP, 5);
		scoopButton.getAllStyles().setPadding(Component.BOTTOM, 5);
		rightContainer.add(scoopButton);
		add(BorderLayout.EAST, rightContainer);

		Container bottomContainer = new Container(new FlowLayout(Component.CENTER));
		//bottomContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.GRAY));
		//Kitten Button
		kittenButton.getUnselectedStyle().setBgTransparency(255);
		kittenButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		kittenButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		kittenButton.getAllStyles().setPadding(Component.TOP, 5);
		kittenButton.getAllStyles().setPadding(Component.BOTTOM, 5);
		kittenButton.getAllStyles().setPadding(Component.LEFT, 5);
		kittenButton.getAllStyles().setPadding(Component.RIGHT, 5);
		//bottomContainer.add(kittenButton);
		//Fight Button
		fightButton.getUnselectedStyle().setBgTransparency(255);
		fightButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		fightButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		fightButton.getAllStyles().setPadding(Component.TOP, 5);
		fightButton.getAllStyles().setPadding(Component.BOTTOM, 5);
		fightButton.getAllStyles().setPadding(Component.LEFT, 5);
		fightButton.getAllStyles().setPadding(Component.RIGHT, 5);
		//bottomContainer.add(fightButton);
		//Tick Button
		tickButton.getUnselectedStyle().setBgTransparency(255);
		tickButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		tickButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		tickButton.getAllStyles().setPadding(Component.TOP, 5);
		tickButton.getAllStyles().setPadding(Component.BOTTOM, 5);
		tickButton.getAllStyles().setPadding(Component.LEFT, 5);
		tickButton.getAllStyles().setPadding(Component.RIGHT, 5);
		//bottomContainer.add(tickButton);
		//Pause Button
		pauseButton.getUnselectedStyle().setBgTransparency(255);
		pauseButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		pauseButton.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		pauseButton.getAllStyles().setPadding(Component.TOP, 5);
		pauseButton.getAllStyles().setPadding(Component.BOTTOM, 5);
		pauseButton.getAllStyles().setPadding(Component.LEFT, 5);
		pauseButton.getAllStyles().setPadding(Component.RIGHT, 5);
		bottomContainer.add(pauseButton);
		//HealButton
		healButton.getUnselectedStyle().setBgTransparency(255);
		healButton.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		healButton.getUnselectedStyle().setBgColor(ColorUtil.GRAY);
		healButton.getAllStyles().setPadding(Component.TOP, 5);
		healButton.getAllStyles().setPadding(Component.BOTTOM, 5);
		healButton.getAllStyles().setPadding(Component.LEFT, 5);
		healButton.getAllStyles().setPadding(Component.RIGHT, 5);
		healButton.setEnabled(false);
		bottomContainer.add(healButton);

		add(BorderLayout.SOUTH, bottomContainer);

		this.show();
		//play();
	}

	/*private void play() {
		Label myLabel = new Label("Enter a Command:");
		this.add(myLabel);
		final TextField myTextField = new TextField();
		this.add(myTextField);
		this.show();

		myTextField.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				String sCommand = myTextField.getText().toString();
				myTextField.clear();
				if (sCommand.length() != 0) {		//Checks if input is empty
					switch (sCommand.charAt(0)) {	//Commands for player
						case 'e':
							gw.expand();
							break;
						case 'c':
							gw.contract();
							break;
						case 's':
							gw.scoop();
							break;
						case 'r':
							gw.moveRight();
							break;
						case 'l':
							gw.moveLeft();
							break;
						case 'u':
							gw.moveUp();
							break;
						case 'd':
							gw.moveDown();
							break;
						case 'o':
							gw.jumpToDog();
							break;
						case 'a':
							gw.jumpToCat();
							break;
						case 'k':
							gw.catCollision();
							break;
						case 'f':
							gw.causeFight();
							break;
						case 't':
							gw.tick();
							break;
						case 'p':
							gw.printPoints();
							break;
						case 'm':
							gw.map();
							break;
						case 'q':
							if(!Dialog.show("Check Quit", "Are you sure?", "No", "Yes")) {	//Prompts the user to quit or not
								gw.quit();
							}
							break;
						default:
							System.out.println("Invalid Input. Please try again."); //Checks for any invalid input
							break;
					} //switch
				} //if
			} //actionPerfomed
		} //new ActionListener()
		); //addActionListener
	} //play*/

	@Override
	public void run() {
		Dimension dCmpSize = new Dimension (mv.getWidth(), mv.getHeight());
		gw.tick(dCmpSize);
	}

	public void start() {
		timer.schedule(20, true, this);
	}

	public void cancel() {
		timer.cancel();
	}
	
	public void control(boolean control) {
		if (!control) {
			contractButton.setEnabled(false);
			expandButton.setEnabled(false);
			upButton.setEnabled(false);
			leftButton.setEnabled(false);
			rightButton.setEnabled(false);
			downButton.setEnabled(false);
			scoopButton.setEnabled(false);
			healButton.setEnabled(true);
			contractCommand.setEnabled(false);
			expandCommand.setEnabled(false);
			upCommand.setEnabled(false);
			leftCommand.setEnabled(false);
			rightCommand.setEnabled(false);
			downCommand.setEnabled(false);
			scoopCommand.setEnabled(false);
			healCommand.setEnabled(true);
		} else {
			contractButton.setEnabled(true);
			expandButton.setEnabled(true);
			upButton.setEnabled(true);
			leftButton.setEnabled(true);
			rightButton.setEnabled(true);
			downButton.setEnabled(true);
			scoopButton.setEnabled(true);
			healButton.setEnabled(false);
			contractCommand.setEnabled(true);
			expandCommand.setEnabled(true);
			upCommand.setEnabled(true);
			leftCommand.setEnabled(true);
			rightCommand.setEnabled(true);
			downCommand.setEnabled(true);
			scoopCommand.setEnabled(true);
			healCommand.setEnabled(true);
		}
	}

}
