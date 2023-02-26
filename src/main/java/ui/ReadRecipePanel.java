package main.java.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.json.JSONObject;

import main.java.Main;
import main.java.io.ReadFile;
import main.java.objects.Planner;

public class ReadRecipePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6254067984140868904L;
	static JButton[] recipebuttons;
	
	public ReadRecipePanel(String title, boolean getRecipePlan, int dayIndex) {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JTextArea recipeArea = new JTextArea(5, 20);
		JScrollPane scrollPane1 = new JScrollPane(recipeArea);
		recipeArea.setLineWrap(true);
		recipeArea.setWrapStyleWord(true);
		recipeArea.append(new JSONObject(ReadFile.getText("recipes/" + title)).getString("fulltext"));
		recipeArea.setEditable(false);
		add(scrollPane1);

		JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 0, 0));
		buttonPanel.setMaximumSize(new Dimension(300, 40));
		
		JButton done = new JButton("Back");
		done.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.recipeListPanel();
			}
		});
		done.setMinimumSize(new Dimension(300, 40));
		buttonPanel.add(done);

		Planner recipePlan = new Planner();
		if (getRecipePlan) {
			JButton planRecipe = new JButton("Plan for " + recipePlan.getDay(dayIndex));
			planRecipe.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(ActionEvent e) {
					recipePlan.setEntry(dayIndex, title.replace(" ", "_"));
					Main.plannerPanel();
				}
			});
			buttonPanel.setMinimumSize(new Dimension(300, 40));
			buttonPanel.add(planRecipe);
		}
		add(buttonPanel);
	}
}
