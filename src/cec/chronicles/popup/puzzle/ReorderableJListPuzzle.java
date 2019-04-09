package cec.chronicles.popup.puzzle;

import java.awt.Dimension;
import java.awt.Font;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import cec.chronicles.popup.AbstractCECPopup;
import cec.chronicles.states.GameState;

/**
 * Drag and drop puzzle MK.II
 * 
 * @author Pýnar Ayaz
 * @version 06.5.2017
 */
public class ReorderableJListPuzzle extends AbstractCECPopup {

	private static final long serialVersionUID = -3015263295844481249L;
	private ReorderableJList list;

	public ReorderableJListPuzzle(JFrame frame) {
		super(frame);
		list = new ReorderableJList(this);
		DefaultListModel defModel = new DefaultListModel();
		list.setModel(defModel);
		String[] listItems = { "Take out new batteries from pocket", "Pick up the remote",
				"Place the new batteries inside the battery holder", "Take the dead batteries out",
				"Remove the lid on the back", "Put the lid back on and secure it",
				"Make sure batteries are facing the right ways polarwise" };

		// RANDOMLY MIX UP THESE GUYS
		java.util.List<String> strList = Arrays.asList(listItems);
		Collections.shuffle(strList);
		listItems = strList.toArray(new String[strList.size()]);

		Iterator it = Arrays.asList(listItems).iterator();
		while (it.hasNext())
			defModel.addElement(it.next());
		// show list
		JScrollPane scroller = new JScrollPane(list, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		list.setFont(new Font("Calibri", Font.BOLD, 36));
		scroller.setPreferredSize(new Dimension(1200, 800));
		add(list);
		add(getDoneButton());
	}

	public void checkCompletion() {
		if (GameState.player.getPuzzleCompletion(3)) {
			enableBackButton();
		}
	}
}