package main.java.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import main.java.Main;
import main.java.objects.Recipe;

public class AddRecipePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6254067984140868904L;
	static JButton[] recipebuttons;
	
	public AddRecipePanel() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JLabel titleLabel = new JLabel("Title of Recipe:");
		add(titleLabel);

		JTextField titleField = new JTextField(50);
		titleField.setMinimumSize(new Dimension(300, 50));
		titleField.setMaximumSize(new Dimension(300, 50));
		add(titleField);

		JLabel ingredientsLabel = new JLabel("Ingredients for Recipe:");
		add(ingredientsLabel);

		JTextArea ingredientsArea = new JTextArea(5, 20);
		JScrollPane scrollPane1 = new JScrollPane(ingredientsArea);
		ingredientsArea.setEditable(true);
		add(scrollPane1);

		JLabel stepsLabel = new JLabel("Steps of Recipe:");
		add(stepsLabel);

		JTextArea stepsArea = new JTextArea(5, 20);
		JScrollPane scrollPane2 = new JScrollPane(stepsArea);
		ingredientsArea.setEditable(true);
		add(scrollPane2);

		JButton submitrecipe = new JButton("");
		submitrecipe.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Recipe(titleField.getText(), ingredientsArea.getText(), stepsArea.getText());
				Main.recipeListPanel();
			}
		});
		submitrecipe.setMinimumSize(new Dimension(300, 40));
		submitrecipe.add(new JLabel("Submit Recipe"));
		submitrecipe.setVisible(true);
		add(submitrecipe);
	}
	
	public static void markRed() {
		for (JButton button : recipebuttons) {
			button.setBackground(Color.RED);
		}
	}
}
