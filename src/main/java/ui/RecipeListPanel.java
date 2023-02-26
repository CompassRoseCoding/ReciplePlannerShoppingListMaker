package main.java.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import main.java.Main;
import main.java.io.ReadFile;
import main.java.io.WriteFile;
import main.java.objects.Planner;

public class RecipeListPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6254067984140868904L;
	static JButton[] recipebuttons;
	static boolean recipeDelete;
	static boolean getRecipePlan;
	
	public RecipeListPanel(boolean plan, int index) {
		setLayout(new BorderLayout());
		recipeDelete = false;
		getRecipePlan = plan;

		JPanel listPanel = new JPanel(new GridLayout(7, 1, 0, 0));
		listPanel.setMinimumSize(new Dimension(300, 500));
		listPanel.setMaximumSize(new Dimension(300, 500));

		if (getRecipePlan) {
			JButton planRecipe = new JButton("Not Cooking");
			planRecipe.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(ActionEvent e) {
						(new Planner()).clearEntry(0);
						Main.plannerPanel();
				}
			});
			listPanel.add(planRecipe);
		}

		String[] savedRecipes = ReadFile.getArchivedRecipes();

		recipebuttons = new JButton[savedRecipes.length];
		
		for (int i = 0; i < savedRecipes.length; i++) {
			JButton temp = new JButton(savedRecipes[i].replace("_", " "));
			final String title = savedRecipes[i];
			temp.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(ActionEvent e) {
					recipeButton(title, index);
				}
			});
			temp.setMinimumSize(new Dimension(300, 40));
			listPanel.add(temp);
			recipebuttons[i] = temp;
		}

		JScrollPane scrollPane = new JScrollPane(listPanel);
		add(scrollPane, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 0, 0));
		buttonPanel.setMaximumSize(new Dimension(300, 40));
		
		JButton mainmenu = new JButton("Back");
		mainmenu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.mainMenuPanel();
			}
		});
		mainmenu.setMinimumSize(new Dimension(100, 40));
		buttonPanel.add(mainmenu);

		JButton addrecipe = new JButton("Add New");
		addrecipe.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.addRecipePanel();
			}
		});
		addrecipe.setMinimumSize(new Dimension(100, 40));
		buttonPanel.add(addrecipe);

		JButton deleterecipe = new JButton("Delete");
		deleterecipe.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				markRed();
			}
		});
		deleterecipe.setMinimumSize(new Dimension(100, 40));
		buttonPanel.add(deleterecipe);

		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public static void markRed() {
		recipeDelete = true;
		for (JButton button : recipebuttons) {
			button.setBackground(Color.RED);
		}
	}
	
	public static void recipeButton(String title, int index) {
		if (recipeDelete) {
				(new Planner()).deleteRecipe(title.replace("_", " "));
				WriteFile.deleteRecipe(title);
				Main.recipeListPanel();
		} else {
			Main.planRecipePanel(title, index);
		}
	}
}
