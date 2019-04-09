package cec.menu;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import cec.chronicles.Launcher;
import cec.chronicles.states.GameState;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;

/**
 * Defines a menu panel, which has references to all submenus.
 * 
 * @author Unsal Ozturk
 * @version 01.05.2017
 */
public class MenuPanel extends AbstractCECPanel {

	private JButton resumeGameButton;
	private JButton newGameButton;
	private JButton loadGameButton;
	private JButton settingsButton;
	private JButton creditsButton;
	private JButton quitButton;

	private NewGamePanel newGameMenu;
	private LoadGamePanel loadGameMenu;
	private SettingsPanel settingsMenu;
	private CreditsPanel creditsMenu;

	private Timer timer;

	public MenuPanel(JFrame frame, int x, int y) {
		super(frame, null);
		newGameMenu = new NewGamePanel(frame, this);
		loadGameMenu = new LoadGamePanel(frame, this);
		settingsMenu = new SettingsPanel(frame, this, x, y);
		creditsMenu = new CreditsPanel(frame, this);

		MenuListener newGameListener = new MenuListener(this, newGameMenu);
		MenuListener loadGameListener = new MenuListener(this, loadGameMenu);
		MenuListener settingsListener = new MenuListener(this, settingsMenu);
		MenuListener creditsListener = new MenuListener(this, creditsMenu);

		ActionListener resumeListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Launcher.game.getDisplay().getFrame().setAlwaysOnTop(true);
				Launcher.game.getDisplay().getFrame().setVisible(true);
				Launcher.game.start();
			}
		};

		ActionListener quitListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setAlwaysOnTop(false);
				int tmp = JOptionPane.showConfirmDialog(null, "Would you like to quit?");
				if (tmp == 0) {
					if (GameState.player != null) {
						GameState.player.writePlayer();
					}
					System.exit(0);
				} else {
					frame.setAlwaysOnTop(true);
				}
			}
		};

		resumeGameButton = new JButton("Resume Game");
		resumeGameButton.addActionListener(resumeListener);
		resumeGameButton.setEnabled(false);

		newGameButton = new JButton("New Game");
		newGameButton.addActionListener(newGameListener);

		loadGameButton = new JButton("Load Game");
		loadGameButton.addActionListener(loadGameListener);

		creditsButton = new JButton("Credits");
		creditsButton.addActionListener(creditsListener);

		settingsButton = new JButton("Settings");
		settingsButton.addActionListener(settingsListener);

		quitButton = new JButton("Quit");
		quitButton.addActionListener(quitListener);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(x / 15)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(quitButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(creditsButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(settingsButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(loadGameButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(newGameButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(resumeGameButton, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, x / 7,
										GroupLayout.PREFERRED_SIZE))
						.addGap(x / 15)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(y / 4)
						.addComponent(resumeGameButton, GroupLayout.PREFERRED_SIZE, y / 25, GroupLayout.PREFERRED_SIZE)
						.addGap(y / 15)
						.addComponent(newGameButton, GroupLayout.PREFERRED_SIZE, y / 25, GroupLayout.PREFERRED_SIZE)
						.addGap(y / 15)
						.addComponent(loadGameButton, GroupLayout.PREFERRED_SIZE, y / 25, GroupLayout.PREFERRED_SIZE)
						.addGap(y / 15)
						.addComponent(settingsButton, GroupLayout.PREFERRED_SIZE, y / 25, GroupLayout.PREFERRED_SIZE)
						.addGap(y / 15)
						.addComponent(creditsButton, GroupLayout.PREFERRED_SIZE, y / 25, GroupLayout.PREFERRED_SIZE)
						.addGap(y / 15)
						.addComponent(quitButton, GroupLayout.PREFERRED_SIZE, y / 25, GroupLayout.PREFERRED_SIZE)
						.addGap(y / 15)));
		setLayout(groupLayout);
		setAnimation(new MenuAnimation(300, 5, "menu", this));
	}

	public SettingsPanel getSettingsPanel() {
		return settingsMenu;
	}

	public void enableResumeGameButton() {
		resumeGameButton.setEnabled(true);
	}
}