package main.java.objects;

import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONObject;

import main.java.io.ReadFile;

public class ShoppingList {
	static String ingredientsPath = "src/main/data/recipes/";
	
	static JSONArray jsonList;
	static LinkedList<String> list;

	public ShoppingList(String[] recipes) {
		jsonList = new JSONArray();
		for (int i = 0; i < recipes.length; i++) {
			if (recipes[i].length() > 0) {
				JSONObject recipe = ReadFile.getRecipeJSON(recipes[i]);
				JSONArray ingredients = (JSONArray)(recipe.get("ingredients"));
				for (int j = 0; j < ingredients.length(); j++) {
					addJSONItem((JSONObject) ingredients.get(j));
				}
			}
		}
	}
	
	public static void addJSONItem(JSONObject ingredient) {
		jsonList.put(ingredient);
	}
	
	public static void addItem(String list) {

	}
	
	public static void addJSONItem(String list) {

	}

	public static void addItems(String[] list) {

	}
	
	public static LinkedList<String> getList() {
		list = new LinkedList<String>();
		for (int i = 0; i < jsonList.length(); i++) {
			JSONObject ingredient = (JSONObject) jsonList.get(i);
			if (ingredient.has("quantity") && ingredient.has("measure") && ingredient.has("modifier")) {
				list.add(ingredient.getDouble("quantity") + " " + ingredient.getString("measure") + ingredient.getString("modifier"));
			}
			else if (ingredient.has("quantity") && ingredient.has("modifier")) {
				list.add(ingredient.getDouble("quantity") + " " + ingredient.getString("modifier"));
			}
			else if (ingredient.has("measure") && ingredient.has("modifier")) {
				list.add(ingredient.getDouble("measure") + ingredient.getString("modifier"));
			}
			else {
				list.add("" + ingredient);
			}
		}
		return list;
	}
}
