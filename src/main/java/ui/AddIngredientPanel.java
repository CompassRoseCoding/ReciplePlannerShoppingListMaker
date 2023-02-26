package main.java.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import main.java.Main;
import main.java.objects.ShoppingList;

public class AddIngredientPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6254067984140868904L;
	static JButton[] recipebuttons;
	
	public AddIngredientPanel() {
		setLayout(new BorderLayout());

		JLabel ingredientsLabel = new JLabel("Items to add:");
		add(ingredientsLabel);

		JTextArea ingredientsArea = new JTextArea(5, 20);
		JScrollPane scrollPane1 = new JScrollPane(ingredientsArea);
		ingredientsArea.setEditable(true);
		add(scrollPane1);

		JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 0, 0));
		buttonPanel.setMaximumSize(new Dimension(300, 40));
		
		JButton mainmenu = new JButton("Back");
		mainmenu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.mainMenuPanel();
			}
		});
		mainmenu.setMinimumSize(new Dimension(100, 40));
		buttonPanel.add(mainmenu);
		
		JButton itemadd = new JButton("Add Items");
		itemadd.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShoppingList mylist = new ShoppingList();
				if (ingredientsArea.getText().length() > 1) {
					mylist.addItems(ingredientsArea.getText().split("\n"));
				}
				Main.existingListPanel(mylist.getComplexList());
			}
		});
		itemadd.setVisible(true);
		buttonPanel.add(itemadd);
		add(buttonPanel, BorderLayout.SOUTH);
	}
}
