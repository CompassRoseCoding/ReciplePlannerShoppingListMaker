package main.java.objects;

import java.io.File;

import org.json.JSONArray;
import org.json.JSONObject;

import main.java.io.WriteFile;

public class Recipe {

	JSONObject recipe;
	static String recipePath = "src/main/data/recipes/";
	
	/**
	 * Creates a recipe object; I decided to use JSON for ease of parsing
	 * @param title: title of recipe
	 * @param ingredients: the ingredients as a string
	 * @param steps: the steps of the recipe
	 */
	public Recipe(String title, String ingredients, String steps) {
		recipe = new JSONObject();
		recipe.put("fulltext", title + "\n" + ingredients + "\n" + steps);
		recipe.put("title", title);
		
		String[] ingList = ingredients.split("\n");
		JSONArray ings = new JSONArray();
		
		ConversionMatrix converter = new ConversionMatrix();
		
		for (int i = 0; i < ingList.length; i++) {
			System.out.println(ingList[i]);
			Ingredient item = new Ingredient(ingList[i], converter);
			ings.put(item.getIngredient());
		}
		recipe.put("ingredients", ings);
		recipe.put("steps", steps);
		WriteFile.writeRecipeJSON(recipe);
	}

	public static boolean deleteRecipe(String title) {
		File file = new File(recipePath + title + ".txt");
		WriteFile.deleteRecipe(title);
		if (file.delete()) {
			return true;
		}
		else {
			return false;
		}
	}
}