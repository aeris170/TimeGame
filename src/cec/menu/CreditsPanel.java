package cec.menu;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A panel to display the credits.
 * 
 * @author Unsal Ozturk
 * @version 01.05.2017
 */
public class CreditsPanel extends AbstractCECPanel {
	private JLabel credits;

	public CreditsPanel(JFrame frame, AbstractCECPanel superPanel) {
		super(frame, superPanel);
		credits = new JLabel("A Game by LSEI.");
		add(credits);
		addBackButton();
		setImage("placeholderPanels.png");
	}
}