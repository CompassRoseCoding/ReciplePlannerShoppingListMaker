package main.java.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import main.java.Main;
import main.java.objects.Planner;

public class PlannerPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6254067984140868904L;
	static JButton[] recipebuttons;
	static boolean recipeDelete;
	
	public PlannerPanel() {
		Planner Planner = new Planner();
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JPanel plannerPanel = new JPanel();
		plannerPanel.setLayout(new GridLayout(7, 1, 0, 0));

		JButton monday = new JButton(Planner.getEntry(0));
		monday.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.recipeListPlanPanel(0);
			}
		});
		monday.setVisible(true);
		plannerPanel.add(monday);

		JButton tuesday = new JButton(Planner.getEntry(1));
		tuesday.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.recipeListPlanPanel(1);
			}
		});
		tuesday.setVisible(true);
		plannerPanel.add(tuesday);

		JButton wednesday = new JButton(Planner.getEntry(2));
		wednesday.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.recipeListPlanPanel(2);
			}
		});
		wednesday.setVisible(true);
		plannerPanel.add(wednesday);

		JButton thursday = new JButton(Planner.getEntry(3));
		thursday.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.recipeListPlanPanel(3);
			}
		});
		thursday.setVisible(true);
		plannerPanel.add(thursday);

		JButton friday = new JButton(Planner.getEntry(4));
		friday.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.recipeListPlanPanel(4);
			}
		});
		friday.setVisible(true);
		plannerPanel.add(friday);

		JButton saturday = new JButton(Planner.getEntry(5));
		saturday.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.recipeListPlanPanel(5);
			}
		});
		saturday.setVisible(true);
		plannerPanel.add(saturday);

		JButton sunday = new JButton(Planner.getEntry(6));
		sunday.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.recipeListPlanPanel(6);
			}
		});
		sunday.setVisible(true);
		plannerPanel.add(sunday);

		add(plannerPanel);
		
		JPanel buttonPanel = new JPanel(new BorderLayout());
		buttonPanel.setMaximumSize(new Dimension(300, 40));
		
		JButton mainmenu = new JButton("Main Menu");
		mainmenu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.mainMenuPanel();
			}
		});
		buttonPanel.add(mainmenu, BorderLayout.CENTER);
		
		add(buttonPanel);
	}
}
