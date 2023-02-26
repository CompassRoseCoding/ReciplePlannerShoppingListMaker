package main.java.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import main.java.Main;

public class ShoppingListPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6254067984140868904L;
	static JButton[] recipebuttons;
	static LinkedList<String> removals;

	public ShoppingListPanel(LinkedList<String> list) {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		removals = new LinkedList<String>();

		JPanel listPan = new JPanel();
		listPan.setLayout(new BoxLayout(listPan, BoxLayout.PAGE_AXIS));

		JScrollPane scrollPane = new JScrollPane(listPan, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		for (int i = 0; i < list.size(); i++) {
			JCheckBox temp = new JCheckBox(list.get(i));
			final int index = i;
			temp.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(ActionEvent e) {
					removals.add(list.remove(index));
					Main.existingListPanel(list, removals);
				}
			});
			listPan.add(temp);
		}

		add(scrollPane);

		JPanel buttonPanel = new JPanel(new BorderLayout());
		buttonPanel.setMaximumSize(new Dimension(300, 40));

		JButton done = new JButton("Back");
		done.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.mainMenuPanel();
			}
		});
		done.setMinimumSize(new Dimension(300, 40));
		buttonPanel.add(done, BorderLayout.WEST);

		JButton undo = new JButton("Undo");
		undo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (removals.size() > 0) {
					list.add(removals.removeLast());
				}
				Main.existingListPanel(list, removals);
			}
		});
		undo.setMinimumSize(new Dimension(300, 40));
		buttonPanel.add(undo, BorderLayout.CENTER);

		JButton addItem = new JButton("Add Item");
		addItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addItem(list);
			}
		});
		addItem.setMinimumSize(new Dimension(300, 40));
		buttonPanel.add(addItem, BorderLayout.EAST);

		add(buttonPanel);
	}

	public ShoppingListPanel(LinkedList<String> list, LinkedList<String> rem) {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		removals = rem;

		JPanel listPan = new JPanel();
		listPan.setLayout(new BoxLayout(listPan, BoxLayout.PAGE_AXIS));

		JScrollPane scrollPane = new JScrollPane(listPan, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		for (int i = 0; i < list.size(); i++) {
			JCheckBox temp = new JCheckBox(list.get(i));
			final int index = i;
			temp.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (removals.size() > 0) {
						removals.add(list.remove(index));
					}
					Main.existingListPanel(list, removals);
				}
			});
			listPan.add(temp);
		}

		add(scrollPane);

		JPanel buttonPanel = new JPanel(new BorderLayout());
		buttonPanel.setMaximumSize(new Dimension(300, 40));

		JButton done = new JButton("Back");
		done.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.mainMenuPanel();
			}
		});
		done.setMinimumSize(new Dimension(300, 40));
		buttonPanel.add(done, BorderLayout.WEST);

		JButton undo = new JButton("Undo");
		undo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (removals.size() > 0) {
					list.add(removals.removeLast());
				}
				Main.existingListPanel(list, removals);
			}
		});
		undo.setMinimumSize(new Dimension(300, 40));
		buttonPanel.add(undo, BorderLayout.CENTER);

		JButton addItem = new JButton("Add Item");
		addItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addItem(list);
			}
		});
		addItem.setMinimumSize(new Dimension(300, 40));
		buttonPanel.add(addItem, BorderLayout.EAST);

		add(buttonPanel);
	}

	public static void addItem(LinkedList<String> list) {
		Main.addIngredientPanel(list);
	}
}
