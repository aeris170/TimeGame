package cec.chronicles.popup;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;

/**
 * Easter egg
 * 
 * @author Unsal Öztürk
 * @version 13.05.2017
 */
public class EasterEggPopup extends AbstractCECPopup {

	private JLabel congratsLabel1;
	private JLabel congratsLabel2;
	private JLabel congratsLabel3;
	private JPanel innerPanel;
	private JButton button;

	private static final long serialVersionUID = -5044163730859720661L;

	/**
	 * Constructor
	 * 
	 * @param frame
	 */
	public EasterEggPopup(JFrame frame) {
		super(frame);
		button = new JButton("Yay!");
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		};
		button.addActionListener(listener);
		setPreferredSize(new Dimension(1200, 650));
		congratsLabel1 = new JLabel("You decide not to steal the design choices.");
		congratsLabel1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		congratsLabel2 = new JLabel("Thou art a good soul.");
		congratsLabel2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		congratsLabel3 = new JLabel("Game Over. You'll make an incomplete computer engineer.");
		congratsLabel3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		innerPanel = new JPanel();
		setLayout(new BorderLayout());
		add(innerPanel, BorderLayout.CENTER);
		GroupLayout gl_innerPanel = new GroupLayout(innerPanel);
		gl_innerPanel.setHorizontalGroup(gl_innerPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_innerPanel
				.createSequentialGroup()
				.addGroup(gl_innerPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_innerPanel.createSequentialGroup().addGap(349).addComponent(congratsLabel1))
						.addGroup(gl_innerPanel.createSequentialGroup().addGap(431).addComponent(congratsLabel2))
						.addGroup(gl_innerPanel.createSequentialGroup().addGap(325).addComponent(congratsLabel3))
						.addGroup(gl_innerPanel.createSequentialGroup().addGap(499).addComponent(button)))
				.addContainerGap(439, Short.MAX_VALUE)));
		gl_innerPanel.setVerticalGroup(gl_innerPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_innerPanel.createSequentialGroup().addGap(257).addComponent(congratsLabel1)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(congratsLabel2)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(congratsLabel3)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(button).addGap(276)));
		innerPanel.setLayout(gl_innerPanel);
	}

}
