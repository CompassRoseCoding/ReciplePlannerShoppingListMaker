package main.java;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import main.java.objects.ShoppingList;
import main.java.ui.*;

public class Main extends JFrame {
	/**
	 * TODO: 
	 * redo the checkmark/undo function
	 * TODO: implement calendar append vs integrate shopping list item aesthetics
	 * edit recipes
	 */
	private static final long serialVersionUID = 1L;

	static LinkedList<Integer> lastInt;
	static LinkedList<String> lastItem;
	
	static JFrame frame;
	static JPanel panel;

	public static void main(String[] args) {
		frame = new JFrame();
		panel = new JPanel();

		int minWidth = 300;
		int minHeight = 500;
		frame.setMinimumSize(new Dimension(minWidth, minHeight));
		panel.setMinimumSize(new Dimension(minWidth, minHeight));

		// Set frame in center of screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (dim.width - minWidth) / 2;
		int y = (dim.height - minHeight) / 2;
		frame.setLocation(x, y);

		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		frame.add(panel);
		frame.validate();
		
		panel.setLayout(new BorderLayout());
		
		mainMenuPanel();
	}
	
	public static void mainMenuPanel() {
		refresh();
		panel.add(new MainMenuPanel(), BorderLayout.CENTER);
		frame.setVisible(true);
	}
	
	public static void recipeListPanel() {
		refresh();
		panel.add(new RecipeListPanel(false, 0));
		frame.setVisible(true);
	}
	
	public static void plannerPanel() {
		refresh();
		panel.add(new PlannerPanel());
		frame.setVisible(true);
	}

	public static void addRecipePanel() {
		refresh();
		panel.add(new AddRecipePanel());
		frame.setVisible(true);
	}

	public static void readRecipePanel(String title) {
		refresh();
		panel.add(new ReadRecipePanel(title, false, -1));
		frame.setVisible(true);
	}
	
	public static void recipeListPlanPanel(int index) {
		refresh();
		refresh();
		panel.add(new RecipeListPanel(true, index));
		frame.setVisible(true);
	}
	
	public static void planRecipePanel(String title, int index) {
		refresh();
		refresh();
		panel.add(new ReadRecipePanel(title, true, index));
		frame.setVisible(true);
	}
	
	public static void shoppingListPanel() {
		lastInt = new LinkedList<Integer>();
		lastItem = new LinkedList<String>();
		refresh();
		ShoppingList mylist = new ShoppingList();
		panel.add(new ShoppingListPanel(mylist.getComplexList()));
		frame.setVisible(true);
	}

	public static void existingListPanel(LinkedList<String> grocerylist) {
		lastInt = new LinkedList<Integer>();
		lastItem = new LinkedList<String>();
		refresh();
		panel.add(new ShoppingListPanel(grocerylist));
		frame.setVisible(true);
	}
	
	public static void existingListPanel(LinkedList<String> grocerylist, LinkedList<String> removals) {
		lastInt = new LinkedList<Integer>();
		lastItem = new LinkedList<String>();
		refresh();
		panel.add(new ShoppingListPanel(grocerylist, removals));
		frame.setVisible(true);
	}

	
	public static void addIngredientPanel(LinkedList<String> list) {
		refresh();
		panel.add(new AddIngredientPanel());
		frame.setVisible(true);
	}
	
	public static void refresh() {
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
	}
}