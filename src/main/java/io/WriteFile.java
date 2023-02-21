package main.java.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

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
		} else {
			return false;
		}
	}

	public static void writeRecipeJSON(JSONObject recipe) {
		File file = new File(dataPath + "/recipes/");
		file.mkdirs();

		FileWriter writer = null;

		try {
			writer = new FileWriter(
					dataPath + "/recipes/" + ((String) (recipe.get("title"))).replace(" ", "_") + ".txt");
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

	public static void writeConversionMatrix(HashMap<String, JSONObject> conversions) {
		File file = new File(dataPath + "conversionMatrix.txt");

		try (FileOutputStream fos = new FileOutputStream(file.getPath());
				OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
				BufferedWriter writer = new BufferedWriter(osw)) {

			for (Map.Entry<String, JSONObject> set : conversions.entrySet()) {
				writer.append(set.getValue() + "\n");
			}

			writer.close();

		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}
