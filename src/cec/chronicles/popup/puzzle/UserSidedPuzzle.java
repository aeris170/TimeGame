package cec.chronicles.popup.puzzle;

import javax.swing.*;

import cec.chronicles.popup.AbstractCECPopup;
import cec.chronicles.states.GameState;

import java.awt.event.*;
import java.awt.*;

/**
 * User sided puzzle
 * 
 * @author Pýnar Ayaz
 * @version 07.5.2017
 */
public class UserSidedPuzzle extends AbstractCECPopup {

	private static final long serialVersionUID = -5119966096125093013L;
	private JTextArea text;
	private JTextArea text2;
	private JTextField answerField;
	private String answer;
	private JButton exitButton;

	public UserSidedPuzzle(JFrame frame, String answer) {
		super(frame);
		this.answer = answer;
		text = new JTextArea(
				"Professor should begin his long and glorious programming life with the traditional “Hello World” program. \nComplete the code below to print the sentence “Hello World!”.");
		text.setEditable(false);
		text2 = new JTextArea(
				"public class Hello\n{\n        public static void main(String[] args)\n        {\n                //Complete this line\n        }\n}");
		text2.setEditable(false);
		answerField = new JTextField("Enter your answer here.");
		text.setFont(new Font("sugoi ui", Font.PLAIN, 20));
		text2.setFont(new Font("Consolas", Font.PLAIN, 20));
		answerField.setFont(new Font("Consolas", Font.PLAIN, 20));

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		exitButton = new JButton("Done!");
		exitButton.setEnabled(false);
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				disposePanel();
			}
		};
		exitButton.addActionListener(listener);
		answerField.addActionListener(new FieldListener());
		add(text);
		add(text2);
		add(answerField);
		add(exitButton);
	}

	public void checkCompletion() {
		if (GameState.player.getPuzzleCompletion(3)) {
			exitButton.setEnabled(true);
		}
	}

	private void checkAnswer() {
		if (answerField.getText().equals(answer)) {
			answerField.setText("Correct! Press done to exit.");
			answerField.setEnabled(false);
			exitButton.setEnabled(true);
		}

		else {
			System.out.println(answerField.getText());
			answerField.setText("Your answer was wrong");
			// System.exit(0); // remove later
		}
	}

	private class FieldListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			checkAnswer();
		}
	}
}