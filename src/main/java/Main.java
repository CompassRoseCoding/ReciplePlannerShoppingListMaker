package main.java;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import main.java.io.ReadFile;
import main.java.io.WriteFile;
import main.java.objects.Recipe;
import main.java.objects.ShoppingList;

public class Main extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static JFrame frame;
	static JPanel newPanel;
	static boolean recipeDelete = false;
	static boolean getRecipePlan = false;
	static String[] recipePlan = new String[] {"", "", "", "", "", "", ""};
	static String[] days = new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	static int dayIndex = -1;
	static LinkedList<Integer> lastInt;
	static LinkedList<String> lastItem;
	static ShoppingList list;

	public static void main(String[] args) {
		recipePlan = ReadFile.getRecipePlannerData();
		frame = new JFrame();

		newPanel = new JPanel(new GridLayout(2, 1, 0, 0));

		int minWidth = 300;
		int minHeight = 500;
		newPanel.setMinimumSize(new Dimension(minWidth, minHeight));
		newPanel.setMaximumSize(new Dimension(minWidth, minHeight));
		frame.setMinimumSize(new Dimension(minWidth, minHeight));

		mainMenu(); 
		
		// Set frame in center of screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (dim.width - minWidth) / 2;
		int y = (dim.height - minHeight) / 2;
		frame.setLocation(x, y);

		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		frame.add(newPanel);

		frame.setVisible(true);
	}
	
	public static void mainMenu() {
		panelRepaint();
		
		newPanel.setLayout(new GridLayout(3, 1, 0, 0));
		
		JButton recipeB = new JButton("Recipes");
		recipeB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recipePerformed();
			}
		});
		newPanel.add(recipeB);

		JButton plannerB = new JButton("Planner");
		plannerB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plannerPerformed();
			}
		});
		newPanel.add(plannerB);
		
		JButton listB = new JButton("Shopping List");
		listB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showShoppingList();
			}
		});
		newPanel.add(listB);
	}

	public static void panelRepaint() {
		newPanel.removeAll();
        newPanel.revalidate();
        newPanel.repaint();
	}

	public static void plannerPerformed() {
		panelRepaint();
		
		newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.PAGE_AXIS));
		
		JPanel plannerPanel = new JPanel();
		plannerPanel.setLayout(new GridLayout(7, 1, 0, 0));
		
		JButton monday = new JButton("Monday: " + recipePlan[0]);
		monday.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dayIndex = 0;
				getRecipePlan = true;
				recipePerformed();
			}
		});
		monday.setVisible(true);
		plannerPanel.add(monday);
		
		JButton tuesday = new JButton("Tuesday: " + recipePlan[1]);
		tuesday.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dayIndex = 1;
				getRecipePlan = true;
				recipePerformed();
			}
		});
		tuesday.setVisible(true);
		plannerPanel.add(tuesday);
		
		JButton wednesday = new JButton("Wednesday: " + recipePlan[2]);
		wednesday.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dayIndex = 2;
				getRecipePlan = true;
				recipePerformed();
			}
		});
		wednesday.setVisible(true);
		plannerPanel.add(wednesday);
		
		JButton thursday = new JButton("Thursday: " + recipePlan[3]);
		thursday.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dayIndex = 3;
				getRecipePlan = true;
				recipePerformed();
			}
		});
		thursday.setVisible(true);
		plannerPanel.add(thursday);
		
		JButton friday = new JButton("Friday: " + recipePlan[4]);
		friday.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dayIndex = 4;
				getRecipePlan = true;
				recipePerformed();
			}
		});
		friday.setVisible(true);
		plannerPanel.add(friday);
		
		JButton saturday = new JButton("Sunday: " + recipePlan[5]);
		saturday.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dayIndex = 5;
				getRecipePlan = true;
				recipePerformed();
			}
		});
		saturday.setVisible(true);
		plannerPanel.add(saturday);
		
		JButton sunday = new JButton("Sunday: " + recipePlan[6]);
		sunday.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dayIndex = 6;
				getRecipePlan = true;
				recipePerformed();
			}
		});
		sunday.setVisible(true);
		plannerPanel.add(sunday);
		
		newPanel.add(plannerPanel);
		
		JButton mainmenu = new JButton("Main Menu");
		mainmenu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainMenu();
			}
		});
		mainmenu.setMinimumSize(new Dimension(300, 40));
		newPanel.add(mainmenu);
	}
	
	public static void recipePerformed() {
		panelRepaint();

		newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.PAGE_AXIS));
		
		JPanel listPanel = new JPanel(new GridLayout(7, 1, 0, 0));
		listPanel.setMinimumSize(new Dimension(300, 500));
		listPanel.setMaximumSize(new Dimension(300, 500));
		
		if (getRecipePlan) {
			JButton planRecipe = new JButton("Not Cooking");
			planRecipe.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(ActionEvent e) {
					recipePlan[dayIndex] = "";
					WriteFile.writeRecipesList(recipePlan);
					plannerPerformed();
				}
			});
			listPanel.add(planRecipe);
		}
		
		String[] savedRecipes = ReadFile.getArchivedRecipes();
		
		for (int i = 0; i < savedRecipes.length; i++) {
			JButton temp = new JButton(savedRecipes[i].replace("_", " "));
			@SuppressWarnings("unused")
			String title = savedRecipes[i].replace("_", " ");
			temp.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (recipeDelete) {
						for (int i = 0; i < recipePlan.length; i++) {
							if (recipePlan[i].replace(" ", "_").equals(title)) {
								recipePlan[i] = "";
							}
						}
						
						WriteFile.deleteRecipe(title.replace(" ", "_"));
						recipeDelete = false;
						recipePerformed();
					} 
					else {
						showRecipe(title.replace(" ", "_"));
					}
				}
			});
			if (recipeDelete) {
				temp.setBackground(Color.RED);
			}
			temp.setMinimumSize(new Dimension(300, 40));
			listPanel.add(temp);
		}
		
	    JScrollPane scrollPane = new JScrollPane(listPanel);
	    newPanel.add(scrollPane);
	    
	    JPanel container = new JPanel();
	    container.setLayout(new GridLayout(1, 3, 0, 0));
	    
	    JButton mainmenu = new JButton("Menu");
		mainmenu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainMenu();
			}
		});
		mainmenu.setMinimumSize(new Dimension(100, 40));
		container.add(mainmenu);
		
		JButton addrecipe = new JButton("Add New");
		addrecipe.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addRecipe();
			}
		});
		addrecipe.setMinimumSize(new Dimension(100, 40));
		container.add(addrecipe);
		
		JButton deleterecipe = new JButton("Delete");
		deleterecipe.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recipeDelete = !recipeDelete;
				recipePerformed();
			}
		});
		deleterecipe.setMinimumSize(new Dimension(100, 40));
		container.add(deleterecipe);
		
		container.setMaximumSize(new Dimension(300, 40));
		newPanel.add(container);
	}
	
	public static void addRecipe() {
		panelRepaint();
		
		newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.PAGE_AXIS));
		
		JLabel titleLabel = new JLabel("Title of Recipe:");
		newPanel.add(titleLabel);
		
		JTextField titleField = new JTextField(50);
		titleField.setMinimumSize(new Dimension(300, 50));
		titleField.setMaximumSize(new Dimension(300, 50));
		newPanel.add(titleField);
		
		JLabel ingredientsLabel = new JLabel("Ingredients for Recipe:");
		newPanel.add(ingredientsLabel);
		
		JTextArea ingredientsArea = new JTextArea(5, 20);
		JScrollPane scrollPane1 = new JScrollPane(ingredientsArea); 
		ingredientsArea.setEditable(true);
		newPanel.add(scrollPane1);
		
		JLabel stepsLabel = new JLabel("Steps of Recipe:");
		newPanel.add(stepsLabel);
		
		JTextArea stepsArea = new JTextArea(5, 20);
		JScrollPane scrollPane2 = new JScrollPane(stepsArea); 
		ingredientsArea.setEditable(true);
		newPanel.add(scrollPane2);
		
		JButton submitrecipe = new JButton("");
		submitrecipe.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Recipe(titleField.getText(), ingredientsArea.getText(), stepsArea.getText());
				recipePerformed();
			}
		});
		submitrecipe.setMinimumSize(new Dimension(300, 40));
		submitrecipe.add(new JLabel("Submit Recipe"));
		submitrecipe.setVisible(true);
		newPanel.add(submitrecipe);
	}
	
	public static void showRecipe(String title) {
		panelRepaint();
		newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.PAGE_AXIS));
		
		JTextArea recipeArea = new JTextArea(5, 20);
		JScrollPane scrollPane1 = new JScrollPane(recipeArea); 
		recipeArea.setLineWrap(true);
		recipeArea.setWrapStyleWord(true);
		recipeArea.append(ReadFile.getRecipeText(title));
		recipeArea.setEditable(false);
		newPanel.add(scrollPane1);
		
		JPanel buttonPan = new JPanel();
		buttonPan.setMaximumSize(new Dimension(300, 40));
		
		JButton done = new JButton("Done");
		done.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.recipePerformed();
			}
		});
		done.setMinimumSize(new Dimension(300, 40));
		buttonPan.add(done);
		
		if (getRecipePlan) {
			JButton planRecipe = new JButton("Plan for " + days[dayIndex]);
			planRecipe.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(ActionEvent e) {
					recipePlan[dayIndex] = title.replace(" ", "_");
					WriteFile.writeRecipesList(recipePlan);
					plannerPerformed();
				}
			});
			buttonPan.setMinimumSize(new Dimension(300, 40));
			buttonPan.add(planRecipe);
		}
		newPanel.add(buttonPan);
	}
	
	public static void showShoppingList() {
		list = new ShoppingList(recipePlan);
		lastInt = new LinkedList<Integer>();
		lastItem = new LinkedList<String>();
		performList(ShoppingList.getList());
	}
	
	public static void performList(LinkedList<String> list) {
		panelRepaint();
		newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.PAGE_AXIS));
		
		JPanel listPan = new JPanel();
		listPan.setLayout(new BoxLayout(listPan, BoxLayout.PAGE_AXIS));
		
		JScrollPane scrollPane = new JScrollPane(listPan, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		for (int i = 0; i < list.size(); i++) {
			JCheckBox temp = new JCheckBox(list.get(i));
			int index = i;
			temp.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(ActionEvent e) {
					lastInt.add(index);
					lastItem.add(list.remove(index));
					performList(list);
				}
			});
			listPan.add(temp);
		}
		
		newPanel.add(scrollPane);
		
		JPanel buttonPan = new JPanel();
		buttonPan.setMaximumSize(new Dimension(300, 40));
		
		JButton done = new JButton("Done");
		done.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.mainMenu();
			}
		});
		done.setMinimumSize(new Dimension(300, 40));
		buttonPan.add(done);
		
		JButton undo = new JButton("Undo");
		undo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lastInt.size() > 0 && lastItem.size() > 0 ) {
					list.add(lastInt.removeLast(), lastItem.removeLast());
					performList(list);
				}
			}
		});
		undo.setMinimumSize(new Dimension(300, 40));
		buttonPan.add(undo);
		
		JButton addItem = new JButton("Add Item");
		addItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addItem(list);
			}
		});
		addItem.setMinimumSize(new Dimension(300, 40));
		buttonPan.add(addItem);
		
		newPanel.add(buttonPan);
	}
	
	public static void addItem(LinkedList<String> list) {
		panelRepaint();
		
		newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.PAGE_AXIS));
		
		JLabel ingredientsLabel = new JLabel("Items to add:");
		newPanel.add(ingredientsLabel);
		
		JTextArea ingredientsArea = new JTextArea(5, 20);
		JScrollPane scrollPane1 = new JScrollPane(ingredientsArea); 
		ingredientsArea.setEditable(true);
		newPanel.add(scrollPane1);
		
		JButton itemadd = new JButton("");
		itemadd.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ingredientsArea.getText().length() > 1) {
					ShoppingList.addItems(ingredientsArea.getText().split("\n"));
				}
				performList(ShoppingList.getList());
			}
		});
		itemadd.setMinimumSize(new Dimension(300, 40));
		itemadd.add(new JLabel("Add Items"));
		itemadd.setVisible(true);
		newPanel.add(itemadd);
	}
}