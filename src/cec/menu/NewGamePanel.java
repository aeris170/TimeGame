package cec.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

import cec.chronicles.Launcher;
import cec.chronicles.input.SoundLoader;

/**
 * Defines a custom panel for starting a new game. UNDER CONSTRUCTION
 * 
 * @author Unsal Ozturk
 * @version 01.05.2017
 */
public class NewGamePanel extends AbstractCECPanel {
	private JLabel info;
	private JButton start;
	private JTextField selectName;
	private JButton selectSkin;

	private int xPlayer;
	private int yPlayer;
	private int skinSelection;
	private String playerName;

	public NewGamePanel(JFrame frame, AbstractCECPanel superPanel) {
		super(frame, superPanel);
		info = new JLabel("Further implementation required...");
		start = new JButton("Start");
		selectName = new JTextField("Enter your name here.");
		selectSkin = new JButton("Change skin!");
		ActionListener startListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Launcher.run();
				MenuPanel tmp = (MenuPanel) superPanel;
				tmp.enableResumeGameButton();
				start.setEnabled(false);
				frame.setVisible(false);
				try {
					setPlayerName(selectName.getText());
					createPlayer();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				fireBackButton();
				SoundLoader.menu_theme.stop();
			}
		};
		start.addActionListener(startListener);

		ActionListener nameListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setPlayerName(selectName.getText());
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		};
		selectName.addActionListener(nameListener);

		ActionListener selectSkinListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					setSkinSelection((skinSelection + 1) % 2);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		};
		selectSkin.addActionListener(selectSkinListener);

		xPlayer = 100;
		yPlayer = 400;
		skinSelection = 0;
		playerName = "Denver";

		int x = frame.getX();
		int y = frame.getY();

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(x / 15)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(start, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(selectName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(selectSkin, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, x / 7,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(getBackButton(), Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(x / 15)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(y / 3)
				.addComponent(start, GroupLayout.PREFERRED_SIZE, y / 25, GroupLayout.PREFERRED_SIZE).addGap(y / 15)
				.addComponent(selectName, GroupLayout.PREFERRED_SIZE, y / 25, GroupLayout.PREFERRED_SIZE).addGap(y / 15)
				.addComponent(selectSkin, GroupLayout.PREFERRED_SIZE, y / 25, GroupLayout.PREFERRED_SIZE).addGap(y / 15)
				.addComponent(getBackButton(), GroupLayout.PREFERRED_SIZE, y / 25, GroupLayout.PREFERRED_SIZE)
				.addGap(y / 4)));
		setLayout(groupLayout);
		setImage("newgame.png");
	}

	private void createPlayer() throws FileNotFoundException {
		PrintWriter writer = new PrintWriter("save.txt");
		writer.print(xPlayer + " " + yPlayer + " " + skinSelection + " " + playerName);
		writer.println();
		writer.println("800000000");
		writer.println("0");
		writer.close();
	}

	private void setPlayerName(String s) throws FileNotFoundException {
		playerName = s;
		createPlayer();
	}

	private void setSkinSelection(int i) throws FileNotFoundException {
		skinSelection = i;
		createPlayer();
	}
}