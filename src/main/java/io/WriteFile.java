package main.java.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import main.java.CheckMethods;

public class WriteFile {
	static String dataPath = "src/main/data/";
	
	public static void writeRecipesList(String[] recipes) {
		try {
			File file = new File(dataPath + "savedRecipes.txt");
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath()));
		    for (int i = 0; i < 7; i++) {
		    	writer.write(recipes[i] + "\n");
		    }
		    writer.close();
		    
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	public static void shoppingList(String[] recipes) {
		try {
			File file = new File(dataPath + "savedRecipes.txt");
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath()));
		    for (int i = 0; i < 7; i++) {
		    	writer.write(" " + recipes[i] + "\n");
		    }
		    writer.close();
		    
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	public static boolean deleteRecipe(String title) {
		File file = new File(dataPath + "/recipes/" + title + ".txt");
		if (file.delete()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void writeRecipeJSON(String title, String ingredients, String steps) {
		JSONObject recipe = new JSONObject();
		recipe.put("fulltext", title + "\n" + ingredients + "\n" + steps);
		recipe.put("title", title);
		recipe.put("measure", "");
		String[] ingList = ingredients.split("\n");
		
		JSONArray ings = new JSONArray();
		
		for (int i = 0; i < ingList.length; i++) {
			
			JSONObject ingredient = new JSONObject();
			ingredient.put("quantity", 0);
			ingredient.put("modifier", "");
			String[] modifier = ingList[i].split(", ");
			String[] ingredientLine = modifier[0].split(" ");
			
			for (int j = 0; j < ingredientLine.length; j++) {
				double totalAmt = 0;
				
				if (ingredientLine[j].length() == 0) {
					continue;
				}
				else {
					if (CheckMethods.isNumeric(ingredientLine[j])) {
						totalAmt = totalAmt + Double.parseDouble(ingredientLine[j]);
						ingredient.put("quantity",  totalAmt);
					}
					else if (CheckMethods.isFraction(ingredientLine[j]) < 1.0 && CheckMethods.isFraction(ingredientLine[j]) > 0.0) {
						totalAmt = totalAmt + CheckMethods.isFraction(ingredientLine[j]);
						ingredient.put("quantity",  totalAmt);
					}
					else if (CheckMethods.isMeasure(ingredientLine[j].replace(".", "")) > -1) {
						ingredient.put("measure", CheckMethods.getMeasure(ingredientLine[j].replace(".", "")));
					}
					else {
						ingredient.put("modifier", (String) ingredient.get("modifier") + " " + ingredientLine[j]);
					}
				}
			}
			ings.put(ingredient);
		}
		recipe.put("ingredients", ings);
		recipe.put("steps", steps);
		
		File file = new File(dataPath + "/recipes/");
		file.mkdirs();
		
		FileWriter writer = null;
		
		try {
			writer = new FileWriter(dataPath + "/recipes/" + ((String)(recipe.get("title"))).replace(" ", "_") + ".txt");
			writer.write(recipe.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
