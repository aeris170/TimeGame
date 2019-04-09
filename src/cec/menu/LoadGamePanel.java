package cec.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cec.chronicles.Launcher;
import cec.chronicles.input.SoundLoader;

/**
 * A panel to display save states. UNDER CONSTRUCTION.
 * 
 * @author Unsal Ozturk
 * @version 01.05.2017
 */
public class LoadGamePanel extends AbstractCECPanel {
	private JLabel info;
	private JButton start;

	public LoadGamePanel(JFrame frame, AbstractCECPanel superPanel) {
		super(frame, superPanel);
		info = new JLabel("Further implementation required...");
		add(info);
		start = new JButton("Load Last Save");
		ActionListener startListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Launcher.run();
				MenuPanel tmp = (MenuPanel) superPanel;
				tmp.enableResumeGameButton();
				start.setEnabled(false);
				frame.setVisible(false);
				fireBackButton();
				SoundLoader.menu_theme.stop();
			}
		};
		start.addActionListener(startListener);
		add(start);
		addBackButton();
		setImage("loadgame.png");
	}
}