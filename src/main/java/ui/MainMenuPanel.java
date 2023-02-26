package main.java.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.java.Main;

public class MainMenuPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6254067984140868904L;
	static JButton[] recipebuttons;

	public MainMenuPanel() {
		setLayout(new GridLayout(3, 1, 0, 0));
		JButton recipeB = new JButton("Recipes");
		recipeB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.recipeListPanel();
			}
		});
		add(recipeB);

		JButton plannerB = new JButton("Planner");
		plannerB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.plannerPanel();
			}
		});
		add(plannerB);

		JButton listB = new JButton("Shopping List");
		listB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.shoppingListPanel();
			}
		});
		add(listB);
		validate();
	}
}
