package main.java.objects;

import java.io.File;

import main.java.io.WriteFile;

public class Recipe {

	String txt;
	String contents;
	static String recipePath = "src/main/data/recipes/";
	
	public Recipe(String title, String ingredients, String steps) {
		WriteFile.writeRecipeJSON(title, ingredients, steps);
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